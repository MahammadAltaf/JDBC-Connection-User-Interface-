package DBMS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class DBMS {
	public static void main(String[] args) throws Exception{
		//Declaring and initializing the all Driver,URl,username and password..
		String Driver,URL,Name,Password;
		Driver="com.mysql.cj.jdbc.Driver";
		URL="jdbc:mysql://localhost:3306/student";
		Name="root";
		Password="Altaf8073@";
		
		//Loading The Driver.....
		Class.forName(Driver);
		//Connection Building to the mysql....
		Connection con=DriverManager.getConnection(URL,Name,Password);
		
		
		
		//Menu Driver for the User....
		System.out.println("Welcome to the 'JDBC' Connection");
		Scanner sc=new Scanner(System.in);
		
		TableCreation table=new TableCreation();
		insertion inst=new insertion();
		Droptable dp=new Droptable();
		Retrivetables ret=new Retrivetables();
		showdata sh=new showdata();
		
		while(true) {
			System.out.print("\n1.Create The table\n2.Insert the values in table\n3.Drop the table\n4.Show all the tables\n5.Show all the data of table\n6.EXIT");
			System.out.print("\nPlease Enter your Choice : ");
			int choice=sc.nextInt();

			switch(choice) {
			
				case 1:table.createtable(con);
					   break;
				case 2:inst.finalinsertion(con);
				       break;
				case 3:dp.droping(con);
						break;
				case 4:ret.retrive(con);
						break;
				case 5:sh.show(con);
						break;
				case 6:return;
			}	
		}
	}
}
