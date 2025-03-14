package cs.dit.mapper;

import org.apache.ibatis.annotations.*;
import cs.dit.domain.MemberVO;

@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO member (userid, passwd, name, email) VALUES (#{userid}, #{passwd}, #{name}, #{email})")
    int insertmember(MemberVO member);

    @Select("SELECT userid, passwd FROM member WHERE userid = #{userid} AND passwd = #{passwd}")
    MemberVO login(MemberVO member); // ✅ MemberVO 객체를 직접 받도록 수정
}
