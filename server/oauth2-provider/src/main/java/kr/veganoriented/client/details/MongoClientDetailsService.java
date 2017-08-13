package kr.veganoriented.client.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import com.google.common.base.Predicate;

import static com.google.common.collect.Sets.filter;

/**
 * Created by terrylee on 17. 8. 13.
 */

@Component
public class MongoClientDetailsService implements ClientDetailsService, ClientRegistrationService {

    private final MongoClientDetailsRepository mongoClientDetailsRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MongoClientDetailsService(MongoClientDetailsRepository mongoClientDetailsRepository,
                                     PasswordEncoder passwordEncoder) {
        this.mongoClientDetailsRepository = mongoClientDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        try {
            return mongoClientDetailsRepository.findByClientId(clientId);
        } catch(IllegalArgumentException e) {
            throw new ClientRegistrationException("No Client Details for client Id", e);
        }
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        final MongoClientDetails mongoClientDetails =
                new MongoClientDetails(
                        clientDetails.getClientId(),
                        passwordEncoder.encode(clientDetails.getClientSecret()),
                        clientDetails.getScope(),
                        clientDetails.getResourceIds(),
                        clientDetails.getAuthorizedGrantTypes(),
                        clientDetails.getRegisteredRedirectUri(),
                        new ArrayList(clientDetails.getAuthorities()),
                        clientDetails.getAccessTokenValiditySeconds(),
                        clientDetails.getRefreshTokenValiditySeconds(),
                        clientDetails.getAdditionalInformation(),
                        getAutoApproveScopes(clientDetails));
        mongoClientDetailsRepository.save(mongoClientDetails);                   
                        
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        final MongoClientDetails mongoClientDetails =
                new MongoClientDetails(
                        clientDetails.getClientId(),
                        clientDetails.getClientSecret(),
                        clientDetails.getScope(),
                        clientDetails.getResourceIds(),
                        clientDetails.getAuthorizedGrantTypes(),
                        clientDetails.getRegisteredRedirectUri(),
                        new ArrayList(clientDetails.getAuthorities()),
                        clientDetails.getAccessTokenValiditySeconds(),
                        clientDetails.getRefreshTokenValiditySeconds(),
                        clientDetails.getAdditionalInformation(),
                        getAutoApproveScopes(clientDetails));

        final boolean result = mongoClientDetailsRepository.update(mongoClientDetails);

        if(!result)
            throw new NoSuchClientException("No such Client Id");
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        final boolean result = mongoClientDetailsRepository.updateClientSecret(clientId, passwordEncoder.encode(secret));

        if(!result)
            throw new NoSuchClientException("No such Client Id");
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        final boolean result = mongoClientDetailsRepository.deleteByClientId(clientId);

        if(!result)
            throw new NoSuchClientException("No such Client Id");
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        return mongoClientDetailsRepository
                .findAll()
                .stream()
                .map(mongoClientDetails -> new BaseClientDetails(mongoClientDetails.getClientId(),
                        Joiner.on(",").join(mongoClientDetails.getResourceIds()),
                        Joiner.on(",").join(mongoClientDetails.getScope()),
                        Joiner.on(",").join(mongoClientDetails.getAuthorizedGrantTypes()),
                        Joiner.on(",").join(mongoClientDetails.getAuthorities()),
                        Joiner.on(",").join(mongoClientDetails.getRegisteredRedirectUri())))
                .collect(Collectors.toList());
    }

    private Set<String> getAutoApproveScopes(final ClientDetails clientDetails) {
        if(clientDetails.isAutoApprove("true"))
            return new HashSet<String>(Arrays.asList("true"));

        return filter(clientDetails.getScope(), byAutoApproveOfScope(clientDetails));
    }

    private Predicate<String> byAutoApproveOfScope(final ClientDetails clientDetails) {
        return new Predicate<String>() {
            @Override
            public boolean apply(String scope) {
                return clientDetails.isAutoApprove(scope);
            }
        };
    }
}
