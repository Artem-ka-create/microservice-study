package com.study.estore.events;

import com.study.estore.core.data.ProductEntity;
import com.study.estore.core.data.ProductRepository;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {

    private final ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){

        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);

        productRepository.save(productEntity);
    }
}
