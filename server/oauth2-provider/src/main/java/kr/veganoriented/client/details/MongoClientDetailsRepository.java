package kr.veganoriented.client.details;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by terrylee on 17. 8. 13.
 */

public interface MongoClientDetailsRepository
        extends MongoRepository<MongoClientDetails, String>, MongoClientDetailsRepositoryBase {

}
