package testCode;


import java.sql.*;



public class TestSqlConn {

//	public static void  main(String[] args) {
//
//		String connectionString = "jdbc:jtds:sqlserver://172.27.85.91/IISS05";
//
//		String userName = "DB_reader";
//
//		String password = "D8re@der";
//
//		try {
//
//			Class.forName("net.sourceforge.jtds.jdbc.Driver"); //specify the jtds driver
//
//
//
//			Connection conn = DriverManager.getConnection(connectionString, userName, password); //establish connection
//
//			String qryString = "Select efsxml from claim where claimnum = '2007001'";
//
//			String cntQryString = "Select count(1) from claim where claimnum = '2007001'";
//
//
//			System.out.println("Connection properly established");
//
//			//Statement stmt = conn.createStatement();
//			//java.sql.ResultSet rslt = stmt.executeQuery(qryString);
//
//			PreparedStatement pstmt = conn.prepareStatement(qryString);
//			String xmlString = "";
//			//pstmt.setString(1, xmlString);
//			java.sql.ResultSet rs = pstmt.executeQuery();
//			//rs.next();
//			//rs.findColumn("efsxml");
//			while(rs.next()) {
//				xmlString = rs.getString(rs.findColumn("efsxml")); 
//				//String city = rslt.getString(3);
//				System.out.println(xmlString);
//			}
//			rs.close();
//			//stmt.close();
//			conn.close();
//
//		}
//
//		catch (Exception e) {
//
//			e.printStackTrace();
//
//		}
//
//
//
//	}
	public static void  main(String[] args) {

		String testStr = "14000";
		
		Float testFloat = Float.parseFloat(testStr);
		
		System.out.println(testFloat);
		System.out.println(Math.round( testFloat ));
		
		int iTestInt = Integer.parseInt(testStr);
		
		
		System.out.println(iTestInt);



	}

}
