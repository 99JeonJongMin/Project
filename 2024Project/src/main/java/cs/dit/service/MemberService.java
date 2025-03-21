package cs.dit.service;

import cs.dit.domain.MemberVO;

public interface MemberService {
	public int memreg(MemberVO member);
	public boolean authenticate(String userid, String passwd);
	public boolean isEmailExists(String email);
	public boolean isUserIdExists(String userid);
}

 