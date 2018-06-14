package com.webuploader.config;

import com.webuploader.repository.QrCodeNewRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Sorin
 * @Descriptions:
 * @Date: Created in 2018/3/23
 */
@Component("listener")
public class Listener {


    protected final Logger logger = LoggerFactory.getLogger(Listener.class);
    @Autowired
    QrCodeNewRepository qrCodeNewRepository;

    @KafkaListener(topics = {"qr_code_notice"})
    public void listen(ConsumerRecord<?, ?> record) {
        logger.info("kafka的key: " + record.key());
        logger.info("kafka的value: " + record.value());
        String line = record.value().toString();
        qrCodeNewRepository.put(line, "qrcodenumber", "code", new String[]{"codevalue"}, new String[]{line});
    }
}
