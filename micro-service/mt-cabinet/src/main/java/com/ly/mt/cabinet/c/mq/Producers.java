//package com.ly.mt.cabinet.c.mq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// *
// */
//@Slf4j
//@Service
//public class Producers {
//
//    @Autowired
//    RabbitMessagingTemplate rabbitSendTemplate;
//
//    public void send(Object json) {
//        System.out.println("send start.....");
//        log.info("格子柜C端传递给RabbitMQ的参数是：{}",json.toString());
//
//        rabbitSendTemplate.convertAndSend("gzg_c_notify_b","",json.toString());
//    }
//}