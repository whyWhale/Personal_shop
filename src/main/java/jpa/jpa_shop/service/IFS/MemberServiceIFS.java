package jpa.jpa_shop.service.IFS;

import jpa.jpa_shop.domain.member.Member;
import jpa.jpa_shop.web.dto.request.member.MemberUpdateRequestDto;
import jpa.jpa_shop.web.dto.response.member.MemberResponseDto;

import java.util.List;

public interface MemberServiceIFS {

    public Long Join(Member member);

    public void update(Long id, MemberUpdateRequestDto requestDto);

    public List<MemberResponseDto> findAll();

    public MemberResponseDto findById(Long MemberId);


    void delete(Long id);
}
