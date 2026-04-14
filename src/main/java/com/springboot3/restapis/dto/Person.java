package com.springboot3.restapis.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {

    private Integer id;
    private String name;
    private String emailId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id.equals(person.id)) return true;
        return false;
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
