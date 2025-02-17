package com.strong.common.util.snowflakeid;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;

import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
 * @author charlie
 * @date 3/8/2023 10:19 AM
 **/
public class SnowflakeIdWorkerGenerator {
    private long workerId;
    private long datacenterId;
    private long sequence = 0L;
    private long lastTimestamp = -1L;

    protected SnowflakeIdWorkerGenerator(long workerId, long datacenterId) {
        if (workerId <= 31L && workerId >= 0L) {
            if (datacenterId <= 31L && datacenterId >= 0L) {
                this.workerId = workerId;
                this.datacenterId = datacenterId;
            } else {
                throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", 31L));
            }
        } else {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", 31L));
        }
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (timestamp < this.lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", this.lastTimestamp - timestamp));
        } else {
            if (this.lastTimestamp == timestamp) {
                this.sequence = this.sequence + 1L & 4095L;
                if (this.sequence == 0L) {
                    timestamp = this.tilNextMillis(this.lastTimestamp);
                }
            } else {
                this.sequence = 0L;
            }

            this.lastTimestamp = timestamp;
            return timestamp - 1420041600000L << 22 | this.datacenterId << 17 | this.workerId << 12 | this.sequence;
        }
    }

    private long tilNextMillis(long lastTimestamp) {
        long timestamp;
        for (timestamp = this.timeGen(); timestamp <= lastTimestamp; timestamp = this.timeGen()) {
        }

        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

    protected static Long getWorkId() {
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            int[] var3 = ints;
            int var4 = ints.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                int b = var3[var5];
                sums += b;
            }

            return (long) (sums % 32);
        } catch (UnknownHostException var7) {
            return RandomUtils.nextLong(0L, 32L);
        }
    }

    protected static Long getDataCenterId() {
        String hostName = SystemUtils.getHostName();
        if (StringUtils.isEmpty(hostName)) {
            try {
                hostName = Inet4Address.getLocalHost().getHostName();
            } catch (UnknownHostException var7) {
                return RandomUtils.nextLong(0L, 32L);
            }
        }

        int[] ints = StringUtils.toCodePoints(hostName);
        int sums = 0;
        int[] var3 = ints;
        int var4 = ints.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            int i = var3[var5];
            sums += i;
        }

        return (long) (sums % 32);
    }
}