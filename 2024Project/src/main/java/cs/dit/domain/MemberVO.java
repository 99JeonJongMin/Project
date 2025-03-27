package cs.dit.domain;
import java.sql.Date;
import lombok.Data;
@Data
public class MemberVO {
	private String user_id, passwd, name,email;
	private Date date;
	
	public String delete(String user_id) {
		// TODO Auto-generated method stub
		return null;
	}
	// 기본 생성자 추가
    public MemberVO() {}
    
	public static MemberVO read(String user_id) {
		// TODO Auto-generated method stub
		return null; 
	}
	
	 public MemberVO(String user_id, String passwd, String name, String email) {
	        this.user_id = user_id;
	        this.passwd = passwd;
	        this.name = name;
	        this.email = email;
	    }
	 
	 public MemberVO(String user_id, String passwd) {
		 this.user_id = user_id;  
		 this.passwd = passwd;
	        
	    }
	public String getPasswd() {
		
		return this.passwd;
	}
}
