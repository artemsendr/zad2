import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBWithTransaction {
    public static void main(String args[]) {
	Connection conn = null;
	Statement stmt = null;
	
	try {
		//Class.forName("org.apache.derby.jdbc.ClientDriver");
		conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Lesson22");
		
		conn.setAutoCommit(false);
		
		stmt = conn.createStatement();
		stmt.addBatch("insert into Employee " + "values(1122,'Oleg','Manager')");
		stmt.addBatch("insert into Employee " + "values(1133,'Nikita','artist')");
		stmt.executeBatch();
		conn.commit();
	} catch (SQLException se) {
		try {
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		se.printStackTrace();
		System.out.println("SQLError: " + se.getMessage() + " code: " + se.getErrorCode());
	} catch (Exception e) {
		System.out.println(e.getMessage());
		e.printStackTrace();
	} finally {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}			
	}
}

}
