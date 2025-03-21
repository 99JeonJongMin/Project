package cs.dit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cs.dit.domain.BoardVO;
import cs.dit.service.BoardService;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

@RequestMapping("/board")
@Controller
public class BoardController {
    
    @Autowired
    private BoardService service;
    
    @GetMapping("/boardlist")
    public void list(Model model) {
        model.addAttribute("list", service.getList());
    }
    
    @GetMapping("/menulist")
    public void list2(Model model) {
        model.addAttribute("list2", service.getList2());
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("index", service.getList());
        return "board/index";
    }

    @GetMapping("/login")
    public void login(Model model) {
        model.addAttribute("login", service.getList());
    }
    
    @PostMapping("/register")
    public String register(@Validated BoardVO board, BindingResult result, RedirectAttributes rttr) {
        if (result.hasErrors() || board.getTitle().trim().isEmpty() || board.getContent().trim().isEmpty()) {
            rttr.addFlashAttribute("errorMessage", "제목과 내용을 입력해주세요.");
            return "redirect:/board/register";
        }

        int count = service.register(board);
        if (count == 1) {
            rttr.addFlashAttribute("result", "registered");
        }
        return "redirect:/board/boardlist";
    }

    @GetMapping({"/menuget", "/menumodify"})
    public void menuget(@RequestParam("bno") Long bno, Model model) {
        BoardVO board = service.menuget(bno);
        if (board == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다.");
        }
        model.addAttribute("mboard", board);
    }
    
    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, @RequestParam("type") String type, RedirectAttributes rttr) {
        int count = service.remove(bno);
        if (count == 1) {
            rttr.addFlashAttribute("result", "removed");
        }
        return type.equals("menu") ? "redirect:/board/menulist" : "redirect:/board/boardlist";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/board/index";
    }
}
