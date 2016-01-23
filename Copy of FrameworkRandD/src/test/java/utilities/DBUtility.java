package utilities;

import java.sql.*;

import org.apache.commons.lang3.StringUtils;
import org.testng.Reporter;

import utilities.Generic;

public class DBUtility {

	private static GetPropVals getPropVal = new GetPropVals();
	//private static String windowsDBLoginString = ";useNTLMv2=true;domain=camelot;integratedSecurity=true";
	//private static String windowsDBLoginString = ";integratedSecurity=true";
	//private static String windowsDBLoginString = ";useNTLMv2=true;domain=camelot";
	private static String windowsDBLoginString = ";domain=camelot;useNTLMv2=true";
	private static String connStringPreText = "jdbc:jtds:sqlserver:";

	private static String getEnvironment(String cmsOrHorizon) {
		cmsOrHorizon = cmsOrHorizon.trim().toUpperCase();
		String env = System.getProperty("Environment");
		if (env == null) { env = getPropVal.getPropValue("Env"); }
		if (cmsOrHorizon.equals("HORIZON")) env = env + "Horizon";
		return env;
	}

	private static String getConnectionString(String cmsOrHorizon) {
		String envString, authType, connectionString;
		envString = getEnvironment(cmsOrHorizon);
		connectionString = getPropVal.getPropValue(getPropVal.getPropValue(envString + "DBServer")) + ";databaseName=" + getPropVal.getPropValue(envString + "DBName");
		connectionString = connStringPreText + connectionString;
		authType = getPropVal.getPropValue(envString + "DBAuthType");
		if (authType.trim().equalsIgnoreCase("WINDOWS")) connectionString = connectionString + windowsDBLoginString;
		return connectionString;

	}

	public static Connection connectToDBAndReturnConnObj(String cmsOrHorizon) {
		String connectionString, authType, envString, userName, password;
		envString = getEnvironment(cmsOrHorizon);
		authType = getPropVal.getPropValue(envString + "DBAuthType");
		connectionString = getConnectionString(cmsOrHorizon);
		Connection conn;
		userName = getPropVal.getPropValue(getPropVal.getPropValue(envString + "DBUserID"));
		password = getPropVal.getPropValue(getPropVal.getPropValue(envString + "DBPwd"));
		//connectionString = connectionString + ";user=" + userName + ";password=" + password;
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			if (authType.trim().equalsIgnoreCase("WINDOWS")) {
				conn = DriverManager.getConnection(connectionString, userName, password);
			} else {
				conn = DriverManager.getConnection(connectionString, userName, password);
			}
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Object getDBVal(String cmsOrHorizon, String queryString, String colName) {

		Connection appDbConn;
		PreparedStatement appPstmt;
		ResultSet appRs;

		Object rtnValObject = null;

		try {

			//Class.forName("net.sourceforge.jtds.jdbc.Driver");
			appDbConn = connectToDBAndReturnConnObj(cmsOrHorizon);
			appPstmt = appDbConn.prepareStatement(queryString);
			appRs = appPstmt.executeQuery();
			appRs.next();
			rtnValObject = appRs.getString(appRs.findColumn(colName));
			appRs.close();
			appDbConn.close();
		}

		catch (Exception e) { e.printStackTrace(); }
		return rtnValObject;
	}

	public static Object getRandomRowValFromDB(String cmsOrHorizon, String queryString, String colName) {

		Connection appDbConn;
		PreparedStatement appPstmt;
		ResultSet appRs;
		Object rtnValObject = null;

		int recordCnt = getRecordCountFromQuery(cmsOrHorizon, queryString);
		int goToRow = 0;
		try {

			//Class.forName("net.sourceforge.jtds.jdbc.Driver");
			appDbConn = connectToDBAndReturnConnObj(cmsOrHorizon);
			appPstmt = appDbConn.prepareStatement(queryString);
			appRs = appPstmt.executeQuery();
			int rndNum = Generic.getRandomInteger(1, recordCnt);
			while (appRs.next()){
				goToRow++;
				if (goToRow == rndNum) break;
			}
			rtnValObject = appRs.getString(appRs.findColumn(colName));
			appRs.close();
			appDbConn.close();
		}

		catch (Exception e) { e.printStackTrace(); }
		return rtnValObject;
	}

	public static String getRandomPolicyFromDbAfterHorizonSearch(String queryString, String horizonQuery, String colName) {

		Connection appDbConn, horizonDbConn;
		PreparedStatement appPstmt, horizonPstmt;
		ResultSet appRs, horizonRs;
		String rtnValObject = null;

		int recordCnt = 0;
		int goToRow = 0;
		//Reporter.log("<p><font face='verdana' color='blue'>Started getting random policy number</font>");
		try {
			appDbConn = connectToDBAndReturnConnObj("CMS");
			//Reporter.log("<p><font face='verdana' color='blue'>Connected to CMS DB</font>");
			appPstmt = appDbConn.prepareStatement(queryString);
			appRs = appPstmt.executeQuery();
			//Reporter.log("<p><font face='verdana' color='blue'>Executed the query:" + queryString + "</font>");
			String[] rsArray = recordSetToStringArray("CMS", queryString, appRs, colName);
			rsArray = Generic.shuffleStringArray(rsArray);
			String arrayStrBldr = StringUtils.join(rsArray, ",");
			horizonQuery = horizonQuery.replace("<PolicyList>", arrayStrBldr);
			//Reporter.log("<p><font face='verdana' color='blue'>Built array list policies:" + arrayStrBldr + "</font>");
			horizonDbConn = connectToDBAndReturnConnObj("Horizon");
			//Reporter.log("<p><font face='verdana' color='blue'>Connected to Horizon DB</font>");
			horizonPstmt = horizonDbConn.prepareStatement(horizonQuery);
			//Reporter.log("<p><font face='verdana' color='blue'>Executed the query:" + horizonQuery + "</font>");
			horizonRs = horizonPstmt.executeQuery();
			recordCnt = getRecordCountFromQuery("Horizon", horizonQuery);
			//Reporter.log("<p><font face='verdana' color='blue'>Record count from Horizon:" + recordCnt + "</font>");
			int rndNum = Generic.getRandomInteger(1, recordCnt);
			while (horizonRs.next()) {
				goToRow++;
				if (goToRow == rndNum){
					//Reporter.log("<p><font face='verdana' color='blue'>Policy row number: " + goToRow + "</font>");
					break;
				}
			}
			rtnValObject = horizonRs.getString(horizonRs.findColumn(colName));
			//Reporter.log("<p><font face='verdana' color='blue'>Policy returned: " + rtnValObject + "</font>");
			horizonRs.close();
			//conn.close();
		}

		catch (Exception e) { e.printStackTrace(); }
		return rtnValObject;
	}

	public static int getRecordCountFromQuery(String cmsOrHorizon, String queryString) {
		int rtnVal = 0;
		Connection appDbConn;
		PreparedStatement appPstmt;
		ResultSet appRs;

		try {

			Class.forName("net.sourceforge.jtds.jdbc.Driver");
			appDbConn = connectToDBAndReturnConnObj(cmsOrHorizon);
			appPstmt = appDbConn.prepareStatement(queryString);
			appRs = appPstmt.executeQuery();
			while (appRs.next()) {
				rtnVal++;
			}
			appRs.close();
			appDbConn.close();
		}
		catch (Exception e) { e.printStackTrace(); }
		return rtnVal;
	}

	public static String[] recordSetToStringArray(String cmsOrHorizon, String queryString, ResultSet rSet, String colName) {
		int cntr = 0;
		int recCount = getRecordCountFromQuery(cmsOrHorizon, queryString);
		String rtnArray[] = new String[recCount];
		try {
			while (rSet.next()){
				rtnArray[cntr] = rSet.getString(rSet.findColumn(colName));
				cntr++;
			}
			return rtnArray;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
