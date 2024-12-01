package DBMS;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class TableCreation extends DBMS{
	
	
	
	int res,cno;String tablename;
	void createtable(Connection con) throws Exception {
		TableCreation tb=new TableCreation();
		while(true) {
			Scanner sc=new Scanner(System.in);
			System.out.print("How Many coloumns Will you insert : ");
			cno=sc.nextInt();
			if(cno==1) {
				tb.createonecoloumn(con);
				
				if (res>=0) {
					System.out.println("Table Is successfully Created...");
					break;
				}
				else {
					System.out.println("Table is Not created...");
					break;
				}
				
			}
			else if(cno>1){
				tb.createonecoloumn(con);
				for(int i=1;i<cno;i++) {
					tb.createanothercol(con);
				}
				if (res>=0) {
					System.out.println("Table Is successfully Created...");
					break;
				}
													else {
					System.out.println("Table is Not created...");
					break;
				}
			}
		
		}	
	}
	
	void createonecoloumn(Connection con) throws Exception {
		String cname,datatype,dsize;
		Scanner sc=new Scanner(System.in);
		System.out.print("\nPlease Enter the table Name : ");
		tablename=sc.next();
		
		System.out.print("Enter Coloumn Name : ");
		cname=sc.next();
		
		System.out.print("Choose the datatype... ");
		
		while(true) {
			System.out.print("\n1.Intiger\n2.varchar\n3.date\n4.char");
			System.out.print("\nEnter Option :  ");
			int dtype=sc.nextInt();

			if(dtype==1) {
				datatype="int";
				break;
			}
			else if(dtype==2) {
				datatype="varchar";
				break;
			}
			else if(dtype==3) {
				datatype="date";
				break;
			}
			else if(dtype==4) {
				datatype="char";
				break;
			}
			else {
				System.out.print("Please Enter the correct Option...");
				
			}	
		}
		System.out.print("Enter the size of datatype : ");
		dsize=sc.next();
		Statement stmt=con.createStatement();
		String q1;
		if(datatype!="date") {
			q1="create table "+tablename+"("+cname+" "+datatype+"("+dsize+"))";
		}
		else
			q1="create table "+tablename+"("+cname+" "+datatype+")";
		res=stmt.executeUpdate(q1);
		
	}	
	
	void createanothercol(Connection con) throws Exception {
		String cname,datatype,dsize;
		Scanner sc=new Scanner(System.in);
		
		System.out.print("Enter Coloumn Name : ");
		cname=sc.next();
		
		System.out.print("Choose the datatype... ");
		
		while(true) {
			System.out.print("\n1.Intiger\n2.varchar\n3.date\n4.char");
			System.out.print("\nEnter Option :  ");
			int dtype=sc.nextInt();

			if(dtype==1) {
				datatype="int";
				break;
			}
			else if(dtype==2) {
				datatype="varchar";
				break;
			}
			else if(dtype==3) {
				datatype="date";
				break;
			}
			else if(dtype==4) {
				datatype="char";
				break;
			}
			else {
				System.out.print("Please Enter the correct Option...");
				
			}	
		}
		System.out.print("Enter the size of datatype : ");
		dsize=sc.next();
		Statement stmt=con.createStatement();
		String q1;

		if(datatype!="date") {
			q1="alter table "+tablename+" add "+cname+" "+datatype+"("+dsize+")";
		}
		else
			q1="alter table "+tablename+" add "+cname+" "+datatype;
		res=stmt.executeUpdate(q1);
		
	}
	

}