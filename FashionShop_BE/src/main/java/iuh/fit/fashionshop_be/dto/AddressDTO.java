package iuh.fit.fashionshop_be.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private Long customerId;
    private String recipientName;
    private String recipientPhone;
    private String streetAddress;
    private String district;
    private String city;
    private String postalCode;
    private String country;
    private Boolean isDefault;
}
