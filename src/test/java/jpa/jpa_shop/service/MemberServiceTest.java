package jpa.jpa_shop.service;

import jpa.jpa_shop.domain.member.Address;
import jpa.jpa_shop.domain.member.Member;
import jpa.jpa_shop.web.dto.response.member.MemberResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @Rollback(value = false)
    public void join() {
            //given
        String username="abc1234";
        Member member = Member.builder()
                .username(username)
                .name("KIM").
                address(Address.builder().city("Seoul").street("soso street").zipcode("59-1").build())
                .build();
        //when
        memberService.Join(member);
        //then
        final MemberResponseDto byUsername = memberService.findByUsername(username);
        Assertions.assertThat(byUsername.getId()).isEqualTo(member.getId());
    }
    @Test
    public void findAll() {
        Member member = Member.builder().
                name("PARK").
                address(Address.builder().city("Seoul").street("soso street").zipcode("59-1").build())
                .build();

        Member member2 = Member.builder().
                name("LEE").
                address(Address.builder().city("Seoul").street("gogo street").zipcode("11-1").build())
                .build();
        memberService.Join(member);
        memberService.Join(member2);

        List<MemberResponseDto> all = memberService.findAll();
        Assertions.assertThat(all).isNotNull();
        Assertions.assertThat(all.get(0).getId()).isEqualTo(member.getId());
        Assertions.assertThat(all.get(1).getId()).isEqualTo(member2.getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void validDuplicateMember() throws Exception{
        //given
        Member member = Member.builder().
                username("PARK").
                address(Address.builder().city("Seoul").street("soso street").zipcode("59-1").build())
                .build();

        Member member2 = Member.builder().
                username("PARK").
                address(Address.builder().city("Seoul").street("gogo street").zipcode("11-1").build())
                .build();
        //when
        memberService.Join(member);
        memberService.Join(member2);
        //then
        fail();
    }
}