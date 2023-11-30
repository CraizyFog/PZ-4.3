import java.util.*;
import java.util.stream.Collectors;

public class EmployeeRepository {
    public List<Employee> employeeRepository = new ArrayList<>(
            List.of(
                    new Employee(0,"Andrii","Bosnenko",Gender.MALE,20,12000,5,Speciality.QA),
                    new Employee(17,"Mia","Lewis",Gender.FEMALE,18,45000,1,Speciality.BACKEND_DEVELOPER),
                    new Employee(9,"Evgen","Hernandez",Gender.MALE,33,10000,12,Speciality.HR),
                    new Employee(6,"Vasa","Miller",Gender.MALE,19,54000,5,Speciality.PM),
                    new Employee(12,"Emma","Wilson",Gender.FEMALE,25,20000,7,Speciality.DEV_OPS),
                    new Employee(20,"Lucas","Walker",Gender.MALE,35,20000,10,Speciality.FRONTEND_DEVELOPER),
                    new Employee(1,"Oleh","Ardis",Gender.MALE,38,8000,4,Speciality.QA),
                    new Employee(2,"Lora","Smith",Gender.FEMALE,48,14000,4,Speciality.QA),
                    new Employee(3,"Anna","Johnson",Gender.FEMALE,45,22000,5,Speciality.QA),
                    new Employee(4,"Artem","Johnson",Gender.MALE,32,50000,13,Speciality.PM),
                    new Employee(5,"Edik","Garcia",Gender.MALE,54,55000,17,Speciality.PM),
                    new Employee(7,"Toll","Davis",Gender.MALE,18,34000,5,Speciality.PM),
                    new Employee(8,"Boris","Rodriguez",Gender.MALE,22,1000,10,Speciality.HR),
                    new Employee(1,"Genadi","Martinez",Gender.MALE,32,21000,10,Speciality.HR),
                    new Employee(11,"Olivia","Moore",Gender.FEMALE,34,28000,7,Speciality.HR),
                    new Employee(13,"Amelia","Lee",Gender.FEMALE,26,30000,8,Speciality.DEV_OPS),
                    new Employee(14,"Liam","Perez",Gender.MALE,29,20000,9,Speciality.DEV_OPS),
                    new Employee(15,"Oliver","Thompson",Gender.MALE,20,21000,2,Speciality.DEV_OPS),
                    new Employee(16,"Henry","White",Gender.MALE,40,40000,15,Speciality.BACKEND_DEVELOPER),
                    new Employee(18,"James","Harris",Gender.MALE,22,50000,7,Speciality.BACKEND_DEVELOPER),
                    new Employee(19,"Ava","Robinson",Gender.FEMALE,31,32000,2,Speciality.BACKEND_DEVELOPER),
                    new Employee(21,"Emma","Garcia",Gender.FEMALE,36,10000,5,Speciality.FRONTEND_DEVELOPER),
                    new Employee(22,"Oleh","Young",Gender.MALE,37,5000,6,Speciality.FRONTEND_DEVELOPER),
                    new Employee(23,"Anna","Allen",Gender.FEMALE,40,7000,20,Speciality.FRONTEND_DEVELOPER)
            )
    );

    public String[] getNameAndSurnameOfTopBySalary() {
        return   employeeRepository.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .limit(3)
                .map((employee) -> employee.getName() + " " + employee.getLastName())
                .toArray(String[]::new);
    }

    public Map<Speciality, List<Employee>> groupBySpeciality() {
        return employeeRepository.stream()
                .collect(Collectors.groupingBy(Employee::getSpeciality, Collectors.toList()));
    }

    public int getSalaryByHugeCriteriaList() {
        return employeeRepository.stream()
                .filter((e) ->
                        e.getSpeciality().equals(Speciality.QA)
                                && e.getWorkExperience() <= 5
                                && e.getGender().equals(Gender.FEMALE))
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public boolean checkIfPresentEmployeeWithWorkExperienceMoreThenTwenty() {
        return employeeRepository.stream()
                .anyMatch((e) ->e.getSpeciality().equals(Speciality.BACKEND_DEVELOPER) && e.getGender().equals(Gender.MALE) && e.getWorkExperience() >= 20);
    }

    public String getDescendingSalaryWithFullName() {
        return employeeRepository.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map((employee -> employee.getName() + " " + employee.getLastName() + " " + employee.getSalary() + "\n"))
                .collect(Collectors.joining(";"));
    }

    public Map<Speciality, Double> getAverageSalaryBySpeciality() {
        return employeeRepository.stream()
                .collect(Collectors.groupingBy(Employee::getSpeciality, Collectors.averagingDouble(Employee::getSalary)));
    }
}
