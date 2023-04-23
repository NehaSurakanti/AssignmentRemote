class Employee
{
	String name,designation;
	int age,salary;
	Employee(String n,int a)
	{
		name=n;
		age=a;
	}
	public void raiseSalary()
	{
	}
	public void display()
	{
		System.out.println("Name:"+name+" Age:"+age+" Salary:"+salary+" Designation:"+designation);
	}
}
class Tester extends Employee
{
	Tester(String name,int age)
	{
		super(name,age);
		salary=30000;
		designation="Tester";
	}
	public void raiseSalary()
	{
		salary=salary+2000;
	}
}
class Programmer extends Employee
{
	Programmer(String name,int age)
	{
		super(name,age);
		salary=40000;
		designation="Programmer";
	}
	public void raiseSalary()
	{
		salary=salary+5000;
	}
}
class Manager extends Employee
{
	Manager(String name,int age)
	{
		super(name,age);
		salary=50000;
		designation="Manager";
	}
	public void raiseSalary()
	{
		salary=salary+10000;
	}
}
public class EmployeeAssign
{
	public static void main(String args[])
	{
		Employee e1=new Tester("Ravi",25);
		Employee e2=new Programmer("Sujith",35);
		Employee e3=new Manager("Kiran",45);
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