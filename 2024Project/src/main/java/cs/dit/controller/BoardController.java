package cs.dit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cs.dit.domain.BoardVO;
import cs.dit.service.BoardService;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	public BoardController() {
        System.out.println("✅ BoardController Initialized!");  // 🚀 서버 콘솔에서 확인
    }
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
	    return "board/index"; // 📌 JSP 뷰를 명시적으로 지정
	}
	

	
	@PostMapping("/boardpost")
	public String post(@Validated BoardVO board, BindingResult result, RedirectAttributes rttr) {
	    if (result.hasErrors()) {
	        rttr.addFlashAttribute("errorMessage", "제목과 내용을 입력해주세요.");
	        return "redirect:/board/post";
	    }

	    int count = service.register(board);

	    if (count == 1) {
	        rttr.addFlashAttribute("result", "registered");
	    }

	    return "redirect:/board/boardlist";
	}
	@PostMapping("/menupost")
	public String menupost(BoardVO board, RedirectAttributes rttr) {
		
		int count = service.menuregister(board);
		
		if(count==1)
			rttr.addFlashAttribute("result", "registered");
		
		return "redirect:/board/menulist";
	}
	
	@GetMapping("/boardpost")
	public void boardpost() {
	}
	 
	@GetMapping("/boardmodify")
	public void boardmodify() {
	}
	
	@GetMapping("/menupost")
	public void menupost() {
	}
	
	@GetMapping({"/get", "/boardmodify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
	    System.out.println("✅ GET 요청 받음 - bno: " + bno);
	    BoardVO board = service.get(bno);

	    if (board == null) {
	        System.out.println("❌ 게시글을 찾을 수 없습니다.");
	    } else {
	        System.out.println("✅ 게시글 조회 성공: " + board.getTitle());
	    }

	    model.addAttribute("board", board);
	}

	@GetMapping("/menuget")
	public void menuget(@RequestParam("bno") Long bno, Model model) {
	    System.out.println("✅ GET 요청 받음 - bno: " + bno);
	    BoardVO board = service.menuget(bno);

	    if (board == null) {
	        System.out.println("❌ 게시글을 찾을 수 없습니다.");
	    } else {
	        System.out.println("✅ 게시글 조회 성공: " + board.getTitle());
	    }

	    model.addAttribute("mboard", board);
	}
	
	// POST method for modifying a board
	@PostMapping("/boardmodify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		
		// Call the service to update the board
		int count = service.modify(board);
		
		if(count == 1) {
			rttr.addFlashAttribute("result", "modified");
		}
		
		return "redirect:/board/boardlist";
	}
	
	// POST method for deleting a board
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		// Call the service to delete the board
		int count = service.remove(bno);
		
		if(count == 1) {
			rttr.addFlashAttribute("result", "removed");
		}
		
		return "redirect:/board/boardlist";
	}
	@PostMapping("/menuremove")
	public String menuremove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		// Call the service to delete the board
		int count = service.menuremove(bno);
		
		if(count == 1) {
			rttr.addFlashAttribute("result", "removed");
		}
		
		return "redirect:/board/menulist";
	}
	
	@Controller
	public class RootController {
	    @GetMapping("/")
	    public String redirectToBoardIndex() {
	        return "redirect:/board/index";
	    }
	}
}