package com.epam.gao.entity;

public class Mark {
    private int id;
    private int markValue;
    private int subjectId;
    private int userInfoId;
    private UserInfo userInfo;
    private Subject subject;

    public Mark() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getMarkValue() {
        return markValue;
    }

    public void setMarkValue(int markValue) {
        this.markValue = markValue;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mark)) return false;

        Mark mark = (Mark) o;

        return id == mark.id;

    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Mark{" +
                "id=" + id +
                ", markValue=" + markValue +
                ", subjectId=" + subjectId +
                ", userInfoId=" + userInfoId +
                ", userInfo=" + userInfo +
                ", subject=" + subject +
                '}';
    }
}
