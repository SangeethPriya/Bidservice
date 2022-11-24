package com.auction.management.buyer.event;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaProducer {

	@Autowired 
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${message.topic.bidInfo}")
	private String topic;
	
	public void send(String bidInfo) {
		kafkaTemplate.send(topic, bidInfo).addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				ProducerRecord<String, String> producerRecord = result.getProducerRecord();
				log.info("Successfully Published new BidInfo Record {} to the topic {}", 
						producerRecord.value(), 
						producerRecord.topic());
			}

			@SneakyThrows
			@Override
			public void onFailure(Throwable ex) {
			    log.warn("Error cannot publish new BidInfo record to the topic {}, {}", topic, ex);
			    throw ex;				
			}
		});
	}
	
}

