package org.chan.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.chan.db.DBConnect;

public class Ex04_select {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;	// select에서만 사용하는 객체
		
		try {
			conn = DBConnect.getConnection();
			String sql = "select * from sample";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();	// select의 리턴 타입
			// 언제나 정렬된 순서로 나오는게 아님
			while(rs.next()) {
				System.out.print(rs.getInt(1) + ", ");
				System.out.print(rs.getString(2) + ", ");
				System.out.println(rs.getDate(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
