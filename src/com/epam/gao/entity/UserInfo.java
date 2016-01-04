package com.epam.gao.entity;

import java.util.List;

public class UserInfo {
    private int id;
    private int userTypeId;
    private int facultyId;
    private boolean recognized;
    private String login;
    private String password;
    private UserType userType;
    private Faculty faculty;
    private List<Mark> marks;

    public UserInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public boolean isRecognized() {
        return recognized;
    }

    public void setRecognized(boolean recognized) {
        this.recognized = recognized;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo userInfo = (UserInfo) o;

        return id == userInfo.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", userTypeId=" + userTypeId +
                ", facultyId=" + facultyId +
                ", recognized=" + recognized +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", faculty=" + faculty +
                ", marks=" + marks +
                '}';
    }
}