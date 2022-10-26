
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;

//import Login.MemberDA;
import database.MemberDAO;
import database.MemberVo;
import database.PayDAO;

import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Home_Login extends JFrame {

	private MemberDAO dao;

	private JButton Login_btn;
	private JButton joinBtn;
	private JButton moreBtn;
	private JPasswordField password_Field;
	private JTextField tfMsg;
	private JTextField iD_field;
	private JLabel backgroud;
	private JPanel more_Aboutme;
	private JLabel more_aboutMe_img;

	public static String getId_check() {
		return id_check;
	}

	public static void setId_check(String id_check) {
		Home_Login.id_check = id_check;
	}

	private final String correct_id = "green";
	private final String correct_pwd = "green1234";

	static String id_check;


	public Home_Login() {

		dao = new MemberDAO();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Home_Login.class.getResource("/img/app_icon.png")));
		setVisible(true);

		setFont(new Font("배달의민족 주아", Font.PLAIN, 12));
		setTitle("오! 출석 - 학생관리시스템 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 752);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		getContentPane().setLayout(null);

		JPanel Main_home = new JPanel();
		Main_home.setBounds(0, 0, 450, 709);
		getContentPane().add(Main_home);
		Main_home.setLayout(null);

		joinBtn = new JButton("\uD68C\uC6D0\uAC00\uC785 \uD558\uAE30");
		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignUp s = new SignUp();
				s.setVisible(true);
			}
		});

		joinBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		joinBtn.setBackground(new Color(204, 204, 255));
		joinBtn.setBounds(147, 623, 170, 42);
		Main_home.add(joinBtn);

		moreBtn = new JButton("\uC790\uC138\uD788...");
		moreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		moreBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
		moreBtn.setBackground(new Color(153, 153, 204));
		moreBtn.setBounds(354, 684, 84, 23);
		Main_home.add(moreBtn);

		// 아이디입력
		iD_field = new JTextField(10); // 10글자 제한
		iD_field.setForeground(Color.WHITE);
		iD_field.setBackground(Color.DARK_GRAY);
		iD_field.setBounds(77, 530, 214, 21);
		Main_home.add(iD_field);
		iD_field.setColumns(10);

		// 패스워드입력
		password_Field = new JPasswordField(10); // 10글자 제한
		password_Field.setForeground(Color.WHITE);
		password_Field.setBackground(Color.DARK_GRAY);
		password_Field.setBounds(77, 561, 214, 21);
		password_Field.setEchoChar('●');
		Main_home.add(password_Field);

		Login_btn = new JButton("\uB85C \uADF8 \uC778");
		Login_btn.addActionListener(new ActionListener() {

			// 아이디 입력했는지? 비밀번호 입력했는지? 확인하는 문구
			public void actionPerformed(ActionEvent e) {

				if (iD_field.getText().equals("")) {
					tfMsg.setText("ID를 입력하세요.");
				} else if (password_Field.equals("")) {
					tfMsg.setText("Password를 입력하세요.");
				} else {
					MemberVo vo = new MemberVo(iD_field.getText(), password_Field.getText());

					// 로그인에 성공하면 화면으로 넘어가고 아니라면 다시 입력하라고하기
					boolean b = dao.list(vo);
					if (b == true) {

						id_check = iD_field.getText();
						tfMsg.setText("로그인 되었습니다.");
						dispose();
						attendance_Main attendance = new attendance_Main(); // 홈화면 호출
						attendance.setVisible(true);

					} else if (b == false) {
						tfMsg.setText("아이디/비밀번호가 틀렸습니다. 다시 입력해주세요.");

					}
				}
			}

		});
		Login_btn.setForeground(Color.BLACK);
		Login_btn.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
		Login_btn.setBackground(new Color(216, 191, 216));
		Login_btn.setBounds(303, 530, 97, 52);
		Main_home.add(Login_btn);

		// 로그인 비밀번호 맞는지 문구 띄워주는 필드
		tfMsg = new JTextField(40);
		tfMsg.setText("오! 출석 - 학생관리시스템에 오신걸 환영합니다.");
		tfMsg.setForeground(new Color(255, 0, 0));
		tfMsg.setBackground(new Color(250, 250, 210));
		tfMsg.setHorizontalAlignment(SwingConstants.CENTER);
		tfMsg.setBounds(77, 592, 323, 21);
		Main_home.add(tfMsg);
		tfMsg.setColumns(10);
		tfMsg.setEditable(false);
		tfMsg.setFocusable(false);

		// 저작권 라벨
		JLabel copyright_SYG_Label = new JLabel("  Copyright 2022.송유진 All rights reserved.");
		copyright_SYG_Label.setHorizontalAlignment(SwingConstants.CENTER);
		copyright_SYG_Label.setOpaque(true); // Opaque값을 true로 미리 설정해 주어야 배경색이 적용된다.
		copyright_SYG_Label.setBackground(new Color(204, 204, 255));
		copyright_SYG_Label.setBounds(0, 682, 450, 27);
		Main_home.add(copyright_SYG_Label);

		backgroud = new JLabel("");
		backgroud.setIcon(new ImageIcon(Home_Login.class.getResource("/img/login_backImg.png")));
		backgroud.setBounds(0, 0, 450, 713);
		Main_home.add(backgroud);

		more_Aboutme = new JPanel();
		more_Aboutme.setBounds(0, 0, 450, 709);
		getContentPane().add(more_Aboutme);
		more_Aboutme.setLayout(null);
		more_Aboutme.setVisible(false); // 자세히 페이지 안보이게

		JButton backBtn = new JButton("\uB4A4\uB85C\uAC00\uAE30");

		backBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				more_Aboutme.setVisible(false); // 자세히 페이지 안보이게
				Main_home.setVisible(true);

			}
		});
		backBtn.setBackground(new Color(204, 204, 255));
		backBtn.setFont(new Font("배달의민족 주아", Font.BOLD, 13));
		backBtn.setBounds(350, 5, 97, 23);
		more_Aboutme.add(backBtn);

		more_aboutMe_img = new JLabel("");
		more_aboutMe_img.setBounds(0, 0, 450, 713);
	}
}