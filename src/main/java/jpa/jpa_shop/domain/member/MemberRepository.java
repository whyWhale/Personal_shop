package jpa.jpa_shop.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository  {
    @PersistenceContext
    EntityManager em;

    public Long save(Member member)
    {
        em.persist(member);
        return member.getId();
    }

    public Member findById(Long id)
    {
        return em.find(Member.class,id);
    }

}
