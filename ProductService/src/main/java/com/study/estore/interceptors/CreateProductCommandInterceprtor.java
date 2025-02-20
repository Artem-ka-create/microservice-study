package com.study.estore.interceptors;

import com.study.estore.commands.CreateProductCommand;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.function.BiFunction;

@Component
public class CreateProductCommandInterceprtor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private static final Logger LOGGER =  LoggerFactory.getLogger(CreateProductCommandInterceprtor.class);

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {

            LOGGER.info("Intercepted command: "+ command.getPayloadType());
            if (CreateProductCommand.class.equals(command.getPayloadType())){
                CreateProductCommand createProductCommand = (CreateProductCommand)command.getPayload();
                if ( createProductCommand.getPrice().compareTo( 0 ) <= 0 ) {
                    throw new IllegalArgumentException("Price cannot be less or equal than zero");
                }

                if (createProductCommand.getTitle() == null || createProductCommand.getTitle().isBlank()){
                    throw new IllegalArgumentException("Title cannot be empty");
                }
            }


            return command;
        };
    }
}
