package jpa.jpa_shop.service;

import jpa.jpa_shop.domain.member.Member;
import jpa.jpa_shop.domain.member.Repository.MemberRepository;
import jpa.jpa_shop.exception.NoEntity;
import jpa.jpa_shop.service.IFS.MemberServiceIFS;
import jpa.jpa_shop.web.dto.request.member.MemberUpdateRequestDto;
import jpa.jpa_shop.web.dto.response.member.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService implements MemberServiceIFS {
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Long Join(Member member) {
        validDuplicateMember(member);
        return memberRepository.save(member);
    }

    @Override
    @Transactional
    public Long update(Long id, MemberUpdateRequestDto requestDto) {
        validDuplicateMember(requestDto.toEntity());
        Member findMember = memberRepository.findById(id);
        findMember.update(requestDto);
        return findMember.getId();
    }

    // private 은 Tranactional 안걸림.
    private void validDuplicateMember(Member member) {
        List<Member> members = memberRepository.findByName(member.getName());
        if (!members.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    @Override
    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll().stream().map(Member::toDto).collect(Collectors.toList());
    }

    @Override
    public MemberResponseDto findById(Long MemberId) {
        Member byIdMember = memberRepository.findById(MemberId);
        if (byIdMember == null)
            throw new NoEntity();
        return byIdMember.toDto();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Member deleteMember = memberRepository.findById(id);
        if (deleteMember == null) {
            throw new NoEntity("No info");
        }
        memberRepository.delete(deleteMember);
    }


}
