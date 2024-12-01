package DBMS;

import java.sql.Connection;
import java.sql.Statement;

public class Droptable {
	
	void droping(Connection con) throws Exception {
	insertion dp=new insertion();
	dp.retrivetables(con);
	
	Statement stmt=con.createStatement();
	
	String q1="Drop table "+dp.finaltable;
	int result=stmt.executeUpdate(q1);
	
	if(result>=0) {
		System.out.print("Successfully Droped table"+dp.finaltable);
	}
	else
		System.out.println("Not Droped");
	}
	
}
