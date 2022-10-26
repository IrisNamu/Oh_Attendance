package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green";
	String password = "green1234";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;

//로그인
	public boolean list(MemberVo p) {
		try {
			connDB();

			String query = "SELECT * FROM login WHERE id='" + p.getId() + "' AND password='" + p.getPassword() + "'";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow());

			if (rs.getRow() == 0) {
				System.out.println("0 row selected...");
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}


	
	// 아이디 중복 확인 //메서드
	public boolean IDCheck(String id) { // 회원정보 id를 받기
		try {
			connDB(); // 디비랑 연결을 해준다.

			String query = "SELECT * FROM login WHERE id='" + id + "'";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			rs.last();
			System.out.println("rs.getRow() : " + rs.getRow()); // getRow 열과번호. a가 몇번재? 4번으로 출력
			// 검색안되면 0이된다. 번호가 1부터 시작 ....
			if (rs.getRow() == 0) {
				System.out.println("0 row selected..."); // 0이면 없는걸로 취급돼서
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	// 회원가입
	public boolean SignUp(MemberVo p) {
		try {
			connDB();

			String query = "INSERT INTO login(user_name, id, password, mail) " + "values('" + p.getUser_name() + "','"
					+ p.getId() + "','" + p.getPassword() + "','" + p.getMail() + "')";
			System.out.println("SQL : " + query);
			rs = stmt.executeQuery(query);
			System.out.println("rs.getRow() : " + rs.getRow());

			if (rs.getRow() == 0) {
				System.out.println("0 row selected...");
			} else {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public void connDB() {
		try {
			Class.forName(driver);
			//System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			//System.out.println("oracle connection success.\n");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//System.out.println("statement create success.\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}