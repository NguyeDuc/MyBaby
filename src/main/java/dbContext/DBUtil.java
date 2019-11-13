/*
 *	@author MRDUC
 *	@date Jun 11, 2019
 */
package dbContext;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

public class DBUtil {

	private static String driver;
	private static String url;
	private static String dbServer;
	private static String dbName;
	private static String dbPassword;
	private static String dbUsername;
	
	
	static {
		Properties pro = new Properties();
		
		try {
			pro.load(DBUtil.class.getClassLoader().getResourceAsStream("DBInfor.properties"));
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			dbServer = pro.getProperty("dbServer");
			dbName = pro.getProperty("dbName");
			dbUsername = pro.getProperty("dbUsername");
			dbPassword = pro.getProperty("dbPassword");
			
			
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public static Connection getConnection() {
		
		Connection conn = null;
		String connectionURL = url + dbServer + dbName;   
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(connectionURL,dbUsername, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeConnection(Connection conn, CallableStatement cs, ResultSet rs) {
		try {
			if(conn!=null && !conn.isClosed()) conn.close();
			if(cs!=null && !cs.isClosed()) cs.close();
			if(rs!=null && !rs.isClosed()) rs.close();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void closeConnection(Connection conn, CallableStatement cs) {
		try {
			if(conn!=null && !conn.isClosed()) conn.close();
			if(cs!=null && !cs.isClosed()) cs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
