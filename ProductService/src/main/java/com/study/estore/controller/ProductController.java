package com.study.estore.controller;

import com.study.estore.ProductDto;
import com.study.estore.commands.CreateProductCommand;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products") // http://localhost:8080/products
public class ProductController {

    private final Environment env;
    private final CommandGateway commandGateway;

    @PostMapping
    public String createProduct( @RequestBody ProductDto productDto){



        CreateProductCommand createCommand = CreateProductCommand.builder()
                .productId(UUID.randomUUID().toString())
                .quantity(productDto.getQuantity())
                .price(productDto.getPrice())
                .title(productDto.getTitle())
                .build();

        String returnValue;

        try{
            returnValue = commandGateway.sendAndWait(createCommand);
        }catch (Exception ex){
            returnValue = ex.getLocalizedMessage();
        }

        return "HTTP POST Handled " + returnValue;
    }

    @GetMapping
    public String getProduct(){
        return "HTTP GET Handled";
    }

    @PutMapping
    public String updateProduct(){
        return "HTTP PUT Handled";
    }
    @DeleteMapping
    public String deleteProduct(){
        return "HTTP DELETE Handled";
    }

}
