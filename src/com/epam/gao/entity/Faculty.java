package com.epam.gao.entity;

import java.util.List;

public class Faculty {
    private int id;
    private String name;
    private int plan;
    private List<Subject> subjects;
    private List<UserInfo> students;

    public Faculty() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlan() {
        return plan;
    }

    public void setPlan(int plan) {
        this.plan = plan;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public List<UserInfo> getStudents() {
        return students;
    }

    public void setStudents(List<UserInfo> students) {
        this.students = students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty)) return false;

        Faculty faculty = (Faculty) o;

        return id == faculty.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", plan=" + plan +
                ", subjects=" + subjects +
                ", students=" + students +
                '}';
    }
}
