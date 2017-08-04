package kr.veganoriented.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by terrylee on 17. 8. 3.
 */

@Getter @EqualsAndHashCode
public class BaseEntity implements Serializable {

    private int version;

    @Id
    private String id;

    private Date timeCreated;

    public BaseEntity() {
        this(UUID.randomUUID());
    }

    public BaseEntity(UUID guid) {
        Assert.notNull(guid, "UUID is required");
        id = guid.toString();
        this.timeCreated = new Date();
    }

    public BaseEntity(String guid) {
        Assert.notNull(guid, "UUID is required");
        id = guid;
        this.timeCreated = new Date();
    }

}
