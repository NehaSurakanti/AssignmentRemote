import java.util.*;
import java.util.function.*;
import java.util.stream.*;
class Emp
{
	String name,designation;
	int age,salary;
	Emp(String name,int age,int salary,String designation)
	{
		this.name=name;
		this.age=age;
		this.salary=salary;
		this.designation=designation;
	}
	public String getName()
	{
		return name;
	}
	public int getAge()
	{
		return age;
	}
	public int getSalary()
	{
		return salary;
	}
	public String getDesignation()
	{
		return designation;
	}
	public String toString()
	{
		return "Name:"+name+",Age:"+age+",Salary:"+salary+",Designation:"+designation;
	}
}
public class EmployeeStream
{
	public static void main(String args[])
	{
		List<Emp> list=new ArrayList<Emp>();
		list.add(new Emp("Rakesh", 25, 30000, "Programmerr"));
		list.add(new Emp("Ramesh", 35, 50000, "Tester"));
		list.add(new Emp("Rajesh", 45, 80000, "Manager"));
		list.add(new Emp("Dinesh", 22, 25000, "Tester"));
		list.add(new Emp("Suresh", 32, 35000, "Programmer"));
		list.add(new Emp("Mahesh", 27, 20000, "Tester"));
		list.add(new Emp("Ganesh", 42, 70000, "Manager"));
		list.add(new Emp("Lokesh", 33, 40000, "Programmer"));
		list.add(new Emp("Brijesh", 44, 90000, "Manager"));
		System.out.println("All managers details:");
		list.stream().filter(e->e.designation.equals("Manager")).collect(Collectors.toList()).forEach(System.out::println);
		System.out.println("-----------------------------------------------------");
		int sum=list.stream().reduce(0,(a,b)->a+b.getSalary(),Integer::sum);
		System.out.println("The total expenses of the company is:"+sum);
		System.out.println("-----------------------------------------------------");
		Optional<Emp> e=list.stream().reduce((a,b)->(a.getAge()>b.getAge())?a:b);
		Emp e1=e.get();
		System.out.println("The eldest employee details:");
		System.out.println(e1);
	}
}