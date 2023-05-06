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
	public static void main(String args[])
	{
		System.out.println("Enter Tester details:");
		Employee e1=new Tester();
		System.out.println("Enter Programmer details:");
		Employee e2=new Programmer();
		System.out.println("Enter Manager details:");
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