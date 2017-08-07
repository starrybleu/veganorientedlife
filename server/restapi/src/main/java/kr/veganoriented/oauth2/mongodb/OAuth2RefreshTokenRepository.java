package kr.veganoriented.oauth2.mongodb;

import kr.veganoriented.oauth2.OAuth2AuthenticationRefreshToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by terrylee on 17. 8. 1.
 */

@Repository
public interface OAuth2RefreshTokenRepository extends MongoRepository<OAuth2AuthenticationRefreshToken, String> {

    public OAuth2AuthenticationRefreshToken findByTokenId(String tokenId);

}
