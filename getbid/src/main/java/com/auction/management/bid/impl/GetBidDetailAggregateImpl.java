package com.auction.management.bid.impl;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.auction.management.bid.dto.GetBidDetailAggregate;
import com.auction.management.bid.entity.ProductEntity;
import com.auction.management.bid.repositories.GetBidRepository;

@Service
public class GetBidDetailAggregateImpl implements GetBidDetailAggregate 
{

	private final Logger logger = LoggerFactory.getLogger(GetBidDetailAggregateImpl.class);

	@Autowired	
	private GetBidRepository getBidRepository;
	
	@Value("${message.topic.bidInfo}")
	private String topic;


	@Override
	@KafkaListener(id = "third_app", topics = "bidInfo")
	public List<ProductEntity> getProduct() {
        String bootstrapServers="127.0.0.1:9092";
        String grp_id="third_app";  
		 Properties properties=new Properties();  
	        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);  
	        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,   StringDeserializer.class.getName());  
	        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());  
	        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,grp_id);  
	        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"latest");   // change to config
        KafkaConsumer<String,String> consumer= new KafkaConsumer<>(properties);  
        consumer.subscribe(Arrays.asList(topic));//collections.singleton
        while(true){  
        	
            ConsumerRecords<String,String> records=consumer.poll(Duration.ofMillis(1000000));  
            for(ConsumerRecord<String,String> record: records){  
                this.logger.info("Received: {} from the topic {} @ {}", record.value(), 
                		record.topic());
               // break;
            }
           // List<ProductEntity> productResponse = getBidRepository.findAll();
    		//return productResponse;
	}
        

	}
}
