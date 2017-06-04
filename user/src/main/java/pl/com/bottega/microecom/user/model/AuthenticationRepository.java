package pl.com.bottega.microecom.user.model;

import org.springframework.data.repository.CrudRepository;

public interface AuthenticationRepository extends CrudRepository<Authentication, Long> {
    Authentication findByToken(String token);
}
