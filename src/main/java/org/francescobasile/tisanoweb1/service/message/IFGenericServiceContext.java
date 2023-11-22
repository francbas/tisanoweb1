package org.francescobasile.tisanoweb1.service.message;

import java.util.Map;

public interface IFGenericServiceContext<K, V> {
    Map<K, V> getContext();
}
