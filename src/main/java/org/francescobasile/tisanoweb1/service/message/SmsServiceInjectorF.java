package org.francescobasile.tisanoweb1.service.message;

public class SmsServiceInjectorF implements IFGenericServiceDependencyInjectionFactory {
    @Override
    public IFGenericServiceConsumer getConsumer() {
        return new MessageServiceConsumer(new SmsService());
    }
}
