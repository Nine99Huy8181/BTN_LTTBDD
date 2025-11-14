/*
 * @ (#) s.java     1.0    17-Oct-25
 *
 * Copyright (c) 2025 IUH. All rights reserved.
 */

package iuh.fit.fashionshop_be.model;

/*
 * @description:
 * @author: Nguyen Quoc Huy
 * @date:17-Oct-25
 * @version: 1.0
 */
// Address.java
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.stream.Stream;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @Column(name = "AddressID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressID;

    @ManyToOne
    @JoinColumn(name = "CustomerID", nullable = false)
    private Customer customer;

    @Column(name = "RecipientName")
    private String recipientName;

    @Column(name = "RecipientPhone")
    private String recipientPhone;

    @Column(name = "StreetAddress")
    private String streetAddress;

    @Column(name = "District")
    private String district;

    @Column(name = "City")
    private String city;

    @Column(name = "PostalCode")
    private String postalCode;

    @Column(name = "Country")
    private String country;

    @Column(name = "IsDefault")
    private Boolean isDefault;

    @Transient
    public String getFullAddress() {
        return Stream.of(streetAddress, district, city, postalCode, country)
                .filter(s -> s != null && !s.isBlank())
                .collect(java.util.stream.Collectors.joining(", "));
    }
}