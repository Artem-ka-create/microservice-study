package com.study.estore.events;

import com.study.estore.core.data.ProductLookUpEntity;
import com.study.estore.core.data.ProductLookUpRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product-group")
public class ProductLookUpEventHandler{

    private final ProductLookUpRepository productLookUpRepository;

    public ProductLookUpEventHandler(ProductLookUpRepository productLookUpRepository) {
        this.productLookUpRepository = productLookUpRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        ProductLookUpEntity productLookUpEntity = new ProductLookUpEntity(
                event.getProductId(),
                event.getTitle());

        productLookUpRepository.save(productLookUpEntity);
    }

}
