package com.ecomerce.ms.service.order.application.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommandGateway {
    @Autowired
    private List<? extends CommandHandler<? extends Command>> handlers;
    private final Map<Class<?>, CommandHandler<? extends Command>> handlerMapper = new HashMap<>();

    private static final String HANDLE_METHOD = "handle";

    public CommandGateway() {
        handlers.forEach(handler -> {
            try {
                Method handleMethod = handler.getClass().getMethod(HANDLE_METHOD);
                Class<?> commandType = handleMethod.getParameterTypes()[0];
                handlerMapper.put(commandType, handler);
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @SuppressWarnings("unchecked")
    public <C extends Command> void handle(C command) {
        CommandHandler<C> handler = (CommandHandler<C>)findHandlerForCommand(command);
        handler.handle(command);
    }

    private CommandHandler<? extends Command> findHandlerForCommand(Command command) {
        return handlerMapper.get(command.getClass());
    }
}
