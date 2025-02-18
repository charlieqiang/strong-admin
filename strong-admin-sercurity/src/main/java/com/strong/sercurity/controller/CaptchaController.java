package com.strong.sercurity.controller;

import com.google.code.kaptcha.Producer;
import com.strong.common.cache.RedisCache;
import com.strong.common.constant.CacheConstants;
import com.strong.common.constant.Constants;
import com.strong.common.entity.result.Result;
import com.strong.common.enums.SystemCodeEnum;
import com.strong.common.util.sign.Base64;
import com.strong.common.util.snowflakeid.SnowflakeIdWorker;
import com.strong.sercurity.vo.CaptchaVo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/public")
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;
    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     */
    @GetMapping("/captcha")
    public Result<CaptchaVo> getCaptcha() {
        // 保存验证码信息
        String snowFlakeId = String.valueOf(SnowflakeIdWorker.getInstance().nextId());
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + snowFlakeId;

        String capStr, code;
        BufferedImage image;

        // 生成验证码
        String capText = captchaProducerMath.createText();
        capStr = capText.substring(0, capText.lastIndexOf("@"));
        code = capText.substring(capText.lastIndexOf("@") + 1);
        image = captchaProducerMath.createImage(capStr);

        redisCache.setCacheObject(verifyKey, code, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return Result.error(SystemCodeEnum.OPERATE_ERROR);
        }

        CaptchaVo captchaVo = new CaptchaVo();
        captchaVo.setCaptchaId(snowFlakeId);
        captchaVo.setCaptchaImg(Base64.encode(os.toByteArray()));
        return Result.success(captchaVo);
    }
}
