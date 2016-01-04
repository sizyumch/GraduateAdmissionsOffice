package com.epam.gao.dao.interfaces;

import com.epam.gao.entity.Mark;

import java.util.List;

public interface IMarkDAO {
    boolean create(Mark mark);
    boolean delete(int markId);
    List<Mark> findMarks(int userInfoId);
    int findSumOfMarks(int userInfoId);
}
