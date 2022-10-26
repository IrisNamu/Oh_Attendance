
import java.awt.EventQueue;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.EventHandler;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

import database.PayDAO;
import database.StudentDAO;
import database.StudentVo;

import java.awt.GridLayout;
import java.awt.Scrollbar;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;

@SuppressWarnings("serial")
public class attendance_Main extends JFrame implements MouseListener {
	private StudentDAO dao;

	private JPanel contentPane_;
	private JTextField search_field;
	private JTable table_stuList;
	private String day;
	private int values;
	private JLabel name;
	private JLabel num;
	private JLabel age;
	private JButton who_absent;
	private JButton who_attendance;
	private JButton who_Did_Not_attend;


	public attendance_Main() {
		
		dao = new StudentDAO();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Home_Login.class.getResource("/img/app_icon.png")));

		setTitle("오! 출석 - 학생관리시스템");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 752);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane_ = new JPanel();
		contentPane_.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_);
		contentPane_.setLayout(null);

		JPanel who_early_leave = new JPanel();
		who_early_leave.setBounds(0, 0, 450, 257);
		who_early_leave.setBackground(Color.WHITE);
		contentPane_.add(who_early_leave);
		who_early_leave.setLayout(null);

		JPanel chooseDateCheck = new JPanel();
		chooseDateCheck.setBackground(new Color(255, 224, 172));
		chooseDateCheck.setBounds(0, 71, 454, 140);
		who_early_leave.add(chooseDateCheck);
		chooseDateCheck.setLayout(null);

		// 요일구하기
		Calendar cal = Calendar.getInstance();
		int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		String day = "";
		switch (dayOfWeek) {
		case 1:
			day = "일";
			break;
		case 2:
			day = "월";
			break;
		case 3:
			day = "화";
			break;
		case 4:
			day = "수";
			break;
		case 5:
			day = "목";
			break;
		case 6:
			day = "금";
			break;
		case 7:
			day = "토";
			break;
		}

		// 패널에 부착될 시간.
		Date d = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy년 MM월 dd일");
		SimpleDateFormat time = new SimpleDateFormat("HH시 mm분");

		// 날짜 시간 요일
		JLabel dateLab = new JLabel("date");
		dateLab.setBackground(Color.WHITE);
		dateLab.setFont(new Font("배달의민족 주아", Font.PLAIN, 30));
		dateLab.setBounds(0, 22, 454, 45);
		chooseDateCheck.add(dateLab);
		dateLab.setHorizontalAlignment(SwingConstants.CENTER);
		dateLab.setText(date.format(d) + " (" + day + ") " + time.format(d));

		JLabel date1 = new JLabel();
		date1.setText(date.format(d));

		num = new JLabel();
		name = new JLabel();
		age = new JLabel();

		/**
		 * [메인화면 출석] DB연동 수강생 리스트 불러오기(이름순)(전체)
		 */

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 254, 450, 461);
		String[] header = new String[] { "출석번호", "이름", "나이", "등원요일" };
		String[][] data = dao.will_come(day, date1.getText());

		contentPane_.add(scrollPane);
		table_stuList = new JTable();
		table_stuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_stuList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int row = table_stuList.getSelectedRow();
				int col = table_stuList.getSelectedColumn();
				for (int i = 0; i < table_stuList.getColumnCount(); i++) {
				}
				num.setText((String) table_stuList.getModel().getValueAt(row, 0));
				name.setText((String) table_stuList.getModel().getValueAt(row, 1));
				age.setText((String) table_stuList.getModel().getValueAt(row, 2));

				attendance_alert_page a = new attendance_alert_page(num.getText(), name.getText(), age.getText());
				a.setVisible(true);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

		});
		table_stuList.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
		table_stuList.setModel(new DefaultTableModel(data, header));
		table_stuList.repaint();
		scrollPane.setViewportView(table_stuList);
		table_stuList.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 17));
		table_stuList.setRowHeight(80);
		table_stuList.getColumn("등원요일").setPreferredWidth(200);
		table_stuList.setShowVerticalLines(false); // 수평 보더라인 지우기
		table_stuList.getTableHeader().setReorderingAllowed(false);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

		TableColumnModel tcm = table_stuList.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

		// 전체 열에 지정
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
			// 컬럼모델에서 컬럼의 갯수만큼 컬럼을 가져와 for문을 이용하여
			// 각각의 셀렌더러를 아까 생성한 dtcr에 set해줌
		}

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(255, 204, 204));
		separator.setBackground(new Color(255, 204, 204));
		separator.setBounds(0, 200, 454, -5);
		who_early_leave.add(separator);

		// 상단메뉴바
		JPanel Menubar = new JPanel();
		Menubar.setBackground(new Color(19, 25, 53));
		Menubar.setBounds(0, 0, 450, 70);
		who_early_leave.add(Menubar);
		Menubar.setLayout(null);

		JButton attendanceMenu = new JButton("");
		attendanceMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attendance_Main att = new attendance_Main();
				dispose();
				att.setVisible(true);
			}
		});
		attendanceMenu.setIcon(new ImageIcon(attendance_Main.class.getResource("/img/click_att_menu.png")));
		attendanceMenu.setBounds(0, 1, 90, 70);
		Menubar.add(attendanceMenu);
		attendanceMenu.setBackground(new Color(19, 25, 53));

		JButton manageStudent_Menu = new JButton("");
		manageStudent_Menu.setIcon(new ImageIcon(attendance_Main.class.getResource("/img/manage_menu.png")));
		manageStudent_Menu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				management_Student manage = new management_Student();
				dispose();
				manage.setVisible(true);
			}
		});
		manageStudent_Menu.setBackground(new Color(19, 25, 53));
		manageStudent_Menu.setBounds(91, 0, 90, 70);
		Menubar.add(manageStudent_Menu);

		JButton directly_att = new JButton("");
		directly_att.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AttendanceNumber num = new AttendanceNumber();
				num.setVisible(true);
			}
		});
		directly_att.setIcon(new ImageIcon(attendance_Main.class.getResource("/img/directly_att.png")));
		directly_att.setBackground(new Color(19, 25, 53));
		directly_att.setBounds(182, 0, 90, 70);
		Menubar.add(directly_att);

		JButton pay_menubar = new JButton("");
		pay_menubar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				pay_manage pay = new pay_manage();
				pay.setVisible(true);
			}
		});
		pay_menubar.setIcon(new ImageIcon(attendance_Main.class.getResource("/img/pay_menu.png")));
		pay_menubar.setBackground(new Color(19, 25, 53));
		pay_menubar.setBounds(273, 0, 90, 70);
		Menubar.add(pay_menubar);

		JButton statistics_menubar = new JButton("");
		statistics_menubar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statistics_manage sta = new Statistics_manage();
				dispose();
				sta.setVisible(true);
			}
		});
		statistics_menubar.setIcon(new ImageIcon(attendance_Main.class.getResource("/img/statistics_menu.png")));
		statistics_menubar.setBackground(new Color(19, 25, 53));
		statistics_menubar.setBounds(360, 0, 90, 70);
		Menubar.add(statistics_menubar);

		/*
		 * 전체버튼 누르면 나오는 테이블
		 * 
		 */

		new StudentDAO().attendance_student(day, date1.getText());
		String[][] num_all = new StudentDAO().attendance_table_all(day);
		JButton all_student = new JButton("전체 " + num_all.length);
		all_student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 요일구하기
				Calendar cal = Calendar.getInstance();
				int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				String day = "";
				switch (dayOfWeek) {
				case 1:
					day = "일";
					break;
				case 2:
					day = "월";
					break;
				case 3:
					day = "화";
					break;
				case 4:
					day = "수";
					break;
				case 5:
					day = "목";
					break;
				case 6:
					day = "금";
					break;
				case 7:
					day = "토";
					break;
				}

				String[] header = new String[] { "출석번호", "이름", "나이", "등원요일" };
				String[][] data = dao.attendance_table_all(day);

				contentPane_.add(scrollPane);
				table_stuList = new JTable();
				table_stuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table_stuList.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = table_stuList.getSelectedRow();
						int col = table_stuList.getSelectedColumn();
						for (int i = 0; i < table_stuList.getColumnCount(); i++) {
						}
						num.setText((String) table_stuList.getModel().getValueAt(row, 0));
						name.setText((String) table_stuList.getModel().getValueAt(row, 1));
						age.setText((String) table_stuList.getModel().getValueAt(row, 2));

						attendance_alert_page a = new attendance_alert_page(num.getText(), name.getText(),
								age.getText());
						a.setVisible(true);

						who_attendance.repaint();
						who_absent.repaint();
						who_Did_Not_attend.repaint();

					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}

				});

				table_stuList.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
				table_stuList.setModel(new DefaultTableModel(data, header));
				table_stuList.repaint();
				scrollPane.setViewportView(table_stuList);
				table_stuList.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 17));
				// 테이블 높이 넓이 조정해주기
				table_stuList.setRowHeight(80);
				table_stuList.getColumn("등원요일").setPreferredWidth(200);
				table_stuList.setShowVerticalLines(false); // 수평 보더라인 지우기
				// table_stuList.setEnabled(false); // 수정불가능
				table_stuList.getTableHeader().setReorderingAllowed(false); // 테이블 컬럼의 이동을 방지한다. 이거 안쓰면 마우스로 드로그 앤 드롭으로
																			// 엉망진창이 될수
																			// 있다.

				DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
				dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

				TableColumnModel tcm = table_stuList.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

				// 전체 열에 지정
				for (int i = 0; i < tcm.getColumnCount(); i++) {
					tcm.getColumn(i).setCellRenderer(dtcr);
					// 컬럼모델에서 컬럼의 갯수만큼 컬럼을 가져와 for문을 이용하여
					// 각각의 셀렌더러를 아까 생성한 dtcr에 set해줌
				}
			}
		});
		all_student.setBackground(Color.DARK_GRAY);
		all_student.setForeground(Color.WHITE);
		all_student.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		all_student.setBounds(332, 86, 89, 38);
		chooseDateCheck.add(all_student);

		/*
		 * 등원버튼 누르면 나오는 테이블
		 * 
		 */
		new StudentDAO().attendance_student(day, date1.getText());
		String[][] num_att = new StudentDAO().attendance_student(day, date1.getText());
		who_attendance = new JButton("등원 " + num_att.length);
		who_attendance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 요일구하기
				Calendar cal = Calendar.getInstance();
				int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				String day = "";
				switch (dayOfWeek) {
				case 1:
					day = "일";
					break;
				case 2:
					day = "월";
					break;
				case 3:
					day = "화";
					break;
				case 4:
					day = "수";
					break;
				case 5:
					day = "목";
					break;
				case 6:
					day = "금";
					break;
				case 7:
					day = "토";
					break;
				}

				// DB연동 수강생 리스트 불러오기
				String[] header = new String[] { "출석번호", "나이", "이름", "등원여부", "등원시간", };
				String[][] data = dao.attendance_student(day, date1.getText());
				scrollPane.setBounds(0, 255, 450, 460);
				contentPane_.add(scrollPane);
				table_stuList = new JTable();

				table_stuList.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
				table_stuList.setModel(new DefaultTableModel(data, header));
				table_stuList.repaint();
				scrollPane.setViewportView(table_stuList);
				table_stuList.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 17));
				table_stuList.setRowHeight(80);
				table_stuList.getColumn("등원시간").setPreferredWidth(200);
				table_stuList.setShowVerticalLines(false); // 수평 보더라인 지우기
				// table_stuList.setEnabled(false); // 수정불가능
				table_stuList.getTableHeader().setReorderingAllowed(false);
				DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
				dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

				TableColumnModel tcm = table_stuList.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

				// 전체 열에 지정
				for (int i = 0; i < tcm.getColumnCount(); i++) {
					tcm.getColumn(i).setCellRenderer(dtcr);
					// 컬럼모델에서 컬럼의 갯수만큼 컬럼을 가져와 for문을 이용하여
					// 각각의 셀렌더러를 아까 생성한 dtcr에 set해줌

					table_stuList.revalidate();
					table_stuList.repaint();
				}
			}
		});
		who_attendance.setBackground(new Color(51, 153, 204));
		who_attendance.setForeground(new Color(255, 255, 255));
		who_attendance.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		who_attendance.setBounds(130, 86, 89, 38);
		chooseDateCheck.add(who_attendance);

//absence_stu

		/*
		 * 미등원버튼 //
		 */

		new StudentDAO().will_come(day, date1.getText());
		String[][] num_will = new StudentDAO().will_come(day, date1.getText());
		who_Did_Not_attend = new JButton("미등 " + num_will.length);
		who_Did_Not_attend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 요일구하기
				Calendar cal = Calendar.getInstance();
				int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				String day = "";
				switch (dayOfWeek) {
				case 1:
					day = "일";
					break;
				case 2:
					day = "월";
					break;
				case 3:
					day = "화";
					break;
				case 4:
					day = "수";
					break;
				case 5:
					day = "목";
					break;
				case 6:
					day = "금";
					break;
				case 7:
					day = "토";
					break;
				}

				// DB연동 수강생 리스트 불러오기
				String[] header = new String[] { "출석번호", "이름", "나이", "등원요일" };
				String[][] data = dao.will_come(day, date1.getText());

				contentPane_.add(scrollPane);
				table_stuList = new JTable();
				table_stuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table_stuList.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {

						int row = table_stuList.getSelectedRow();
						int col = table_stuList.getSelectedColumn();
						for (int i = 0; i < table_stuList.getColumnCount(); i++) {
						}
						num.setText((String) table_stuList.getModel().getValueAt(row, 0));
						name.setText((String) table_stuList.getModel().getValueAt(row, 1));
						age.setText((String) table_stuList.getModel().getValueAt(row, 2));

						attendance_alert_page a = new attendance_alert_page(num.getText(), name.getText(),
								age.getText());
						a.setVisible(true);

					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub

					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub

					}

				});
				table_stuList.revalidate();
				table_stuList.repaint();
				table_stuList.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
				table_stuList.setModel(new DefaultTableModel(data, header));
				table_stuList.repaint();
				scrollPane.setViewportView(table_stuList);
				table_stuList.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 17));
				table_stuList.setRowHeight(80);
				table_stuList.getColumn("등원요일").setPreferredWidth(200);
				table_stuList.setShowVerticalLines(false); // 수평 보더라인 지우기
				// table_stuList.setEnabled(false); // 수정불가능
				table_stuList.getTableHeader().setReorderingAllowed(false);
				DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
				dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

				TableColumnModel tcm = table_stuList.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

				// 전체 열에 지정
				for (int i = 0; i < tcm.getColumnCount(); i++) {
					tcm.getColumn(i).setCellRenderer(dtcr);
					// 컬럼모델에서 컬럼의 갯수만큼 컬럼을 가져와 for문을 이용하여
					// 각각의 셀렌더러를 아까 생성한 dtcr에 set해줌
				}
			}
		});
		who_Did_Not_attend.setBackground(new Color(51, 204, 204));
		who_Did_Not_attend.setForeground(new Color(255, 255, 255));
		who_Did_Not_attend.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		who_Did_Not_attend.setBounds(29, 86, 89, 38);
		chooseDateCheck.add(who_Did_Not_attend);

		new StudentDAO().absence_stu(day, date1.getText());
		String[][] num_abs = new StudentDAO().absence_stu(day, date1.getText());
		who_absent = new JButton("결석 " + num_abs.length);
		who_absent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 요일구하기
				Calendar cal = Calendar.getInstance();
				int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
				String day = "";
				switch (dayOfWeek) {
				case 1:
					day = "일";
					break;
				case 2:
					day = "월";
					break;
				case 3:
					day = "화";
					break;
				case 4:
					day = "수";
					break;
				case 5:
					day = "목";
					break;
				case 6:
					day = "금";
					break;
				case 7:
					day = "토";
					break;
				}

				// DB연동 수강생 리스트 불러오기
				String[] header = new String[] { "출석번호", "이름", "나이", "결석날짜", "결석사유", };
				String[][] data = dao.absence_stu(day, date1.getText());

				scrollPane.setBounds(0, 250, 450, 460);
				contentPane_.add(scrollPane);
				table_stuList = new JTable();
				table_stuList.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
				table_stuList.setModel(new DefaultTableModel(data, header));
				table_stuList.repaint();
				scrollPane.setViewportView(table_stuList);
				table_stuList.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

				table_stuList.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
				table_stuList.setRowHeight(80);
				table_stuList.getColumn("이름").setPreferredWidth(70);
				table_stuList.getColumn("출석번호").setPreferredWidth(100);
				table_stuList.getColumn("결석날짜").setPreferredWidth(240);
				table_stuList.getColumn("결석사유").setPreferredWidth(250);
				table_stuList.setShowVerticalLines(false); // 수평 보더라인 지우기
				table_stuList.getTableHeader().setReorderingAllowed(false);
				DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
				dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

				TableColumnModel tcm = table_stuList.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

				// 전체 열에 지정
				for (int i = 0; i < tcm.getColumnCount(); i++) {
					tcm.getColumn(i).setCellRenderer(dtcr);
					// 컬럼모델에서 컬럼의 갯수만큼 컬럼을 가져와 for문을 이용하여
					// 각각의 셀렌더러를 아까 생성한 dtcr에 set해줌
					table_stuList.revalidate();
					table_stuList.repaint();
				}
			}
		});
		who_absent.setBackground(new Color(255, 0, 0));
		who_absent.setForeground(new Color(255, 255, 255));
		who_absent.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		who_absent.setBounds(231, 85, 89, 38);
		chooseDateCheck.add(who_absent);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 145, 454, 33);
		chooseDateCheck.add(separator_1);
		separator_1.setBackground(new Color(255, 204, 204));

		JPanel date_panel = new JPanel();
		date_panel.setBackground(new Color(255, 224, 172));
		date_panel.setBounds(12, 10, 430, 61);

		JPanel panel = new JPanel();
		panel.setBounds(0, 212, 454, 39);
		who_early_leave.add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("검색");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// 검색하면 테이블에 해당 검색결과 나타내기
				DefaultTableModel model = (DefaultTableModel) table_stuList.getModel();
				model.setRowCount(0);

				String word = search_field.getText();
				if (word.length() < 1) {
					return;
				}
				StudentDAO dao = new StudentDAO();
				ArrayList<StudentVo> list = dao.search(word);

				for (StudentVo vo : list) {
					String[] data = { vo.getStuNumber(), vo.getStuName(), vo.getAge(), vo.getWhen_day() };
					model.addRow(data);// 이걸 적어줘야 테이블에 추가가 된다.
				}

				table_stuList.setModel(model);
			}
		});
		btnNewButton.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		btnNewButton.setBackground(new Color(176, 196, 222));
		btnNewButton.setBounds(359, 0, 83, 35);
		panel.add(btnNewButton);

		search_field = new JTextField();
		search_field.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (search_field.getText().equals(" 검색어를 입력해주세요.")) {
					search_field.setText("");
					search_field.setForeground(new Color(153, 153, 153));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (search_field.getText().equals("")) {
					search_field.setText(" 검색어를 입력해주세요.");
					search_field.setForeground(new Color(153, 153, 153));
				}
			}

		});
		search_field.setBounds(5, 1, 347, 35);
		panel.add(search_field);
		search_field.setFont(new Font("굴림", Font.PLAIN, 15));
		search_field.setForeground(new Color(105, 105, 105));
		search_field.setBackground(new Color(255, 250, 250));
		search_field.setText(" 검색어를 입력해주세요.");
		search_field.setColumns(10);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}