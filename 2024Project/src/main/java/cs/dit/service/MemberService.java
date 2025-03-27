package cs.dit.service;

import cs.dit.domain.MemberVO;

public interface MemberService {
	public int memreg(MemberVO member);
	public boolean authenticate(String userid, String passwd);
	public boolean isEmailExists(String email);
	public boolean isUserIdExists(String userid);
	public int countByUserId(String userid);
	public boolean isUserIdAvailable(String userid);
	MemberVO findByUserId(String userid);
	public boolean updateMember(MemberVO updatedMember, String currentPassword);
}

 