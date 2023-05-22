import java.io.*;
import java.sql.*;
class Emp
{
	private int eid;
	private String name;
	private int age;
	private int salary;
	private String desig;
	private static Connection con = null;
	private static Statement stmt = null;
	static 
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "tiger");
			stmt = con.createStatement();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	Emp()
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));			
			PreparedStatement pstmt = con.prepareStatement("insert into EMP values(?, ?, ?, ?, ?)");
			System.out.print("Enter ID : ");
			eid = Integer.parseInt(br.readLine());
			System.out.print("Enter name : ");
			name = br.readLine();
			System.out.print("Enter age : ");
			age = Integer.parseInt(br.readLine());
			System.out.print("Enter salary : ");
			salary = Integer.parseInt(br.readLine());
			System.out.print("Enter designation : ");
			desig = br.readLine();
			pstmt.setInt(1, eid);
			pstmt.setString(2, name);
			pstmt.setInt(3, age);
			pstmt.setInt(4, salary);
			pstmt.setString(5, desig);
			pstmt.execute();
			pstmt.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public static void display()
	{
		try
		{
			ResultSet rs = stmt.executeQuery("select * from EMP");
			while(rs.next())
			{
				System.out.println("Emp ID : "+rs.getInt(1));
				System.out.println("Name : "+rs.getString(2));
				System.out.println("Age : "+rs.getInt(3));
				System.out.println("Salary : "+rs.getInt(4));
				System.out.println("Designation : "+rs.getString(5));
				System.out.println();
			}
			rs.close();
		}
		catch(Exception e)
		{	
			System.out.println(e);
		}		
	}
	public static void raiseSalary()
	{
		try
		{
			stmt.executeUpdate("update EMP set SALARY = SALARY * 1.1");
		}
		catch(Exception e)
		{	System.out.println(e);
		}		
	}
	public static void remove()
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter ID : ");
			int id = Integer.parseInt(br.readLine());
			ResultSet rs = stmt.executeQuery("select * from EMP where EID="+id);
			if(rs.next())
			{
				System.out.println("Emp ID : "+rs.getInt(1));
				System.out.println("Name : "+rs.getString(2));
				System.out.println("Age : "+rs.getInt(3));
				System.out.println("Salary : "+rs.getInt(4));
				System.out.println("Designation : "+rs.getString(5));
				System.out.println();
				System.out.print("Do you really want to remove this employee record (y/n) : ");
				String confirm = br.readLine();
				if(confirm.equalsIgnoreCase("y"))
					stmt.executeUpdate("delete from EMP where EID="+id);
			}
			else
				System.out.println("Sorry ! Invalid Employee ID");
			rs.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}		
	}
	public static void close()
	{
		try
		{
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
public class Employee
{
	public static void main(String args[])
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int ch=0;
			do
			{
				System.out.println("------------------------");
				System.out.println("  1.  Create ");
				System.out.println("  2.  Display ");
				System.out.println("  3.  Raise Salary ");
				System.out.println("  4.  Remove ");
				System.out.println("  5.  Exit ");
				System.out.println("------------------------");
				System.out.print("Enter choice :- ");
				ch = Integer.parseInt(br.readLine());
				switch(ch)
				{
					case 1 : new Emp();
						break;
					case 2 : Emp.display();
						break;
					case 3 : Emp.raiseSalary();
						break;
					case 4 : Emp.remove();
						break;
				}
			}while(ch!=5);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			Emp.close();
		}
	}
}