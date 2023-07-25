package com.moviement.service;

import com.moviement.container.Container;
import com.moviement.dao.MemberDao;
import com.moviement.dto.Member;

public class MemberService {
	private MemberDao memberDao;

	public MemberService() {
		memberDao = Container.memberDao;
	}

	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public Member getMemberByEmail(String Email) {
		return memberDao.getMemberByEmail(Email);
	}

	public Member getMemberBynickName(String nickName) {
		return memberDao.getMemberBynickName(nickName);
	}

	public void join(String loginId, String Email, String nickName, String loginPw, String name) {
		Member member = new Member(loginId, Email, nickName, loginPw, name);
		memberDao.join(member);
	}

	public String getMemberByNameId(int memberId) {
		return null;
	}

	public void modifyEmail(String Email) {
		memberDao.modifyEmail(Email);
	}

	public void modifyLoginPw(String loginPw) {
		memberDao.modifyLoginPw(loginPw);
	}

	public void modifyNickName(String nickName) {
		memberDao.modifyNickName(nickName);
	}
	
	public void delete(int id) {
		memberDao.delete(id);
	}
}