package jpa.jpa_shop.web.controller;

import jpa.jpa_shop.web.dto.request.member.MemberSaveRequestDto;
import jpa.jpa_shop.service.IFS.MemberServiceIFS;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
@Controller
public class MemberController {
    private final MemberServiceIFS memberService;

    @GetMapping("/signUp")
    public String signUpForm(Model model)
    {
        model.addAttribute("memberSaveRequestDto",new MemberSaveRequestDto());
        return "member/signUpForm";
    }
    // RestController 대체.
   /* @PostMapping("")
    public String save(@Valid MemberSaveRequestDto requestDto , BindingResult result)
    {
        if(result.hasErrors())
        {
            log.warn("{}","name Required");
            return "member/signUpForm";
        }
        log.info("{}","CREAT MEMBER"+requestDto.toString());
        memberService.Join(requestDto.toEntity());

        return "redirect:/";
    }*/
    @GetMapping("/list")
    public String list(Model model)
    {
        model.addAttribute("members", memberService.findAll());
        return "member/memberList";
    }
}
