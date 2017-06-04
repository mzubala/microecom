package pl.com.bottega.microecom.commons.model;


import pl.com.bottega.microecom.commons.infrastructure.JpaInjectingListener;

import javax.persistence.*;

@MappedSuperclass
@EntityListeners(JpaInjectingListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
}
