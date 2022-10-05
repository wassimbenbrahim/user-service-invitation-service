package com.project.batch.writer;

import com.project.dao.InvitationDao;
import com.project.entities.Invitation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class InvitationWriter implements ItemWriter<Invitation> {
    @Autowired
    InvitationDao invitationDao;

    @Override
    public void write(List<? extends Invitation> list) throws Exception {
      log.debug("item writer: {}", list.get(0));
      invitationDao.saveAllAndFlush(list);
    }
}