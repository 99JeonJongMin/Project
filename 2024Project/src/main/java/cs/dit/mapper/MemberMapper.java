package cs.dit.mapper;

import org.apache.ibatis.annotations.*;
import cs.dit.domain.MemberVO;

@Mapper
public interface MemberMapper {

    // ✅ 회원 정보 삽입
    @Insert("INSERT INTO member (userid, passwd, name, email) VALUES (#{user_id}, #{passwd}, #{name}, #{email})")
    int insertmember(MemberVO member);

    // ✅ 로그인 시 사용자 정보 조회
    @Select("SELECT userid, passwd FROM member WHERE userid = #{user_id} AND passwd = #{passwd}")
    MemberVO login(MemberVO member);

    // ✅ 이메일 중복 체크
    @Select("SELECT COUNT(*) FROM member WHERE email = #{email}")
    int countByEmail(String email);

    // ✅ 아이디 중복 체크 추가
    @Select("SELECT COUNT(*) FROM member WHERE userid = #{user_id}")
    int countByUserId(String user_id);
    
    @Select("SELECT * FROM member WHERE userid = #{user_id}")
    MemberVO findByUserId(String user_id);
}
 