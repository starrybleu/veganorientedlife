package kr.veganoriented.rest.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

/**
 * Created by terrylee on 17. 8. 1.
 */
public class ResourceCollection<T> extends ResourceSupport {

    private Collection<T> data;

    public ResourceCollection(Collection<T> data) {
        this.data = data;
    }

    public Collection<T> getData() {
        return data;
    }
}
