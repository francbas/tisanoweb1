package org.francescobasile.tisanoweb1.service.message;

public interface IFGenericService {
    <K, V> void executeService(IFGenericServiceContext<K, V> context);
}
