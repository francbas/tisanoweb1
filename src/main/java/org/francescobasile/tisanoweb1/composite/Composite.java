package org.francescobasile.tisanoweb1.composite;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

//@Stateful
@Entity
public class Composite extends AbstractComponent {

    private String nameId;

    //    @ManyToOne
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AbstractComponent parent;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Collection<AbstractComponent> children;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AbstractContainer abstractContainer;

    public Composite() {
        this.children = new ArrayList<>();
    }

    /**
     * Classe implementa {@link IComponent} con i metodi per attaccare e staccare un nodo dalla struttura ad albero ed
     * il metodo attraversare tutta la struttura.
     * Gli oggetti di questa classe rappresentano un generico nodo composito in una gerarchia ad albero.
     * Il nodo puo essere sia un nodo stesso, in modo ricorsivo, che una foglia.
     * La struttura che si determina è potenzialmente indeterminata. La verifica se un nodo composito sia una
     * foglia avviene testando la lunghezza della lista children mediante true/false sul metodo isLeaf.
     * L'attributo abstractContainer {@link AbstractContainer} è un wrapper per un qualsiasi oggetto che implementi IContainable
     * atttraverso l'oggetto AbstractContainer.
     *
     * @param abstractContainer
     * @param nameId
     */
    public Composite(AbstractContainer abstractContainer, String nameId) {
        this();
        this.abstractContainer = abstractContainer;
        this.nameId = nameId;
    }

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String compositeIdentifier) {
        this.nameId = compositeIdentifier;
    }

    @Override
    public AbstractContainer getAbstractContainer() {
        return abstractContainer;
    }

    public void setAbstractContainer(AbstractContainer abstractContainer) {
        this.abstractContainer = abstractContainer;
    }

    @Override
    public AbstractComponent getParent() {
        return parent;
    }

    @Override
    public void setParent(AbstractComponent parent) {
        this.parent = parent;
    }

    @Override
    public Collection<AbstractComponent> getChildren() {
        return this.children;
    }

    @Override
    public void attachChild(AbstractComponent child) {
        children.add(child);
        child.setParent(this);
    }

    @Override
    public void detachChild(AbstractComponent child) {
        children.remove(child);
        child.setParent(null);
    }

    @Override
    public void traverse() {
        abstractContainer.getContent().process();
        children.forEach(AbstractComponent::traverse);
    }

    @Override
    public boolean isLeaf() {
        return this.children.isEmpty();
    }
}
