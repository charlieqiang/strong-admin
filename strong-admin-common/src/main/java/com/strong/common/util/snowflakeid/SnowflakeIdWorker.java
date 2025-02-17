package com.strong.common.util.snowflakeid;

/**
 * @author charlie
 * @date 3/8/2023 10:12 AM
 **/
public class SnowflakeIdWorker extends SnowflakeIdWorkerGenerator {

    /**
     * 单例对象 volatile防止指令重排引发的初始化问题
     */
    private volatile static SnowflakeIdWorker singleton = null;

    /**
     * 构造函数
     *
     * @param workerId 工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    private SnowflakeIdWorker(long workerId, long datacenterId) {
        super(workerId, datacenterId);
    }

    public static SnowflakeIdWorker getInstance() {
        // 双重检测机制
        if (singleton == null) {
            synchronized (SnowflakeIdWorker.class) {
                if (singleton == null) {
                    Long workId = getWorkId();
                    Long dataCenterId = getDataCenterId();
                    singleton = new SnowflakeIdWorker(workId, dataCenterId);
                }
            }
        }
        return singleton;
    }
}