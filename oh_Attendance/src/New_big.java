
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

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

import database.PayDAO;
import database.StudentDAO;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JMonthChooser;

public class New_big extends JFrame {

	private StudentDAO daoS;
	private JPanel contentPane;
	private JPanel panel2;
	private JPanel panel1;
	private JTable table_all;
	private JButton search_btn;
	private JScrollPane scrollPane2;
	private JTable table_new_big;
	private PayDAO daoP;

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

	public New_big() {
		StudentDAO daoS = new StudentDAO();
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home_Login.class.getResource("/img/app_icon.png")));
		setBounds(100, 100, 1300, 820);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.WHITE);
		panel2.setForeground(Color.BLACK);
		panel2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel2.setBounds(613, 85, 686, 507);
		contentPane.add(panel2);
		panel2.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.WHITE);
		panel1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel1.setForeground(new Color(0, 0, 0));
		panel1.setBounds(0, 85, 611, 507);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setBounds(12, 68, 587, 418);
		panel1.add(scrollPane1);

		table_all = new JTable();
		scrollPane1.setViewportView(table_all);

		JYearChooser yearChooser = new JYearChooser();
		yearChooser.setBounds(261, 22, 102, 36);
		panel1.add(yearChooser);

		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.setBounds(367, 21, 112, 37);
		panel1.add(monthChooser);

		search_btn = new JButton("검색");
		search_btn.setBounds(491, 24, 83, 37);
		panel1.add(search_btn);
		search_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String enter_date = (yearChooser.getYear() + "-" + (monthChooser.getMonth() + 1));
				System.out.println(enter_date);
				// DB연동 수강생 리스트 불러오기
				String[] header = new String[] { "출석번호", "이름", "성별", "나이", "학교", "학년", "반", "생년월일", "등원요일", "주소", "등록일",
						"학생 전화번호", "보호자1", "보호자1 전화번호", "보호자2", "보호자2 전화번호", "특이사항" };
				String[][] data = daoS.big_new(enter_date);

				table_all.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
				table_all.setModel(new DefaultTableModel(data, header));
				table_all.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

				table_all.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
				table_all.setRowHeight(30);
				table_all.getColumn("출석번호").setPreferredWidth(80);
				table_all.getColumn("성별").setPreferredWidth(60);
				table_all.getColumn("나이").setPreferredWidth(70);
				table_all.getColumn("학교").setPreferredWidth(100);
				table_all.getColumn("학년").setPreferredWidth(90);
				table_all.getColumn("반").setPreferredWidth(110);
				table_all.getColumn("생년월일").setPreferredWidth(120);
				table_all.getColumn("등원요일").setPreferredWidth(120);
				table_all.getColumn("주소").setPreferredWidth(200);
				table_all.getColumn("등록일").setPreferredWidth(120);
				table_all.getColumn("학생 전화번호").setPreferredWidth(170);
				table_all.getColumn("보호자1").setPreferredWidth(110);
				table_all.getColumn("보호자1 전화번호").setPreferredWidth(170);
				table_all.getColumn("보호자2").setPreferredWidth(110);
				table_all.getColumn("보호자2 전화번호").setPreferredWidth(170);
				table_all.getColumn("특이사항").setPreferredWidth(270);
				table_all.getTableHeader().setReorderingAllowed(false);
				table_all.getTableHeader().setReorderingAllowed(false);
				DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
				dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

				TableColumnModel tcm = table_all.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

				// 전체 열에 지정
				for (int i = 0; i < tcm.getColumnCount(); i++) {
					tcm.getColumn(i).setCellRenderer(dtcr);
				}
			}
		});
		search_btn.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		search_btn.setBackground(new Color(176, 196, 222));

		JLabel lblNewLabel_2 = new JLabel("월별 등록 수강생 조회");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("배달의민족 주아", Font.PLAIN, 26));
		lblNewLabel_2.setBounds(12, 10, 241, 58);
		panel1.add(lblNewLabel_2);

		// 월별 신입생 통계
		String[] header = new String[] { "년/월", "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월",
				"12월" };
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

		JLabel lblNewLabel_1 = new JLabel("월별 신입생 추이 비교");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 33));
		lblNewLabel_1.setBounds(12, 0, 650, 74);
		panel2.add(lblNewLabel_1);

		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 611, 1274, 160);
		contentPane.add(scrollPane2);

		table_new_big = new JTable();
		scrollPane2.setViewportView(table_new_big);
		table_new_big.setEnabled(false);
		table_new_big.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		table_new_big.repaint();
		table_new_big.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_new_big.setModel(new DefaultTableModel(new_data, header));
		table_new_big.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 17));

		table_new_big.setRowHeight(40);

		JLabel lblNewLabel = new JLabel("신입생 조회 및 통계");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(SystemColor.activeCaption);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("배달의민족 주아", Font.PLAIN, 46));
		lblNewLabel.setBounds(0, 0, 1284, 75);
		contentPane.add(lblNewLabel);

		JButton backBtn = new JButton("<");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statistics_manage sta = new Statistics_manage();
				sta.setVisible(true);
				dispose();
			}
		});
		backBtn.setForeground(Color.WHITE);
		backBtn.setFont(new Font("배달의민족 주아", Font.BOLD, 34));
		backBtn.setBorderPainted(false);
		backBtn.setBackground(Color.GRAY);
		backBtn.setBounds(1217, 0, 67, 47);
		contentPane.add(backBtn);
		table_new_big.getColumn("1월").setPreferredWidth(160);
		table_new_big.getColumn("2월").setPreferredWidth(160);
		table_new_big.getColumn("3월").setPreferredWidth(160);
		table_new_big.getColumn("4월").setPreferredWidth(160);
		table_new_big.getColumn("5월").setPreferredWidth(160);
		table_new_big.getColumn("6월").setPreferredWidth(160);
		table_new_big.getColumn("7월").setPreferredWidth(160);
		table_new_big.getColumn("8월").setPreferredWidth(160);
		table_new_big.getColumn("9월").setPreferredWidth(160);
		table_new_big.getColumn("10월").setPreferredWidth(160);
		table_new_big.getColumn("11월").setPreferredWidth(160);
		table_new_big.getColumn("12월").setPreferredWidth(160);

		TableColumnModel tcm = table_new_big.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
		// 신규생 그래프 붙여주기
		final CategoryDataset new_dataset = new_createDataset();
		final JFreeChart new_chart = new_createChart(new_dataset);
		ChartPanel new_chartPanel = new ChartPanel((new_chart));
		new_chartPanel.setBounds(12, 69, 650, 428);
		panel2.add(new_chartPanel);
		

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

		// 전체 열에 지정
		for (int k = 0; k < tcm.getColumnCount(); k++) {
			tcm.getColumn(k).setCellRenderer(dtcr);
			// 컬럼모델에서 컬럼의 갯수만큼 컬럼을 가져와 for문을 이용하여
			// 각각의 셀렌더러를 아까 생성한 dtcr에 set해줌
		}
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
		panel2.add(new_chartPanel);

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
