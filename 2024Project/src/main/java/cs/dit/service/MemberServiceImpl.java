package cs.dit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cs.dit.domain.MemberVO;
import cs.dit.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper mapper;

    // ✅ 회원가입 (이메일 중복 체크 포함)
    @Override
    public int memreg(MemberVO member) {
        if (isEmailExists(member.getEmail())) {
            return 0; // 이메일 중복이면 회원가입 실패
        }
        return mapper.insertmember(member);
    }

    // ✅ 로그인 처리
    @Override
    public boolean authenticate(String userid, String passwd) {
        MemberVO member = mapper.login(new MemberVO(userid, passwd));

        return member != null && member.getPasswd().equals(passwd);
    }

    // ✅ 이메일 중복 체크
    public boolean isEmailExists(String email) {
        return mapper.countByEmail(email) > 0;
    }
}
