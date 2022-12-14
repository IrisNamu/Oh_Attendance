
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
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

import database.PayDAO;
import database.StudentDAO;
import database.StudentVo;

@SuppressWarnings("serial")
public class Update_stuInfo extends JFrame {
	private StudentDAO dao;

	private JPanel contentPane;
	private JTextField s_name;
	private JTextField s_memo;
	private JTextField s_call_num;
	private JTextField s_guardian1;
	private JTextField s_guardian2;
	private JTextField address_;
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
	String filePath = "";
	private JLabel stunum;
	private JTextField birth_field;
	private JTextField enter_field;
	private ImageIcon pic_btn;
	private StudentVo vo1;

	// private StudentVo vo1 : list;
	public Update_stuInfo(String num, String name, String sex, String age, String school, String grade, String class_,
			String birth, String when_come, String address, String enter_date, String stu_call, String G1,
			String G1_call, String G2, String G2_call, String memo) {

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
		backBtn.setFont(new Font("??????????????? ??????", Font.BOLD, 17));
		backBtn.setBackground(new Color(128, 128, 128));
		backBtn.setBounds(429, 0, 65, 58);
		contentPane.add(backBtn);

		// ????????????
		s_name = new JTextField(name);
		if (s_name.getText().equals("")) {
			s_name.setText(" * ????????? ??????????????????.");
		}
		s_name.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_name.getText().equals(" * ????????? ??????????????????.")) {
					s_name.setText("");
					s_name.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_name.getText().equals("")) {
					s_name.setText(" * ????????? ??????????????????.");
					s_name.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_name.setForeground(new Color(112, 128, 144));
		s_name.setFont(new Font("??????", Font.PLAIN, 16));
		s_name.setBounds(204, 107, 155, 29);
		contentPane.add(s_name);
		s_name.setColumns(10);

		// ?????? ????????????
		JRadioButton s_boy_check = new JRadioButton("???");
		s_boy_check.setFont(new Font("??????", Font.PLAIN, 16));
		s_boy_check.setBounds(367, 109, 45, 23);
		contentPane.add(s_boy_check);
		s_boy_check.setActionCommand(s_boy_check.getText());

		JRadioButton s_girl_check = new JRadioButton("???");
		s_girl_check.setFont(new Font("??????", Font.PLAIN, 16));
		s_girl_check.setBounds(415, 109, 45, 23);
		contentPane.add(s_girl_check);
		s_girl_check.setActionCommand(s_girl_check.getText());

		ButtonGroup gender_group = new ButtonGroup();

		gender_group.add(s_boy_check);
		gender_group.add(s_girl_check);

		if (sex.equals("???")) {
			s_boy_check.setSelected(true);
		} else {
			s_girl_check.setSelected(true);
		}

		// ????????????
		s_school = new JTextField(school);
		if (s_school.getText().equals("")) {
			s_school.setText(" * ?????? ????????? ???) OO???");
			s_school.setForeground(new Color(153, 153, 153));
		}
		s_school.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_school.getText().equals(" * ?????? ????????? ???) OO???")) {
					s_school.setText("");
					s_school.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_school.getText().equals("")) {
					s_school.setText(" * ?????? ????????? ???) OO???");
					s_school.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_school.setForeground(new Color(112, 128, 144));
		s_school.setFont(new Font("??????", Font.PLAIN, 16));
		s_school.setBounds(204, 194, 240, 29);
		contentPane.add(s_school);
		s_school.setColumns(10);

		s_grade = new JTextField(grade);
		s_grade.setFont(new Font("??????", Font.PLAIN, 16));
		s_grade.setForeground(new Color(112, 128, 144));
		s_grade.setColumns(10);
		s_grade.setBounds(204, 242, 146, 29);
		contentPane.add(s_grade);

		s_class = new JTextField(class_);
		if (s_class.getText().equals("")) {
			s_class.setText(" * OO???");
		}
		s_class.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_class.getText().equals(" * OO???")) {
					s_class.setText("");
					s_class.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_class.getText().equals("")) {
					s_class.setText(" * OO???");
					s_class.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_class.setForeground(new Color(112, 128, 144));
		s_class.setFont(new Font("??????", Font.PLAIN, 16));
		s_class.setColumns(10);
		s_class.setBounds(358, 242, 86, 29);
		contentPane.add(s_class);

		// ????????????
		JCheckBox s_mon = new JCheckBox("???");
		s_mon.setFont(new Font("??????", Font.PLAIN, 15));
		s_mon.setHorizontalAlignment(SwingConstants.TRAILING);
		s_mon.setBounds(108, 380, 45, 34);
		contentPane.add(s_mon);
		s_mon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_mon.getText() + " ");

			}
		});

		JCheckBox s_tue = new JCheckBox("???");
		s_tue.setFont(new Font("??????", Font.PLAIN, 15));
		s_tue.setHorizontalAlignment(SwingConstants.CENTER);
		s_tue.setBounds(157, 379, 43, 37);
		contentPane.add(s_tue);
		s_tue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_tue.getText() + " ");
			}
		});

		JCheckBox s_wed = new JCheckBox("???");
		s_wed.setFont(new Font("??????", Font.PLAIN, 15));
		s_wed.setHorizontalAlignment(SwingConstants.CENTER);
		s_wed.setBounds(204, 378, 43, 39);
		contentPane.add(s_wed);
		s_wed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_wed.getText() + " ");
			}
		});

		JCheckBox s_thur = new JCheckBox("???");
		s_thur.setFont(new Font("??????", Font.PLAIN, 15));
		s_thur.setHorizontalAlignment(SwingConstants.CENTER);
		s_thur.setBounds(251, 383, 41, 29);
		contentPane.add(s_thur);
		s_thur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_thur.getText() + " ");
			}
		});

		JCheckBox s_fri = new JCheckBox("???");
		s_fri.setFont(new Font("??????", Font.PLAIN, 15));
		s_fri.setHorizontalAlignment(SwingConstants.CENTER);
		s_fri.setBounds(296, 378, 43, 39);
		contentPane.add(s_fri);
		s_fri.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_fri.getText() + " ");
			}
		});

		JCheckBox s_sat = new JCheckBox("???");
		s_sat.setFont(new Font("??????", Font.PLAIN, 15));
		s_sat.setHorizontalAlignment(SwingConstants.CENTER);
		s_sat.setBounds(341, 383, 46, 29);
		contentPane.add(s_sat);
		s_sat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_sat.getText() + " ");
			}
		});

		JCheckBox s_sun = new JCheckBox("???");
		s_sun.setFont(new Font("??????", Font.PLAIN, 15));
		s_sun.setHorizontalAlignment(SwingConstants.LEFT);
		s_sun.setBounds(391, 383, 43, 29);
		contentPane.add(s_sun);
		s_sun.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.print(s_sun.getText() + " ");
			}
		});

		// ?????? ????????????
		s_call_num = new JTextField(stu_call);
		if (s_call_num.getText().equals("")) {
			s_call_num.setText(" * ?????? ????????????");
		}
		s_call_num.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_call_num.getText().equals(" * ?????? ????????????")) {
					s_call_num.setText("");
					s_call_num.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_call_num.getText().equals("")) {
					s_call_num.setText(" * ?????? ????????????");
					s_call_num.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_call_num.setForeground(new Color(112, 128, 144));
		s_call_num.setFont(new Font("??????", Font.PLAIN, 16));
		s_call_num.setBounds(40, 559, 394, 34);
		contentPane.add(s_call_num);
		s_call_num.setColumns(10);

		// ?????????1 ?????? ????????????
		JComboBox<String> s_who_guardian1 = new JComboBox<String>();
		s_who_guardian1.setBounds(45, 610, 86, 29);
		contentPane.add(s_who_guardian1);
		s_who_guardian1.addItem(" ????????? ");
		s_who_guardian1.addItem(" (???)");
		s_who_guardian1.addItem(" (???)");
		s_who_guardian1.addItem(" (??????)");
		s_who_guardian1.addItem(" (??????)");
		s_who_guardian1.addItem(" (??????)");
		s_who_guardian1.getSelectedItem();

		// ?????????1 ?????? ????????????
		JComboBox<String> s_who_guardian2 = new JComboBox<String>();
		s_who_guardian2.setBounds(45, 649, 86, 29);
		contentPane.add(s_who_guardian2);
		s_who_guardian2.addItem(" ????????? ");
		s_who_guardian2.addItem(" (???)");
		s_who_guardian2.addItem(" (???)");
		s_who_guardian2.addItem(" (??????)");
		s_who_guardian2.addItem(" (??????)");
		s_who_guardian2.addItem(" (??????)");
		s_who_guardian2.getSelectedItem();

		// ?????????1 ??????
		s_guardian1 = new JTextField(G1);
		if (s_guardian1.getText().equals("")) {
			s_guardian1.setText(" * ?????????1 ??????");
		}
		s_guardian1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_guardian1.getText().equals(" * ?????????1 ??????")) {
					s_guardian1.setText("");
					s_guardian1.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_guardian1.getText().equals("")) {
					s_guardian1.setText(" * ?????????1 ??????");
					s_guardian1.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_guardian1.setFont(new Font("??????", Font.PLAIN, 16));
		s_guardian1.setForeground(new Color(112, 128, 144));
		s_guardian1.setColumns(10);
		s_guardian1.setBounds(143, 610, 102, 29);
		contentPane.add(s_guardian1);

		// ????????? 2 ??????
		s_guardian2 = new JTextField(G2);
		if (s_guardian2.getText().equals("")) {
			s_guardian2.setText(" * ?????????2 ??????");
		}
		s_guardian2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_guardian2.getText().equals(" * ?????????2 ??????")) {
					s_guardian2.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_guardian2.getText().equals("")) {
					s_guardian2.setText(" * ?????????2 ??????");
					s_guardian2.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_guardian2.setFont(new Font("??????", Font.PLAIN, 16));
		s_guardian2.setForeground(new Color(112, 128, 144));
		s_guardian2.setColumns(10);
		s_guardian2.setBounds(143, 649, 102, 29);
		contentPane.add(s_guardian2);

		// ???????????? ????????????
		address_ = new JTextField(address);
		if (address_.getText().equals("")) {
			address_.setText(" * ????????? ??????????????????. ???) 00????????? 000??? 000???");
		}
		address_.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (address_.getText().equals(" * ????????? ??????????????????. ???) 00????????? 000??? 000???")) {
					address_.setText("");
					address_.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (address_.getText().equals("")) {
					address_.setText(" * ????????? ??????????????????. ???) 00????????? 000??? 000???");
					address_.setForeground(new Color(153, 153, 153));
				}
			}
		});
		address_.setForeground(new Color(112, 128, 144));
		address_.setFont(new Font("??????", Font.PLAIN, 16));
		address_.setColumns(10);
		address_.setBounds(40, 292, 350, 29);
		contentPane.add(address_);

		// ?????????1 ????????????
		s_guardian1_call_ = new JTextField(G1_call);
		if (s_guardian1_call_.getText().equals("")) {
			s_guardian1_call_.setText(" * ?????????1 ????????????");
		}
		s_guardian1_call_.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_guardian1_call_.getText().equals(" * ?????????1 ????????????")) {
					s_guardian1_call_.setText("");
					s_guardian1_call_.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_guardian1_call_.getText().equals("")) {
					s_guardian1_call_.setText(" * ?????????1 ????????????");
					s_guardian1_call_.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_guardian1_call_.setForeground(new Color(112, 128, 144));
		s_guardian1_call_.setFont(new Font("??????", Font.PLAIN, 15));
		s_guardian1_call_.setColumns(10);
		s_guardian1_call_.setBounds(257, 610, 177, 29);
		contentPane.add(s_guardian1_call_);

		// ?????????2 ????????????
		s_guardian2_call = new JTextField(G2_call);
		if (s_guardian2_call.getText().equals("")) {
			s_guardian2_call.setText(" * ?????????1 ????????????");
		}
		s_guardian2_call.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_guardian2_call.getText().equals(" * ?????????1 ????????????")) {
					s_guardian2_call.setText("");
					s_guardian2_call.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_guardian2_call.getText().equals("")) {
					s_guardian2_call.setText(" * ?????????1 ????????????");
					s_guardian2_call.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_guardian2_call.setForeground(new Color(112, 128, 144));
		s_guardian2_call.setFont(new Font("??????", Font.PLAIN, 15));
		s_guardian2_call.setColumns(10);
		s_guardian2_call.setBounds(257, 649, 177, 29);
		contentPane.add(s_guardian2_call);

		// ??? ??? ??????
		JLabel copyright_SYG_Label = new JLabel("???! ?????? - ?????? ?????? ??????");
		copyright_SYG_Label.setForeground(Color.WHITE);
		copyright_SYG_Label.setFont(new Font("??????????????? ??????", Font.PLAIN, 23));
		copyright_SYG_Label.setOpaque(true);
		copyright_SYG_Label.setHorizontalAlignment(SwingConstants.CENTER);
		copyright_SYG_Label.setBackground(SystemColor.activeCaption);
		copyright_SYG_Label.setBounds(0, 0, 494, 58);
		contentPane.add(copyright_SYG_Label);

		// ????????? ????????? ?????? ??????
		// ?????? URL??? ???????????????????????? ??????????????? ???????????? ??????????????? ??????
		StudentDAO dao = new StudentDAO();
		String pic_path_ = "\"" + new StudentDAO().sum_pay(num) + "\"";
		String pic_path_icon = pic_path_.replace("\"", "/"); // ??????????????? \??? ????????? /??? ?????? ????????? ?????????!

		// ????????? ?????? ????????? ????????????
		ImageIcon pic_stu = new ImageIcon(pic_path_icon);
		Image img_stu_ = pic_stu.getImage();
		Image changeImg = img_stu_.getScaledInstance(170, 180, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImg);

		JLabel pic_path = new JLabel("New label");
		JFileChooser fileChooser = new JFileChooser();
		JButton stu_pic = new JButton();
		stu_pic.setText("?????? +");
		stu_pic.setIcon(changeIcon);

		stu_pic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = JOptionPane.showConfirmDialog(null, "????????? ?????????????????????????", "???!?????? - ?????? ?????? ??????",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.CLOSED_OPTION) {

				} else if (result == JOptionPane.YES_OPTION) {

					JFrame window = new JFrame();

					// ?????? Path??? ?????? ?????? (????????????)
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "//" + "Desktop"));
					// ???????????? ?????????
					FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg & png ??????", "png", "jpg");

					// ???????????? ????????? ??????
					fileChooser.addChoosableFileFilter(filter);

					// ???????????? ??????????????? ??? ??????
					int pic = fileChooser.showSaveDialog(window);

					if (pic == JFileChooser.APPROVE_OPTION) {

						// ????????? ????????? ?????? ??????

						selectedFile = fileChooser.getSelectedFile(); // ???????????? ?????????????????? ???????????? ??????????????? ?????????
						String filePath = fileChooser.getSelectedFile().getPath();
						stu_pic.setIcon(new ImageIcon(filePath));

						// ????????? ?????? ????????? ????????????
						ImageIcon pic2 = new ImageIcon(filePath);
						Image img = pic2.getImage();
						Image changeImg = img.getScaledInstance(170, 180, Image.SCALE_SMOOTH);
						ImageIcon changeIcon = new ImageIcon(changeImg);
						stu_pic.setIcon(changeIcon);

						// ?????? ?????? - DB??? ????????????????????? ?????? ????????? ??? ?????????.
						// System.out.println(selectedFile);
						System.out.println(filePath.toString());
						pic_path.setText(filePath.toString());

					}
				}
			}
		});

		stu_pic.setFont(new Font("??????", Font.PLAIN, 21));
		stu_pic.setBackground(new Color(255, 255, 255));
		stu_pic.setBounds(37, 96, 146, 175);
		contentPane.add(stu_pic);

		JLabel lblNewLabel = new JLabel("* ????????? ??????????????? ?????? ?????????????????????.");
		lblNewLabel.setForeground(new Color(255, 69, 0));
		lblNewLabel.setFont(new Font("??????", Font.BOLD, 15));
		lblNewLabel.setBounds(37, 62, 365, 26);
		contentPane.add(lblNewLabel);

		JLabel day_label = new JLabel("????????????");
		day_label.setHorizontalAlignment(SwingConstants.CENTER);
		day_label.setFont(new Font("??????", Font.BOLD, 14));
		day_label.setBounds(37, 380, 75, 35);
		contentPane.add(day_label);

		JTextArea s_memo = new JTextArea(memo); // ????????? ?????? ????????????????????? ????????????????????? ????????? ?????????.
		if (s_memo.getText().equals("")) {
			s_memo.setText(" * ???????????? ??????");
		}
		s_memo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (s_memo.getText().equals(" * ???????????? ??????")) {
					s_memo.setText("");
					s_memo.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (s_memo.getText().equals("")) {
					s_memo.setText(" * ???????????? ??????");
					s_memo.setForeground(new Color(153, 153, 153));
				}
			}
		});
		s_memo.setLineWrap(true); // ????????? ?????? ?????? ???????????? ???????????? ??????
		s_memo.setForeground(new Color(112, 128, 144));
		s_memo.setFont(new Font("??????", Font.PLAIN, 16));
		s_memo.setToolTipText("");
		s_memo.setBounds(40, 467, 394, 82);
		contentPane.add(s_memo);
		s_memo.setColumns(10);

		// ????????????
		JButton s_save_btn = new JButton("??????");
		s_save_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (when_daycome == "") {
					when_daycome = "";
				}
				// ?????? ????????? ???????????? ???????????? ???????????? ???????????? ??????
				if (s_school.getText().equals(" * ?????? ????????? ???) OO???")) {
					s_school.setText("");
				}
				if (s_grade.getText().equals(" * ?????? (????????? ??????)")) {
					s_grade.setText("");
				}
				if (s_class.getText().equals(" * OO???")) {
					s_class.setText("");
				}
				if (address_.getText().equals(" * ????????? ??????????????????. ???) 00????????? 000??? 000???")) {
					address_.setText("");
				}
				if (s_memo.getText().equals(" * ???????????? ??????")) {
					s_memo.setText("");
				}
				if (s_call_num.getText().equals(" * ?????? ??????????????? ??????????????????. ???) 010-0000-0000")) {
					s_call_num.setText("");
				}
				if (s_guardian1.getText().equals(" * ?????????1 ??????")) {
					s_guardian1.setText("");
				}
				if (s_guardian1_call_.getText().equals(" * phone ???)010-0000-0000")) {
					s_guardian1_call_.setText("");
				}
				if (s_guardian2.getText().equals(" * ?????????2 ??????")) {
					s_guardian2.setText("");
				}
				if (s_guardian2_call.getText().equals(" * phone ???)010-0000-0000")) {
					s_guardian2_call.setText("");
				}

				if (s_mon.isSelected() == true) {
					when_daycome += "??? ";
				}

				if (s_tue.isSelected() == true) {
					when_daycome += "??? ";
				}

				if (s_wed.isSelected() == true) {
					when_daycome += "??? ";
				}

				if (s_thur.isSelected() == true) {
					when_daycome += "??? ";
				}

				if (s_fri.isSelected() == true) {
					when_daycome += "??? ";
				}
				if (s_sat.isSelected() == true) {
					when_daycome += "??? ";
				}

				if (s_sun.isSelected() == true) {
					when_daycome += "??? ";
				}
				when_daycome = when_daycome.substring(0, when_daycome.length());

				char temp = 0;
				boolean flag = true;
				for (int i = 0; i < s_grade.getText().length(); i++) {
					temp = s_grade.getText().charAt(i); // ????????? str??? ????????? ????????? temp??? ??????
					if (temp < '0' || temp > '9') {// temp??? ?????? '0'????????? '9'?????? ??? ??????
						flag = false;
						break;
					}
				}
				if (s_name.getText().equals(" * ????????? ??????????????????.")) {
					JOptionPane.showMessageDialog(null, "????????? ?????? ???????????????.\r\n????????? ??????????????????.", "?????? ??????!",
							JOptionPane.ERROR_MESSAGE);
				} else if (s_name.getText().length() > 12) {
					JOptionPane.showMessageDialog(null, "????????? 10?????? ????????? ??????????????????.", "?????? ??????!", JOptionPane.ERROR_MESSAGE);
				} else {
					// ????????? ???????????? ???????????? ???????????? ??????????????? ?????? ??????
					JLabel gender = new JLabel();
					if (s_boy_check.isSelected()) {
						gender = new JLabel(s_boy_check.getText());
					} else if (s_girl_check.isSelected()) {
						gender = new JLabel(s_girl_check.getText());
					}

					StudentVo vo = new StudentVo(num, s_name.getText(), gender.getText(), age, s_school.getText(),
							s_grade.getText(), s_class.getText(), birth_field.getText(), when_daycome,
							address_.getText(), enter_date, s_call_num.getText(),
							s_guardian1.getText() + s_who_guardian1.getSelectedItem().toString(),
							s_guardian1_call_.getText(),
							s_guardian2.getText() + s_who_guardian2.getSelectedItem().toString(),
							s_guardian2_call.getText(), s_memo.getText(), pic_path.getText());

					dao.Update_stuInfo(vo);
					dispose();

					management_Student manage = new management_Student();
					manage.setVisible(true);
				}
			}

		});
		s_save_btn.setFont(new Font("???????????????", Font.PLAIN, 16));
		s_save_btn.setBounds(176, 700, 144, 46);
		contentPane.add(s_save_btn);

		stunum = new JLabel(num);
		stunum.setForeground(new Color(128, 0, 0));
		stunum.setHorizontalAlignment(SwingConstants.CENTER);
		stunum.setOpaque(true);
		stunum.setBackground(Color.white);
		stunum.setFont(new Font("??????", Font.PLAIN, 17));
		stunum.setBounds(40, 341, 404, 29);
		contentPane.add(stunum);

		birth_field = new JTextField(birth);
		birth_field.setForeground(new Color(112, 128, 144));
		birth_field.setFont(new Font("??????", Font.PLAIN, 16));
		birth_field.setColumns(10);
		birth_field.setBounds(283, 146, 161, 29);
		contentPane.add(birth_field);

		enter_field = new JTextField(enter_date);
		enter_field.setForeground(new Color(112, 128, 144));
		enter_field.setFont(new Font("??????", Font.PLAIN, 16));
		enter_field.setColumns(10);
		enter_field.setBounds(214, 423, 177, 29);
		contentPane.add(enter_field);

		JLabel birth_label = new JLabel("???????????? :");
		birth_label.setHorizontalAlignment(SwingConstants.RIGHT);
		birth_label.setLabelFor(birth_field);
		birth_label.setFont(new Font("??????", Font.BOLD, 15));
		birth_label.setBounds(204, 146, 75, 29);
		contentPane.add(birth_label);

		JLabel birth_label_1 = new JLabel("?????? ????????? :");
		birth_label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		birth_label_1.setFont(new Font("??????", Font.BOLD, 15));
		birth_label_1.setBounds(100, 423, 111, 29);
		contentPane.add(birth_label_1);

	}
}