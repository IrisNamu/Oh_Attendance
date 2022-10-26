
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.MemberDAO;
import database.MemberVo;
import database.PayDAO;
import database.PayVo;
import database.StudentDAO;
import database.StudentVo;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.GridLayout;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSeparator;
import java.awt.FlowLayout;

public class payment extends JFrame {
	private StudentDAO dao;
	private PayDAO pay_dao;

	private JPanel contentPane;
	private JTextField search_field;
	private JTextField pay_input;
	private JTextField stuNum;
	private JTable table_stuList;
	private JButton hundred_thousand;
	private int sum;

	public payment() {
		dao = new StudentDAO();
		pay_dao = new PayDAO();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home_Login.class.getResource("/img/app_icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 752);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pay_input = new JTextField();
		pay_input.setFont(new Font("배달의민족 주아", Font.PLAIN, 31));
		pay_input.setHorizontalAlignment(SwingConstants.CENTER);
		pay_input.setText("0원");
		pay_input.setBounds(12, 346, 426, 155);
		contentPane.add(pay_input);
		pay_input.setColumns(10);

		stuNum = new JTextField();
		stuNum.setHorizontalAlignment(SwingConstants.CENTER);
		stuNum.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				if (stuNum.getText().equals("출석번호를 입력하세요.")) {
					stuNum.setText("");
					stuNum.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (stuNum.getText().equals("")) {
					stuNum.setText("출석번호를 입력하세요.");
					stuNum.setForeground(Color.black);
				}
			}
		});
		stuNum.setText("출석번호를 입력하세요.");
		stuNum.setForeground(new Color(220, 20, 60));
		stuNum.setFont(new Font("배달의민족 주아", Font.PLAIN, 17));
		stuNum.setColumns(10);
		stuNum.setBounds(34, 302, 180, 34);
		contentPane.add(stuNum);

		JLabel lblNewLabel = new JLabel(" * 동명이인의 문제를 방지하기 위해 출석번호를 입력해주세요.");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 13));
		lblNewLabel.setForeground(new Color(204, 0, 0));
		lblNewLabel.setBounds(24, 83, 399, 15);
		contentPane.add(lblNewLabel);

		JButton backBtn = new JButton("<");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pay_manage manage = new pay_manage();
				manage.setVisible(true);
				dispose();
			}
		});
		backBtn.setForeground(Color.WHITE);
		backBtn.setFont(new Font("배달의민족 주아", Font.BOLD, 17));
		backBtn.setBorderPainted(false);
		backBtn.setBackground(Color.GRAY);
		backBtn.setBounds(384, 0, 66, 58);
		contentPane.add(backBtn);

		JLabel title_bar = new JLabel("오! 출석 - 수강료 납부하기");
		title_bar.setOpaque(true);
		title_bar.setHorizontalAlignment(SwingConstants.CENTER);
		title_bar.setForeground(Color.WHITE);
		title_bar.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		title_bar.setBackground(SystemColor.activeCaption);
		title_bar.setBounds(0, 0, 450, 58);
		contentPane.add(title_bar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 152, 377, 117);
		contentPane.add(scrollPane);

		String[] header = new String[] { "출석번호", "이름", "성별", "나이", "학교명", "학년", "반", "생년월일", "등원요일", "주소", "등록일",
				"학생 전화번호", "보호자1 성함", "보호자1 전화번호", "보호자2 성함", "보호자2 전화번호", "특이사항" };
		String[][] data = dao.getStudent();

		table_stuList = new JTable();
		table_stuList.setFont(new Font("굴림", Font.PLAIN, 13));
		table_stuList.setModel(new DefaultTableModel(data, header));
		scrollPane.setViewportView(table_stuList);
		table_stuList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// 테이블 높이 넓이 조정해주기
		table_stuList.setRowHeight(30);
		table_stuList.getColumn("출석번호").setPreferredWidth(60);
		table_stuList.getColumn("성별").setPreferredWidth(50);
		table_stuList.getColumn("나이").setPreferredWidth(50);
		table_stuList.getColumn("학년").setPreferredWidth(60);
		table_stuList.getColumn("반").setPreferredWidth(90);
		table_stuList.getColumn("생년월일").setPreferredWidth(100);
		table_stuList.getColumn("등원요일").setPreferredWidth(100);
		table_stuList.getColumn("주소").setPreferredWidth(180);
		table_stuList.getColumn("등록일").setPreferredWidth(100);
		table_stuList.getColumn("학생 전화번호").setPreferredWidth(150);
		table_stuList.getColumn("보호자1 성함").setPreferredWidth(90);
		table_stuList.getColumn("보호자1 전화번호").setPreferredWidth(150);
		table_stuList.getColumn("보호자2 성함").setPreferredWidth(90);
		table_stuList.getColumn("보호자2 전화번호").setPreferredWidth(150);
		table_stuList.getColumn("특이사항").setPreferredWidth(250);
		table_stuList.setEnabled(false);

		// 검색

		search_field = new JTextField();
		search_field.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (search_field.getText().equals(" 검색어를 입력하세요.")) {
					search_field.setText("");
					search_field.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (search_field.getText().equals("")) {
					search_field.setText(" 검색어를 입력하세요.");
					search_field.setForeground(new Color(153, 153, 153));
				}
			}
		});

		search_field.setText(" 검색어를 입력하세요.");
		search_field.setForeground(Color.GRAY);
		search_field.setFont(new Font("배달의민족 주아", Font.PLAIN, 17));
		search_field.setColumns(10);
		search_field.setBounds(109, 108, 180, 34);
		contentPane.add(search_field);

		JButton search = new JButton("검색");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 검색하면 테이블에 해당 검색결과 나타내기
				DefaultTableModel model = (DefaultTableModel) table_stuList.getModel();
				model.setRowCount(0);

				String word = search_field.getText();
				System.out.println(word);
				if (word.length() < 1) {
					return;
				}
				StudentDAO dao = new StudentDAO();
				ArrayList<StudentVo> list = dao.search_Info(word);

				for (StudentVo vo : list) {
					String[] data = { vo.getStuNumber(), vo.getStuName(), vo.getSex(), vo.getAge(), vo.getSchool(),
							vo.getGrade(), vo.getClassName(), vo.getBirth(), vo.getWhen_day(), vo.getAddress(),
							vo.getEnter_date(), vo.getStudent_call(), vo.getGuardian1(), vo.getGuardian1_call(),
							vo.getGuardian2(), vo.getGuardian2_call(), vo.getStu_memo() };
					model.addRow(data);// 이걸 적어줘야 테이블에 추가가 된다.
				}

				table_stuList.setModel(model);
			}
		});
		search.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		search.setBackground(new Color(176, 196, 222));
		search.setBounds(301, 108, 66, 34);
		contentPane.add(search);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 250));
		panel.setBounds(0, 523, 450, 103);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));

		hundred_thousand = new JButton("10만원");
		hundred_thousand.setBackground(new Color(255, 255, 255));
		hundred_thousand.setFont(new Font("배달의민족 주아", Font.BOLD, 18));
		panel.add(hundred_thousand);
		hundred_thousand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sum += 100000;
				pay_input.setText(sum + "원");

			}
		});

		JButton fifty_thousand = new JButton("5만원");
		fifty_thousand.setBackground(new Color(255, 255, 255));
		fifty_thousand.setFont(new Font("배달의민족 주아", Font.BOLD, 20));
		fifty_thousand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sum += 50000;
				pay_input.setText(sum + "원");
			}
		});
		panel.add(fifty_thousand);

		JButton five_thousand = new JButton("오천원");
		five_thousand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sum += 5000;
				pay_input.setText(sum + "원");
			}
		});
		five_thousand.setBackground(new Color(255, 255, 255));
		five_thousand.setFont(new Font("배달의민족 주아", Font.BOLD, 20));
		panel.add(five_thousand);

		JButton ten_thousand = new JButton("1만원");
		ten_thousand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sum += 10000;
				pay_input.setText(sum + "원");

			}
		});
		ten_thousand.setBackground(new Color(255, 255, 255));
		ten_thousand.setForeground(new Color(0, 0, 0));
		ten_thousand.setFont(new Font("배달의민족 주아", Font.BOLD, 20));
		panel.add(ten_thousand);

		JButton thousand = new JButton("1천원");
		thousand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sum += 1000;
				pay_input.setText(sum + "원");
			}
		});
		thousand.setBackground(new Color(255, 255, 255));
		thousand.setFont(new Font("배달의민족 주아", Font.BOLD, 20));
		panel.add(thousand);

		JButton reset = new JButton("리셋");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sum = 0;
				pay_input.setText("0원");
			}
		});
		reset.setForeground(new Color(255, 255, 255));
		reset.setBackground(new Color(0, 0, 0));
		reset.setFont(new Font("배달의민족 주아", Font.PLAIN, 21));
		panel.add(reset);

		JDateChooser pay_date = new JDateChooser();
		pay_date.getCalendarButton().setBackground(new Color(255, 255, 255));
		pay_date.getCalendarButton().setFont(new Font("배달의민족 주아", Font.PLAIN, 14));
		pay_date.getCalendarButton().setText("결제일");
		pay_date.setBounds(219, 302, 192, 33);
		contentPane.add(pay_date);

		JButton btnNewButton_2 = new JButton("수납 정보 저장");
		btnNewButton_2.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String pay_when = (pay_date.getJCalendar().getYearChooser().getYear() + "-"
						+ (pay_date.getJCalendar().getMonthChooser().getMonth() + 1) + "-"
						+ pay_date.getJCalendar().getDayChooser().getDay());
				System.out.println(pay_when);

				// 트루이면 중복, false이면 새로 생성
				boolean user = new StudentDAO().StuNumCheck(stuNum.getText());
				// 멤버다오에 있는 아이디체크메서드 사용하기위해 호출

				if (stuNum.getText().equals("출석번호를 입력하세요.")) {
					JOptionPane.showMessageDialog(null, "출석번호를 입력해주세요.", "수강 납부 실패", JOptionPane.ERROR_MESSAGE);
				} else if (pay_date.getJCalendar().getYearChooser().getYear() == 0) {
					JOptionPane.showMessageDialog(null, "수강납부일을 선택해주세요.", "수강 납부 실패", JOptionPane.ERROR_MESSAGE);
				} else if (user == false) {
					JOptionPane.showMessageDialog(null, "존재하지 않는 출석 번호 입니다.\n\r다시 확인해주세요.", "수강 납부 실패",
							JOptionPane.ERROR_MESSAGE);

				} else if (pay_input.getText().equals("0원")) {
					JOptionPane.showMessageDialog(null, "금액을 바르게 입력해주세요.", "수강 납부 실패", JOptionPane.ERROR_MESSAGE);
				} else {

					// 디비값넣기

					StudentDAO dao = new StudentDAO();
					ArrayList<StudentVo> list = dao.number_name(stuNum.getText());

					String str = pay_input.getText();

					str = str.replaceAll("[원]", "");
					System.out.println(str);

					int pay_amount = Integer.parseInt(str);

					PayVo vo = new PayVo(stuNum.getText(), pay_when, pay_amount);
					boolean b = pay_dao.pay(vo);

					for (StudentVo vo1 : list) {
						String[] name = { vo1.getStuName() };
						System.out.println(Arrays.toString(name));

						int r = JOptionPane.showConfirmDialog(null,
								Arrays.toString(name) + "님 " + pay_amount + "원 납부처리 하시겠습니까?", "수강 납부 확인 메세지",
								JOptionPane.YES_NO_OPTION);

						if (r == JOptionPane.YES_OPTION) {
							JOptionPane.showMessageDialog(null, Arrays.toString(name) + "님 수강납부 완료되었습니다.");
							payment pay = new payment();
							pay.setVisible(true);
							dispose();
							pay_input.setText("0원");

						} else {
							JOptionPane.showMessageDialog(null, "취소되었습니다.");
						}
					}
				}
			}
		});
		btnNewButton_2.setBounds(151, 638, 151, 39);
		btnNewButton_2.setBackground(SystemColor.activeCaption);
		contentPane.add(btnNewButton_2);

		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(112, 128, 144));
		separator.setBounds(0, 279, 450, 2);
		contentPane.add(separator);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 58, 450, 226);
		contentPane.add(panel_1);
	}
}
