package org.francescobasile.tisanoweb1.service.message;

public interface IFGenericServiceConsumer {
    <K, V> void processService(IFGenericServiceContext<K, V> ctx);
}
