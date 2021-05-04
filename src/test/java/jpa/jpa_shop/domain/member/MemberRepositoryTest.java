package jpa.jpa_shop.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional // test에 들어간 Transcational은 무조건 ROLLBACK
    @Rollback(value = false)
    public void memberSave() throws Exception {
        //given
        Member admin = Member.builder().username("admin").build();
        //when
        Member save = memberRepository.save(admin);
        //then
        Optional<Member> byId = memberRepository.findById(save.getId());
        byId.ifPresent(member -> {
            Assertions.assertThat(member.getId()).isEqualTo(save.getId());
            Assertions.assertThat(member).isEqualTo(save);
            // 같은 영속성 컨텍스트에 있으므로 추가적으로 1차캐시 이미 있기때문에 select 쿼리도 안나간다.
        });
    }
}