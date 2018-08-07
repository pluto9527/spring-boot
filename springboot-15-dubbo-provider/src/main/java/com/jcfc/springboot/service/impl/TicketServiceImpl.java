package com.jcfc.springboot.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jcfc.springboot.service.TicketService;
import org.springframework.stereotype.Component;

@Component
@Service   //将服务发布出去
public class TicketServiceImpl implements TicketService {

    @Override
    public String getTicket() {
        return "动物世界！";
    }
}
