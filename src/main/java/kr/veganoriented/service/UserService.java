package kr.veganoriented.service;

import kr.veganoriented.domain.User;
import kr.veganoriented.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.Assert.notNull;

/**
 * Created by terrylee on 17. 7. 31.
 */

@Service
public class UserService implements UserDetailsService{

    private Logger LOG = LoggerFactory.getLogger(UserService.class);

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserRepository userRepository;

    public User findByEmailAddress(String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress);
    }

    public User save(User user) {
        user.setHashedPassword(encryptPassword(user.getHashedPassword()));
        return userRepository.save(user);
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return locateUser(username);
    }

    private User locateUser(final String username) {
        notNull(username, "Mandatory argument 'username' missing.");
        User user = userRepository.findByEmailAddress(username.toLowerCase());
        if (user == null) {
            LOG.debug("Credentials [{}] failed to locate a user.", username.toLowerCase());
            throw new UsernameNotFoundException("failed to locate a user");
        }
        return user;
    }
}
