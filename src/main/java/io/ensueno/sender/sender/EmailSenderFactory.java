package io.ensueno.sender.sender;

import io.ensueno.sender.model.ReqSendMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public enum EmailSenderFactory {

    INSTANCE,
    ;

    private final KafkaTemplate<String, byte[]> producer;

    EmailSenderFactory() {
        this.producer = new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(setProducerProps()));
    }

    static Map<String, Object> setProducerProps(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
        return props;
    }

    public void doStart(){
        new Thread(() -> {
            while(true){
                try {
                    byte[] byteMessage = payloadToByteArray();

                    producer.send("test", byteMessage);
                    //debug test data
                    ReqSendMessage.ReqEmMessage reqEmMessage = ReqSendMessage.ReqEmMessage.parseFrom(byteMessage);
                    log.info("message={}", reqEmMessage.getReqMessage());

                    Thread.sleep(500);

                } catch (Exception e){
                    log.error("error", e);
                }
            }
        }).start();
    }

    private byte[] payloadToByteArray() {

        String tc = "From: \"=?euc-kr?B?udrB1sij?=\" <seongkwon87@humuson.com>\r\n" +
                "To: \"=?euc-kr?B?dGVzdA==?=\" <seongkwon87@humuson.com>\r\n" +
                "Reply-to: <seongkwon87@humuson.com>\r\n" +
                "Subject: =?euc-kr?B?xde9usauuN7Az8DUtM+02S4=?=\r\n" +
                "Date: Tue, 23 Feb 2021 17:42:02 +0900\r\n" +
                "X-WORKER_ID: <single.default_Worker_19>\r\n" +
                "X-MAIL_ID: <UE9TVF9JRD0yMDIxMDIyM18xOQ==>\r\n" +
                "X-MEMBER_ID: <TV9JRD10bXNfMzEyOTczNTU=>\r\n" +
                "X-SEND_TYPE: <U1RZUEU9QVVUTw==>\r\n" +
                "X-LIST_TABLE: <TElTVF9UQUJMRT1UTVNfQVVUT19TRU5EX0xJU1RfMDE=>\r\n" +
                "X-Mailer: eMsSMTP Ver6.5( PLUTO-build 0322 )\r\n" +
                "MIME-Version: 1.0\r\n" +
                "Message-ID: <202102231742024642_TMS@humuson.com>\r\n" +
                "Content-Type: text/html;\r\n" +
                "        charset=\"euc-kr\"\r\n" +
                "Content-Transfer-Encoding: 8bit\r\n" +
                "\r\n" +
                "테스트메일\r\n" +
                "test\r\n" +
                "tms";

        ReqSendMessage.ReqEmMessage.Builder reqEmMessageBuilder = ReqSendMessage.ReqEmMessage.newBuilder();
        ReqSendMessage.Content.Builder content = ReqSendMessage.Content.newBuilder();
        content.setKey("key test");
        content.setContent(tc);

        reqEmMessageBuilder.getReqMessageBuilder().setCustomCode("");
        reqEmMessageBuilder.getReqMessageBuilder().setChannel(ReqSendMessage.Channel.EM);
        reqEmMessageBuilder.getReqMessageBuilder().setKey("key test");
        reqEmMessageBuilder.getReqMessageBuilder().setFrom("seongkwon87@humuson.com");
        reqEmMessageBuilder.getReqMessageBuilder().setTo("seongkwon87@humuson.com");
        reqEmMessageBuilder.getReqMessageBuilder().setReqTime("20220103100000");
        reqEmMessageBuilder.getReqMessageBuilder().setSendType("AUTO");
        reqEmMessageBuilder.getReqMessageBuilder().setPriority(0);
        reqEmMessageBuilder.getReqMessageBuilder().setMsgContent(content);

        return reqEmMessageBuilder.build().toByteArray();
    }

}
