package kr.veganoriented.rest.service;

import kr.veganoriented.rest.domain.ApiUser;
import kr.veganoriented.rest.domain.User;
import kr.veganoriented.rest.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

import static org.springframework.util.Assert.notNull;

/**
 * Created by terrylee on 17. 7. 31.
 */

@Service
public class UserService implements UserDetailsService {

    private Logger LOG = LoggerFactory.getLogger(UserService.class);
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private DefaultTokenServices tokenServices;

//    @Autowired
//    private ClientDetailsService clientDetailsService;

    public User findByEmailAddress(String emailAddress) {
        Assert.notNull(emailAddress);
        User user = userRepository.findByEmailAddress(emailAddress);
        if(user == null) {
        }
        return user;
    }

    public User save(User user) {
        String emailAddress = user.getEmailAddress();
        if (userRepository.findByEmailAddress(emailAddress) == null) {
            LOG.info("User does not already exist in the data store - creating a new user [{}].", emailAddress);
            user.setHashedPassword(encryptPassword(user.getHashedPassword()));
//            createTokenForNewUser(user.getId(), user.getHashedPassword(), "foo");
            return userRepository.save(user);
        } else {
            LOG.info("Duplicate email located.");
            return userRepository.save(user);
        }
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public ApiUser authenticate(String username, String password) {
        Assert.notNull(username);
        Assert.notNull(password);
        User user = locateUser(username);
        if(!passwordEncoder.encode(password).equals(user.getHashedPassword())) {
        }
        return new ApiUser(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
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

//    private OAuth2AccessToken createTokenForNewUser(String userId, String password, String clientId) {
//        LOG.info("createTokenForNewUser =>>> userId : {}, password : {}, clientId : {}", userId, password, clientId);
//        String hashedPassword = passwordEncoder.encode(password);
//        UsernamePasswordAuthenticationToken userAuthentication = new UsernamePasswordAuthenticationToken(
//                userId,
//                hashedPassword, Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_USER.toString())));
//        ClientDetails authenticatedClient = clientDetailsService.loadClientByClientId(clientId);
//        OAuth2Request oAuth2Request = createOAuth2Request(null, clientId,
//                Collections.singleton(new SimpleGrantedAuthority(Role.ROLE_USER.toString())),
//                true, authenticatedClient.getScope(), null, null, null, null);
//        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, userAuthentication);
//        return tokenServices.createAccessToken(oAuth2Authentication);
//    }

    private OAuth2Request createOAuth2Request(Map<String, String> requestParameters, String clientId,
                                              Collection<? extends GrantedAuthority> authorities, boolean approved, Collection<String> scope,
                                              Set<String> resourceIds, String redirectUri, Set<String> responseTypes,
                                              Map<String, Serializable> extensionProperties) {
        return new OAuth2Request(requestParameters, clientId, authorities, approved, scope == null ? null
                : new LinkedHashSet<String>(scope), resourceIds, redirectUri, responseTypes, extensionProperties);
    }
}
