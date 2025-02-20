package com.study.estore.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "products")
@Data
public class ProductEntity implements Serializable {


    @Serial
    private static final long serialVersionUID = -3183232038074388205L;

    @Id
    @Column(unique = true)
    private String productId;

    @Column(unique = true)
    private String title;
    private Integer price;
    private Integer quantity;

}
