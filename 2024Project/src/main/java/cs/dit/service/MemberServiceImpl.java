package cs.dit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import cs.dit.domain.MemberVO;
import cs.dit.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ 회원가입 (아이디 & 이메일 중복 체크)
    @Override
    public int memreg(MemberVO member) {
        if (isUserIdExists(member.getUserid())) return -1; // 👉 아이디 중복 체크
        if (isEmailExists(member.getEmail())) return -2; // 👉 이메일 중복 체크

        // 🔒 비밀번호 암호화 후 저장
        member.setPasswd(passwordEncoder.encode(member.getPasswd()));

        return mapper.insertmember(member);
    }

    // ✅ 로그인 처리
    @Override
    public boolean authenticate(String userid, String passwd) {
        MemberVO member = mapper.findByUserId(userid); // 👉 ID 기반 조회

        if (member == null) return false; // ❌ 사용자 없음

        // 🔒 암호화된 비밀번호 비교 (BCrypt의 `matches` 사용)
        return passwordEncoder.matches(passwd, member.getPasswd());
    }

    // ✅ 이메일 중복 검사
    @Override
    public boolean isEmailExists(String email) {
        return mapper.countByEmail(email) > 0;
    }

    // ✅ 아이디 중복 검사
    @Override
    public boolean isUserIdExists(String userid) {
        return mapper.countByUserId(userid) > 0;
    }

    // ✅ 아이디 개수 조회
    @Override
    public int countByUserId(String userid) {
        return mapper.countByUserId(userid);
    }
}
