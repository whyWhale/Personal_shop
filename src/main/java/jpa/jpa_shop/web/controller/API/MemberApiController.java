package jpa.jpa_shop.web.controller.API;

import jpa.jpa_shop.web.controller.dto.request.MemberSaveRequestDto;
import jpa.jpa_shop.service.IFS.MemberServiceIFS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/member")
@RestController
public class MemberApiController {
    private final MemberServiceIFS memberService;

    @PostMapping("")
    public Long save(@Valid @RequestBody MemberSaveRequestDto requestDto)
    {
        log.info("{}","CREAT MEMBER"+requestDto.toString());
        memberService.Join(requestDto.toEntity());
        return 1L;
    }
}
