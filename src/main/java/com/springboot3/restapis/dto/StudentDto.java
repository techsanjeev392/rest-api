package com.springboot3.restapis.dto;

public class StudentDto {

    private Long id;
    private String name;
    private String emailId;

    public StudentDto() {
    }

    public StudentDto(Long id, String name, String emailId) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return "StudentDto{" + "id=" + id + ", name='" + name + '\'' + ", emailId='" + emailId + '\'' + '}';
    }
}
