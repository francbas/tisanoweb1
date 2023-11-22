package org.francescobasile.tisanoweb1.observer;

public interface IEventManger {

    void subscribe(Subscribable subscribable, EventSchema.EventType eventType);

    void unSubscribe(Subscribable subscribable, EventSchema.EventType eventType);

    void publish(EventSchema schema);

    void register(Publishable publisher);

    void unregister(Publishable publishable);
}
