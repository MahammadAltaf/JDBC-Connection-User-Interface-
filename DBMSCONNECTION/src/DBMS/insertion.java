package DBMS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class insertion {
	public String[] tablename;
	public String[] tableattribute;
	String[] tabledatatype;
	public String finaltable;
	int tableindex;
	int atlen;
	int value;
	String values;
	int val;
	String atval;
	void retrivetables(Connection con) throws Exception {
		insertion ins=new insertion();
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
		tablename=col.split(" ");
		
		for(int j=0;j<tablename.length;j++) {
			System.out.println( (j+1)+"."+tablename[j]);
		}
		


			while(true) {
			System.out.print("\nPlease Select the Table : ");
			int stb=sc.nextInt();
			int temp=0;
			for(int j=0;j<tablename.length;j++) {
				if(stb-1==j) {
					finaltable=tablename[j];
					tableindex=j;
				}
				else
					temp=temp+1;
			}
			if(temp==tablename.length) {
			System.out.println("Please Enter the correct option...");
			}
			else {
				break;
			  }
			}

		System.out.println("You select the table : "+finaltable);
		
		String q2="desc "+finaltable;
		ResultSet result=stmt.executeQuery(q2);
		
		String ci,cn;
		String coi="";
		String cod="";
		while (true) {
			if(result.next()) {
				ci=result.getString(1);
				coi=coi+ci+" ";
				cn=result.getString(2);
				cod=cod+cn+" ";
			}
			else
				break;
		}
		tableattribute=coi.split(" ");
		tabledatatype=cod.split(" ");
		atlen=tableattribute.length;
		
		
	}
	
	
	void insertionintables(Connection con) throws Exception {
		
		Statement stmt=con.createStatement();
		DatabaseMetaData dbs=con.getMetaData();
		String q2="desc "+finaltable;
		ResultSet result=stmt.executeQuery(q2);
		
		String co,cn;
		String col="";
		String cod="";
		while (true) {
			if(result.next()) {
				co=result.getString(1);
				col=col+co+" ";
				cn=result.getString(2);
				cod=cod+cn+" ";
			}
			else
				break;
		}
		tableattribute=col.split(" ");
		tabledatatype=cod.split(" ");
		atlen=tableattribute.length;
		for(int j=0;j<tableattribute.length;j++) {
			System.out.println( (j+1)+"."+tableattribute[j]+"("+tabledatatype[j]+")");
		}
		insertionatfirstcoloumn(con);
	}
	
	void insertionatfirstcoloumn(Connection con) throws Exception {
		Statement stmt=con.createStatement();
			Scanner sc=new Scanner(System.in);
			String intiger="int";
			
			if (tabledatatype[0].equals("date")) {
				System.out.print("ENTETER VALUE OF "+tableattribute[0]+" fomat(yyyy-mm-dd) : ");
				
			}
			else {
				System.out.print("ENTETER VALUE OF "+tableattribute[0]+" : ");
			}
			if (tabledatatype[0].equals(intiger)) {
				value = sc.nextInt();
				
			}
			else {
				values=sc.next();
			}
	
		String queary="";
		for(int i=2;i<=atlen;i++){
			if(i==tableattribute.length) {
				queary=queary+"null";	
			}
			else {
				queary=queary+"null,";
			}
		}
		
		String q1;

		if (tabledatatype[0].equals(intiger)) {
			q1="insert into "+finaltable+" values("+value+","+queary+")";
		}
		else {
			q1="insert into "+finaltable+" values('"+values+"',"+queary+")";
		}
		int i=stmt.executeUpdate(q1);
		
		if(i>=0) {
			System.out.println("Succesfully Inserted..");
		}
		else {
			System.out.println("Not inserted ...");
		}
		insertionanothorcoloumns(con);
	}
	
	void insertionanothorcoloumns(Connection con) throws SQLException {
		String q1;
		Statement stmt=con.createStatement();
		Scanner sc=new Scanner(System.in);
		
		for (int i=1;i<tableattribute.length;i++) {
			if (tabledatatype[i].equals("date")) {
				System.out.print("ENTETER VALUE OF "+tableattribute[i]+" fomat(yyyy-mm-dd) : ");
				
			}
			else {
				System.out.print("ENTETER VALUE OF "+tableattribute[i]+" : ");
			}
			
			
			if (tabledatatype[i].equals("int")) {
				val = sc.nextInt();	
			}
			else {
				atval=sc.next();
			}
			
			
			if (tabledatatype[i].equals("int")) {
				if (tabledatatype[0].equals("int")) {
					q1="update "+finaltable+" SET "+tableattribute[i]+" = "+val+" where "+ tableattribute[0]+" = "+value;;
					
				}
				else {
					q1="update "+finaltable+" SET "+tableattribute[i]+" = "+val+" where "+ tableattribute[0]+" = '"+values+"'";
				}
				
			}
			
			
			else {
				if (tabledatatype[0].equals("int")) {
					q1="update "+finaltable+" SET "+tableattribute[i]+" = '"+atval+"' where "+ tableattribute[0]+" = "+value;;
					
				}
				else {
					q1="update "+finaltable+" SET "+tableattribute[i]+" = '"+atval+"' where "+ tableattribute[0]+" = '"+values	+"'";
				}
				
			}
			int j=stmt.executeUpdate(q1);
			
			if(j>=0) {
				System.out.println("Succesfully Inserted..");
			}
			else {
				System.out.println("Not inserted ...");
			}
		}
	}
	
	void finalinsertion(Connection con) throws Exception {
		retrivetables(con);
		insertionintables(con);
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("Choose otion");
			System.out.println("1.For more Insertion\n2.Return to the Main Index");
			int choice=sc.nextInt();
			if(choice==1) {
				insertionatfirstcoloumn(con);
			}
			else if(choice==2) {
				break;
			}
			else {
				System.out.println("Please Enter the correct option...");
			}
		}
	}
}
