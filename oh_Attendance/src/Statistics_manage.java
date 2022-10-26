
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import database.PayDAO;
import database.PayVo;
import database.StudentDAO;
import database.StudentVo;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.SystemColor;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JDayChooser;

public class Statistics_manage extends JFrame {

	private JPanel contentPane;
	private JTable table_Income;
	private JTable table_Income_1;
	private PayDAO daoP;
	private StudentDAO daoS;

	private int January22;
	private int February22;
	private int March22;
	private int April22;
	private int May22;
	private int June22;
	private int July22;
	private int August22;
	private int September22;
	private int October22;
	private int November22;
	private int December22;

	private int S_January22;
	private int S_February22;
	private int S_March22;
	private int S_April22;
	private int S_May22;
	private int S_June22;
	private int S_July22;
	private int S_August22;
	private int S_September22;
	private int S_October22;
	private int S_November22;
	private int S_December22;

	private String date_when;

	private JTabbedPane tabbedPane;
	private JPanel Income_menu;
	private JPanel new_menu;
	private JScrollPane scrollPane_Income;
	private JScrollPane scrollPane_new;

	// private String month2022;
	String[][] data;
	ArrayList<PayVo> value;
	private JTable new_stu_table;
	private JTable att_table;

	
	public Statistics_manage() {
		PayDAO daoP = new PayDAO();
		StudentDAO daoS = new StudentDAO();

		setIconImage(Toolkit.getDefaultToolkit().getImage(Home_Login.class.getResource("/img/app_icon.png")));
		setFont(new Font("배달의민족 주아", Font.PLAIN, 12));
		setTitle("오! 출석 - 학생관리시스템 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 466, 752);
		setLocationRelativeTo(null);
		setResizable(false);
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel Menubar = new JPanel();
		Menubar.setBounds(0, 0, 450, 70);
		Menubar.setBackground(new Color(19, 25, 53));
		contentPane.add(Menubar);
		Menubar.setLayout(null);

		JButton attendanceMenu = new JButton("");
		attendanceMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				attendance_Main att = new attendance_Main();
				dispose();
				att.setVisible(true);
			}
		});
		attendanceMenu.setIcon(new ImageIcon(management_Student.class.getResource("/img/att_menu.png")));
		attendanceMenu.setBounds(0, 1, 90, 70);
		Menubar.add(attendanceMenu);
		attendanceMenu.setBackground(new Color(19, 25, 53));

		JButton manageStudent_Menu = new JButton("");
		manageStudent_Menu.setIcon(new ImageIcon(Statistics_manage.class.getResource("/img/manage_menu.png")));
		manageStudent_Menu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				management_Student manage = new management_Student(); // 홈화면 호출
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
		statistics_menubar.setIcon(new ImageIcon(Statistics_manage.class.getResource("/img/click_statistics.png")));
		statistics_menubar.setBackground(new Color(19, 25, 53));
		statistics_menubar.setBounds(360, 0, 90, 70);
		Menubar.add(statistics_menubar);

		// 월별 수입 통계
		String[] header = new String[] { "년/월", "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월",
				"12월" };

		DecimalFormat decFormat = new DecimalFormat("###,###,###");
		January22 = new PayDAO().sum_pay("2022-1");
		February22 = new PayDAO().sum_pay("2022-2");
		March22 = new PayDAO().sum_pay("2022-3");
		April22 = new PayDAO().sum_pay("2022-4");
		May22 = new PayDAO().sum_pay("2022-5");
		June22 = new PayDAO().sum_pay("2022-6");
		July22 = new PayDAO().sum_pay("2022-7");
		August22 = new PayDAO().sum_pay("2022-8");
		September22 = new PayDAO().sum_pay("2022-9");
		October22 = new PayDAO().sum_pay("2022-10");
		November22 = new PayDAO().sum_pay("2022-11");
		December22 = new PayDAO().sum_pay("2022-12");

		String[][] income_data = new String[3][13];

		// 2022년
		income_data[0][0] = "2022년";
		income_data[0][1] = decFormat.format(January22) + "원";
		income_data[0][2] = decFormat.format(February22) + "원";
		income_data[0][3] = decFormat.format(March22) + "원";
		income_data[0][4] = decFormat.format(April22) + "원";
		income_data[0][5] = decFormat.format(May22) + "원";
		income_data[0][6] = decFormat.format(June22) + "원";
		income_data[0][7] = decFormat.format(July22) + "원";
		income_data[0][8] = decFormat.format(August22) + "원";
		income_data[0][9] = decFormat.format(September22) + "원";
		income_data[0][10] = decFormat.format(October22) + "원";
		income_data[0][11] = decFormat.format(November22) + "원";
		income_data[0][12] = decFormat.format(December22) + "원";

		// 2021년
		income_data[1][0] = "2021년";

		// 2020년
		income_data[2][0] = "2020년";

		// 월별 신입생 통계
		String[][] new_data = new String[3][13];

		S_January22 = new StudentDAO().count_new("2022-1");
		S_February22 = new StudentDAO().count_new("2022-2");
		S_March22 = new StudentDAO().count_new("2022-3");
		S_April22 = new StudentDAO().count_new("2022-4");
		S_May22 = new StudentDAO().count_new("2022-5");
		S_June22 = new StudentDAO().count_new("2022-6");
		S_July22 = new StudentDAO().count_new("2022-7");
		S_August22 = new StudentDAO().count_new("2022-8");
		S_September22 = new StudentDAO().count_new("2022-9");
		S_October22 = new StudentDAO().count_new("2022-10");
		S_November22 = new StudentDAO().count_new("2022-12");
		S_December22 = new StudentDAO().count_new("2022-12");

		// 2022년
		new_data[0][0] = "2022년";
		new_data[0][1] = S_January22 + "명";
		new_data[0][2] = S_February22 + "명";
		new_data[0][3] = S_March22 + "명";
		new_data[0][4] = S_April22 + "명";
		new_data[0][5] = S_May22 + "명";
		new_data[0][6] = S_June22 + "명";
		new_data[0][7] = S_July22 + "명";
		new_data[0][8] = S_August22 + "명";
		new_data[0][9] = S_September22 + "명";
		new_data[0][10] = S_October22 + "명";
		new_data[0][11] = S_November22 + "명";
		new_data[0][12] = S_December22 + "명";

		// 2021년
		new_data[1][0] = "2021년";

		// 2020년
		new_data[2][0] = "2020년";

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("배달의민족 주아", Font.PLAIN, 21));
		tabbedPane.setForeground(new Color(0, 0, 128));
		tabbedPane.setBounds(10, 80, 428, 623);
		contentPane.add(tabbedPane);
		new_menu = new JPanel();
		new_menu.setBackground(new Color(255, 255, 255));
		JPanel att_menu = new JPanel();
		att_menu.setBackground(Color.WHITE);
		Income_menu = new JPanel(); // JPanel 생성
		Income_menu.setBackground(Color.WHITE);
		tabbedPane.add(Income_menu, "월 수입");
		Income_menu.setLayout(null);

		scrollPane_Income = new JScrollPane();
		scrollPane_Income.setBounds(12, 439, 399, 145);
		Income_menu.add(scrollPane_Income);
		scrollPane_Income.setEnabled(false);
		scrollPane_Income.setViewportView(table_Income);

		// 수입 그래프 붙여주기
		final CategoryDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel((chart));
		chartPanel.setBounds(0, 60, 423, 369);
		Income_menu.add(chartPanel);

		table_Income_1 = new JTable();
		table_Income_1.setEnabled(false);
		table_Income_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		table_Income_1.repaint();
		scrollPane_Income.setViewportView(table_Income_1);
		table_Income_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Income_1.setModel(new DefaultTableModel(income_data, header));
		table_Income_1.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 17));

		table_Income_1.setRowHeight(30);
		table_Income_1.getColumn("1월").setPreferredWidth(160);
		table_Income_1.getColumn("2월").setPreferredWidth(160);
		table_Income_1.getColumn("3월").setPreferredWidth(160);
		table_Income_1.getColumn("4월").setPreferredWidth(160);
		table_Income_1.getColumn("5월").setPreferredWidth(160);
		table_Income_1.getColumn("6월").setPreferredWidth(160);
		table_Income_1.getColumn("7월").setPreferredWidth(160);
		table_Income_1.getColumn("8월").setPreferredWidth(160);
		table_Income_1.getColumn("9월").setPreferredWidth(160);
		table_Income_1.getColumn("10월").setPreferredWidth(160);
		table_Income_1.getColumn("11월").setPreferredWidth(160);
		table_Income_1.getColumn("12월").setPreferredWidth(160);

		JLabel lblNewLabel = new JLabel(" 수입 통계");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("배달의민족 주아", Font.PLAIN, 34));
		lblNewLabel.setBounds(33, 4, 228, 55);
		Income_menu.add(lblNewLabel);

		JButton more_income = new JButton("자세히 보기");
		more_income.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Income_big inBig = new Income_big();
				inBig.setVisible(rootPaneCheckingEnabled);

			}
		});
		more_income.setForeground(new Color(255, 250, 250));
		more_income.setBackground(new Color(0, 0, 128));
		more_income.setFont(new Font("배달의민족 주아", Font.PLAIN, 17));
		more_income.setBounds(279, 10, 132, 49);
		Income_menu.add(more_income);

		TableColumnModel tcm = table_Income_1.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
		tabbedPane.add(new_menu, "월별 신규생");
		new_menu.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("신규생 통계");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 34));
		lblNewLabel_1.setBounds(33, 4, 228, 55);
		new_menu.add(lblNewLabel_1);

		JButton more_income_1 = new JButton("자세히 보기");
		more_income_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				New_big newbig = new New_big();
				newbig.setVisible(true);

			}
		});
		more_income_1.setForeground(new Color(255, 250, 250));
		more_income_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 17));
		more_income_1.setBackground(new Color(0, 0, 128));
		more_income_1.setBounds(279, 10, 132, 49);
		new_menu.add(more_income_1);

		// 월별 신규생
		scrollPane_new = new JScrollPane();
		scrollPane_new.setBounds(12, 439, 399, 145);
		new_menu.add(scrollPane_new);

		new_stu_table = new JTable();
		scrollPane_new.setViewportView(new_stu_table);
		tabbedPane.add(att_menu, "출석부");
		att_menu.setLayout(null);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(58, 53, 237, 39);
		att_menu.add(dateChooser);

		if (((dateChooser.getJCalendar().getMonthChooser().getMonth() + 1) < 10)
				&& (dateChooser.getJCalendar().getDayChooser().getDay() < 10)) {

			date_when = (dateChooser.getJCalendar().getYearChooser().getYear() + "년 0"
					+ (dateChooser.getJCalendar().getMonthChooser().getMonth() + 1) + "월 0"
					+ dateChooser.getJCalendar().getDayChooser().getDay()) + "일";

		} else if (((dateChooser.getJCalendar().getMonthChooser().getMonth() + 1) < 10)
				&& (dateChooser.getJCalendar().getDayChooser().getDay() > 10)) {

			date_when = (dateChooser.getJCalendar().getYearChooser().getYear() + "년 0"
					+ (dateChooser.getJCalendar().getMonthChooser().getMonth() + 1) + "월 "
					+ dateChooser.getJCalendar().getDayChooser().getDay()) + "일";

		} else if (((dateChooser.getJCalendar().getMonthChooser().getMonth() + 1) > 10)
				&& (dateChooser.getJCalendar().getDayChooser().getDay() < 10)) {

			date_when = (dateChooser.getJCalendar().getYearChooser().getYear() + "년 "
					+ (dateChooser.getJCalendar().getMonthChooser().getMonth() + 1) + "월 0"
					+ dateChooser.getJCalendar().getDayChooser().getDay()) + "일";

		} else {
			date_when = (dateChooser.getJCalendar().getYearChooser().getYear() + "년 "
					+ (dateChooser.getJCalendar().getMonthChooser().getMonth() + 1) + "월 "
					+ dateChooser.getJCalendar().getDayChooser().getDay()) + "일";
		}
		System.out.println(date_when);

		att_table = new JTable();
		JScrollPane att_scrollPane = new JScrollPane();
		att_scrollPane.setBounds(12, 102, 399, 475);
		att_menu.add(att_scrollPane);
		att_scrollPane.setViewportView(att_table);

		JButton search_btn = new JButton("검색");
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String[] headeratt = new String[] { "출석번호", "이름", "나이", "등원 요일", "출석 정보", "출석 시간", "결석 사유" };
				String[][] dataatt = daoS.att_all(date_when);

				att_table.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
				att_table.setModel(new DefaultTableModel(dataatt, headeratt));
				att_table.repaint();
				att_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				att_table.getColumn("등원 요일").setPreferredWidth(130);
				att_table.getColumn("출석 시간").setPreferredWidth(130);
				att_table.getColumn("결석 사유").setPreferredWidth(150);
				att_table.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
				att_table.setRowHeight(40);
				att_table.getTableHeader().setReorderingAllowed(false);
			}
		});
		search_btn.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		search_btn.setBackground(new Color(176, 196, 222));
		search_btn.setBounds(307, 53, 83, 39);
		att_menu.add(search_btn);
		
		JLabel lblNewLabel_2 = new JLabel("* 조회를 원하는 날짜를 선택해주세요.");
		lblNewLabel_2.setFont(new Font("배달의민족 주아", Font.PLAIN, 18));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 12, 423, 31);
		att_menu.add(lblNewLabel_2);

		new_stu_table = new JTable();
		new_stu_table.setEnabled(false);
		new_stu_table.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		new_stu_table.repaint();
		scrollPane_new.setViewportView(new_stu_table);
		new_stu_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		new_stu_table.setModel(new DefaultTableModel(new_data, header));
		new_stu_table.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 17));
		new_stu_table.setRowHeight(30);

		// 신규생 그래프 붙여주기
		final CategoryDataset new_dataset = new_createDataset();
		final JFreeChart new_chart = new_createChart(new_dataset);
		ChartPanel new_chartPanel = new ChartPanel((new_chart));
		new_chartPanel.setBounds(0, 60, 423, 369);
		new_menu.add(new_chartPanel);
	}

	/**
	 * 
	 * 수입통계 붙여주기!
	 *
	 */

	void income_chart(final String title) {

		CategoryDataset dataset = createDataset();
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel((chart));
		chartPanel.setBounds(0, 60, 423, 323);
		Income_menu.add(chartPanel);
	}

	private CategoryDataset createDataset() {

		// row keys...
		final String series1 = "2020";
		final String series2 = "2021";
		final String series3 = "2022";

		// column keys...
		final String category1 = "1";
		final String category2 = "2";
		final String category3 = "3";
		final String category4 = "4";
		final String category5 = "5";
		final String category6 = "6";
		final String category7 = "7";
		final String category8 = "8";
		final String category9 = "9";
		final String category10 = "10";
		final String category11 = "11";
		final String category12 = "12";

		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		dataset.addValue(January22, series1, category1);
		dataset.addValue(February22, series1, category2);
		dataset.addValue(March22, series1, category3);
		dataset.addValue(April22, series1, category4);
		dataset.addValue(May22, series1, category5);
		dataset.addValue(June22, series1, category6);
		dataset.addValue(July22, series1, category7);
		dataset.addValue(August22, series1, category8);
		dataset.addValue(September22, series1, category9);
		dataset.addValue(October22, series1, category10);
		dataset.addValue(November22, series1, category11);
		dataset.addValue(December22, series1, category12);

		dataset.addValue(January22, series2, category1);
		dataset.addValue(February22, series2, category2);
		dataset.addValue(March22, series2, category3);
		dataset.addValue(April22, series2, category4);
		dataset.addValue(May22, series2, category5);
		dataset.addValue(June22, series2, category6);
		dataset.addValue(July22, series2, category7);
		dataset.addValue(August22, series2, category8);
		dataset.addValue(September22, series2, category9);
		dataset.addValue(October22, series2, category10);
		dataset.addValue(November22, series2, category11);
		dataset.addValue(December22, series2, category12);

		dataset.addValue(January22, series3, category1);
		dataset.addValue(February22, series3, category2);
		dataset.addValue(March22, series3, category3);
		dataset.addValue(April22, series3, category4);
		dataset.addValue(May22, series3, category5);
		dataset.addValue(June22, series3, category6);
		dataset.addValue(July22, series3, category7);
		dataset.addValue(August22, series3, category8);
		dataset.addValue(September22, series3, category9);
		dataset.addValue(October22, series3, category10);
		dataset.addValue(November22, series3, category11);
		dataset.addValue(December22, series3, category12);

		return dataset;

	}

	private JFreeChart createChart(final CategoryDataset dataset) {
		// create the chart...
		final JFreeChart chart = ChartFactory.createBarChart("", "Month", // domain axis label
				"", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
		);

		chart.setBackgroundPaint(Color.white);

		final CategoryPlot plot = chart.getCategoryPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		// set the range axis to display integers only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// disable bar outlines...
		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// set up gradient paints for series...
		final GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, Color.darkGray, 0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp1 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, Color.lightGray);
		final GradientPaint gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, Color.lightGray);
		renderer.setSeriesPaint(0, gp0);
		renderer.setSeriesPaint(1, gp1);
		renderer.setSeriesPaint(2, gp2);

		final CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

		return chart;

	}

	/**
	 * 
	 * 신입생통계 붙여주기!
	 *
	 */

	void new_chart(final String title) {

		final CategoryDataset new_dataset = new_createDataset();
		final JFreeChart new_chart = new_createChart(new_dataset);
		ChartPanel new_chartPanel = new ChartPanel((new_chart));
		new_chartPanel.setBounds(0, 60, 423, 323);
		new_menu.add(new_chartPanel);

	}

	private CategoryDataset new_createDataset() {

		// row keys...
		final String series1 = "2020";
		final String series2 = "2021";
		final String series3 = "2022";

		// column keys...
		final String category1 = "1";
		final String category2 = "2";
		final String category3 = "3";
		final String category4 = "4";
		final String category5 = "5";
		final String category6 = "6";
		final String category7 = "7";
		final String category8 = "8";
		final String category9 = "9";
		final String category10 = "10";
		final String category11 = "11";
		final String category12 = "12";

		final DefaultCategoryDataset new_dataset = new DefaultCategoryDataset();

		new_dataset.addValue(S_January22, series1, category1);
		new_dataset.addValue(S_February22, series1, category2);
		new_dataset.addValue(S_March22, series1, category3);
		new_dataset.addValue(S_April22, series1, category4);
		new_dataset.addValue(S_May22, series1, category5);
		new_dataset.addValue(S_June22, series1, category6);
		new_dataset.addValue(S_July22, series1, category7);
		new_dataset.addValue(S_August22, series1, category8);
		new_dataset.addValue(S_September22, series1, category9);
		new_dataset.addValue(S_October22, series1, category10);
		new_dataset.addValue(S_November22, series1, category11);
		new_dataset.addValue(S_December22, series1, category12);

		new_dataset.addValue(S_January22, series2, category1);
		new_dataset.addValue(S_February22, series2, category2);
		new_dataset.addValue(S_March22, series2, category3);
		new_dataset.addValue(S_April22, series2, category4);
		new_dataset.addValue(S_May22, series2, category5);
		new_dataset.addValue(S_June22, series2, category6);
		new_dataset.addValue(S_July22, series2, category7);
		new_dataset.addValue(S_August22, series2, category8);
		new_dataset.addValue(S_September22, series2, category9);
		new_dataset.addValue(S_October22, series2, category10);
		new_dataset.addValue(S_November22, series2, category11);
		new_dataset.addValue(S_December22, series2, category12);

		new_dataset.addValue(S_January22, series3, category1);
		new_dataset.addValue(S_February22, series3, category2);
		new_dataset.addValue(S_March22, series3, category3);
		new_dataset.addValue(S_April22, series3, category4);
		new_dataset.addValue(S_May22, series3, category5);
		new_dataset.addValue(S_June22, series3, category6);
		new_dataset.addValue(S_July22, series3, category7);
		new_dataset.addValue(S_August22, series3, category8);
		new_dataset.addValue(S_September22, series3, category9);
		new_dataset.addValue(S_October22, series3, category10);
		new_dataset.addValue(S_November22, series3, category11);
		new_dataset.addValue(S_December22, series3, category12);

		return new_dataset;

	}

	private JFreeChart new_createChart(CategoryDataset new_dataset) {
		final JFreeChart new_chart = ChartFactory.createBarChart("", "Month", // domain axis label
				"", // range axis label
				new_dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
		);

		new_chart.setBackgroundPaint(Color.white);

		// get a reference to the plot for further customisation...
		final CategoryPlot new_plot = new_chart.getCategoryPlot();
		new_plot.setBackgroundPaint(Color.lightGray);
		new_plot.setDomainGridlinePaint(Color.white);
		new_plot.setRangeGridlinePaint(Color.white);

		// set the range axis to display integers only...
		final NumberAxis new_rangeAxis = (NumberAxis) new_plot.getRangeAxis();
		new_rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		// disable bar outlines...
		final BarRenderer new_renderer = (BarRenderer) new_plot.getRenderer();
		new_renderer.setDrawBarOutline(false);

		// set up gradient paints for series...
		final GradientPaint new_gp0 = new GradientPaint(0.0f, 0.0f, Color.darkGray, 0.0f, 0.0f, Color.lightGray);
		final GradientPaint new_gp1 = new GradientPaint(0.0f, 0.0f, Color.blue, 0.0f, 0.0f, Color.lightGray);
		final GradientPaint new_gp2 = new GradientPaint(0.0f, 0.0f, Color.red, 0.0f, 0.0f, Color.lightGray);
		new_renderer.setSeriesPaint(0, new_gp0);
		new_renderer.setSeriesPaint(1, new_gp1);
		new_renderer.setSeriesPaint(2, new_gp2);

		final CategoryAxis new_domainAxis = new_plot.getDomainAxis();
		new_domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
		// OPTIONAL CUSTOMISATION COMPLETED.

		return new_chart;

	}
}
