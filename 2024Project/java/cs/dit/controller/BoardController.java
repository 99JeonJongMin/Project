package cs.dit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cs.dit.domain.BoardVO;
import cs.dit.service.BoardService;
import lombok.extern.log4j.Log4j;

@RequestMapping("/board/*")
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/list")
	public void list(Model model) {
		model.addAttribute("list", service.getList());
	}
	
	@GetMapping("/list2")
	public void list2(Model model) {
		model.addAttribute("list", service.getList2());
	}

	@GetMapping("/index")
	public void index(Model model) {
		model.addAttribute("index", service.getList());
	}
	
	@GetMapping("/login")
	public void login(Model model) {
		model.addAttribute("login", service.getList());
	}
	
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		
		int count = service.register(board);
		
		if(count==1)
			rttr.addFlashAttribute("result", "registered");
		
		return "redirect:/board/list2";
	}
	
	@GetMapping("/register")
	public void register() {
	}
	
	@GetMapping("/modifyBoard")
	public void modifyBoard() {
	}
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") Long bno, Model model) {
		model.addAttribute("board", service.get(bno));
	}
	
	// POST method for modifying a board
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		
		// Call the service to update the board
		int count = service.modify(board);
		
		if(count == 1) {
			rttr.addFlashAttribute("result", "modified");
		}
		
		return "redirect:/board/list2";
	}
	
	// POST method for deleting a board
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		
		// Call the service to delete the board
		int count = service.remove(bno);
		
		if(count == 1) {
			rttr.addFlashAttribute("result", "removed");
		}
		
		return "redirect:/board/list";
	}
	
	@Controller
	public class RootController {
	    @GetMapping("/")
	    public String redirectToBoardIndex() {
	        return "redirect:/board/index";
	    }
	}
}
