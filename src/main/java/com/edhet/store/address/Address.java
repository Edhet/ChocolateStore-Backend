package com.edhet.store.address;


import com.edhet.store.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String country;

    @NonNull
    private String state;

    @NonNull
    private String city;

    @NonNull
    private String postalCode;

    @NonNull
    private String road;

    @NonNull
    private Long number;

    @OneToOne(mappedBy = "address")
    @NonNull
    private User user;
}
