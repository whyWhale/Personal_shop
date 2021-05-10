package jpa.jpa_shop.service.IFS;

import jpa.jpa_shop.domain.member.Member;

import java.util.List;

public interface MemberServiceIFS {

    public Long Join(Member member);

    public List<Member> findAll();

    public Member findById(Long MemberId);


}
