package cn.xxstudy.api.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @date: 2024/7/7 18:50
 * @author: TangRen
 * @remark: Token 信息配置
 */
@ConfigurationProperties(prefix = "token")
@Component
public class TokenProperties {
    private long expireTime;
    private String secret;

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
