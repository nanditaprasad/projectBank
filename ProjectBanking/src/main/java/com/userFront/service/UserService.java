package com.userFront.service;

import java.util.List;
import java.util.Set;

import com.userFront.domain.User;
import com.userFront.domain.security.UserRole;



public interface UserService {
	
	User findByUsername(String username);
	User findByEmail(String email);
	User findByCif(String cif);
	
	boolean checkUserExists(String username, String email , String cif);
	
	boolean checkUsernameExists(String username);
	boolean checkEmailExists(String email);
	boolean checkCifExists(String cif);
	
	void save(User user);
	User createUser(User user);
	User saveUser(User user);
	
	List<User> findUserList();

    void enableUser (String username);

    void disableUser (String username);
}
