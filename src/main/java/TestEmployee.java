import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import in.co.vwits.ems.model.Employee;
import in.co.vwits.ems.service.EmployeeService;
import in.co.vwits.ems.service.impl.EmployeeServiceImpl;
import in.co.vwits.model.exception.EmployeeNotFoundException;

public class TestEmployee {

	public static void main(String[] args) throws EmployeeNotFoundException {

		int option = 1;

		Scanner sc = null;	
		try {
			sc = new Scanner(System.in);
			EmployeeService service = new EmployeeServiceImpl();

			do {
				System.out.println("*********************Welcome to Employee Management Portal*********************");
				System.out.println("1. Find all records");
				System.out.println("2. Insert records");
				System.out.println("3. Find Employee by Employee Id");
				System.out.println("4. Delete a Employee by Emp Id");
				System.out.println("5. Update salary by EmpId");
				System.out.println("6. Sort Employees in Descending order of salary");
				System.out.println("7. Sort Employees in alphabetical order");
				System.out.println("-1 to exit");
				System.out.println("Enter your choice");

				option = sc.nextInt();
				switch(option)
				{
				case 1:
					List<Employee> employees = service.findAll();
					System.out.println(employees);
					break;

				case 2:
					Employee e = new Employee();
					System.out.print("Enter Employee Id ");
					e.setEmpId(sc.nextInt());
					sc.nextLine(); 
					System.out.print("Enter Name: ");
					e.setName(sc.nextLine());
					System.out.print("Enter Salary: ");
					e.setSalary(sc.nextDouble());
					service.save(e);
					System.out.println("Employee saved successfully");
					break;

				case 3:
					System.out.print("Enter Employee Id to find: ");
					int empId = sc.nextInt();
					Optional<Employee> foundEmployee;
					try {
						foundEmployee = service.findByEmpId(empId);
						System.out.println("Found Employee " + foundEmployee.get());
					} catch (EmployeeNotFoundException x) {
						//e.printStackTrace();
						System.out.println("Employee Not Found");
					}
					break;

				case 4:
					System.out.print("Enter Emp Id to delete: ");
					try {
						empId = sc.nextInt();
						service.deleteByEmpId(empId);
					}

					catch(InputMismatchException ex){
						System.out.println("Emp Id must be an integer value");
						sc.nextLine();
					}
					break;

				case 5:
					System.out.print("Enter Employee Id to update salary:");
					empId = sc.nextInt();
					Employee foundEmp = Optional.ofNullable(service.findByEmpId(empId).get()).get();
					System.out.print("Enter the new salary: ");
					double newSalary = sc.nextDouble();
					foundEmp.setSalary(newSalary);
					service.updateByEmpId(foundEmp);
					break;

				case 6:
					System.out.println(service.findAllEmployeeSortedBySalary());
					break;

				case 7:
					System.out.println(service.findAllEmployeeSortedByName());
					break;

				case -1:
					System.out.println("Thank You for Visitng");
					break;

				}

			}while(option!= -1);
		}
		finally {
			sc.close();
		}
	}
}
