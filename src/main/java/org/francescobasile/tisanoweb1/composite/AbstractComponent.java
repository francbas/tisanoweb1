package org.francescobasile.tisanoweb1.composite;

import jakarta.persistence.*;

//@Stateful
//@Entity
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractComponent implements IComponent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

//    public abstract void setParent(AbstractComponent parent);
//
//    public abstract void attachChild(AbstractComponent child);
//
//    public abstract void detachChild(AbstractComponent child);

}
