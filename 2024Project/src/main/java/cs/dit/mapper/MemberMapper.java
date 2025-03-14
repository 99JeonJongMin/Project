package cs.dit.mapper;

import org.apache.ibatis.annotations.Mapper;

import cs.dit.domain.MemberVO;

@Mapper
public interface MemberMapper {
    public int insertmember(MemberVO member);

    // email과 passwd로 사용자 정보를 조회
    public MemberVO login(MemberVO member);
    
}
