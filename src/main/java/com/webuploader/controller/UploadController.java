package com.webuploader.controller;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.webuploader.config.Listener;
import com.webuploader.repository.QrCodeNewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * The type Upload controller.
 */
@Controller
public class UploadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    QrCodeNewRepository qrCodeNewRepository;


    @Autowired
    KafkaTemplate<String, String> template;


    @Autowired
    Listener listener;
    /**
     * The Named thread factory.
     */
    private ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("UploadController.-pool-%d").build();
    /**
     * Common Thread Pool
     */


    private ExecutorService pool;

    @PostConstruct
    void init() {

        pool = new ThreadPoolExecutor(100, 200,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());


    }

    @PreDestroy
    void destroy() {
        if (pool != null) {
            pool.shutdown();
        }
    }

    /**
     * Index string.
     *
     * @return the string
     */
    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/")
    public String render() {
        return "index";
    }


    /**
     * new annotation since 4.3
     *
     * @param files              the files
     * @param redirectAttributes the redirect attributes
     * @return the string
     * @throws IOException the io exception
     */
    @PostMapping("/upload")
    @ResponseBody
    public String singleFileUpload(@RequestParam("file") List<MultipartFile> files,
                                   RedirectAttributes redirectAttributes) throws IOException {

        if (files.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        //文件夹
        int size = files.size();
        List<MultipartFileCall> fileCalls = files.stream().map(MultipartFileCall::new).collect(Collectors.toList());
        try {
            List<Future<List<String>>> futures = pool.invokeAll(fileCalls);
            for (Future<List<String>> future : futures) {
                List<String> strings = future.get();
                List<List<String>> partition = Lists.partition(strings, 10000);
                List<QrCodeCall> transform = partition.stream()
                        .map(input -> new QrCodeCall(qrCodeNewRepository, input, template)).collect(Collectors.toList());
                try {
                    List<Future<Integer>> futureList = pool.invokeAll(transform);
                    for (Future<Integer> it : futureList) {
                        Integer integer = it.get();
                    }
                } catch (InterruptedException | ExecutionException ignored) {

                }
            }
        } catch (InterruptedException | ExecutionException ignored) {

        }
        return "suc";
    }


    class MultipartFileCall implements Callable<List<String>> {


        MultipartFile file;

        MultipartFileCall(MultipartFile file) {
            this.file = file;
        }

        @Override
        public List<String> call() throws Exception {
            List<String> strings = Lists.newArrayList();
            try (InputStream inputStream = file.getInputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while (reader.ready()) {
                    String line = reader.readLine();
                    strings.add(line);
                }
            }
            return strings;
        }
    }

    /**
     * The type My call.
     */
    class QrCodeCall implements Callable<Integer> {
        /**
         * The Qr code new repository.
         */
        QrCodeNewRepository qrCodeNewRepository;

        List<String> list;


        KafkaTemplate<String, String> template;


        /**
         * Instantiates a new My call.
         *
         * @param qrCodeNewRepository the qr code new repository
         * @param list                the list
         */
        public QrCodeCall(QrCodeNewRepository qrCodeNewRepository, List<String> list,
                          KafkaTemplate<String, String> template) {
            this.qrCodeNewRepository = qrCodeNewRepository;
            this.list = list;
            this.template = template;
        }

        @Override
        public Integer call() throws Exception {
            for (String line : list) {

                ListenableFuture<SendResult<String, String>> listenableFuture = template.send("qr_code_notice", line, line);


                listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                    @Override
                    public void onFailure(Throwable throwable) {


                    }

                    @Override
                    public void onSuccess(SendResult<String, String> result) {

                    }
                });
            }
            LOGGER.info("处理成功,当前线程名:{}", Thread.currentThread().getName());
            return 1;
        }


    }


    /**
     * Upload status string.
     *
     * @return the string
     */
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}