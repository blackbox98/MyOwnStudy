package happyhouse04;
public class HouseDeal{
	private int no;
	private int aptCode;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String floor;
	private String type;
	private String rentMoney;

	public HouseDeal(){}
	public HouseDeal(int no, int aptCode, String dealAmount, int dealMonth, int dealDay, int dealYear, String area, String floor, String type, String rentMoney){
		setNo(no);
		setAptCode(aptCode);
		setDealAmount(dealAmount);
		setDealMonth(dealMonth);
		setDealDay(dealDay);
		setDealYear(dealYear);
		setArea(area);
		setFloor(floor);
		setType(type);
		setRentMoney(rentMoney);
	}
	public void setNo(int no){
		this.no=no;
	}
	public  int   getNo(){
		return no;
	}
	
	public int getAptCode() {
		return aptCode;
	}
	public void setAptCode(int aptCode) {
		this.aptCode = aptCode;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public int getDealYear() {
		return dealYear;
	}
	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}
	public int getDealMonth() {
		return dealMonth;
	}
	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}
	public int getDealDay() {
		return dealDay;
	}
	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRentMoney() {
		return rentMoney;
	}
	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}
	public String toString(){
		String msg=no+"\t"+aptCode+"\t"+dealAmount;
		return msg;
	}
}
