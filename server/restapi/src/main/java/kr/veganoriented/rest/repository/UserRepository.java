package kr.veganoriented.rest.repository;

import kr.veganoriented.rest.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by terrylee on 17. 8. 1.
 */

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmailAddress(String emailAddress);

    User findById(String id);

}