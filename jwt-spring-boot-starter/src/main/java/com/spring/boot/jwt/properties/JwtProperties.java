package com.spring.boot.jwt.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * <p>
 * Jwt配置信息
 * </p>
 *
 * @author wangliang
 * @since 2017/12/22
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     * default enabled false.
     */
    private boolean enabled;
    /**
     * default secret
     */
    private String secret;
    /**
     * default accessToken expiration 2 hours.
     */
    private Long accessTokenExpiration;
    /**
     * default refreshToken expiration 30 days.
     */
    private Long refreshTokenExpiration;
    /**
     * 不拦截路径
     */
    private List excludePaths;
}
