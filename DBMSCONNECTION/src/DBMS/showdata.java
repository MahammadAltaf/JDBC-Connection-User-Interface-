package DBMS;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class showdata {
	void show(Connection con) throws Exception {
		insertion inst=new insertion();
		inst.retrivetables(con);
		
		Statement stmt=con.createStatement();
		String q1="select * from "+inst.finaltable;
		ResultSet res=stmt.executeQuery(q1);
		int i=0;
		System.out.println("*******************************************************");
		while(true) {
			
			if(res.next()) {
				
				for(i=0;i<inst.tableattribute.length;i++) {
					if(inst.tabledatatype[i].equals("int")) {
						System.out.println(inst.tableattribute[i]+ " : "+res.getInt(i+1));
					}
					else
						System.out.println(inst.tableattribute[i]+ ": "+res.getString(i+1));
				}
				System.out.println("*******************************************************");
			}
			else {
				if(i==0) {
					System.out.println("********EMPTY TABLE********");
				}
				break;
			}
		}
	}
	

}
