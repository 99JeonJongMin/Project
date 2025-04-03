package cs.dit.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cs.dit.domain.MemberVO;
import cs.dit.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    // ✅ 생성자 주입 방식으로 수정
    public MemberServiceImpl(MemberMapper mapper, BCryptPasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ 회원가입 (아이디 & 이메일 중복 체크)
    @Override
    public int memreg(MemberVO member) {
        if (countByUserId(member.getUserid()) > 0) return -1; // 👉 아이디 중복 체크
//        if (mapper.countByEmail(member.getEmail()) > 0) return -2; // 👉 이메일 중복 체크

        // 🔒 비밀번호 암호화 후 저장
        member.setPasswd(passwordEncoder.encode(member.getPasswd()));

        return mapper.insertmember(member);
    }
 
    // ✅ 로그인 처리
    @Override
    public boolean authenticate(String userid, String passwd) {
        MemberVO member = mapper.findByUserId(userid);
        System.out.println("🔥 findByUserId 결과: " + member);
        if (member == null) return false;

        // 🔒 암호화된 비밀번호 비교
        return passwordEncoder.matches(passwd, member.getPasswd());
    }
    
    @Override
    public boolean isUserIdAvailable(String userid) {
    	MemberVO member = mapper.findByUserId(userid);
    	System.out.println("🔥 findByUserId 결과: " + member);
        return member == null;
    }


    @Override
    public boolean isEmailExists(String email) {
        return mapper.countByEmail(email) > 0;
    }

    @Override
    public boolean isUserIdExists(String userid) {
        return mapper.countByUserId(userid) > 0;
    }

    @Override
    public int countByUserId(String userid) {
        return mapper.countByUserId(userid);
    }
    public MemberVO findByUserId(String userid) {
    	return mapper.findByUserId(userid);
    }
    public boolean updateMember(MemberVO updatedMember, String currentPassword) {
        MemberVO existingMember = mapper.findByUserId(updatedMember.getUserid());
        
        if (!passwordEncoder.matches(currentPassword, existingMember.getPasswd())) {
            return false;
        }

        // 새 비밀번호가 입력된 경우만 변경
        if (updatedMember.getPasswd() != null && !updatedMember.getPasswd().isEmpty()) {
            updatedMember.setPasswd(passwordEncoder.encode(updatedMember.getPasswd()));
        } else {
            updatedMember.setPasswd(existingMember.getPasswd());
        }

        mapper.update(updatedMember); // 실제 업데이트 실행
        return true;
    }
    
    public String resetPassword(String userid, String name, String email) {
        MemberVO member = mapper.findByUserId(userid);

        if (member != null && member.getName().equals(name) && member.getEmail().equals(email)) {
            String tempPassword = generateSimpleTempPassword(); // 쉬운 임시비번 생성
            String encoded = passwordEncoder.encode(tempPassword);
            member.setPasswd(encoded);

            mapper.updatePassword(member);
            return tempPassword; // 사용자가 볼 수 있도록 리턴
        }

        return null;
    }

    public String generateSimpleTempPassword() {
        int num = (int)(Math.random() * 9000) + 1000; // 1000~9999
        return "temp" + num;
    }


}
