package org.francescobasile.tisanoweb1.service.message;

public class EmailServiceInjectorF implements IFGenericServiceDependencyInjectionFactory {
    @Override
    public IFGenericServiceConsumer getConsumer() {
        return new MessageServiceConsumer(new EmailService());
    }
}
