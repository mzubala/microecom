package pl.com.bottega.microecom.user.model;

import pl.com.bottega.microecom.commons.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
public class Authentication extends BaseEntity {

    @OneToOne(mappedBy = "authentication", fetch = FetchType.EAGER)
    private User user;

    private String token;

    public Authentication(User user) {
        this.user = user;
        token = UUID.randomUUID().toString();
    }

    Authentication() {
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }
}
