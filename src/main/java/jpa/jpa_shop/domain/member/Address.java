package jpa.jpa_shop.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
@Embeddable
@NoArgsConstructor
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    @Builder
    protected Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
