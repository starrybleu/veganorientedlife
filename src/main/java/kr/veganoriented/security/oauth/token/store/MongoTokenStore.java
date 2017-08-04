package kr.veganoriented.security.oauth.token.store;

import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.IndexOptions;

import org.bson.Document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.util.Assert;

/**
 * Created by terrylee on 17. 8. 4.
 */

public class MongoTokenStore// implements TokenStore, InitializingBean {
{

//    private static final Marker TOKEN = MarkerFactory.getDetachedMarker("token");
//    private static final Logger LOG = LoggerFactory.getLogger(MongoTokenStore.class);
//
//    private static final WriteConcern DEFAULT_WRITE_CONCERN = WriteConcern.NORMAL;
//
//    private static final String DEFAULT_ACCESS_TOKEN_COLLECTION_NAME = "access_tokens";
//    private static final String DEFAULT_REFRESH_TOKEN_COLLECTION_NAME = "refresh_tokens";
//
//    private static final String DEFAULT_TOKEN_ID_FIELD_NAME = "tokenId";
//    private static final String DEFAULT_TOKEN_FIELD_NAME = "token";
//    private static final String DEFAULT_AUTHENTICATION_ID_FIELD_NAME = "authenticationId";
//    private static final String DEFAULT_USERNAME_FIELD_NAME = "username";
//    private static final String DEFAULT_CLIENT_ID_FIELD_NAME = "clientId";
//    private static final String DEFAULT_AUTHENTICATION_FIELD_NAME = "authentication";
//    private static final String DEFAULT_REFRESH_TOKEN_FIELD_NAME = "refreshToken";
//
//    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();
//
//    private final MongoDatabase db;
//
//    private String accessTokenCollectionName = DEFAULT_ACCESS_TOKEN_COLLECTION_NAME;
//    private String refreshTokenCollectionName = DEFAULT_REFRESH_TOKEN_COLLECTION_NAME;
//
//    private String tokenIdFieldName = DEFAULT_TOKEN_ID_FIELD_NAME;
//    private String tokenFieldName = DEFAULT_TOKEN_FIELD_NAME;
//    private String authenticationIdFieldName = DEFAULT_AUTHENTICATION_ID_FIELD_NAME;
//    private String usernameFieldName = DEFAULT_USERNAME_FIELD_NAME;
//    private String clientIdFieldName = DEFAULT_CLIENT_ID_FIELD_NAME;
//    private String authenticationFieldName = DEFAULT_AUTHENTICATION_FIELD_NAME;
//    private String refreshTokenFieldName = DEFAULT_REFRESH_TOKEN_FIELD_NAME;
//
//    private WriteConcern writeConcern = DEFAULT_WRITE_CONCERN;
//
//    public MongoTokenStore(MongoDatabase db) {
//        Assert.notNull(db, "DB is required");
//        this.db = db;
//    }
//
//    public MongoTokenStore(MongoClient mongoClient, String databaseName) {
//        this(mongoClient.getDatabase(databaseName));
//    }
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        if(this.db.getCollection(accessTokenCollectionName) == null) {
//            LOG.trace(TOKEN, "Creating {} collection", accessTokenCollectionName);
//
//            this.db.createCollection(accessTokenCollectionName, new CreateCollectionOptions());
//
//            MongoCollection<Document> collection = this.db.getCollection(accessTokenCollectionName);
//
//            IndexOptions idxOptsTokenId = new IndexOptions().background(true)
//                    .name(accessTokenCollectionName + "_" + tokenIdFieldName + "_ix");
//            collection.createIndex( Indexes.ascending(accessTokenCollectionName) );
//
//            IndexOptions idxOptsAuthId = new IndexOptions().background(true)
//                    .name(accessTokenCollectionName + "_" + authenticationFieldName + "_ix");
//            collection.createIndex( Indexes.ascending(authenticationIdFieldName) );
//
//            LOG.debug(TOKEN, "Collection {} successfully created and indexed", accessTokenCollectionName);
//        }
//        if(this.db.getCollection(refreshTokenCollectionName) == null) {
//            LOG.trace(TOKEN, "Creating {} collection", refreshTokenCollectionName);
//
//            this.db.createCollection(refreshTokenCollectionName, new CreateCollectionOptions());
//
//            MongoCollection<Document> collection = this.db.getCollection(refreshTokenCollectionName);
//
//            IndexOptions idxOptsRefreshId = new IndexOptions().name(refreshTokenCollectionName + "_ix");
//
//            collection.createIndex( Indexes.ascending(refreshTokenCollectionName), idxOptsRefreshId );
//
//            LOG.debug(TOKEN, "Collection {} successfully created and indexed", refreshTokenCollectionName);
//        }
//
//        private MongoCollection<Document> getAccessTokenCollection() {
//            return db.getCollection(accessTokenCollectionName);
//        }


//    }







}
