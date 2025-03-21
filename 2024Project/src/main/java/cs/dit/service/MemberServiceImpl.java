package cs.dit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cs.dit.domain.MemberVO;
import cs.dit.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper mapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ✅ 회원가입
    @Override
    public int memreg(MemberVO member) {
        if (isUserIdExists(member.getUserid())) return -1;
        if (isEmailExists(member.getEmail())) return -2;

        // 👉 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(member.getPasswd());
        member.setPasswd(encodedPassword);

        return mapper.insertmember(member);
    }

    // ✅ 로그인 처리
    @Override
    public boolean authenticate(String userid, String passwd) {
        MemberVO member = mapper.findByUserId(userid); // login() 말고 userid만으로 찾기

        // 👉 비밀번호 비교 (matches)
        return member != null && passwordEncoder.matches(passwd, member.getPasswd());
    }

    @Override
    public boolean isEmailExists(String email) {
        return mapper.countByEmail(email) > 0;
    }

    @Override
    public boolean isUserIdExists(String userid) {
        return mapper.countByUserId(userid) > 0;
    }
}
