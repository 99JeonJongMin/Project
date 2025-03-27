package cs.dit.service;

import cs.dit.domain.MemberVO;

public interface MemberService {
	public int memreg(MemberVO member);
	public boolean authenticate(String user_id, String passwd);
	public boolean isEmailExists(String email);
	public boolean isUserIdExists(String user_id);
	public int countByUserId(String user_id);
	public boolean isUserIdAvailable(String user_id);
	MemberVO findByUserId(String user_id);
	public boolean updateMember(MemberVO updatedMember, String currentPassword);
}

 