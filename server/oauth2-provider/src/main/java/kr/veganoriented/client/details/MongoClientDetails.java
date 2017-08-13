package kr.veganoriented.client.details;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

/**
 * Created by terrylee on 17. 8. 13.
 */

@ToString
@EqualsAndHashCode
@Document
public class MongoClientDetails implements ClientDetails {

    @Id
    @Getter private String clientId;
    @Getter private String clientSecret;
    @Getter private Set<String> scope = Collections.emptySet();
    @Getter private Set<String> resourceIds = Collections.emptySet();
    @Getter private Set<String> authorizedGrantTypes = Collections.emptySet();
    private Set<String> registeredRedirectUris;
    @Getter private List<GrantedAuthority> authorities = Collections.emptyList();
    @Getter private Integer accessTokenValiditySeconds;
    @Getter private Integer refreshTokenValiditySeconds;
    @Getter private Map<String, Object> additionalInformation = new LinkedHashMap<>();
    @Getter @Setter private Set<String> autoApproveScopes;

    @PersistenceConstructor
    public MongoClientDetails(final String clientId,
                              final String clientSecret,
                              final Set<String> scope,
                              final Set<String> resourceIds,
                              final Set<String> authorizedGrantTypes,
                              final Set<String> registeredRedirectUris,
                              final List<GrantedAuthority> authorities,
                              final Integer accessTokenValiditySeconds,
                              final Integer refreshTokenValiditySeconds,
                              final Map<String, Object> additionalInformation,
                              final Set<String> autoApproveScopes) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.resourceIds = resourceIds;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUris = registeredRedirectUris;
        this.authorities = authorities;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.additionalInformation = additionalInformation;
        this.autoApproveScopes = autoApproveScopes;
    }


    @Override
    public boolean isSecretRequired() {
        return this.clientSecret != null;
    }

    @Override
    public boolean isScoped() {
        return this.scope != null && !this.scope.isEmpty();
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUris;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if(autoApproveScopes == null)
            return false;
        for(String autoApproval : autoApproveScopes) {
            if(autoApproval.equals("true") || scope.matches(autoApproval))
                return true;
        }
        return false;
    }
}
