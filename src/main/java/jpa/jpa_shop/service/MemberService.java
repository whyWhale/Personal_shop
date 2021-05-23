package jpa.jpa_shop.service;

import jpa.jpa_shop.domain.member.Member;
import jpa.jpa_shop.domain.member.Repository.MemberRepository;
import jpa.jpa_shop.exception.NoEntity;
import jpa.jpa_shop.service.IFS.MemberServiceIFS;
import jpa.jpa_shop.web.controller.dto.request.member.MemberUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.type.MethodMetadata;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService implements MemberServiceIFS {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long Join(Member member) {
        validDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    @Override
    @Transactional
    public void update(Long id, MemberUpdateRequestDto requestDto) {
        validDuplicateMember(requestDto.toEntity());
        memberRepository.findById(id).update(requestDto);
    }

    // private 은 Tranactional 안걸림.
    private void validDuplicateMember(Member member) {
        List<Member> members = memberRepository.findByName(member.getName());
        if (!members.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member findById(Long MemberId) {
        Member byIdMember = memberRepository.findById(MemberId);
        if (byIdMember == null)
            throw new NoEntity();
        return byIdMember;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Member deleteMember = findById(id);
        if (deleteMember == null) {
            throw new NoEntity("No info");
        }
        memberRepository.delete(findById(id));
    }


}
