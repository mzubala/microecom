package pl.com.bottega.microecom.commons.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseAggregateRoot, ID extends Serializable> extends CrudRepository<T, ID> {

    default Optional<T> getOptionally(ID id) {
        return Optional.ofNullable(findOne(id));
    }

    default T get(ID id) {
        return getOptionally(id).orElseThrow(() -> new EntityNotFoundException());
    }

}
