package cs.dit.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import cs.dit.domain.MemberVO;

public interface MemberMapper {
	
	@Insert("INSERT INTO member (userid, passwd, name, email, join_date) VALUES (#{userid}, #{passwd}, #{name}, #{email}, now())")
	int insertMember(MemberVO member);

	@Select("SELECT * FROM member WHERE userid = #{userid}")
	MemberVO getMemberById(String userid);

	// ✅ 이메일 중복 체크
	@Select("SELECT COUNT(*) FROM member WHERE email = #{email}")
	int countByEmail(String email);
}
