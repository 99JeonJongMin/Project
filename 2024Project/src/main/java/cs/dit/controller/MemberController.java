package cs.dit.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cs.dit.domain.MemberVO;
import cs.dit.service.MemberService;

@Controller
public class MemberController {
	
    @Autowired
    private MemberService service;
	
    // ✅ 회원가입 (이메일 중복 체크 포함)
    @PostMapping("/memreg")
    public String memreg(MemberVO member, RedirectAttributes rttr) {

        if (service.isEmailExists(member.getEmail())) {
            rttr.addFlashAttribute("error", "이미 등록된 이메일입니다.");
            return "redirect:/board/memreg"; // 회원가입 페이지로 이동
        }

        int count = service.memreg(member);

        if (count == 1)
            rttr.addFlashAttribute("memreg", "registered");

        return "redirect:/board/login";
    }
	
    // ✅ 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String userid, @RequestParam String passwd, RedirectAttributes rttr, HttpSession session) {
        boolean isAuthenticated = service.authenticate(userid, passwd);

        if (isAuthenticated) {
            session.setAttribute("userid", userid);
            return "redirect:/board/index"; // 로그인 성공 시 메인 페이지로 이동
        } else {
            rttr.addFlashAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "redirect:/board/login"; // 로그인 페이지로 이동
        }
    }

    // ✅ 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/board/index";
    }
	
    @GetMapping("/memreg")
    public void memreg() {}
}
