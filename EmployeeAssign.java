class Employee
{
	String name,designation;
	int age,salary;
	Employee(String n,int a,int s,String d)
	{
		name=n;
		age=a;
		salary=s;
		designation=d;
	}
	public void raiseSalary(int s)
	{
		salary=salary+s;
	}
	public void display()
	{
		System.out.println("Name:"+name+" Age:"+age+" Salary:"+salary+" Designation:"+designation);
	}
}
public class EmployeeAssign
{
	public static void main(String args[])
	{
		Employee e1=new Employee("Ravi",25,30000,"Tester");
		Employee e2=new Employee("Sujith",35,40000,"Programmer");
		Employee e3=new Employee("Kiran",45,50000,"Manager");
		e1.display();
		e2.display();
		e3.display();
		System.out.println("After raising:");
		e1.raiseSalary(-5000);
		e2.raiseSalary(7000);
		e3.raiseSalary(0);
		e1.display();
		e2.display();
		e3.display();
	}
}