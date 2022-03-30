package InterestDAOFiles;

public interface DAO {
	public void insertInterest(Interest i);
	public void viewInterest(String id, String dongCode) ;
	public void viewInterest() ;
	public void deleteInterest(String id, String dongCode);
	public void updateInterest(Interest i);
}