
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.time.LocalDate;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import database.StudentDAO;
import database.StudentVo;

@SuppressWarnings("serial")
public class addStudentPage extends JFrame {
	private StudentDAO dao;

	private JPanel contentPane;
	private JTextField s_name;
	private JTextField s_memo;
	private JTextField s_call_num;
	private JTextField s_guardian1;
	private JTextField s_guardian2;
	private JTextField address;
	private JTextField stu_num;
	private JTextField s_guardian1_call_;
	private JTextField s_guardian2_call;
	private JTextField s_school;
	private JTextField s_grade;
	private JTextField s_class;
	private File selectedFile;
	private String age;
	private String birth;
	private String enter_date;
	private String grade;
	private String current_date;
	private String when_daycome = "";
	private String filePath = "";

	public addStudentPage() {
		dao = new StudentDAO();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home_Login.class.getResource("/img/app_icon.png")));

		setTitle("\uC624!\uCD9C\uC11D - \uD559\uC0DD \uC815\uBCF4 \uB4F1\uB85D\uD558\uAE30");
		setBounds(100, 100, 510, 820);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton backBtn = new JButton("<");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				management_Student manage = new management_Student();
				manage.setVisible(true);
			}
		});

		backBtn.setForeground(Color.WHITE);
		backBtn.setFont(new Font("배달의민족 주아", Font.BOLD, 17));
		backBtn.setBackground(new Color(128, 128, 128));
		backBtn.setBounds(429, 0, 65, 58);
		contentPane.add(backBtn);

		// 학생이름
		s_name = new JTextField();
		s_name.setForeground(new Color(128, 0, 0));
		s_name.setFont(new Font("굴림", Font.PLAIN, 14));
		s_name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_name.getText().equals(" * 이름을 입력해주세요.")) {
					s_name.setText("");
					s_name.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_name.getText().equals("")) {
					s_name.setText(" * 이름을 입력해주세요.");
					s_name.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_name.setText(" * 이름을 입력해주세요.");
		s_name.setBounds(204, 107, 155, 29);
		contentPane.add(s_name);
		s_name.setColumns(10);

		// 성별 체크
		JRadioButton s_boy_check = new JRadioButton("남");
		s_boy_check.setFont(new Font("굴림", Font.PLAIN, 16));
		s_boy_check.setBounds(367, 109, 45, 23);
		contentPane.add(s_boy_check);
		s_boy_check.setSelected(true); // 남자선택 기본값

		s_boy_check.setActionCommand(s_boy_check.getText());
		JRadioButton s_girl_check = new JRadioButton("여");
		s_girl_check.setFont(new Font("굴림", Font.PLAIN, 16));
		s_girl_check.setBounds(415, 109, 45, 23);
		contentPane.add(s_girl_check);
		s_girl_check.setActionCommand(s_girl_check.getText());

		ButtonGroup gender_group = new ButtonGroup();

		gender_group.add(s_boy_check);
		gender_group.add(s_girl_check);

		// 학교이름
		s_school = new JTextField();
		s_school.setForeground(new Color(112, 128, 144));
		s_school.setFont(new Font("굴림", Font.PLAIN, 14));
		s_school.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_school.getText().equals(" * 학교 입력란 예) OO초")) {
					s_school.setText("");
					s_school.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_school.getText().equals("")) {
					s_school.setText(" * 학교 입력란 예) OO초");
					s_school.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_school.setText(" * 학교 입력란 예) OO초");
		s_school.setBounds(204, 194, 240, 29);
		contentPane.add(s_school);
		s_school.setColumns(10);

		s_grade = new JTextField();
		s_grade.setFont(new Font("굴림", Font.PLAIN, 14));
		s_grade.setForeground(new Color(112, 128, 144));
		s_grade.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_grade.getText().equals(" * 학년 예) 초4, 고2")) {
					s_grade.setText("");
					s_grade.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_grade.getText().equals("")) {
					s_grade.setText(" * 학년 예) 초4, 고2");
					s_grade.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_grade.setText(" * 학년 예) 초4, 고2");
		s_grade.setColumns(10);
		s_grade.setBounds(204, 242, 146, 29);
		contentPane.add(s_grade);

		s_class = new JTextField();
		s_class.setForeground(new Color(112, 128, 144));
		s_class.setFont(new Font("굴림", Font.PLAIN, 14));
		s_class.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_class.getText().equals(" * OO반")) {
					s_class.setText("");
					s_class.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_class.getText().equals("")) {
					s_class.setText(" * OO반");
					s_class.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_class.setText(" * OO반");
		s_class.setColumns(10);
		s_class.setBounds(358, 242, 86, 29);
		contentPane.add(s_class);

		// 등원요일
		JCheckBox s_mon = new JCheckBox("월");
		s_mon.setFont(new Font("굴림", Font.PLAIN, 15));
		s_mon.setHorizontalAlignment(SwingConstants.TRAILING);
		s_mon.setBounds(108, 380, 45, 34);
		contentPane.add(s_mon);
		s_mon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_mon.getText() + " ");

			}
		});

		JCheckBox s_tue = new JCheckBox("화");
		s_tue.setFont(new Font("굴림", Font.PLAIN, 15));
		s_tue.setHorizontalAlignment(SwingConstants.CENTER);
		s_tue.setBounds(157, 379, 43, 37);
		contentPane.add(s_tue);
		s_tue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_tue.getText() + " ");
			}
		});

		JCheckBox s_wed = new JCheckBox("수");
		s_wed.setFont(new Font("굴림", Font.PLAIN, 15));
		s_wed.setHorizontalAlignment(SwingConstants.CENTER);
		s_wed.setBounds(204, 378, 43, 39);
		contentPane.add(s_wed);
		s_wed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_wed.getText() + " ");
			}
		});

		JCheckBox s_thur = new JCheckBox("목");
		s_thur.setFont(new Font("굴림", Font.PLAIN, 15));
		s_thur.setHorizontalAlignment(SwingConstants.CENTER);
		s_thur.setBounds(251, 383, 41, 29);
		contentPane.add(s_thur);
		s_thur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_thur.getText() + " ");
			}
		});

		JCheckBox s_fri = new JCheckBox("금");
		s_fri.setFont(new Font("굴림", Font.PLAIN, 15));
		s_fri.setHorizontalAlignment(SwingConstants.CENTER);
		s_fri.setBounds(296, 378, 43, 39);
		contentPane.add(s_fri);
		s_fri.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_fri.getText() + " ");
			}
		});

		JCheckBox s_sat = new JCheckBox("토");
		s_sat.setFont(new Font("굴림", Font.PLAIN, 15));
		s_sat.setHorizontalAlignment(SwingConstants.CENTER);
		s_sat.setBounds(341, 383, 46, 29);
		contentPane.add(s_sat);
		s_sat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_sat.getText() + " ");
			}
		});

		JCheckBox s_sun = new JCheckBox("일");
		s_sun.setFont(new Font("굴림", Font.PLAIN, 15));
		s_sun.setHorizontalAlignment(SwingConstants.LEFT);
		s_sun.setBounds(391, 383, 43, 29);
		contentPane.add(s_sun);
		s_sun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_sun.getText() + " ");
			}
		});

		// 학생 전화번호
		s_call_num = new JTextField();
		s_call_num.setForeground(new Color(112, 128, 144));
		s_call_num.setFont(new Font("굴림", Font.PLAIN, 14));
		s_call_num.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (s_call_num.getText().equals(" * 학생 전화번호를 입력해주세요. 예) 010-0000-0000")) {
					s_call_num.setText("");
					s_call_num.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_call_num.getText().equals("")) {
					s_call_num.setText(" * 학생 전화번호를 입력해주세요. 예) 010-0000-0000");
					s_call_num.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_call_num.setText(" * 학생 전화번호를 입력해주세요. 예) 010-0000-0000");
		s_call_num.setBounds(52, 559, 382, 34);
		contentPane.add(s_call_num);
		s_call_num.setColumns(10);

		// 보호자1 관계 콤보박스
		JComboBox<String> s_who_guardian1 = new JComboBox<String>();
		s_who_guardian1.setBounds(52, 613, 86, 23);
		contentPane.add(s_who_guardian1);
		s_who_guardian1.addItem(" 미선택 ");
		s_who_guardian1.addItem(" (모)");
		s_who_guardian1.addItem(" (부)");
		s_who_guardian1.addItem(" (조모)");
		s_who_guardian1.addItem(" (조부)");
		s_who_guardian1.addItem(" (기타)");
		s_who_guardian1.getSelectedItem();

		// 보호자1 관계 콤보박스
		JComboBox<String> s_who_guardian2 = new JComboBox<String>();
		s_who_guardian2.setBounds(52, 652, 86, 23);
		contentPane.add(s_who_guardian2);
		s_who_guardian2.addItem(" 미선택 ");
		s_who_guardian2.addItem(" (모)");
		s_who_guardian2.addItem(" (부)");
		s_who_guardian2.addItem(" (조모)");
		s_who_guardian2.addItem(" (조부)");
		s_who_guardian2.addItem(" (기타)");
		s_who_guardian2.getSelectedItem();

		// 보호자1 성함
		s_guardian1 = new JTextField();
		s_guardian1.setForeground(new Color(112, 128, 144));
		s_guardian1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_guardian1.getText().equals(" * 보호자1 성함")) {
					s_guardian1.setText("");
					s_guardian1.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_guardian1.getText().equals("")) {
					s_guardian1.setText(" * 보호자1 성함");
					s_guardian1.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_guardian1.setText(" * 보호자1 성함");
		s_guardian1.setColumns(10);
		s_guardian1.setBounds(152, 610, 93, 29);
		contentPane.add(s_guardian1);

		// 보호자 2 성함
		s_guardian2 = new JTextField();
		s_guardian2.setForeground(new Color(112, 128, 144));
		s_guardian2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_guardian2.getText().equals(" * 보호자2 성함")) {
					s_guardian2.setText("");
					s_guardian2.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_guardian2.getText().equals("")) {
					s_guardian2.setText(" * 보호자2 성함");
					s_guardian2.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_guardian2.setText(" * 보호자2 성함");
		s_guardian2.setColumns(10);
		s_guardian2.setBounds(152, 649, 93, 29);
		contentPane.add(s_guardian2);

		// 학생주소 입력필드
		address = new JTextField();
		address.setForeground(new Color(112, 128, 144));
		address.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (address.getText().equals(" * 주소를 입력해주세요. 예) 00아파트 000동 000호")) {
					address.setText("");
					address.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (address.getText().equals("")) {
					address.setText(" * 주소를 입력해주세요. 예) 00아파트 000동 000호");
					address.setForeground(new Color(153, 153, 153));
				}
			}
		});
		address.setText(" * 주소를 입력해주세요. 예) 00아파트 000동 000호");
		address.setFont(new Font("굴림", Font.PLAIN, 14));
		address.setColumns(10);
		address.setBounds(40, 292, 350, 29);
		contentPane.add(address);

		// 학생 출석번호 4자리 설정 필드
		stu_num = new JTextField();
		stu_num.setForeground(new Color(165, 42, 42));
		stu_num.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (stu_num.getText().equals("* 출석번호 4자리(숫자)")) {
					stu_num.setText("");
					stu_num.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (stu_num.getText().equals("")) {
					stu_num.setText("* 출석번호 4자리(숫자)");
					stu_num.setForeground(new Color(153, 153, 153));
				}
			}
		});
		stu_num.setText("* 출석번호 4자리(숫자)");
		stu_num.setFont(new Font("굴림", Font.PLAIN, 14));
		stu_num.setColumns(10);
		stu_num.setBounds(157, 331, 163, 29);
		contentPane.add(stu_num);

		// 보호자1 전화번호
		s_guardian1_call_ = new JTextField();
		s_guardian1_call_.setForeground(new Color(112, 128, 144));
		s_guardian1_call_.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (s_guardian1_call_.getText().equals(" * phone 예)010-0000-0000")) {
					s_guardian1_call_.setText("");
					s_guardian1_call_.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_guardian1_call_.getText().equals("")) {
					s_guardian1_call_.setText(" * phone 예)010-0000-0000");
					s_guardian1_call_.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_guardian1_call_.setFont(new Font("굴림", Font.PLAIN, 13));
		s_guardian1_call_.setText(" * phone 예)010-0000-0000");
		s_guardian1_call_.setColumns(10);
		s_guardian1_call_.setBounds(257, 610, 177, 29);
		contentPane.add(s_guardian1_call_);

		// 보호자2 전화번호
		s_guardian2_call = new JTextField();
		s_guardian2_call.setForeground(new Color(112, 128, 144));
		s_guardian2_call.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_guardian2_call.getText().equals(" * phone 예)010-0000-0000")) {
					s_guardian2_call.setText("");
					s_guardian2_call.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_guardian2_call.getText().equals("")) {
					s_guardian2_call.setText(" * phone 예)010-0000-0000");
					s_guardian2_call.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_guardian2_call.setFont(new Font("굴림", Font.PLAIN, 13));
		s_guardian2_call.setText(" * phone 예)010-0000-0000");
		s_guardian2_call.setColumns(10);
		s_guardian2_call.setBounds(257, 649, 177, 29);
		contentPane.add(s_guardian2_call);

		// 맨 위 라벨
		JLabel copyright_SYG_Label = new JLabel("오! 출석 - 학생 정보 추가");
		copyright_SYG_Label.setForeground(Color.WHITE);
		copyright_SYG_Label.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		copyright_SYG_Label.setOpaque(true);
		copyright_SYG_Label.setHorizontalAlignment(SwingConstants.CENTER);
		copyright_SYG_Label.setBackground(SystemColor.activeCaption);
		copyright_SYG_Label.setBounds(0, 0, 494, 58);
		contentPane.add(copyright_SYG_Label);

		// !!!! if문안에 있는 selected파일은 지역변수라 밖으로 나오지 못한다.
		// 지인짜 안됨. 정말루 안됨. 그냥 라벨에다가 붙여버렸음
		// 3시간 내내 geText이며 set이며 예외처리, 디비 추가방식, 형변환이며 다 해봤지만 안됐음.
		JLabel pic_path = new JLabel("New label");
		JFileChooser fileChooser = new JFileChooser();
		// String selectedFile;
		// String filePath;
		JButton stu_pic = new JButton();
		stu_pic.setText("사진 +");
		stu_pic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null, "사진을 등록하시겠습니까?", "오!출석 - 학생 정보 추가",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {

				} else if (result == JOptionPane.YES_OPTION) {

					JFrame window = new JFrame();

					// 기본 Path의 경로 설정 (바탕화면)
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "//" + "Desktop"));

					// 필터링될 확장자
					FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg & png 파일", "png", "jpg");

					// 필터링될 확장자 추가
					fileChooser.addChoosableFileFilter(filter);

					// 파일오픈 다이얼로그 를 띄움
					int pic = fileChooser.showSaveDialog(window);

					if (pic == JFileChooser.APPROVE_OPTION) {

						// 선택한 파일의 경로 반환

						selectedFile = fileChooser.getSelectedFile(); // 지역변수 이프문안세만 클래스의 멤버변수로 바꾸기
						String filePath = fileChooser.getSelectedFile().getPath();
						stu_pic.setIcon(new ImageIcon(filePath));

						// 가져온 사진 사이즈 맞춰주기
						ImageIcon pic2 = new ImageIcon(filePath);
						Image img = pic2.getImage();
						Image changeImg = img.getScaledInstance(170, 180, Image.SCALE_SMOOTH);
						ImageIcon changeIcon = new ImageIcon(changeImg);
						stu_pic.setIcon(changeIcon);

						// 경로 출력 - DB에 스트링타입으로 넣는 용도로 쓸 것이다.
						// System.out.println(selectedFile);
						System.out.println(filePath.toString());
						pic_path.setText(filePath.toString());

					}
				}
			}
		});

		stu_pic.setFont(new Font("굴림", Font.PLAIN, 21));
		stu_pic.setBackground(new Color(255, 255, 255));
		stu_pic.setBounds(37, 96, 146, 175);
		contentPane.add(stu_pic);

		JLabel lblNewLabel = new JLabel("* 이름과 출석번호는 필수 입력사항입니다. 그 외는 선택사항");
		lblNewLabel.setForeground(new Color(255, 69, 0));
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel.setBounds(37, 62, 445, 26);
		contentPane.add(lblNewLabel);

		JLabel day_label = new JLabel("등원요일");
		day_label.setHorizontalAlignment(SwingConstants.CENTER);
		day_label.setFont(new Font("배달의민족 주아", Font.BOLD, 16));
		day_label.setBounds(37, 380, 75, 35);
		contentPane.add(day_label);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(415, 468, 17, 81);
		contentPane.add(scrollBar);

		JTextArea s_memo = new JTextArea(); // 메모할 때는 텍스트에어리어 텍스트필드쓰면 엔터가 안된다.
		s_memo.setLineWrap(true); // 한줄이 너무 길면 자동으로 개행할지 설정
		// JScrollPane scrollPane = new JScrollPane(s_memo);
		// contentPane.add(scrollPane);
		s_memo.setForeground(new Color(112, 128, 144));
		s_memo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_memo.getText().equals(" * 특이사항 메모")) {
					s_memo.setText("");
					s_memo.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_memo.getText().equals("")) {
					s_memo.setText(" * 특이사항 메모");
					s_memo.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_memo.setFont(new Font("굴림", Font.PLAIN, 16));
		s_memo.setText(" * 특이사항 메모");
		s_memo.setToolTipText("");
		s_memo.setBounds(52, 467, 377, 82);
		contentPane.add(s_memo);
		s_memo.setColumns(10);

		// 생일 날짜 달력
		JDateChooser birth_dateChooser = new JDateChooser();
		birth_dateChooser.setBounds(205, 146, 217, 29);
		contentPane.add(birth_dateChooser);

		// 생년월일 라벨
		JLabel lblNewLabel_1_1 = new JLabel("생년월일 ");
		birth_dateChooser.add(lblNewLabel_1_1, BorderLayout.WEST);
		lblNewLabel_1_1.setFont(new Font("함초롬돋움", Font.PLAIN, 16));

		// 등록날짜 달력
		JDateChooser enter_dateChooser = new JDateChooser();
		enter_dateChooser.setBounds(103, 420, 290, 29);
		contentPane.add(enter_dateChooser);

		JLabel enter_label = new JLabel("학원 등록일 ");
		enter_dateChooser.add(enter_label, BorderLayout.WEST);
		enter_label.setFont(new Font("함초롬돋움", Font.PLAIN, 16));

		// 저장버튼
		JButton s_save_btn = new JButton("저장");
		s_save_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 학생 나이 구하기
				// 현재 날짜 구하기
				LocalDate current_date = LocalDate.now();
				int current_Year = current_date.getYear();
				// System.out.println("Current year: "+current_Year);

				int age_ = current_date.getYear() - birth_dateChooser.getJCalendar().getYearChooser().getYear() + 1;

				String age = current_date.getYear() - birth_dateChooser.getJCalendar().getYearChooser().getYear() + 1
						+ "세";

				// 생년월일 셋팅
				String birth = (birth_dateChooser.getJCalendar().getYearChooser().getYear() + "-"
						+ (birth_dateChooser.getJCalendar().getMonthChooser().getMonth() + 1) + "-"
						+ birth_dateChooser.getJCalendar().getDayChooser().getDay());
				System.out.println(birth);

				// 학년

				switch (age_) {
				case 8:
					s_grade.setText("초1");
				case 9:
					s_grade.setText("초2");
				case 10:
					s_grade.setText("초3");
				case 11:
					s_grade.setText("초4");
				case 12:
					s_grade.setText("초5");
				case 13:
					s_grade.setText("초6");
				case 14:
					s_grade.setText("중1");
				case 15:
					s_grade.setText("중2");
				case 16:
					s_grade.setText("중3");
				case 17:
					s_grade.setText("고1");
				case 18:
					s_grade.setText("고2");
				case 19:
					s_grade.setText("고3");

				}

				// 등록일 셋팅

				String enter_date = (enter_dateChooser.getJCalendar().getYearChooser().getYear() + "-"
						+ (enter_dateChooser.getJCalendar().getMonthChooser().getMonth() + 1) + "-"
						+ enter_dateChooser.getJCalendar().getDayChooser().getDay());
				System.out.println(enter_date);

				String grade = s_grade.getText() + "학년";

				// 만약 등원요일 체크박스 안해줬다면 빈값으로 받기 when_daycome null값으로 받기
				if (when_daycome == "") {
					when_daycome = "";
				}

				// 만약 정보를 입력하지 않았다면 빈값으로 바꿔주는 작업
				if (s_school.getText().equals(" * 학교 입력란 예) OO초")) {
					s_school.setText("");
				}
				if (s_grade.getText().equals(" * 학년 예) 초4, 고2")) {
					s_grade.setText("");
				}
				if (s_class.getText().equals(" * OO반")) {
					s_class.setText("");
					grade = "";
				}
				if (address.getText().equals(" * 주소를 입력해주세요. 예) 00아파트 000동 000호")) {
					address.setText("");
				}
				if (s_memo.getText().equals(" * 특이사항 메모")) {
					s_memo.setText("");
				}
				if (s_call_num.getText().equals(" * 학생 전화번호를 입력해주세요. 예) 010-0000-0000")) {
					s_call_num.setText("");
				}
				if (s_guardian1.getText().equals(" * 보호자1 성함")) {
					s_guardian1.setText("");
				}
				if (s_guardian1_call_.getText().equals(" * phone 예)010-0000-0000")) {
					s_guardian1_call_.setText("");
				}
				if (s_guardian2.getText().equals(" * 보호자2 성함")) {
					s_guardian2.setText("");
				}
				if (s_guardian2_call.getText().equals(" * phone 예)010-0000-0000")) {
					s_guardian2_call.setText("");
				}

				if (s_mon.isSelected() == true) {
					when_daycome += "월 ";
				}

				if (s_tue.isSelected() == true) {
					when_daycome += "화 ";
				}

				if (s_wed.isSelected() == true) {
					when_daycome += "수 ";
				}

				if (s_thur.isSelected() == true) {
					when_daycome += "목 ";
				}

				if (s_fri.isSelected() == true) {
					when_daycome += "금 ";
				}
				if (s_sat.isSelected() == true) {
					when_daycome += "토 ";
				}

				if (s_sun.isSelected() == true) {
					when_daycome += "일 ";
				}
				when_daycome = when_daycome.substring(0, when_daycome.length());

				char temp = 0;
				boolean flag = true;
				for (int i = 0; i < s_grade.getText().length(); i++) {
					temp = s_grade.getText().charAt(i); // 입력한 str을 문자로 쪼개서 temp로 받기
					if (temp < '0' || temp > '9') {// temp의 값이 '0'작거나 '9'보다 클 경우
						flag = false;
						break;
					}
				}
				if (s_name.getText().equals(" * 이름을 입력해주세요.")) {
					JOptionPane.showMessageDialog(null, "이름은 필수 입력 항목입니다.\r\n이름을 입력해주세요.", "저장 실패!",
							JOptionPane.ERROR_MESSAGE);
				} else if (s_name.getText().length() > 12) {
					JOptionPane.showMessageDialog(null, "이름을 10글자 이하로 입력해주세요.", "저장 실패!", JOptionPane.ERROR_MESSAGE);
				} else if (stu_num.getText().equals("* 출석번호 4자리(숫자)")) {
					JOptionPane.showMessageDialog(null, "출석번호는 필수 입력 사항입니다.", "저장 실패!", JOptionPane.ERROR_MESSAGE);
				} else {

					// 라디오 체크박스 남자인지 여자인지 값가져오기 위한 작업
					JLabel gender = new JLabel();
					if (s_boy_check.isSelected()) {
						gender = new JLabel(s_boy_check.getText());
					} else if (s_girl_check.isSelected()) {
						gender = new JLabel(s_girl_check.getText());
					}

					// 디비에 학생 정보 추가값넣기
					StudentVo vo = new StudentVo(stu_num.getText(), s_name.getText(), gender.getText(), age,
							s_school.getText(), s_grade.getText(), s_class.getText(), birth, when_daycome,
							address.getText(), enter_date, s_call_num.getText(),
							s_guardian1.getText() + s_who_guardian1.getSelectedItem().toString(),
							s_guardian1_call_.getText(),
							s_guardian2.getText() + s_who_guardian2.getSelectedItem().toString(),
							s_guardian2_call.getText(), s_memo.getText(), pic_path.getText());

					boolean b = dao.add_stu_info(vo);

					// 회원가입 성공했다는 알림 띄우기
					if (b == false) {

						int result = JOptionPane.showConfirmDialog(null,
								"저장완료!\n다른 학생을 추가하시겠습니까?\n'아니요'를 누르면 홈으로 돌아갑니다. ", "오!출석 - 학생 추가하기",
								JOptionPane.YES_NO_OPTION);
						if (result == JOptionPane.CLOSED_OPTION) {
							dispose();
						} else if (result == JOptionPane.YES_OPTION) {
							addStudentPage addstudent = new addStudentPage();
							addstudent.setVisible(true);
							dispose();
						} else {
							management_Student manage = new management_Student(); // 홈화면 호출
							manage.setVisible(true);
							dispose();
						}
					}
				}
			}

		});
		s_save_btn.setEnabled(false); // 출석번호 중복확인 해야지만 정보 저장할 수 있도록 하기
		s_save_btn.setFont(new Font("함초롬바탕", Font.PLAIN, 16));
		s_save_btn.setBounds(176, 700, 144, 46);
		contentPane.add(s_save_btn);

		// 출석번호 중복여부 버튼
		JButton StunumCheck = new JButton("중복 확인");
		StunumCheck.setBackground(new Color(255, 240, 245));
		StunumCheck.setBounds(330, 331, 104, 29);
		StunumCheck.setFont(new Font("굴림", Font.PLAIN, 14));
		StunumCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 트루이면 중복, false이면 새로 생성
				boolean user = new StudentDAO().StuNumCheck(stu_num.getText());

				// 입력받은 출석번호가 숫자가 아닐 경우 - 입력된 문자열이 숫자인지 문자가 섞여있는지 판단하는 코드를 작성
				char temp = 0;
				boolean flag = true;
				for (int i = 0; i < stu_num.getText().length(); i++) {
					temp = stu_num.getText().charAt(i); // 입력한 str을 문자로 쪼개서 temp로 받기
					if (temp < '0' || temp > '9') {// temp의 값이 '0'작거나 '9'보다 클 경우
						flag = false;
						break;
					}
				}
				if (flag == false) {
					JOptionPane.showMessageDialog(null, "번호는 '숫자' 4자리입니다.\r\n다시 입력해주세요.");
				} else if (stu_num.getText().length() != 4) {
					JOptionPane.showMessageDialog(null, "번호는 숫자 '4자리'입니다.\r\n다시 입력해주세요.");
				} else if (user == true) {
					JOptionPane.showMessageDialog(null, "이미 사용중인 번호입니다.\n다른번호를 입력해주세요.");
				} else if (user == false) {
					JOptionPane.showMessageDialog(null, "사용가능한 번호입니다.");
					s_save_btn.setEnabled(true);
				}

			}
		});
		contentPane.add(StunumCheck);

		JLabel lblNewLabel_1 = new JLabel("* 중복확인을 해야지만 학생 정보를 저장할 수 있습니다.");
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(42, 365, 392, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("출석번호 생성 : ");
		lblNewLabel_2.setFont(new Font("배달의민족 주아", Font.PLAIN, 17));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(40, 331, 104, 29);
		contentPane.add(lblNewLabel_2);
	}
}
