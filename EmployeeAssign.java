package emp.assignment;
import java.util.Scanner;
abstract class Employee
{
	private String name;
	private String designation;
	private int age;
	private int salary;
	static int count;
	Employee(int age,String designation)
	{
		this.salary=salary;
		this.designation=designation;
		Scanner sc=new Scanner(System.in);
		name=sc.nextLine();
		System.out.print("Enter age:");
		age=sc.nextInt();
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
public class EmployeeAssign
{
	public static void main(String args[])
	{
		Employee e1=new Tester();
		Employee e2=new Programmer();
		Employee e3=new Manager();
		e1.display();
		e2.display();
		e3.display();
		System.out.println("-----------------");
		System.out.println("After raising:");
		System.out.println("-----------------");
		e1.raiseSalary();
		e2.raiseSalary();
		e3.raiseSalary();
		e1.display();
		e2.display();
		e3.display();
	}
}