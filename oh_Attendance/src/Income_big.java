
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

public class Income_big extends JFrame {

	private StudentDAO daoS;
	private JPanel contentPane;
	private JTable table_all;
	private JTextField search;
	private JButton search_btn;

	private JTable table_Income;
	private PayDAO daoP;
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

	private JTabbedPane tabbedPane;
	private JPanel panel2;
	private JScrollPane scrollPane2;
	private JTable table_Income_big;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;


	public Income_big() {
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
		
				search = new JTextField();
				search.setBounds(214, 21, 270, 37);
				panel1.add(search);
				search.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						if (search.getText().equals("이름을 입력해주세요.")) {
							search.setText("");
							search.setForeground(new Color(153, 153, 153));
						}
					}

					@Override
					public void focusLost(FocusEvent e) {
						if (search.getText().equals("")) {
							search.setText("이름을 입력해주세요.");
							search.setForeground(new Color(153, 153, 153));
						}
					}

				});
				search.setText("이름을 입력해주세요.");
				search.setForeground(SystemColor.controlDkShadow);
				search.setFont(new Font("굴림", Font.PLAIN, 18));
				search.setColumns(10);
				search.setBackground(new Color(255, 250, 250));
				
						search_btn = new JButton("검색");
						search_btn.setBounds(496, 21, 83, 37);
						panel1.add(search_btn);
						search_btn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								// DB연동 수강생 리스트 불러오기
								String[] header = new String[] { "출석번호", "이름", "나이", "결제일", "수납액", "주소", "보호자1", "보호자1 전화번호" };
								String[][] data = daoS.big_income(search.getText());

								table_all.setFont(new Font("배달의민족 주아", Font.PLAIN, 19));
								table_all.setModel(new DefaultTableModel(data, header));
								scrollPane1.setViewportView(table_all);
								table_all.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

								table_all.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
								table_all.setRowHeight(35);
								table_all.getColumn("결제일").setPreferredWidth(180);
								table_all.getColumn("수납액").setPreferredWidth(150);
								table_all.getColumn("주소").setPreferredWidth(250);
								table_all.getColumn("보호자1").setPreferredWidth(130);
								table_all.getColumn("보호자1 전화번호").setPreferredWidth(150);
								table_all.getTableHeader().setReorderingAllowed(false);
								table_all.setShowVerticalLines(false); // 수평 보더라인 지우기
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
						
						lblNewLabel_2 = new JLabel("수강생 납부 조회");
						lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
						lblNewLabel_2.setFont(new Font("배달의민족 주아", Font.PLAIN, 26));
						lblNewLabel_2.setBounds(12, 10, 201, 58);
						panel1.add(lblNewLabel_2);

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

		// 수입 그래프 붙여주기
		final CategoryDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);

		ChartPanel chartPanel = new ChartPanel((chart));
		chartPanel.setBounds(12, 65, 650, 420);
		panel2.add(chartPanel);
		
		lblNewLabel_1 = new JLabel("월별 총 수강액 비교");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("배달의민족 주아", Font.PLAIN, 33));
		lblNewLabel_1.setBounds(12, -12, 650, 86);
		panel2.add(lblNewLabel_1);

		//
		scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(10, 611, 1274, 160);
		contentPane.add(scrollPane2);

		table_Income_big = new JTable();
		scrollPane2.setViewportView(table_Income_big);
		table_Income_big.setEnabled(false);
		table_Income_big.setFont(new Font("배달의민족 주아", Font.PLAIN, 16));
		table_Income_big.repaint();
		table_Income_big.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table_Income_big.setModel(new DefaultTableModel(income_data, header));
		table_Income_big.getTableHeader().setFont(new Font("배달의민족 주아", Font.PLAIN, 17));

		table_Income_big.setRowHeight(40);

		JLabel lblNewLabel = new JLabel("수강생 납부 조회 및 통계");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(SystemColor.activeCaption);
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
		table_Income_big.getColumn("1월").setPreferredWidth(160);
		table_Income_big.getColumn("2월").setPreferredWidth(160);
		table_Income_big.getColumn("3월").setPreferredWidth(160);
		table_Income_big.getColumn("4월").setPreferredWidth(160);
		table_Income_big.getColumn("5월").setPreferredWidth(160);
		table_Income_big.getColumn("6월").setPreferredWidth(160);
		table_Income_big.getColumn("7월").setPreferredWidth(160);
		table_Income_big.getColumn("8월").setPreferredWidth(160);
		table_Income_big.getColumn("9월").setPreferredWidth(160);
		table_Income_big.getColumn("10월").setPreferredWidth(160);
		table_Income_big.getColumn("11월").setPreferredWidth(160);
		table_Income_big.getColumn("12월").setPreferredWidth(160);

		TableColumnModel tcm = table_Income_big.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴
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
		chartPanel.setBounds(12, 28, 650, 537);
		panel2.add(chartPanel);
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
}
