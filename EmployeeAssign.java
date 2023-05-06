package emp.assignment;
import java.util.*;
import java.io.*;
abstract class Employee
{
	private String name;
	private String designation;
	private int age;
	private int salary;
	static int count=0;
	Employee(int salary,String designation)
	{
		this.salary=salary;
		this.designation=designation;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter name for "+designation);
		name=sc.nextLine();
		age=InvalidAgeException.readAge();
		writeToFile();
		count++;
	}
	public void raiseSalary()
	{
		Scanner sc=new Scanner(System.in);
		System.out.print("Enter the amount to be raised for "+name+":");
		int s=sc.nextInt();
		salary=salary+s;	
	}
	public final void display()
	{
		System.out.println("Name:"+name+" Age:"+age+" Salary:"+salary+" Designation:"+designation);
	}

	public static void readFromFile()
	{
		try
		{
			File f=new File("D:/Target/Assignments/employee/employees.csv");
			BufferedReader fr=new BufferedReader(new FileReader("D:/Target/Assignments/AssignmentLocal/employee/employees.csv"));
			String line=null;
			System.out.println();
			while((line=fr.readLine())!=null)
			{
				StringTokenizer st=new StringTokenizer(line,",");
				int tokens=st.countTokens();
				System.out.println("Name:"+st.nextToken());
				System.out.println("Age:"+st.nextToken());
				System.out.println("Salary:"+st.nextToken());
				System.out.println("Designation:"+st.nextToken());
				System.out.println("-----------------------");						
			}
			fr.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	public void writeToFile()
	{
		try
		{
			File dir1=new File("employee");
			dir1.mkdir();
			File f=new File(dir1,"employees.csv");
			f.createNewFile();
			PrintWriter pw=new PrintWriter(new FileOutputStream(f,true));
			pw.write(name+","+age+","+salary+","+designation+"\n");
			pw.flush();
			pw.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
final class Tester extends Employee
{
	Tester()
	{
		super(15000,"Tester");
	}
}
final class Programmer extends Employee
{
	Programmer()
	{
		super(30000,"Programmer");
	}
}
final class Manager extends Employee
{
	Manager()
	{
		super(90000,"Manager");
	}
}
class InvalidAgeException extends RuntimeException
{
	public InvalidAgeException()
	{
	}
	public InvalidAgeException(String msg)
	{
		super(msg);
	}
	public static int readAge()
	{
		int age=0;
		while(true)
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter age:");
			try
			{
				age=sc.nextInt();
				if(age<21 || age>60)
					throw new InvalidAgeException();
				return age;
			}
			catch(InputMismatchException e)
			{
				System.out.println("Please enter number only");
			}
			catch(InvalidAgeException e)
			{
				System.out.println("Please enter the age between 21 and 60");
			}
		}
	}
}
public class EmployeeAssign
{
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			System.out.println("Enter your choice:\n1.Create\n2.Display\n3.Exit");
			int ch=Integer.parseInt(br.readLine());
			switch(ch)
			{
				case 1:boolean c=true;
					while(c)
					{
						System.out.println("Enter your choice:\n1.Tester\n2.Programmer\n3.Manager\n4.Exit");
						int ch1=Integer.parseInt(br.readLine());
						switch(ch1)
						{
							case 1:Employee e1=new Tester();break;
							case 2:Employee e2=new Programmer();break;
							case 3:Employee e3=new Manager();break;
							case 4:c=false;break;
							default:System.out.println("Wrong choice");
						}
					}break;
				case 2:Employee.readFromFile();
					break;
				case 3:System.exit(0);break;
				default:System.out.println("Wrong choice");
			}
		}	
	}
}