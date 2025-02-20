package com.study.estore.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "productlookup")
public class ProductLookUpEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -7632157928493580609L;

    @Id
    private String productId;

    @Column(unique = true)
    private String title;

}
