package com.springboot3.restapis.dto;

import java.util.Comparator;

public class EmployeeComparator implements Comparator<Employee> {

    public int compare(Employee e1, Employee e2) {
        return  e1.getLastName().compareTo(e2.getLastName());
    }


}
