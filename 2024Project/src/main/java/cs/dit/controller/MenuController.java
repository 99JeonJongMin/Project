package cs.dit.controller;

import cs.dit.domain.MenuVO;
import cs.dit.service.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    // ✅ 전체 메뉴 가져오기
    @GetMapping("/list")
    public List<MenuVO> getAllMenus() {
        return menuService.getAllMenus();
    }

    // ✅ 특정 카테고리 메뉴 가져오기
    @GetMapping("/category/{category}")
    public List<MenuVO> getMenusByCategory(@PathVariable String category) {
        return menuService.getMenusByCategory(category);
    }

    // ✅ 특정 시간대 메뉴 가져오기
    @GetMapping("/time/{time}")
    public List<MenuVO> getMenusByTime(@PathVariable String time) {
        return menuService.getMenusByTime(time);
    }

    // ✅ 새로운 메뉴 추가 (menu + menu_time)
    @PostMapping("/add")
    public String addMenu(@RequestBody MenuVO menu) {
        menuService.addMenu(menu);
        return "메뉴 추가 완료!";
    }

    // ✅ 메뉴 삭제
    @DeleteMapping("/delete/{id}")
    public String deleteMenu(@PathVariable int id) {
        menuService.deleteMenu(id);
        return "메뉴 삭제 완료!";
    }
}
