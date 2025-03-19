package cs.dit.mapper;

import cs.dit.domain.MenuVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MenuMapper {

    // ✅ 모든 메뉴 가져오기 (JOIN으로 menu_time 포함)
    @Select("SELECT m.*, GROUP_CONCAT(mt.time) AS times " +
            "FROM menu m " +
            "LEFT JOIN menu_time mt ON m.id = mt.menu_id " +
            "GROUP BY m.id")
    @Results({
            @Result(property = "times", column = "times", javaType = List.class, typeHandler = cs.dit.utils.StringToListHandler.class)
    })
    List<MenuVO> getAllMenus();

    // ✅ 특정 카테고리 메뉴 가져오기 (JOIN 사용)
    @Select("SELECT m.*, GROUP_CONCAT(mt.time) AS times " +
            "FROM menu m " +
            "LEFT JOIN menu_time mt ON m.id = mt.menu_id " +
            "WHERE m.category = #{category} " +
            "GROUP BY m.id")
    @Results({
            @Result(property = "times", column = "times", javaType = List.class, typeHandler = cs.dit.utils.StringToListHandler.class)
    })
    List<MenuVO> getMenusByCategory(String category);

    // ✅ 새로운 메뉴 추가 (menu + menu_time)
    @Insert("INSERT INTO menu (name, category, video_url) VALUES (#{name}, #{category}, #{videoUrl})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMenu(MenuVO menu);

    // ✅ 추가된 menu_time 저장
    @Insert("<script>" +
            "INSERT INTO menu_time (menu_id, time) VALUES " +
            "<foreach collection='times' item='time' separator=','>" +
            "(#{menuId}, #{time})" +
            "</foreach>" +
            "</script>")
    void insertMenuTimes(@Param("menuId") int menuId, @Param("times") List<String> times);

    // ✅ 메뉴 삭제 (menu_time도 함께 삭제됨)
    @Delete("DELETE FROM menu WHERE id = #{id}")
    int deleteMenu(int id);

    // ✅ 특정 시간대의 메뉴 가져오기
    @Select("SELECT m.*, GROUP_CONCAT(mt.time) AS times " +
            "FROM menu m " +
            "JOIN menu_time mt ON m.id = mt.menu_id " +
            "WHERE mt.time = #{time} " +
            "GROUP BY m.id")
    @Results({
            @Result(property = "times", column = "times", javaType = List.class, typeHandler = cs.dit.utils.StringToListHandler.class)
    })
    List<MenuVO> getMenusByTime(String time);
}
