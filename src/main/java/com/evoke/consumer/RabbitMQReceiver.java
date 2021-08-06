package com.evoke.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.evoke.exception.InvalidEmployeeNameException;
import com.evoke.model.Employee;

@Component
public class RabbitMQReceiver {

    @Autowired
    private Jackson2JsonMessageConverter jackson2JsonMessageConverter;

    private final Logger log = LoggerFactory.getLogger(RabbitMQReceiver.class);

    @RabbitListener(queues = "${evoke.rabbitmq.queue}")
    public void consumeMessageFromQueue(Message message) throws InvalidEmployeeNameException {

        Employee employee = (Employee) jackson2JsonMessageConverter.fromMessage(message);

        log.info("Receiving Message from queue :" + employee);
        if (employee != null && employee.getEmpName() != null && employee.getEmpName()
                .length() > 6) {
            throw new InvalidEmployeeNameException();
        }

        log.info("Message recieved from queue :" + employee);

    }
}

