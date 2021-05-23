package jpa.jpa_shop.web.controller.dto.request.member;

import jpa.jpa_shop.domain.member.Address;
import jpa.jpa_shop.domain.member.Member;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberSaveRequestDto {
    @NotEmpty(message = "이름은 필수 입니다.")
    private String name;

    @NotEmpty(message = "도시는 필수 입니다.")
    private String city;

    @NotEmpty(message = "거리는 필수 입니다.")
    private String street;

    @NotEmpty(message = "지번은 필수 입니다.")
    private String zipcode;


    public Member toEntity()
    {
        Address address = Address.builder()
                .city(this.getCity())
                .street(this.getStreet())
                .zipcode(this.getZipcode())
                .build();
        return Member.builder()
                .name(this.name)
                .address(address)
                .build();
    }
}
