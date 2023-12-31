package com.edhet.store.user;

import com.edhet.store.address.Address;
import com.edhet.store.category.Category;
import com.edhet.store.order.BuyOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "storeUsers")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String firstName;

    @NonNull
    private String surname;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    private String password;

    @NonNull
    private LocalDate birthDate;

    @NonNull
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne
    private Address address;

    @Column(insertable = false, updatable = false, nullable = false)
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime creationTimestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category preferredCategory;

    @OneToMany(mappedBy = "buyer", orphanRemoval = true)
    private List<BuyOrder> buyOrders;

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
