package org.francescobasile.tisanoweb1.observer;

public interface Triggable {
    void trigger(EventSchema schema);

    boolean isTriggable();

}
