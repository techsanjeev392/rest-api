package com.springboot3.restapis.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee{

    private Integer id;
    private String firstName;
    private String lastName;
    private Double salary;
    private String emailId;


//    @Override
//    public int compareTo(Employee employee) {
//        return this.id-employee.id;
//    }


}
