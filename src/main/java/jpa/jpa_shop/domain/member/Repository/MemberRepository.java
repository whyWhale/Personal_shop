package jpa.jpa_shop.domain.member.Repository;

import jpa.jpa_shop.domain.member.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository  {
    @PersistenceContext
    EntityManager em;

    public void save(Member member)
    {
        em.persist(member);
    } 

    public Member findById(Long id)
    {
        return em.find(Member.class,id);
    }

    public List<Member> findAll()
    {
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }
    public List<Member> findByName(String name)
    {
        return em.createQuery("select m from Member m where m.name=:name",Member.class)
                .setParameter("name",name).getResultList();
    }
    public void delete(Member member)
    {
        em.remove(member);
    }

}
