package com.spring.boot.kafka.config;

import com.spring.boot.kafka.producer.Sender;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义producer配置
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {

    @Value("${kafka.broker}")
    private String bootstrapServers;

    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "D:\\\\kafka\\\\kafka_2.11-0.10.1.1\\\\auth\\\\client.truststore.jks");
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "wangbowang");
        props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "D:\\\\kafka\\\\kafka_2.11-0.10.1.1\\\\auth\\\\client.keystore.jks");
        props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "wangbowang");
//        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");
//        props.put(SaslConfigs.SASL_MECHANISM, "ONS");
        props.put(ProducerConfig.ACKS_CONFIG,"all");//是判别请求是否为完整的条件（就是是判断是不是成功发送了）。我们指定了“all”将会阻塞消息，这种设置性能最低，但是是最可靠的。
        props.put(ProducerConfig.RETRIES_CONFIG, 0);//如果请求失败，生产者会自动重试，我们指定是0次，如果启用重试，则会有重复消息的可能性。
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 4096);//分批发送缓存大小(send方法是异步的，会将信息添加到缓存区)
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);//延迟发送等待时间，单位：毫秒
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 40960);//控制生产者可用的缓存总量，如果消息发送速度比其传输到服务器的快，将会耗尽这个缓存空间。当缓存空间耗尽，其他发送调用将被阻塞，阻塞时间的阈值通过max.block.ms设定，之后它将抛出一个TimeoutException。
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //指定分区处理类
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG,CustomPartitioner.class);
        return props;
    }

    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<String, String>(producerFactory());
    }

    @Bean
    public Sender sender() {
        return new Sender();
    }
}
