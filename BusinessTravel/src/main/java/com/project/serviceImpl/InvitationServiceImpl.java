package com.project.serviceImpl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.InvitationDao;
import com.project.entities.Invitation;
import com.project.services.InvitationService;

import com.project.email.EmailConfig;
import com.project.email.EmailServiceImpl;

@Service
@Transactional
public class InvitationServiceImpl implements InvitationService {

	@Autowired
	private InvitationDao dao;

	@Autowired
	EmailServiceImpl emailServiceImpl;

	@Override
	public void add(Invitation invitation) {
		dao.save(invitation); }

	@Override
	public void update(Invitation invitation) { dao.save(invitation); }

	@Override
	public void delete(Long id) { dao.deleteById(id); }

	@Override
	public Optional<Invitation> find(Long id) { return dao.findById(id); }

	@Override
	public List<Invitation> findAll() { return dao.findAll(); }

	@Override
	public Optional<Invitation> findInvitByStatus(boolean status) { return dao.findByStatus(status); }

	@Override
	public List<Invitation> findInvitByEmailSentAndStatus(boolean emailSent, boolean status)
	{ return dao.findByEmailSentAndStatus(emailSent, status);}

	@Override
	public long countInvitByStatus(boolean status) { return dao.countByStatus(status); }

	@Override
	public long countInvitByEmailSent(boolean emailSent) { return dao.countByEmailSent(emailSent); }

	@Override
	public long countInvit() { return dao.count(); }

	@Override
	public Invitation retrieveInvitation(Long id) {

		Invitation inv = dao.findById(id).orElse(null);
		inv.setStatus(true);
		dao.save(inv);
		return inv;
	}

	/*@Override
	public void sendInacceptedInvitation(List<Invitation> c) {

		for (Invitation inv : c) {

			emailServiceImpl.sendSimpleMessage(inv.getEmailEmpl(), inv.getEmailEmpl(),
					"To accept the invitation click on this link: http://localhost:8080/api/invitation/retrieve-invitation/"
							+ inv.getIdInvit());
		}
	}*/
}
