package com.webuploader.config;

import com.webuploader.repository.QrCodeNewRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @Author: Sorin
 * @Descriptions:
 * @Date: Created in 2018/3/23
 */
@Component("listener")
public class Listener {

    public CountDownLatch countDownLatch0 = new CountDownLatch(3);
    public CountDownLatch countDownLatch1 = new CountDownLatch(3);
    public CountDownLatch countDownLatch2 = new CountDownLatch(3);


    long start = System.currentTimeMillis();
    protected final Logger logger = LoggerFactory.getLogger(Listener.class);
    @Autowired
    QrCodeNewRepository qrCodeNewRepository;
    @Autowired
    KafkaConsumer<String, String> consumer;
    // Threadpool of consumers

    private ExecutorService executor;


    public void execute(int numberOfThreads) {

        // Initialize a ThreadPool with size = 5 and use the BlockingQueue with size =1000 to
        executor = new ThreadPoolExecutor(numberOfThreads, numberOfThreads, 0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.CallerRunsPolicy());


        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (final ConsumerRecord record : records) {
                executor.submit(new ConsumerThreadHandler(record));
            }
        }
    }

    public void shutdown() {
        if (consumer != null) {
            consumer.close();
        }
        if (executor != null) {
            executor.shutdown();
        }
        try {
            if (!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)) {
                System.out
                        .println("Timed out waiting for consumer threads to shut down, exiting uncleanly");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted during shutdown, exiting uncleanly");
        }
    }

    @KafkaListener(topics = "qr_code_notice")
    public void listen0(ConsumerRecord<?, ?> record) {
        String line = record.value().toString();
        System.out.println(line);
       // qrCodeNewRepository.put(line, "qrcodenumber", "code", new String[]{"codevalue"}, new String[]{line});
    }

    public class ConsumerThreadHandler implements Runnable {

        private ConsumerRecord consumerRecord;

        public ConsumerThreadHandler(ConsumerRecord consumerRecord) {
            this.consumerRecord = consumerRecord;
        }

        @Override
        public void run() {
            System.out.println("Process: " + consumerRecord.value() + ", Offset: " + consumerRecord.offset()
                    + ", By ThreadID: " + Thread.currentThread().getId());
        }
    }


}
