package jpa.jpa_shop.service;

import jpa.jpa_shop.domain.member.Address;
import jpa.jpa_shop.domain.member.Member;
import jpa.jpa_shop.domain.member.Repository.MemberRepository;
import jpa.jpa_shop.web.dto.request.member.MemberUpdateRequestDto;
import jpa.jpa_shop.web.dto.response.member.MemberResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class unitUserServiceTest {
    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    private Member member;

    private Member member2;


    @Before
    public void memberData() {
        member = Member.builder().
                name("KIM").
                address(Address.builder().city("Seoul").street("soso street").zipcode("59-1").build())
                .build();

        member2 = Member.builder().
                name("PARK").
                address(Address.builder().city("Incheon").street("gugu street").zipcode("12-1").build())
                .build();
    }

    @Test
   public void MemberServiceJoin() {
       // given
       given(memberRepository.save(any())).willReturn(1L);
       given(memberRepository.findById(1L)).willReturn(member);

       // when
       long memberId  = memberService.Join(member);
       Member findMember = memberRepository.findById(memberId);

       // then
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember.getAddress().getCity()).isEqualTo(member.getAddress().getCity());
        assertThat(findMember.getAddress().getStreet()).isEqualTo(member.getAddress().getStreet());
        assertThat(findMember.getAddress().getZipcode()).isEqualTo(member.getAddress().getZipcode());

   }

   @Test
   public void MemberServiceUpdate() {
       // given
       String name = "Park";
       String city = "Seoul";
       String street = "soso street";
       String zipcode = "65-1";
       MemberUpdateRequestDto Dto = MemberUpdateRequestDto.builder().name(name).
               city(city).street(street).zipcode(zipcode).build();

       given(memberRepository.findById(1L)).willReturn(member2);

       // when

       Long updateId = memberService.update(1L, Dto);
       Member findMember = memberRepository.findById(1L);

       // then
       assertThat(findMember.getAddress().getCity()).isEqualTo(city);
       assertThat(findMember.getAddress().getStreet()).isEqualTo(street);
       assertThat(findMember.getAddress().getZipcode()).isEqualTo(zipcode);
       assertThat(findMember.getName()).isEqualTo(name);
   }
   
   @Test
   public void MemberServiceFindAll() {
       // given
       List<Member> list = getMembers();
       given(memberRepository.findAll()).willReturn(list);
       // when
       List<MemberResponseDto> memberResponseDtos = memberService.findAll();
       // then
       MemberResponseDto memberResponseDto1 = memberResponseDtos.get(0);
       MemberResponseDto memberResponseDto2 = memberResponseDtos.get(1);
       assertThat(memberResponseDto1.getName()).isEqualTo(member.getName());
       assertThat(memberResponseDto1.getStreet()).isEqualTo(member.getAddress().getStreet());
       assertThat(memberResponseDto2.getStreet()).isEqualTo(member2.getAddress().getStreet());
       assertThat(memberResponseDto2.getStreet()).isEqualTo(member2.getAddress().getStreet());
   }

   @Test
   public void MemberServiceDelete() {
       // given

       // when
        given(memberRepository.findById(1L)).willReturn(member);
        doNothing().when(memberRepository).delete(any(Member.class));
       // then
       memberService.delete(1L);
   }

    private List<Member> getMembers() {
        List<Member> list=new LinkedList<>();
        list.add(member);
        list.add(member2);
        return list;
    }
}
