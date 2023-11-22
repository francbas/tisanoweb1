package org.francescobasile.tisanoweb1.composite;

import java.util.Collection;

public interface IComponent {
    AbstractComponent getParent();

    void setParent(AbstractComponent parent);

    void attachChild(AbstractComponent child);

    void detachChild(AbstractComponent child);

    Collection<AbstractComponent> getChildren();

    AbstractContainer getAbstractContainer();

    void traverse();

    public abstract boolean isLeaf();

}

