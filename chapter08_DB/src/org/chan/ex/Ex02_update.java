package org.chan.ex;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.chan.db.DBConnect;

public class Ex02_update {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = DBConnect.getConnection();
			// 김씨의 이름을 박씨로 바꾸자
			// 김씨 = 실제 데이터, 박씨 = 동적으로 변경될 값
			String sql = "update sample set name=? where name=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "박씨");
			ps.setString(2, "김씨");
			int result = ps.executeUpdate();
			if(result > 0) {
				System.out.println("데이터 수정 성공 !!");
				conn.commit();
			}else {
				System.out.println("데이터 수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps != null) ps.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
