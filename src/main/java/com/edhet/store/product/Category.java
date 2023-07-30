package com.edhet.store.product;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @OneToMany
    private List<Product> products;
}
