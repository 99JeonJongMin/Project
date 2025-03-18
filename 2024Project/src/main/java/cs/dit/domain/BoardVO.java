package cs.dit.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {
	private int bno;
	private String title, content, writer;
	private Date regdate, updatedate;
	public String delete(long bno) {
		// TODO Auto-generated method stub
		return null;
	}
	public static BoardVO read(long bno) {
		// TODO Auto-generated method stub
		return null;
	}
}
