import java.io.*;
import java.sql.*;
public class Employee
{
	public static void main(String args[])
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
			Statement stmt=null;
			ResultSet rs=null;
			PreparedStatement pstmt=null;
			Boolean flag=true;
			while(flag)
			{
				System.out.println("1.Create\n2.Display\n3.Raise salary\n4.Remove\n5.Exit");
				System.out.print("Enter your choice:");
				int ch=Integer.parseInt(br.readLine());
				switch(ch)
				{
					case 1:pstmt=con.prepareStatement("insert into EMPLOYEE values(?,?,?,?,?)");
						System.out.println("Enter EID:");
						int eid=Integer.parseInt(br.readLine());
						System.out.println("Enter NAME:");
						String name=br.readLine();
						System.out.println("Enter AGE:");
						int age=Integer.parseInt(br.readLine());
						System.out.println("Enter SALARY:");
						int salary=Integer.parseInt(br.readLine());
						System.out.println("Enter DESIGNATION:");
						String desig=br.readLine();
						pstmt.setInt(1,eid);
						pstmt.setString(2,name);
						pstmt.setInt(3,age);
						pstmt.setInt(4,salary);
						pstmt.setString(5,desig);
						pstmt.execute();
						pstmt.close();
						break;
					case 2:stmt=con.createStatement();
						rs=stmt.executeQuery("select * from EMPLOYEE");
						while(rs.next())
						{
							System.out.println("EID:"+rs.getInt(1));
							System.out.println("Name:"+rs.getString(2));
							System.out.println("Age:"+rs.getInt(3));
							System.out.println("Salary:"+rs.getInt(4));
							System.out.println("Designation:"+rs.getString(5));
							System.out.println();
						}
						break;
					case 3:stmt.executeUpdate("update EMPLOYEE set SALARY=SALARY+((10/100)*SALARY)");
						System.out.println("Salary incremented");
						break;
					case 4:System.out.println("Enter eid:");
						int id=Integer.parseInt(br.readLine());
						stmt=con.createStatement();
						int f=0;
						rs=stmt.executeQuery("select * from EMPLOYEE");
						while(rs.next())
						{
							if(rs.getInt(1)==id)
							{
								f=1;
								break;
							}
						}
						if(f==1)
						{
							System.out.println("EID:"+rs.getInt(1));
							System.out.println("Name:"+rs.getString(2));
							System.out.println("Age:"+rs.getInt(3));
							System.out.println("Salary:"+rs.getInt(4));
							System.out.println("Designation:"+rs.getString(5));
							System.out.print("Are you sure,you want to delete the record(yes/no):");
							String option=br.readLine();
							if(option.equalsIgnoreCase("yes"))
							{
								System.out.println(id);
								stmt.executeUpdate("delete from EMPLOYEE where EID='"+id+"'");
								System.out.println("Record deleted successfully");
							}
							else if(option.equalsIgnoreCase("No"))
								System.out.println("Removal of record cancelled");
							else
								System.out.println("Wrong choice");
						}
						else
							System.out.println("EID does not exist");
						break;
					case 5:flag=false;
						break;
					default:System.out.println("Wrong choice");
				}
			}
			rs.close();
			stmt.close();
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}