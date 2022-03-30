package happyhouse04;


class HouseInfoTest{
	public static void main(String[] args) throws Exception	{
		HouseInfoDAO  dao=new HouseInfoDAO();
//		dao.insertCustomer(1314,"홍길동","역삼동");
//		dao.insertCustomer(1315,"고길동","하남시");
//		dao.insertCustomer(1316,"둘리","천호동");
		dao.viewHouseInfoA(1);
		//dao.deleteCustomer(1315);
		//dao.viewHouseDeal();
	}
}


