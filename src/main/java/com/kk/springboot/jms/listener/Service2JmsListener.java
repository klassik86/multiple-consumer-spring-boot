package com.kk.springboot.jms.listener;

import com.kk.springboot.jms.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Author: PDudin
 * Created date: 20.08.2017.
 */
@Component
public class Service2JmsListener {

    private static final Logger LOG = LoggerFactory.getLogger(Service2JmsListener.class);

    @JmsListener(
            destination = "${service2.srcQueue}",
            concurrency = "${service2.srcQueue.concurrency}",
            containerFactory = Application.JMS_CONTAINER_FACTORY_2
    )
    public void handleMessage(Message message) throws JMSException {
        LOG.debug(">> handleMessage (service 2)");

        if (message instanceof TextMessage) {
            String text = ((TextMessage) message).getText();
            LOG.debug("have got message: {}", text);

            /*do something usefull...*/

        } else {
            LOG.error("Not suitable jms message type, try resend with TextMessage type");
        }

        LOG.debug("<< handleMessage (service 2)");
    }

}
