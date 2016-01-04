package com.epam.gao.entity;

public class FacultyRequest {
    private int id;
    private int facultyId;
    private int subjectId;
    private Faculty faculty;
    private Subject subject;

    public FacultyRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacultyRequest)) return false;

        FacultyRequest facultyRequest = (FacultyRequest) o;

        return id == facultyRequest.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "FacultyRequest{" +
                "id=" + id +
                ", facultyId=" + facultyId +
                ", subjectId=" + subjectId +
                ", faculty=" + faculty +
                ", subject=" + subject +
                '}';
    }
}