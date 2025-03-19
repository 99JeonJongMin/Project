package cs.dit.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;

import cs.dit.domain.BoardVO;

@Mapper // MyBatis 매퍼 어노테이션 추가
public interface BoardMapper {
    
    @Select("SELECT * FROM board")
    List<BoardVO> getList();
    
    @Select("SELECT * FROM mboard")
    List<BoardVO> getList2();
    
    @Insert("INSERT INTO board (title, content, writer) VALUES (#{title}, #{content}, #{writer})")
    int insert(BoardVO board);
    
    @Insert("INSERT INTO mboard (title, content,link, writer) VALUES (#{title}, #{content},#{link}, #{writer})")
    int menuinsert(BoardVO board);
    
    @Delete("DELETE FROM board WHERE bno = #{bno}")
    int delete(Long bno);
    
    @Select("SELECT * FROM board WHERE bno = #{bno}")
    BoardVO read(long bno);
    
    @Update("UPDATE board SET title = #{title}, content = #{content}, writer = #{writer}, updateDate = now() WHERE bno = #{bno}")
    int update(BoardVO board); 
}
