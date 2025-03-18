package cs.dit.mapper;

import cs.dit.domain.MenuVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {

    // ✅ 모든 메뉴 가져오기
    @Select("SELECT * FROM menu")
    List<MenuVO> getAllMenus();

    // ✅ 특정 카테고리별 메뉴 가져오기
    @Select("SELECT * FROM menu WHERE category = #{category}")
    List<MenuVO> getMenusByCategory(String category);

    // ✅ 새로운 메뉴 추가하기
    @Insert("INSERT INTO menu (name, category, time, video_url) VALUES (#{name}, #{category}, #{time}, #{videoUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMenu(MenuVO menu);

    // ✅ 메뉴 삭제하기
    @Delete("DELETE FROM menu WHERE id = #{id}")
    int deleteMenu(int id);
}
