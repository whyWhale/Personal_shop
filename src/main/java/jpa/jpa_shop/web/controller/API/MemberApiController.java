package jpa.jpa_shop.web.controller.API;

import jpa.jpa_shop.domain.member.Member;
import jpa.jpa_shop.web.controller.dto.request.member.MemberSaveRequestDto;
import jpa.jpa_shop.service.IFS.MemberServiceIFS;
import jpa.jpa_shop.web.controller.dto.request.member.MemberUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/member")
@RestController
public class MemberApiController {
    private final MemberServiceIFS memberService;

    @PostMapping("")
    public void save(@Valid @RequestBody MemberSaveRequestDto requestDto)
    {
        log.info("{}","create memberName -- >"+requestDto.getName());
        memberService.Join(requestDto.toEntity());
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @Valid @RequestBody MemberUpdateRequestDto requestDto)
    {
        log.info("{}","update Id -- >"+id);
        memberService.update(id,requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id")Long id)
    {
        log.info("{}","delete Id -- >"+id);
        memberService.delete(id);
    }
}
