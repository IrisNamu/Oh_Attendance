package database;

public class PayVo {

	// 결제 수납관련 (달마다 보여줄 것임)
	private String stuNumber;// 학생번호
	private String stuName;// 학생이름

	private String payment_date;// 결제날짜
	private int payment_amount;// 결제액

	private String stuname;//
	private String AGE;//
	private String address;//
	private String GUARDIAN1;//
	private String GUARDIAN1_CALL;
	
	public PayVo() {
		
	}

	// 결제관련 정보
	public PayVo(String stuNumber, String payment_date, int payment_amount) {

		this.stuNumber = stuNumber;
		this.payment_amount = payment_amount;
		this.payment_date = payment_date;
	}

	public PayVo(String stuNumber, String stuname, String AGE, String payment_date, int payment_amount, String address,
			String GUARDIAN1, String GUARDIAN1_CALL) {
		this.stuNumber = stuNumber;
		this.stuname = stuname;
		this.AGE = AGE;
		this.payment_date = payment_date;
		this.payment_amount = payment_amount;
		this.address = address;
		this.GUARDIAN1 = GUARDIAN1;
		this.GUARDIAN1_CALL = GUARDIAN1_CALL;

	}

	public int getPayment_amount() {
		return payment_amount;
	}

	public void setPayment_amount(int payment_amount2) {
		this.payment_amount = payment_amount2;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(String stuNumber) {
		this.stuNumber = stuNumber;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getStuname() {
		return stuname;
	}

	public String getAGE() {
		return AGE;
	}

	public String getAddress() {
		return address;
	}

	public String getGUARDIAN1() {
		return GUARDIAN1;
	}

	public String getGUARDIAN1_CALL() {
		return GUARDIAN1_CALL;
	}
}