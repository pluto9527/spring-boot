package com.jcfc.springboot.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Reference
    TicketService ticketService;

    public String hello() {
        String ticket = ticketService.getTicket();
        System.out.println(ticket);
        return ticket;
    }

}
