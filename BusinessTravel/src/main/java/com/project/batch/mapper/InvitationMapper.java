package com.project.batch.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.project.entities.Invitation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InvitationMapper implements RowMapper<Invitation> {

    @Override
    public Invitation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Invitation
                .builder()
                .idInvit(rs.getLong("id_invit"))
                .titreInvit(rs.getString("titre"))
                .descInvit(rs.getString("description"))
                .dateCreated(rs.getDate("date_created"))
                .lastUpdated(rs.getDate("last_updated"))
                .emailEmpl(rs.getString("email_empl"))
                .status(rs.getBoolean("status"))
                .emailSent(rs.getBoolean("email_sent"))
                .build();
    }
}