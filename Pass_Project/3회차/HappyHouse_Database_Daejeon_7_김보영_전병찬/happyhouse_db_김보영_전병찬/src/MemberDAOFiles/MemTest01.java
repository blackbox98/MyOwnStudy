package MemberDAOFiles;

public class MemTest01 {

	public static void main(String[] args) throws Exception {
		MemberDAO dao = new MemberDAO();
//		dao.insertMember(new Member("abcde","12344321","qwert@naver.com", "홀길동", 12));
//		dao.viewMember("abcde");
//		dao.deleteMember("wjsqudcks123");
//		Member m = dao.viewMember("abcde");
//		System.out.println(m);
//		dao.updateMember(new Member("abcde", "456789", "qwer123@naver", "홍길똥", 21));
		String str = dao.login("abcde");
		System.out.println(str);
//		dao.viewMember();
	}
}