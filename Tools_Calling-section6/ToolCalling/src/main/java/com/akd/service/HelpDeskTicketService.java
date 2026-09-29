package com.akd.service;

import com.akd.entity.HelpDeskTicket;
import com.akd.model.TicketRequest;
import com.akd.repository.HelpDeskTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HelpDeskTicketService {

    private final HelpDeskTicketRepository helpDeskTicketRepository;

    public HelpDeskTicket createTicket(TicketRequest ticketRequest, String userName) {
        HelpDeskTicket ticket = HelpDeskTicket.builder()
                .issue(ticketRequest.issue())
                .username(userName)
                .status("OPEN")
                .createdAt(LocalDateTime.now())
                .eta(LocalDateTime.now())
                .build();
        return helpDeskTicketRepository.save(ticket);
    }

    public List<HelpDeskTicket> getTicketByUsername(String username) {
        return helpDeskTicketRepository.findByUsername(username);
    }
}
