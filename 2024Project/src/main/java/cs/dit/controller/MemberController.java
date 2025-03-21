package cs.dit.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import cs.dit.domain.MemberVO;
import cs.dit.service.MemberService;

@RequestMapping("/board")  // ✅ 컨트롤러 전체에 /board 경로 설정
@Controller
public class MemberController {

    @Autowired
    private MemberService service;

    // ✅ 회원가입 (아이디 & 이메일 중복 체크 추가)
    @PostMapping("/memreg")
    public String memreg(@ModelAttribute MemberVO member, RedirectAttributes rttr) {
        // 필수 입력값 확인
        if (member.getUserid() == null || member.getUserid().trim().isEmpty() ||
            member.getPasswd() == null || member.getPasswd().trim().isEmpty() ||
            member.getEmail() == null || member.getEmail().trim().isEmpty()) {
            rttr.addFlashAttribute("error", "모든 필수 정보를 입력해주세요.");
            return "redirect:/board/memreg"; // 회원가입 페이지로 이동
        }

        int result = service.memreg(member);

        switch (result) {
            case -1:
                rttr.addFlashAttribute("error", "이미 사용 중인 아이디입니다."); 
                return "redirect:/board/memreg";
            case -2:
                rttr.addFlashAttribute("error", "이미 등록된 이메일입니다."); 
                return "redirect:/board/memreg";
            case 1:
                rttr.addFlashAttribute("memreg", "registered");
                return "redirect:/board/login";
            default:
                rttr.addFlashAttribute("error", "회원가입 중 오류가 발생했습니다.");
                return "redirect:/board/memreg";
        }
    }

    // ✅ 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String userid, 
                        @RequestParam String passwd, 
                        RedirectAttributes rttr, 
                        HttpSession session) {
        // 아이디 및 비밀번호 필수 입력 확인
        if (userid == null || userid.trim().isEmpty() || passwd == null || passwd.trim().isEmpty()) {
            rttr.addFlashAttribute("error", "아이디와 비밀번호를 입력해주세요.");
            return "redirect:/board/login";
        }

        boolean isAuthenticated = service.authenticate(userid, passwd); 

        if (isAuthenticated) {
            session.setAttribute("userid", userid);
            return "redirect:/board/index"; // 로그인 성공 시 메인 페이지로 이동
        } else {
            rttr.addFlashAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "redirect:/board/login";
        }
    }

    // ✅ 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/board/index";
    }

    // ✅ 아이디 중복 확인 API (AJAX 요청 대응)
    @GetMapping("/checkUserId")
    @ResponseBody
    public String checkUserId(@RequestParam(required = false) String userid) {
        if (userid == null || userid.trim().isEmpty()) {
            return "invalid"; // 잘못된 입력
        }
        int count = service.countByUserId(userid);
        return (count == 0) ? "available" : "unavailable";
    }

    // ✅ 회원가입 페이지 이동
    @GetMapping("/memreg")
    public String memreg() {  
        return "board/memreg";  
    }
}
