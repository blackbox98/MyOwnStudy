package MemberDAOFiles;

public interface DAO {
	public void insertMember(Member m);
	public Member viewMember(String id) ;
	public void viewMember() ;
	public void deleteMember(String id);
	public void updateMember(Member m);
	public String login(String id);
}