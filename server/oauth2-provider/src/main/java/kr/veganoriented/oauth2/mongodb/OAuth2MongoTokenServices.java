package kr.veganoriented.oauth2.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Created by terrylee on 17. 8. 5.
 */

@Configuration
public class OAuth2MongoTokenServices {

    private OAuth2AccessTokenRepository oAuth2AccessTokenRepository;
    private OAuth2RefreshTokenRepository oAuth2RefreshTokenRepository;

    @Bean
    public TokenStore tokenStore() {
        return new OAuth2RepositoryTokenStore(oAuth2AccessTokenRepository, oAuth2RefreshTokenRepository);
    }

    @Bean
    public DefaultTokenServices tokenServices() {
        final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setAccessTokenValiditySeconds(-1);
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

}
