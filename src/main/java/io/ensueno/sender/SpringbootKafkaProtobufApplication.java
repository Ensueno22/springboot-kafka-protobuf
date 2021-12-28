package io.ensueno.sender;

import io.ensueno.sender.sender.EmailSenderFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootKafkaProtobufApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootKafkaProtobufApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        EmailSenderFactory.INSTANCE.doStart();
    }
}
