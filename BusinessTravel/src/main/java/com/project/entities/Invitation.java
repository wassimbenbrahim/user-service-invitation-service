package com.project.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity(name = "invitation")
@Data @NoArgsConstructor @AllArgsConstructor
public class Invitation implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id

	@Column(name = "id_invit")
	private long idInvit;

	@Column(name = "titre")
	private String titreInvit;
	
	@Column(name = "description")
	private String descInvit;
	
	@Column(name = "email_empl")
	private String emailEmpl;
	
	@Column(name="date_created")
    @CreationTimestamp
    private Date dateCreated;
	
	@Column(name="last_updated")
    @UpdateTimestamp
    private Date lastUpdated;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "email_sent")
	private Boolean emailSent;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_comp")
	@JsonIgnore
	public Company company;
	
	@Override
	public String toString() {
		return "Invitations [titre=" + titreInvit + ", description=" + descInvit + ", emailEmpl=" + emailEmpl + ", dateCreated=" + dateCreated + ", lastUpdated=" + lastUpdated + ", status=" + status + ", email_sent=" + emailSent + ", company=" + company + "]";
	}

}
