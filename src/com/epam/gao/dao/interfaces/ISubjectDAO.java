package com.epam.gao.dao.interfaces;

import com.epam.gao.entity.Subject;

import java.util.List;

public interface ISubjectDAO {
    Subject findSubject(int id);
    List<Subject> findSubjects(int facultyId);
}
