package emp.assignment;
import java.util.*;
import java.io.*;
abstract class Employee implements Serializable
{
	private String name;
	private String designation;
	private int age;
	private int salary;
	static int count=0;
	static ArrayList<Employee>list;
	Employee(int salary,String designation)
	{
		this.salary=salary;
		this.designation=designation;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter name for "+designation);
		name=sc.nextLine();
		age=InvalidAgeException.readAge();
		list.add(this);
		count++;
	}
	public String toString()
	{
		display();
		return "";
	}
	public void raiseSalary(int s)
	{
		salary=salary+s;	
	}
	public final void display()
	{
		System.out.println("Name:"+name+" Age:"+age+" Salary:"+salary+" Designation:"+designation);
	}
	public abstract void raisesalary();
	public static void serialize()
	{
		try
		{
			ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("employees.ser"));
			oos.writeObject(list);
			oos.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}
	public static void deserialize()
	{
		try
		{
			File f=new File("employees.ser");
			if(f.exists())
			{
				ObjectInputStream ois=new ObjectInputStream(new FileInputStream(f));
				list=(ArrayList<Employee>)ois.readObject();
				ois.close();
			}
			else
			{
				list=new ArrayList<Employee>();
			}
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
	public void raisesalary()
	{
		super.raisesalary(2000);
	}
}
final class Programmer extends Employee
{
	Programmer()
	{
		super(30000,"Programmer");
	}
	public void raisesalary()
	{
		super.raisesalary(5000);
	}
}
final class Manager extends Employee
{
	Manager()
	{
		super(90000,"Manager");
	}
	public void raisesalary()
	{
		super.raisesalary(10000);
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
class InvalidOptionException extends RuntimeException
{
	public InvalidOptionException()
	{
	}
	public InvalidOptionException(String msg)
	{
		super(msg);
	}
	public static int readOption()
	{
		int option=0;
		while(true)
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Your choice:");
			try
			{
				option=sc.nextInt();
				return option;
			}
			catch(Exception e)
			{
				System.out.println("Please choose the right option from the given option only");
			}
		}
	}
}
class EmpMenu
{
	public static void menu()
	{
		int ch1=0, ch2=0;
		Employee.deserialize();
		do
		{
			System.out.println("-------------------------");
			System.out.println("  1.  Create  ");
			System.out.println("  2.  Display  ");
			System.out.println("  3.  Raise Salary  ");
			System.out.println("  4.  Exit  ");
			System.out.println("-------------------------");
			ch1 = InvalidOptionException.readOption();
			switch(ch1)
			{
				case 1:do
					{
						System.out.println("-------------------------");
						System.out.println("  1.  Tester  ");
						System.out.println("  2.  Programmer  ");
						System.out.println("  3.  Manager  ");
						System.out.println("  4.  Exit  ");
						System.out.println("-------------------------");
						ch2 = InvalidOptionException.readOption();
						switch(ch2)
						{
							case 1 : new Tester();
								break;
							case 2 : new Programmer();
								break;
							case 3 : new Manager();
								break;		
							case 4 : break;
							default : System.out.println("Please enter correct choice.....");
						}
					}while( ch2 != 4 );
					break;
				case 2:	Iterator<Employee> i1 = Employee.list.iterator();
					while(i1.hasNext())
						System.out.println(i1.next());
					break;
				case 3:Iterator<Employee> i2 = Employee.list.iterator();
					while(i2.hasNext())
						i2.next().raisesalary();
					break;
				case 4:break;
				default : System.out.println("Please enter correct choice.....");
			}
		}while( ch1 != 4 );
		Employee.serialize();
		System.out.println("Total no. employees added : "+Employee.count);
	}
}
public class EmployeeAssign
{
	public static void main(String args[]) throws IOException
	{
		EmpMenu.menu();	
	}
}