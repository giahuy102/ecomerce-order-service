package com.ecomerce.ms.service.order.application.command;

@FunctionalInterface
public interface CommandHandler<C extends Command> {
    public void handle(C command);
}
