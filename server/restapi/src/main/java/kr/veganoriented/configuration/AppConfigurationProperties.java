package kr.veganoriented.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by terrylee on 17. 8. 1.
 */

@Component
@ConfigurationProperties(prefix="appconfig")
@Getter @Setter
public class AppConfigurationProperties {

    private List defaultUserRoles;
    private String clientId;
    private String clientSecret;
    private String[] onPopStateUrls;

}
