package kr.veganoriented.oauth2;


import kr.veganoriented.domain.BaseEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Created by terrylee on 17. 8. 1.
 */

@Document(collection = "OAuth2AuthenticationRefreshToken")
public class OAuth2AuthenticationRefreshToken extends BaseEntity {

    private String tokenId;
    private OAuth2RefreshToken oAuth2RefreshToken;
    private OAuth2Authentication authentication;

    public OAuth2AuthenticationRefreshToken(OAuth2RefreshToken oAuth2RefreshToken, OAuth2Authentication authentication) {
        this.oAuth2RefreshToken = oAuth2RefreshToken;
        this.authentication = authentication;
        this.tokenId = oAuth2RefreshToken.getValue();
    }

    public String getTokenId() {
        return tokenId;
    }

    public OAuth2RefreshToken getoAuth2RefreshToken() {
        return oAuth2RefreshToken;
    }

    public OAuth2Authentication getAuthentication() {
        return authentication;
    }
}
