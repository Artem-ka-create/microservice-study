package com.study.estore.interceptors;

import com.study.estore.commands.CreateProductCommand;
import com.study.estore.core.data.ProductLookUpEntity;
import com.study.estore.core.data.ProductLookUpRepository;
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
    private ProductLookUpRepository productLookUpRepository;

    public CreateProductCommandInterceprtor(ProductLookUpRepository productLookUpRepository) {
        this.productLookUpRepository = productLookUpRepository;
    }

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(@Nonnull List<? extends CommandMessage<?>> messages) {


        return (index, command) -> {

            LOGGER.info("Intercepted command: "+ command.getPayloadType());
            if (CreateProductCommand.class.equals(command.getPayloadType())){
                CreateProductCommand createProductCommand = (CreateProductCommand)command.getPayload();

                ProductLookUpEntity  productLookUpEntity= productLookUpRepository.findByProductIdOrTitle(
                        createProductCommand.getProductId(),
                        createProductCommand.getTitle());

                if (productLookUpEntity!=null){
                    throw new IllegalStateException(
                            String.format("Product with productId %s or title %s already exists",
                                    createProductCommand.getProductId(),
                                    createProductCommand.getTitle()
                            ));
                }
            }


            return command;
        };
    }
}
