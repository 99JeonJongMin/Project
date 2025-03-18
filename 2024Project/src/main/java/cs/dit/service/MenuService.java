package cs.dit.service;

import cs.dit.domain.MenuVO;
import cs.dit.mapper.MenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private final MenuMapper menuMapper;

    public MenuService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    public List<MenuVO> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    public List<MenuVO> getMenusByCategory(String category) {
        return menuMapper.getMenusByCategory(category);
    }

    public int addMenu(MenuVO menu) {
        return menuMapper.insertMenu(menu);
    }

    public int deleteMenu(int id) {
        return menuMapper.deleteMenu(id);
    }
}
