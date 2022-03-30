package InterestDAOFiles;

public class Interest {
	private int no;
	private String id;
	private String dongCode;

	public Interest() {
	}

	public Interest(int no, String id, String dongCode) {
		setNo(no);
		setId(id);
		setDongCode(dongCode);
	}
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String toString() {
		String msg = no + "\t" + id + "\t" + dongCode;
		return msg;
	}
}
