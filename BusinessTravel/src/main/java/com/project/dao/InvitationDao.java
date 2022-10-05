package com.project.dao;

import java.util.List;
import java.util.Optional;

import com.project.entities.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface InvitationDao extends JpaRepository<Invitation, Long>  {
	
	Optional<Invitation> findByStatus(boolean status);
    List<Invitation> findByEmailSentAndStatus(boolean emailSent, boolean status);
    long countByStatus(boolean status);
    long countByEmailSent(boolean emailSent);
    long count();
}
