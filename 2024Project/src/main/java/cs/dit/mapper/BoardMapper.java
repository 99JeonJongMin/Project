package cs.dit.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cs.dit.domain.BoardVO;

@Mapper
public interface BoardMapper {
	public List<BoardVO> getList();
	
	public List<BoardVO> getList2();
	
	public int insert(BoardVO board);
	
	public int delete(Long bno);
	
	public BoardVO read(long bno);
	
	public int update(BoardVO board); 
		
}
