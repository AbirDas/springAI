package com.akd.repository;

import com.akd.entity.HelpDeskTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HelpDeskTicketRepository extends JpaRepository<HelpDeskTicket,Long> {

    public List<HelpDeskTicket> findByUsername(String username);

}
