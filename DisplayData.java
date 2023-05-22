import java.io.*;
import java.sql.*;
import java.util.Scanner;
class Display
{
	String table_name;
	public void displaying()
	{
		table_name=Display.readTableName();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from "+table_name);
			ResultSetMetaData rsmd=rs.getMetaData();
			int columns=rsmd.getColumnCount();
			System.out.print("Available columns are:");
			for(int i=1;i<=columns;i++)
				System.out.print(rsmd.getColumnName(i)+",");
			String col=Display.readColumn();
			int f=0;
			for(int i=1;i<=columns;i++)
				if(rsmd.getColumnName(i).equalsIgnoreCase(col))
				{
					f=1;
					break;
				}
			if(f==1)
			{
				String order=Display.readOrder();
				if(order.equalsIgnoreCase("asc"))
				{
					rs=stmt.executeQuery("select * from "+table_name+" order by "+col);
					rsmd=rs.getMetaData();
					columns=rsmd.getColumnCount();
					System.out.println();
					while(rs.next())
					{
						for(int i=1;i<=columns;i++)
							System.out.println(rsmd.getColumnName(i)+":"+rs.getString(i));
						System.out.println();
					}
				}
				else if(order.equalsIgnoreCase("desc"))
				{
					rs=stmt.executeQuery("select * from "+table_name+" order by "+col+" desc");
					rsmd=rs.getMetaData();
					columns=rsmd.getColumnCount();
					System.out.println();
					while(rs.next())
					{
						for(int i=1;i<=columns;i++)
							System.out.println(rsmd.getColumnName(i)+":"+rs.getString(i));
						System.out.println("-----------------------");
					}
				}
				else
					System.out.println("Invalid choice");
			}
			else
				System.out.println("The column name does not exist in the table chosen");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	static String readTableName()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the table name:");
		String name=sc.next(); 
		return name;
	}	
	static String readColumn()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("\nWant to sort based on which column:");
		String name=sc.next(); 
		return name;
	}
	static String readOrder()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Choose the order to sort(asc/desc):");
		String name=sc.next(); 
		return name;
	}		
}
public class DisplayData
{
	public static void main(String args[])
	{
		Display d1=new Display();
		d1.displaying();
	}
}
