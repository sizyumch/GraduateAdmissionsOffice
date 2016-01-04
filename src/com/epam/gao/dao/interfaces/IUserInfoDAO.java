package com.epam.gao.dao.interfaces;

import com.epam.gao.entity.UserInfo;

import java.util.List;

public interface IUserInfoDAO {
    public UserInfo findAdminInfo(String login);
    UserInfo findUserInfo(String login, int facultyId);
    List<UserInfo> findUnrecognizedUsersInfo(int facultyId);
    List<UserInfo> findRecognizedUsersInfo(int facultyId, int plan);
    boolean updateRecognizing(boolean recognized, int userInfoId);
    boolean create(UserInfo userInfo);
    boolean delete(int userInfoId);
}
