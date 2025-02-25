package com.strong.sercurity.controller;

import com.google.code.kaptcha.Producer;
import com.strong.common.cache.RedisCache;
import com.strong.common.constant.CacheConstants;
import com.strong.common.entity.result.Result;
import com.strong.common.enums.SystemCodeEnum;
import com.strong.common.exception.StrongException;
import com.strong.common.util.sign.Base64;
import com.strong.common.util.snowflakeid.SnowflakeIdWorker;
import com.strong.sercurity.vo.CaptchaVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作处理
 *
 * @author charlie
 */
@RestController
@RequestMapping("/auth")
public class CaptchaController {
    private static final Logger log = LoggerFactory.getLogger(CaptchaController.class);
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Autowired
    private RedisCache redisCache;

    @Value("${captcha.expiration}")
    private Integer captchaExpiration;

    /**
     * 生成验证码
     */
    @GetMapping("/captcha")
    public Result<CaptchaVo> getCaptcha() {
        // 保存验证码信息
        String snowFlakeId = String.valueOf(SnowflakeIdWorker.getInstance().nextId());
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + snowFlakeId;

        // 生成验证码
        String code = captchaProducer.createText();
        log.info("验证码：{}", code);
        BufferedImage image = captchaProducer.createImage(code);

        redisCache.setCacheObject(verifyKey, code, captchaExpiration, TimeUnit.MINUTES);

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            throw new StrongException("验证码获取异常");
        }

        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setCaptchaId(snowFlakeId);
        captchaVo.setCaptchaImg(Base64.encode(os.toByteArray()));
        return Result.success(captchaVo);
    }
}
