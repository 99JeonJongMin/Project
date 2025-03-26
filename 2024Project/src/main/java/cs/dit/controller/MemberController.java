package cs.dit.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String memreg(MemberVO member, RedirectAttributes rttr) {

        int result = service.memreg(member);

        if (result == -1) {
            rttr.addFlashAttribute("error", "이미 사용 중인 아이디입니다."); // 아이디 중복 시 에러 메시지
            return "redirect:/board/memreg"; // 회원가입 페이지로 이동
        }

//        if (result == -2) {
//            rttr.addFlashAttribute("error", "이미 등록된 이메일입니다."); // 이메일 중복 시 에러 메시지
//            return "redirect:/board/memreg"; // 회원가입 페이지로 이동
//        }

        if (result == 1) {
            rttr.addFlashAttribute("success", "회원가입이 완료되었습니다!");
            return "redirect:/board/login"; // 회원가입 성공 시 로그인 페이지로 이동
        }

        // 만약 예상치 못한 오류가 발생했을 경우
        rttr.addFlashAttribute("error", "회원가입 중 오류가 발생했습니다.");
        return "redirect:/board/memreg";
    }

	
    @GetMapping("/checkUserId")
    @ResponseBody
    public String checkUserId(@RequestParam("userid") String userid) {
        System.out.println("🧪 userid 값 = " + userid);
        boolean isAvailable = service.isUserIdAvailable(userid);
        return isAvailable ? "AVAILABLE" : "TAKEN";
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
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/board/index";
    }

    @GetMapping("/memreg")
    public String memreg() {  
        return "board/memreg";  // ✅ 올바른 JSP 뷰 경로 지정
    }

    
}