package utilities;

import org.testng.annotations.DataProvider;
import java.sql.*;

public class dataProvider {
	
	@DataProvider(parallel = true)
    public static Object[][] userCreds() {
        return new Object[][] { { "LoadTest1", "password" }, { "LoadTest2", "password" }, { "LoadTest3", "password" }, { "LoadTest4", "password" } };
    }
	@DataProvider//(parallel = true)
    public static Object[][] userKeys() {
        return new Object[][] { { "LoadTest1"}, { "LoadTest2"}, { "LoadTest3"} };
    }
	
	@DataProvider(name = "dbdata")
	public static Object[][] dataFromAccessDB()
	{
		Object[][] arr = new Object[2][4];
		try{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		String database = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=./data/CMS.mdb;";
	    Connection conn = DriverManager.getConnection(database, "", "");
	    Statement s = conn.createStatement();
	    String selTable = "SELECT * FROM " + "Creds";
        s.execute(selTable);
        ResultSet rs = s.getResultSet();
        int j = 0;
        while(rs.next()){
            int cols = rs.getMetaData().getColumnCount();
            //Object[] arr = new Object[cols];
            for(int i=0; i<cols; i++){
            	String tmpStr = rs.getObject(i+1).toString();
            	
              arr[j][i] = rs.getObject(i+1);
            }
            j++;
            //records.add(arr);
        }
        
		}
		catch(Exception ex)
		{
			System.out.println("Error error: " + ex.getMessage());
		}
		return arr;
	}
	
	public String [][] myAccess_Data() 
	{
		// Connect to the database.

		int rowCount = 0;
		int columnCount = 0;
		String myData [][] = null;

		try
		{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String url = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=./data/CMS.mdb;";
			Connection con = DriverManager.getConnection(url, "uname", "pword");

			// Execute the SQL statement
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT * from data_Table");

			// Get Column count
			ResultSetMetaData resultSet_metaData= resultSet.getMetaData();
			columnCount = resultSet_metaData.getColumnCount();

			// Get Row Count
			while( resultSet.next() )
				rowCount++;

			//Initialize data structure
			myData = new String [rowCount][columnCount];

			resultSet.beforeFirst();


			//populate data structure
			for(int row=0; row<rowCount; row++)
			{
				resultSet.next();

				for(int col=1; col<=columnCount; col++)
					myData[row][col-1] = resultSet.getString(col); 
			}

			stmt.close();
			con.close();

		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

		return myData;

	}
	
	public String [][] mySQL_Data() 
	{
		// Connect to the database.

		int rowCount = 0;
		int columnCount = 0;
		String myData [][] = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/mysql_db";
			Connection con = DriverManager.getConnection(url, "uname", "pword");

			// Execute the SQL statement
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt.executeQuery("SELECT * from data_Table");

			// Get Column count
			ResultSetMetaData resultSet_metaData= resultSet.getMetaData();
			columnCount = resultSet_metaData.getColumnCount();

			// Get Row Count
			while( resultSet.next() )
				rowCount++;

			//Initialize data structure
			myData = new String [rowCount][columnCount];

			resultSet.beforeFirst();


			//populate data structure
			for(int row=0; row<rowCount; row++)
			{
				resultSet.next();

				for(int col=1; col<=columnCount; col++)
					myData[row][col-1] = resultSet.getString(col); 
			}

			stmt.close();
			con.close();

		}

		catch (Exception e)
		{
			e.printStackTrace();
		}

		return myData;

	}

}
