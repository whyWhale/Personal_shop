package jpa.jpa_shop.domain.member.Repository;

import jpa.jpa_shop.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    Optional<Member> findById(Long id);

    Optional<Member> findByUsername(String username);

    boolean existsByName(String username);
}

