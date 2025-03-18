package cs.dit.mapper;

import org.apache.ibatis.annotations.*;
import cs.dit.domain.MemberVO;

@Mapper
public interface MemberMapper {

    // ✅ 회원 정보 삽입
    @Insert("INSERT INTO member (userid, passwd, name, email) VALUES (#{userid}, #{passwd}, #{name}, #{email})")
    int insertmember(MemberVO member);

    // ✅ 로그인 시 사용자 정보 조회
    @Select("SELECT userid, passwd FROM member WHERE userid = #{userid} AND passwd = #{passwd}")
    MemberVO login(MemberVO member); // ✅ MemberVO 객체 반환

    // ✅ 이메일 중복 확인
    @Select("SELECT COUNT(*) FROM member WHERE email = #{email}")
    int countByEmail(String email);
}
