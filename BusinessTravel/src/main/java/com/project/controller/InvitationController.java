package com.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.entities.Invitation;
import com.project.services.InvitationService;

@RestController
@RequestMapping(value="/api")
public class InvitationController {
	@Autowired
	private InvitationService service ;	
	
	
	@PostMapping(value="/invitation/save")
	public void add(@RequestBody Invitation invitation ) {
		service.add(invitation );
	}
	
	@PutMapping(value="/invitation/update")
	public void update(@RequestBody  Invitation invitation  ) {
		service.update(invitation );	
	}
	
	@GetMapping(value="/invitation/{id}")
	public Optional<Invitation> find(@PathVariable("id")Long id ) {
		return service.find(id);
	}
	
	@GetMapping(value="/invitations")
	public List<Invitation> findAll() {
		return service.findAll();
	}
	@DeleteMapping(value="/invitation/delete/{id}")
	public void delete(@PathVariable("id") Long id ) {
		service.delete(id);
	}

}