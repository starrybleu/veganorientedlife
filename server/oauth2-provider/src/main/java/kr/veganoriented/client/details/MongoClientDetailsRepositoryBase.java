package kr.veganoriented.client.details;

/**
 * Created by terrylee on 17. 8. 13.
 */
public interface MongoClientDetailsRepositoryBase {

    boolean deleteByClientId(String clientId);

    boolean update(MongoClientDetails mongoClientDetails);

    boolean updateClientSecret(String clientId, String newSecret);

    MongoClientDetails findByClientId(String clientId) throws IllegalArgumentException;
}
