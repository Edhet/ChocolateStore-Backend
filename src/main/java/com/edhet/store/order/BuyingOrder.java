package com.edhet.store.order;

import com.edhet.store.product.Product;
import com.edhet.store.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class BuyingOrder {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    @ManyToOne
    private Product product;

    @NonNull
    private Long amount;

    @NonNull
    @ManyToOne
    private User buyer;
}
