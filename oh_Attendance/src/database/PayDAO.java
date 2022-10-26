package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class PayDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green";
	String password = "green1234";

	private Connection con;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement ps;
	private Statement st;

	// [pay] 결제 정보 넘겨주기
	public boolean pay(PayVo p) {
		try {
			connDB();
			String query = "INSERT INTO PAYMENT(stuNumber, payment_date, payment_amount) " + "values('"
					+ p.getStuNumber() + "','" + p.getPayment_date() + "','" + p.getPayment_amount() + "')";
			rs = stmt.executeQuery(query);

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

	// [결제관리] 납부자 확인하기
	public String[][] pay_stulList(String day) {

		try {
			connDB();

			String query = "SELECT  s.stuNumber, s.stuname, s.AGE, p.payment_date, p.payment_amount, s.address,s.GUARDIAN1 ,s.GUARDIAN1_CALL"
					+ "	FROM STUDENT s" + "	LEFT OUTER JOIN  PAYMENT p" + "	ON s.STUNUMBER = p.STUNUMBER"
					+ " WHERE payment_date LIKE '%" + day + "%' ORDER BY STUNAME";

			PreparedStatement statement = con.prepareStatement(
					"s.stuNumber, s.stuName, s.AGE,p.payment_date, p.payment_amount, s.address,s.GUARDIAN1 ,s.GUARDIAN1_CALL FROM student s, PAYMENT p");
			ResultSet results = statement.executeQuery(query); // 쿼리 실행 결과를 받아야하기때문에 데이터베이스에 접속, 그걸

			ArrayList<String[]> list = new ArrayList<String[]>();
			while (results.next()) {
				list.add(new String[] { results.getString("stuNumber"), results.getString("stuName"),
						results.getString("age"), results.getString("payment_date"),
						results.getString("payment_amount"), results.getString("address"),
						results.getString("GUARDIAN1"), results.getString("GUARDIAN1_CALL") });
			}
			String[][] arr = new String[list.size()][9];
			return list.toArray(arr);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("pay_stulList 실패");
			return null;
		}
	}

	

	// [결제관리] [미납자] 확인하기
	public String[][] did_not_pay(String day, String last) {

		try {
			connDB();

			String query = "SELECT stuNumber, stuname, AGE, payment_date, payment_amount, address, GUARDIAN1 , GUARDIAN1_CALL"
					+ " FROM STUDENT S LEFT OUTER JOIN PAYMENT P" + " USING(STUNUMBER)" + " MINUS"
					+ " SELECT  stuNumber, stuname, AGE, payment_date, payment_amount, address, GUARDIAN1 , GUARDIAN1_CALL"
					+ " FROM STUDENT s" + " LEFT OUTER JOIN  PAYMENT p"
					+ " using(stuNumber) WHERE p.PAYMENT_DATE LIKE '%" + day + "%'" + " or p.PAYMENT_DATE NOT LIKE '%"
					+ last + "%'";

			PreparedStatement statement = con.prepareStatement(
					"stuNumber, stuname, AGE, payment_date, payment_amount, address, GUARDIAN1 , GUARDIAN1_CALL"
							+ " FROM STUDENT S LEFT OUTER JOIN PAYMENT P");
			ResultSet results = statement.executeQuery(query); // 쿼리 실행 결과를 받아야하기때문에 데이터베이스에 접속, 그걸

			ArrayList<String[]> list = new ArrayList<String[]>();
			while (results.next()) {
				list.add(new String[] { results.getString("stuNumber"), results.getString("stuName"),
						results.getString("age"), results.getString("payment_date"),
						results.getString("payment_amount"), results.getString("address"),
						results.getString("GUARDIAN1"), results.getString("GUARDIAN1_CALL") });
			}
			String[][] arr = new String[list.size()][9];
			return list.toArray(arr);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("did_not_pay 실패");
			return null;
		}
	}

	// [통계] 월별 수입 통계
	public int sum_pay(String month) {
		ArrayList<PayVo> list = new ArrayList<PayVo>();
		try {
			// 연결
			connDB();
			// SQL 문장 전송
			String sql = "SELECT sum(PAYMENT_AMOUNT)" + "FROM PAYMENT p" + " WHERE PAYMENT_DATE like '%" + month + "%'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			rs.next();
			rs.getString(1);
			System.out.println(rs.getString(1));
			return rs.getInt(1); // 쿼리문 결과값을 반환해주면 간단하다.

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.getStackTrace();
		} finally {
			dbClose();
		}
		return 0;
	}

	
		
	public void connDB() {
		try {
			Class.forName(driver);
			System.out.println("jdbc driver loading success.");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("oracle connection success.\n");
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			System.out.println("statement create success.\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// [출석화면] [수납자] 이번달 수납한 수강생들 리스트

	/**
	 * DB닫기 기능 메소드
	 */
	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}// dbClose() ---

	public static void main(String[] args) {
		PayDAO dao = new PayDAO();
		dao.sum_pay("2022-7");

	}

}