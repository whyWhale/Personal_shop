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
    public void Join(Member member) {
        if(validDuplicateMember(member)){
            throw new IllegalArgumentException("Duplicated username");
        }
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void update(Long id, MemberUpdateRequestDto requestDto) {
        Member member = memberRepository.findById(id).orElseThrow(NoEntity::new);
        member.update(requestDto);
    }

    // private 은 Tranactional 안걸림.
    private boolean validDuplicateMember(Member member) {
       return memberRepository.existsByName(member.getName());
    }

    @Override
    public List<MemberResponseDto> findAll() {
        return memberRepository.findAll().stream().map(Member::toDto).collect(Collectors.toList());
    }

    @Override
    public MemberResponseDto findById(Long MemberId) {
        Member byIdMember = memberRepository.findById(MemberId).orElseThrow(NoEntity::new);
        return byIdMember.toDto();
    }

    @Override
    public MemberResponseDto findByUsername(String memberUsername) {
        return memberRepository.findByUsername(memberUsername).orElseThrow(NoEntity::new).toDto();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Member deleteMember = memberRepository.findById(id).orElseThrow(NoEntity::new);
        memberRepository.delete(deleteMember);
    }
}
