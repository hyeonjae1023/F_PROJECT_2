package com.moviement.dao;

import java.util.Map;

import com.moviement.container.Container;
import com.moviement.db.DBConnection;
import com.moviement.dto.Member;

public class MemberDao {
	private DBConnection dbConnection;

	public MemberDao() {
		dbConnection = Container.getDBConnection();
	}
	
	public int join(Member member) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("INSERT INTO `member` "));
		sb.append(String.format("SET regDate = NOW(), "));
		sb.append(String.format("updateDate = NOW(), "));
		sb.append(String.format("loginId = '%s', ", member.loginId));
		sb.append(String.format("loginPw = '%s', ", member.loginPw));
		sb.append(String.format("Email = '%s', ", member.eMail));
		sb.append(String.format("nickName = '%s', ", member.nickName));
		sb.append(String.format("`name` = '%s' ", member.name));

		return dbConnection.insert(sb.toString());
	}
	
	public Member getMemberByLoginId(String loginId) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `member` "));
		sb.append(String.format("WHERE loginId = '%s' ", loginId));

		Map<String, Object> memberRow = dbConnection.selectRow(sb.toString());

		if (memberRow.isEmpty()) {
			return null;
		}
		return new Member(memberRow);
	}
	
	public Member getMemberByEmail(String Email) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `member` "));
		sb.append(String.format("WHERE Email = '%s' ", Email));

		Map<String, Object> memberRow = dbConnection.selectRow(sb.toString());

		if (memberRow.isEmpty()) {
			return null;
		}
		return new Member(memberRow);
	}
	
	public Member getMemberBynickName(String nickName) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM `member` "));
		sb.append(String.format("WHERE nickName = '%s' ", nickName));

		Map<String, Object> memberRow = dbConnection.selectRow(sb.toString());

		if (memberRow.isEmpty()) {
			return null;
		}
		return new Member(memberRow);
	}

	public int modifyEmail(int id, String Email) { //modify(int id, String Email)
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("UPDATE `member` "));  //UPDATE `member`
		sb.append(String.format("SET updateDate = NOW(), ")); // SET updateDate = NOW(), ""
		sb.append(String.format("Email = '%s' ", Email)); // Email = '%s', ",Email
		sb.append(String.format("WHERE id = '%d' ", id));
		return dbConnection.update(sb.toString());
	}

	public int modifyLoginPw(int id, String loginPw) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("UPDATE `member` "));  //UPDATE `member`
		sb.append(String.format("SET updateDate = NOW(), ")); // SET updateDate = NOW(), ""
		sb.append(String.format("loginPw = '%s' ", loginPw)); // Email = '%s', ",Email
		sb.append(String.format("WHERE id = '%d' ", id));
		return dbConnection.update(sb.toString());
	}

	public int modifyNickName(int id, String nickName) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("UPDATE `member` "));  //UPDATE `member`
		sb.append(String.format("SET updateDate = NOW(), ")); // SET updateDate = NOW(), ""
		sb.append(String.format("nickName = '%s' ", nickName)); // Email = '%s', ",Email
		sb.append(String.format("WHERE id = '%d' ", id));
		return dbConnection.update(sb.toString());
	}

	public int delete(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("DELETE FROM `member` "));
		sb.append(String.format("WHERE id = '%d' ", id));

		return dbConnection.delete(sb.toString());
	}
}