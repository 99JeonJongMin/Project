package cs.dit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cs.dit.domain.BoardVO;
import cs.dit.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequestMapping("/board")
@Controller
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @Autowired
    private BoardService service;

    public BoardController() {
        logger.info("✅ BoardController Initialized!");
    }

    // ✅ 게시글 리스트 (자유 게시판)
    @GetMapping("/boardlist")
    public void list(Model model) {
        model.addAttribute("list", service.getList());
    }

    // ✅ 게시글 리스트 (메뉴 추천 게시판)
    @GetMapping("/menulist")
    public void list2(Model model) {
        model.addAttribute("list2", service.getList2());
    }

    // ✅ 메인 페이지
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("index", service.getList());
        return "board/index"; 
    }

    // ✅ 로그인 페이지
    @GetMapping("/login")
    public String login() {
        return "board/login"; 
    }

    // ✅ 게시글 등록 (자유 게시판)
    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {
        if (board.getTitle() == null || board.getTitle().trim().isEmpty() ||
            board.getContent() == null || board.getContent().trim().isEmpty()) {
            rttr.addFlashAttribute("errorMessage", "제목과 내용을 입력해주세요.");
            return "redirect:/board/register";
        }

        int count = service.register(board);

        if (count == 1) {
            rttr.addFlashAttribute("result", "registered");
        }

        return "redirect:/board/boardlist";
    }

    // ✅ 게시글 등록 (메뉴 추천 게시판)
    @PostMapping("/menuregister")
    public String menuregister(BoardVO board, RedirectAttributes rttr) {
        if (board.getTitle() == null || board.getTitle().trim().isEmpty() ||
            board.getContent() == null || board.getContent().trim().isEmpty()) {
            rttr.addFlashAttribute("errorMessage", "제목과 내용을 입력해주세요.");
            return "redirect:/board/register";
        }

        int count = service.menuregister(board);

        if (count == 1) {
            rttr.addFlashAttribute("result", "registered");
        }

        return "redirect:/board/menulist";
    }

    @GetMapping("/register")
    public String register() {
        return "board/register";
    }

    @GetMapping("/boardmodify")
    public String boardmodify() {
        return "board/boardmodify";
    }

    @GetMapping("/menumodify")
    public String menumodify() {
        return "board/menumodify";
    }

    // ✅ 게시글 상세 조회 (자유 게시판)
    @GetMapping({"/get", "/modify"})
    public String get(@RequestParam("bno") Long bno, Model model) {
        logger.info("✅ GET 요청 받음 - bno: {}", bno);
        BoardVO board = service.get(bno);

        if (board == null) {
            logger.error("❌ 게시글을 찾을 수 없습니다. bno: {}", bno);
            model.addAttribute("errorMessage", "게시글을 찾을 수 없습니다.");
            return "board/error"; 
        }

        model.addAttribute("board", board);
        return "board/get"; 
    }

    // ✅ 게시글 상세 조회 (메뉴 추천 게시판)
    @GetMapping("/menuget")
    public String menuget(@RequestParam("bno") Long bno, Model model) {
        logger.info("✅ GET 요청 받음 - bno: {}", bno);
        BoardVO board = service.menuget(bno);

        if (board == null) {
            logger.error("❌ 게시글을 찾을 수 없습니다. bno: {}", bno);
            model.addAttribute("errorMessage", "게시글을 찾을 수 없습니다.");
            return "board/error"; 
        }

        model.addAttribute("mboard", board);
        return "board/menuget"; 
    }

    // ✅ 게시글 수정 (자유 게시판)
    @PostMapping("/modify")
    public String modify(BoardVO board, RedirectAttributes rttr) {
        int count = service.modify(board);

        if (count == 1) {
            rttr.addFlashAttribute("result", "modified");
        }

        return "redirect:/board/boardlist";
    }

    // ✅ 게시글 삭제 (자유 게시판)
    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
        if (bno == null) {
            rttr.addFlashAttribute("errorMessage", "삭제할 게시글을 선택해주세요.");
            return "redirect:/board/boardlist";
        }

        int count = service.remove(bno);

        if (count == 1) {
            rttr.addFlashAttribute("result", "removed");
        } else {
            rttr.addFlashAttribute("errorMessage", "게시글 삭제 실패.");
        }

        return "redirect:/board/boardlist";
    }

    // ✅ 게시글 삭제 (메뉴 추천 게시판)
    @PostMapping("/menuremove")
    public String menuremove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
        if (bno == null) {
            rttr.addFlashAttribute("errorMessage", "삭제할 게시글을 선택해주세요.");
            return "redirect:/board/menulist";
        }

        int count = service.menuremove(bno);

        if (count == 1) {
            rttr.addFlashAttribute("result", "removed");
        } else {
            rttr.addFlashAttribute("errorMessage", "게시글 삭제 실패.");
        }

        return "redirect:/board/menulist";
    }

    // ✅ 루트 경로 리다이렉트 처리
    @Controller
    public class RootController {
        @GetMapping("/")
        public String redirectToBoardIndex() {
            return "redirect:/board/index";
        }
    }
}
