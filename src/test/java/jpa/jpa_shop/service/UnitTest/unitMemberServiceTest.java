package jpa.jpa_shop.service.UnitTest;

import jpa.jpa_shop.domain.member.Address;
import jpa.jpa_shop.domain.member.Member;
import jpa.jpa_shop.domain.member.Repository.MemberRepository;
import jpa.jpa_shop.exception.NoEntity;
import jpa.jpa_shop.service.MemberService;
import jpa.jpa_shop.web.dto.request.member.MemberUpdateRequestDto;
import jpa.jpa_shop.web.dto.response.member.MemberResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class unitMemberServiceTest {
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
        ReflectionTestUtils.setField(member,"id",1L);

        member2 = Member.builder().
                name("PARK").
                address(Address.builder().city("Incheon").street("gugu street").zipcode("12-1").build())
                .build();
        ReflectionTestUtils.setField(member2,"id",2L);

        log.info("before Test");
    }

    @Test
   public void MemberServiceJoin() {
       // given
       given(memberRepository.findById(1L)).willReturn(Optional.of(member));

       // when
       memberService.Join(member);
       Member findMember = memberRepository.findById(1L).orElseThrow(NoEntity::new);

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

       given(memberRepository.findById(member2.getId())).willReturn(Optional.of(member2));

       // when

       memberService.update(member2.getId(), Dto);
       Member findMember = memberRepository.findById(2L).orElseThrow(NoEntity::new);

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
        given(memberRepository.findById(1L)).willReturn(Optional.of(member));
        doNothing().when(memberRepository).delete(any(Member.class));
       // then
       memberService.delete(member.getId());
   }

    private List<Member> getMembers() {
        List<Member> list=new LinkedList<>();
        list.add(member);
        list.add(member2);
        return list;
    }
}
