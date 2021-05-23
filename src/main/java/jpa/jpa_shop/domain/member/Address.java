package jpa.jpa_shop.domain.member;

import lombok.*;

import javax.persistence.Embeddable;
@Embeddable
@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
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

    public Address update(String city, String street, String zipcode)
    {
        this.city=city;
        this.street=street;
        this.zipcode=zipcode;
        return this;
    }
}
