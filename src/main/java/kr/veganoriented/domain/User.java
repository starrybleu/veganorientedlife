package kr.veganoriented.domain;

import com.mongodb.DBObject;
import kr.veganoriented.enums.Role;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * Created by terrylee on 17. 7. 31.
 */

@Document(collection = "api_user")
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class User extends BaseEntity implements UserDetails {

    @Setter
    private String emailAddress;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private Integer age;
    @Setter
    private String hashedPassword;
    @Setter
    private Boolean verified = false;
    @Setter
    private List<Role> roles = new ArrayList<Role>();

    public User() {
        super();
    }

    public User(String id) {
        super(id);
    }

    public User(ApiUser apiUser, final String password, Role role) {
        this();
        this.emailAddress = apiUser.getEmailAddress().toLowerCase();
        this.hashedPassword = hashedPassword;
        this.firstName = apiUser.getFirstName();
        this.lastName = apiUser.getLastName();
        this.age = apiUser.getAge();
        this.roles.add(role);
    }

    public User(DBObject dbObject) {
        this((String)dbObject.get("_id"));
        this.emailAddress = (String)dbObject.get("emailAddress");
        this.firstName = (String)dbObject.get("firstName");
        this.lastName = (String)dbObject.get("lastName");
        this.hashedPassword = (String)dbObject.get("hashedPassword");
        this.verified = (Boolean)dbObject.get("verified");
        List<String> roles = (List<String>)dbObject.get("roles");
        deSerializeRoles(roles);
    }

    private void deSerializeRoles(List<String> roles) {
        for(String role : roles) {
            this.addRole(Role.valueOf(role));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for( Role role : this.getRoles() ){
            GrantedAuthority authority = new SimpleGrantedAuthority(role.name());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Role> getRoles() {
        return Collections.unmodifiableList(this.roles);
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public boolean hasRole(Role role) {
        return (this.roles.contains(role));
    }

}
