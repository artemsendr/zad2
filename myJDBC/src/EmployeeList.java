import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class EmployeeList
{
    public static void main(String argv[])
    {
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	
	try
	{
	    conn  = DriverManager.getConnection("jdbc:derby://localhost:1527/Lesson22");
	    String sqlQuery="SELECT *from Employee";
	    stmt  =  conn.createStatement();
	    rs  =  stmt.executeQuery(sqlQuery);
	    
	    while(rs.next()){
		int empNo = rs.getInt("EMPO");
		String eName=rs.getString("ENAME");
		String job = rs.getString("JOB_TITLE");
		
		System.out.println("¹"+empNo+": "+eName+" - "+job);
		
	    }
	    
	}catch(Exception e){
	    System.out.println(e.getMessage());
	    e.printStackTrace();
	} finally{
	    try {
	rs.close();
	stmt.close();
	conn.close();
	} catch (SQLException e) {
	    e.printStackTrace();
	}	
      }
    }
}