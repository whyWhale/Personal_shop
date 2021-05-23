package jpa.jpa_shop.service.IFS;

import jpa.jpa_shop.domain.member.Member;
import jpa.jpa_shop.web.controller.dto.request.member.MemberUpdateRequestDto;

import java.util.List;

public interface MemberServiceIFS {

    public Long Join(Member member);

    public void update(Long id, MemberUpdateRequestDto requestDto);

    public List<Member> findAll();

    public Member findById(Long MemberId);


    void delete(Long id);
}
