package happyhouse04;


class HDealTest{
	public static void main(String[] args) throws Exception	{
		HouseDealDAO  dao=new HouseDealDAO();
//		dao.insertCustomer(1314,"홍길동","역삼동");
		//dao.viewHouseDealInfo(1); //no로 검색
		//dao.viewHouseDealApart(1); //아파트로 검색
		dao.viewHouseDealDong("1111011500"); //동으로 검색
		
		//dao.deleteCustomer(1315);
		//dao.viewHouseDeal(); //전체 검색
	}
}


