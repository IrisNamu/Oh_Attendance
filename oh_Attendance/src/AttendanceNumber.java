
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.MemberDAO;
import database.MemberVo;
import database.StudentDAO;
import database.StudentVo;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class AttendanceNumber extends JFrame {
	private StudentDAO dao;

	private JPanel contentPane;
	private JTextField InputNumber;
	private JTextField TFLabel;

	public AttendanceNumber() {
		dao = new StudentDAO();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home_Login.class.getResource("/img/app_icon.png")));
		setTitle("");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 752);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		InputNumber = new JTextField();
		InputNumber.setHorizontalAlignment(SwingConstants.CENTER);
		InputNumber.setFont(new Font("배달의민족 주아", Font.PLAIN, 43));
		InputNumber.setBackground(new Color(255, 228, 149));
		InputNumber.setBounds(22, 209, 404, 72);
		contentPane.add(InputNumber);
		InputNumber.setColumns(20);

		TFLabel = new JTextField();
		TFLabel.setForeground(Color.GRAY);
		TFLabel.setHorizontalAlignment(SwingConstants.CENTER);
		TFLabel.setText("출석번호 4자리를 눌러주세요^^");
		TFLabel.setBackground(new Color(255, 239, 213));
		TFLabel.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		TFLabel.setBounds(22, 291, 404, 40);
		contentPane.add(TFLabel);
		TFLabel.setColumns(10);

		JPanel clock_panel = new JPanel();
		clock_panel.setBounds(22, 77, 404, 122);
		contentPane.add(clock_panel);
		clock_panel.setLayout(null);

		JLabel dateLab = new JLabel("date");
		dateLab.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		dateLab.setHorizontalAlignment(SwingConstants.CENTER);
		dateLab.setBounds(0, 0, 404, 50);
		clock_panel.add(dateLab);

		JLabel timeLab = new JLabel("time");
		timeLab.setHorizontalAlignment(SwingConstants.CENTER);
		timeLab.setFont(new Font("배달의민족 주아", Font.PLAIN, 50));
		timeLab.setBounds(0, 43, 404, 79);
		clock_panel.add(timeLab);

		// 패널에 부착될 시간.
		Date d = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
		dateLab.setText(date.format(d));
		System.out.println(date);
		SimpleDateFormat time = new SimpleDateFormat("a hh시 mm분");
		timeLab.setText(time.format(d));

		JButton backBtn = new JButton("<");
		backBtn.setBorderPainted(false);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
				attendance_Main attendance = new attendance_Main(); // 홈화면 호출
				attendance.setVisible(true);
			}
		});

		backBtn.setForeground(Color.WHITE);
		backBtn.setFont(new Font("배달의민족 주아", Font.BOLD, 17));
		backBtn.setBackground(new Color(128, 128, 128));
		backBtn.setBounds(376, 0, 74, 58);
		contentPane.add(backBtn);

		// 맨 위 라벨
		JLabel title_bar = new JLabel("오! 출석 - 번호로 출석하기");
		title_bar.setForeground(Color.WHITE);
		title_bar.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		title_bar.setOpaque(true);
		title_bar.setHorizontalAlignment(SwingConstants.CENTER);
		title_bar.setBackground(SystemColor.activeCaption);
		title_bar.setBounds(0, 0, 450, 58);
		contentPane.add(title_bar);

		JPanel btn_panel = new JPanel();
		btn_panel.setBounds(22, 350, 404, 338);
		btn_panel.setLayout(new GridLayout(4, 4, 5, 5));
		contentPane.add(btn_panel);

		JButton btn1 = new JButton("1");
		btn_panel.add(btn1);
		btn1.setBackground(new Color(255, 255, 240));
		btn1.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btn1.setBorderPainted(false);

		JButton btn2 = new JButton("2");
		btn_panel.add(btn2);
		btn2.setBackground(new Color(255, 255, 240));
		btn2.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btn2.setBorderPainted(false);

		JButton btn3 = new JButton("3");
		btn_panel.add(btn3);
		btn3.setBackground(new Color(255, 255, 240));
		btn3.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btn3.setBorderPainted(false);

		JButton btn4 = new JButton("4");
		btn_panel.add(btn4);
		btn4.setBackground(new Color(255, 255, 240));
		btn4.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btn4.setBorderPainted(false);

		JButton btn5 = new JButton("5");
		btn_panel.add(btn5);
		btn5.setBackground(new Color(255, 255, 240));
		btn5.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btn5.setBorderPainted(false);

		JButton btn6 = new JButton("6");
		btn_panel.add(btn6);
		btn6.setBackground(new Color(255, 255, 240));
		btn6.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btn6.setBorderPainted(false);

		JButton btn7 = new JButton("7");
		btn_panel.add(btn7);
		btn7.setBackground(new Color(255, 255, 240));
		btn7.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btn7.setBorderPainted(false);

		JButton btn8 = new JButton("8");
		btn_panel.add(btn8);
		btn8.setBackground(new Color(255, 255, 240));
		btn8.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btn8.setBorderPainted(false);

		JButton btn9 = new JButton("9");
		btn_panel.add(btn9);
		btn9.setBackground(new Color(255, 255, 240));
		btn9.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btn9.setBorderPainted(false);

		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputNumber.setText("");
			}
		});
		btn_panel.add(btnC);
		btnC.setBackground(new Color(255, 255, 240));
		btnC.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btnC.setBorderPainted(false);

		JButton btn0 = new JButton("0");
		btn_panel.add(btn0);
		btn0.setBackground(new Color(255, 255, 240));
		btn0.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btn0.setBorderPainted(false);

		JButton btnOk = new JButton("출석");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean user = new StudentDAO().StuNumCheck(InputNumber.getText());
				// 트루이면 있는 출석번호, false면 없는 출석번호

				// 멤버다오에 있는 아이디체크메서드 사용하기위해 호출

				if ((InputNumber.getText().length() > 4) || (InputNumber.getText().length() < 4)) {
					TFLabel.setText("번호는 '4자리'입니다. 다시 입력해주세요.");
					TFLabel.setForeground(new Color(165, 42, 42));
					InputNumber.setText("");
				} else if (user == false) {
					TFLabel.setText("존재하지 않는 출석번호입니다.");
					TFLabel.setForeground(new Color(165, 42, 42));
					InputNumber.setText("");
				} else if (user == true) {

					String attendance_stuatus = "출석";

					StudentVo vo = new StudentVo(InputNumber.getText(), dateLab.getText(), attendance_stuatus,
							timeLab.getText());

					boolean b = dao.Insert_attendance_Info(vo);

					StudentDAO dao = new StudentDAO();
					ArrayList<StudentVo> list = dao.number_name(InputNumber.getText());

					for (StudentVo vo1 : list) {
						String[] data = { vo1.getStuName() };
						System.out.println(data);

						if (b == false) {
							TFLabel.setText(Arrays.toString(data) + "님 출석되었습니다.");
							TFLabel.setForeground(new Color(58, 76, 168));
							InputNumber.setText("");
						}

						// 회원가입 성공했다는 알림 띄우기

					}
				}
			}
		});

		btnOk.setForeground(new Color(70, 130, 180));
		btn_panel.add(btnOk);
		btnOk.setBackground(new Color(255, 255, 240));
		btnOk.setFont(new Font("배달의민족 주아", Font.BOLD, 44));
		btnOk.setBorderPainted(false);

		btn0.addActionListener(new NumberActionListner(InputNumber, "0"));
		btn1.addActionListener(new NumberActionListner(InputNumber, "1"));
		btn2.addActionListener(new NumberActionListner(InputNumber, "2"));
		btn3.addActionListener(new NumberActionListner(InputNumber, "3"));
		btn4.addActionListener(new NumberActionListner(InputNumber, "4"));
		btn5.addActionListener(new NumberActionListner(InputNumber, "5"));
		btn6.addActionListener(new NumberActionListner(InputNumber, "6"));
		btn7.addActionListener(new NumberActionListner(InputNumber, "7"));
		btn8.addActionListener(new NumberActionListner(InputNumber, "8"));
		btn9.addActionListener(new NumberActionListner(InputNumber, "9"));

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 54, 450, 13);
		contentPane.add(separator);
	}
}

class NumberActionListner implements ActionListener {
	private JTextField label;
	private String text;

	public NumberActionListner(JTextField inputNumber, String s) {
		label = inputNumber;
		text = s;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String curr = label.getText();
		if (curr.equals("홍길동님 출석 완료되었습니다.")) {
			label.setText(text);
		} else {
			label.setText(label.getText() + text);
		}
	}

}