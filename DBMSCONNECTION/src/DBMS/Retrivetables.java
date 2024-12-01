package DBMS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Retrivetables {
	insertion inst=new insertion();
	void retrive(Connection con) throws Exception {
	Scanner sc=new Scanner(System.in);
	Statement stmt=con.createStatement();
	ResultSet res=stmt.executeQuery("Show tables");
	int i=1;
	System.out.println("\nThe Available Tables are......");
	String co;
	String col="";
	while (true) {
		if(res.next()) {
			co=res.getString(i);
			col=col+co+" ";
		}
		else
			break;
	}
	inst.tablename=col.split(" ");
	
	for(int j=0;j<inst.tablename.length;j++) {
		System.out.println( (j+1)+"."+inst.tablename[j]);
	}
	}
}
