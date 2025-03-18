package cs.dit.controller; // ✅ API 전용 폴더 필요 없음!

import cs.dit.domain.MenuVO;
import cs.dit.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu") // ✅ '/api/menu' 엔드포인트 설정
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // ✅ 전체 메뉴 가져오기 (GET 요청)
    @GetMapping("/list")
    public List<MenuVO> getAllMenus() {
        return menuService.getAllMenus();
    }

    // ✅ 특정 카테고리 메뉴 가져오기 (GET 요청)
    @GetMapping("/category/{category}")
    public List<MenuVO> getMenusByCategory(@PathVariable String category) {
        return menuService.getMenusByCategory(category);
    }

    // ✅ 메뉴 추가하기 (POST 요청)
    @PostMapping("/add")
    public String addMenu(@RequestBody MenuVO menu) {
        menuService.addMenu(menu);
        return "메뉴 추가 완료!";
    }

    // ✅ 메뉴 삭제하기 (DELETE 요청)
    @DeleteMapping("/delete/{id}")
    public String deleteMenu(@PathVariable int id) {
        menuService.deleteMenu(id);
        return "메뉴 삭제 완료!";
    }
}
