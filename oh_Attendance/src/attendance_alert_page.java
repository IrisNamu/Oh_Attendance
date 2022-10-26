
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import database.MemberVo;
import database.StudentDAO;
import database.StudentVo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import java.awt.Color;

public class attendance_alert_page extends JFrame {
	private StudentDAO dao;
	private JPanel contentPane;
	private JTextField X_reson;

	public attendance_alert_page(String stunum, String stuname, String stuage) {

		dao = new StudentDAO();
		attendance_Main att = new attendance_Main();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Home_Login.class.getResource("/img/app_icon.png")));

		setBackground(new Color(105, 105, 105));
		setTitle("오! 출석 - 출석체크");
		setBounds(100, 100, 347, 224);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 출석한 날짜와 시간 정보 받기
		JLabel dateLab = new JLabel("");
		JLabel timeLab = new JLabel("");
		Date d = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
		dateLab.setText(date.format(d));
		System.out.println(date);
		SimpleDateFormat time = new SimpleDateFormat("a hh시 mm분");
		timeLab.setText(time.format(d));

		JButton O = new JButton("등원");
		O.setBackground(new Color(211, 211, 211));
		O.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		O.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String O = "출석";
				String reason = " ";
				StudentVo vo = new StudentVo(stunum, dateLab.getText(), O, timeLab.getText(), reason);
				boolean b = dao.att_btn(vo);

				dispose();
				if (b == false) {
					attendance_Main att = new attendance_Main();
					att.setVisible(true);
					dispose();
				}
			}
		});
		O.setBounds(48, 78, 113, 36);
		contentPane.add(O);

		JButton X = new JButton("결석");
		X.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String X = "결석";
				String time = " ";

				if (X_reson.getText().equals(" * (선택사항) 결석사유를 입력해주세요.")) {
					X_reson.setText("");
				}

				StudentVo vo = new StudentVo(stunum, dateLab.getText(), X, time, X_reson.getText());
				boolean b = dao.att_btn(vo);
				dispose();
				if (b == false) {
					dispose();
					attendance_Main att = new attendance_Main();
					att.setVisible(true);
				}

			}
		});
		X.setBackground(new Color(211, 211, 211));
		X.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		X.setBounds(173, 78, 113, 36);
		contentPane.add(X);

		JLabel data = new JLabel(stunum + " " + stuname + "(" + stuage + ")" + " 등원처리");
		data.setHorizontalAlignment(SwingConstants.CENTER);
		data.setFont(new Font("배달의민족 주아", Font.PLAIN, 26));
		data.setBounds(0, 25, 331, 40);
		contentPane.add(data);

		X_reson = new JTextField();
		X_reson.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		X_reson.setForeground(Color.GRAY);
		X_reson.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (X_reson.getText().equals(" * (선택사항) 결석사유를 입력해주세요.")) {
					X_reson.setText("");
					X_reson.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (X_reson.getText().equals("")) {
					X_reson.setText(" * (선택사항) 결석사유를 입력해주세요.");
					X_reson.setForeground(new Color(153, 153, 153));
				}
			}
		});
		X_reson.setText(" * (선택사항) 결석사유를 입력해주세요.");
		X_reson.setBounds(12, 129, 307, 36);
		contentPane.add(X_reson);
		X_reson.setColumns(10);
		setVisible(true);
	}
}
