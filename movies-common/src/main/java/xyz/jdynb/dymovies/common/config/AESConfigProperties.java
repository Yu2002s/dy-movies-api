package xyz.jdynb.dymovies.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "aes")
public class AESConfigProperties {

    private String transformation;

    private Integer keySize;

    private Integer iterations;

    private String salt;

    private String iv;

    private String password;
}
