package com.study.estore.events;

import lombok.Data;

@Data
public class ProductCreatedEvent {

    private String productId;
    private String title;
    private Integer price;
    private Integer quantity;
}
