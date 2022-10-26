package database;

public class MemberVo {

	// 로그인
	private String id;
	private String password;

	// 회원가입
	private String user_name;
	private String mail;

	public MemberVo(String id) {

		this.id = id;
	}

//로그인
	public MemberVo(String id, String password) {

		this.id = id;
		this.password = password;
	}

//회원가입
	public MemberVo(String user_name, String id, String password, String mail) {
		this.user_name = user_name;
		this.id = id;
		this.password = password;
		this.mail = mail;

	}

	public String getUser_name() {
		return user_name;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getMail() {
		return mail;
	}

	//

}