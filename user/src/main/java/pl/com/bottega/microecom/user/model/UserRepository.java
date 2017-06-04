package pl.com.bottega.microecom.user.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);
}
