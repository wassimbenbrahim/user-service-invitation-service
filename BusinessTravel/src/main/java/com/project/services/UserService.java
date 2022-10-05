package com.project.services;

import java.util.List;

import javax.mail.SendFailedException;

import com.project.entities.Role;
import com.project.entities.User;



public interface UserService {
    User saveUser(User user) throws SendFailedException;
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    void deleteRoleToUser(String username, String roleName);

    User getUser(String username);
    List<User>getUsers();
	int deleteUser(Long id);
	 User updateUser(long id,User c);
}
