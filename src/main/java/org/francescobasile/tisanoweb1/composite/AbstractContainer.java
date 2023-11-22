package org.francescobasile.tisanoweb1.composite;

import jakarta.persistence.*;

//@Stateful
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractContainer implements IContainable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public AbstractContainer() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

//    public abstract Object getContent();

}
