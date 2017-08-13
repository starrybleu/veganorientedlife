package kr.veganoriented.configuration;

import kr.veganoriented.client.details.MongoClientDetailsService;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.AuthorizationServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Created by terrylee on 17. 8. 7.
 */

@Configuration
public class JwtOAuth2AuthorizationServerConfiguration extends OAuth2AuthorizationServerConfiguration {

    @Autowired
    private MongoClientDetailsService mongoClientDetailsService;

    public JwtOAuth2AuthorizationServerConfiguration(BaseClientDetails details, AuthenticationManager authenticationManager, ObjectProvider<TokenStore> tokenStore, ObjectProvider<AccessTokenConverter> tokenConverter, AuthorizationServerProperties properties) {
        super(details, authenticationManager, tokenStore, tokenConverter, properties);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
//        endpoints.accessTokenConverter(new JwtAccessTokenConverter());
        // 위에 주석 처리한 부분 때문에(주석달기 전에) JwtAccessTokenConverter의 인스턴스가 새롭게 생성되면서 setSigningKey 메소드로 설정한 키가 변하는 것이었음
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(mongoClientDetailsService);

//                clients.inMemory()
//                .withClient("my_client_id")
//                .secret("my_client_secret")
//                .authorizedGrantTypes("authorization_code", "password",
//                        "client_credentials", "implicit", "refresh_token")
//                .authorities("ROLE_USER")
//                .scopes("read", "write")
//                .accessTokenValiditySeconds(60 * 60 * 24)
//                .refreshTokenValiditySeconds(60 * 60 * 24)
//            .and()
//                .withClient("your_client_id")
//                .secret("your_client_secret")
//                .authorizedGrantTypes("authorization_code", "password",
//                        "client_credentials", "implicit", "refresh_token")
//                .authorities("ROLE_USER")
//                .scopes("read", "write")
//                .accessTokenValiditySeconds(60 * 60 * 24)
//                .refreshTokenValiditySeconds(60 * 60 * 24);
    }

}
