package org.francescobasile.tisanoweb1.observer;

public interface Publishable {
    void publish(EventSchema eventSchema);

    void setEventManager(EventManager eventManager);

    boolean isPublishable();
}
