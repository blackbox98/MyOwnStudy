package InterestDAOFiles;

public class InterestTest01 {

	public static void main(String[] args) throws Exception {
		InterestDAO dao = new InterestDAO();
//		dao.insertInterest(new Interest(2,"wjsqudcks","1111010400"));
//		dao.viewInterest("abcde", "1111010200");
		dao.deleteInterest("wjsqudcks", "1111010400");
//		dao.viewInterest("abcde", "1111010100");
//		dao.updateInterest(new Interest("abcde", "456789", "qwer123@naver", "홍길똥", 21));
		dao.viewInterest();
	}
}