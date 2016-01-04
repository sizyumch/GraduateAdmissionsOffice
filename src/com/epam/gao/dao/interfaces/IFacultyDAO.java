package com.epam.gao.dao.interfaces;

import com.epam.gao.entity.Faculty;

import java.util.List;

public interface IFacultyDAO {
    Faculty findFaculty(int id);
    List<Faculty> findAll();
}
