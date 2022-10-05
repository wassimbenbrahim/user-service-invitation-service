package com.project.serviceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.RoleRepo;
import com.project.dao.UserRepo;
import com.project.email.EmailServiceImpl;
import com.project.entities.Role;
import com.project.entities.User;
import com.project.services.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.mail.SendFailedException;

@Service @RequiredArgsConstructor  @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    EmailServiceImpl EmailServiceImp;
    EmailServiceImpl emailService;
   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }

    @Override
    public User saveUser(User user) throws SendFailedException {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        String title="Invitaion of business travel";
   	 String description="<h4>To accept the invitation click on this link:</h4> "+" <button>http://localhost:8080/api/v1/invitation/retrieve-invitation/";
   	 
       emailService.sendSimpleMessage(user.getEmail(), title, description);        return userRepo.save(user);
    }
    @Override
    public int deleteUser(Long id) {
		userRepo.deleteById(id);
		return 0;
    	
    }
    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        return roleRepo.save(role);
    }

    @Transactional
    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
        userRepo.save(user);
    }

    @Transactional
    @Override
    public void deleteRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().remove(role);
        userRepo.save(user);
    }
    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }
    @Override
   	public User updateUser(long id,User c) {
   		
   		User cl= userRepo.findById(id).get();
   		c.setId(id);
   		
   		c.setEmail(c.getEmail());
   		c.setPassword(c.getPassword());
   		c.setName(c.getName());
   	c.setLast_name(c.getLast_name());
   	c.setRoles(c.getRoles());
   	c.setUsername(cl.getUsername());
   		c.setPassword(passwordEncoder.encode(c.getPassword()));
   		userRepo.save(c);
   		return c;
   	}
}
