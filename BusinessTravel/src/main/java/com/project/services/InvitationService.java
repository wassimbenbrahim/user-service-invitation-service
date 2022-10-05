package com.project.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.project.entities.Invitation;

@Service 
@Transactional

public interface InvitationService {
	
	public void add (Invitation invitation );
	public void update (Invitation invitation);
	public void delete (Long id) ;
	public Optional<Invitation> find( Long id );
	public List <Invitation> findAll();
	
	public Optional<Invitation> findInvitByStatus(boolean status);
	public List<Invitation> findInvitByEmailSentAndStatus(boolean emailSent, boolean status);
	public long countInvitByStatus(boolean status);
	public long countInvitByEmailSent(boolean emailSent);
	public long countInvit();
	
	//public List<Invitation> retrieveAllInvitations();
	//public List<Invitation> retrieveUnacceptedInvitation();
	//public void sendInacceptedInvitation(List<Invitation> c);
	public Invitation retrieveInvitation(Long id);
	
}
