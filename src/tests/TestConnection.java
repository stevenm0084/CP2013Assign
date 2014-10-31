package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TestConnection {
	
	public static void main(String[] args){
		
        /*Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;*/
		
		//Connection c = DriverManager.getConnection("jdbc:mysql://localhost/payroll", "root", "admin");
		
/*        String connectionUrl = "jdbc:mysql://120.146.171.162:3306/payroll";
        String connectionUser = "steven";
        String connectionPassword = "";
        conn = DriverManager.getConnection(connectionUrl, connectionUser, connectionPassword);
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM Employee");       
                                );
        }
        */		
			/*} finally {
			        try { if (rs != null) rs.close(); } catch (SQLException e) { e.printStackTrace(); }
			        try { if (stmt != null) stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
			        try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }*/			
	}	
}
