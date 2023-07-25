package com.moviement.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.moviement.container.Container;
import com.moviement.db.DBConnection;
import com.moviement.dto.Member;
import com.moviement.dto.Seat;

public class SeatDao extends Dao {
	private DBConnection dbConnection;

	public SeatDao() {
		dbConnection = Container.getDBConnection();
	}

	public int doTicketing(String movieTitle, String[] titles) {
		Member loginedMember = Container.getSession().getLoginedMember();
		String title;
		int rn = 0;

		for (int i = 0; i < titles.length; i++) {
			title = titles[i];
			String sql = String.format("INSERT INTO seats (regDate, movieTitle, title, nickName) VALUES (NOW(), '%s', '%s', '%s')",movieTitle, title,
					loginedMember.nickName);
			int rnn = dbConnection.insert(sql);
			rn += rnn;
		}
		return rn;
	}

	public List<Seat> getForPrintSeats(String nickName) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM seats "));
		sb.append(String.format("WHERE nickName = '%s' ", nickName));

		List<Seat> seats = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			seats.add(new Seat(row));
		}
		return seats;
	}

	public List<Seat> getSeats() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * FROM seats "));

		List<Seat> seats = new ArrayList<>();
		List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

		for (Map<String, Object> row : rows) {
			seats.add(new Seat(row));
		}
		return seats;
	}

	public Seat getSeat(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("SELECT * "));
		sb.append(String.format("FROM seats "));
		sb.append(String.format("WHERE id = '%d' ", id));

		Map<String, Object> row = dbConnection.selectRow(sb.toString());

		if (row.isEmpty()) {
			return null;
		}
		return new Seat(row);
	}
	
	public int deleteSeat(int id) {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("DELETE FROM seats "));
		sb.append(String.format("WHERE id = '%d' ", id));

		return dbConnection.delete(sb.toString());
	}
	
	public int deleteSeats() {
		StringBuilder sb = new StringBuilder();

		sb.append(String.format("DELETE FROM seats "));
		

		return dbConnection.delete(sb.toString());
	}
}