
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class  DBWithPreparedStatement {
	
	public static final int number1 = 1111;
	public static final int number2 = 1112;
	public static final int number3 = 1116;
	
	public static void main(String args[]) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet resultSet = null;
		
		try {
			//Class.forName("org.apache.derby.jdbc.ClientDriver");
			conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Lesson22");
			stmt = conn.prepareStatement("Select * from Employee WHERE EMPO=?");
			
			int numbers[] = {number1,number2,number3};
			for (int i = 2; i >= 0; i--) {
				stmt.setInt(1, numbers[i]);
				resultSet = stmt.executeQuery();
				while(resultSet.next()){
					int number = resultSet.getInt("EMPO");
					String ename = resultSet.getString("ENAME");
					String job_title = resultSet.getString("JOB_TITLE");
					
					System.out.println("" + number + ": " + ename + " - " + job_title);
				} //while
			}//for
		} catch (SQLException se) {
			System.out.println("SQLError: " + se.getMessage() + " code: " + se.getErrorCode());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
}
