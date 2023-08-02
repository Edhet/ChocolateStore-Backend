package com.edhet.store.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
    private Long productId;

    @NonNull
    private Long amount;

    @NonNull
    private Long buyerId;
}
