package com.springboot3.restapis;

import com.springboot3.restapis.dto.Employee;
import com.springboot3.restapis.dto.EmployeeComparator;
import com.springboot3.restapis.dto.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

class Main22 {

    public static void main(String[] args) {

        ArrayList<Employee> employeeArrayList = new ArrayList<>();
        employeeArrayList.add(new Employee(100, "John", "Doe", 50000.22, "JohnDoe@gmail.com"));
        employeeArrayList.add(new Employee(20, "Jane", "Smith", 60000.32, "Janesmith@gmail.com"));
        employeeArrayList.add(new Employee(2, "Jane", "Smith", 60010.32, "Jawnesmith@gmail.com"));
        employeeArrayList.add(new Employee(13, "Bob", "Johnson", 55000.32, "BobJohson@gmail.com"));

       Collections.sort(employeeArrayList,new EmployeeComparator());

//        Map<Integer, Employee> map =employeeArrayList
//                .stream()
//                .collect(Collectors.toMap(Employee::getId, a->a));

//        System.out.println(map);

//        System.out.println(employeeArrayList);
//
//        employeeArrayList.sort(Comparator.comparing(Employee::getFirstName));
//        List<Employee> employees=employeeArrayList.stream().distinct().toList();
//
//        Set<Integer> seen = new HashSet<>();
//        List<Employee> employees2 = employeeArrayList.stream()
//                .filter(e -> seen.add(e.getId()))
//                .toList();
//
//
//        for(Employee e: employeeArrayList){
//            System.out.println(e);
//        }
//
//    }

//    public static void main(String[] args) {
//
//        Person person1=new Person(1,"John", "john@gmail.com");
//        Person person2=new Person(1,"John", "johsn@gmail.com");
//        System.out.println((person1.equals(person2)));
//        System.out.println("person1 hashcode: "+person1.hashCode());
//        System.out.println("person2 hashcode: "+person2.hashCode());
//

    }

}
