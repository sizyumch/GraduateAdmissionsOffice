package com.epam.gao.dao.interfaces;

import java.util.List;

public interface IFacultyRequestDAO {
    List<Integer> findSubjectsId(int facultyId);
}
