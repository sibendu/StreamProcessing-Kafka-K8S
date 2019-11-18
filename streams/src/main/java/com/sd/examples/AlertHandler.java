package com.sd.examples;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class AlertHandler extends Thread {
	private final KafkaConsumer consumer;
	private final String topic;
	private String clientId;
	private AlertDAO dao;

	public static void main(String[] args) throws Exception {
		String bootstrapServers = args.length > 0 ? args[0] : "localhost:9092";
		String inputTopic = args.length > 1 ? args[1] : "alerts";
		String dbUrl = args.length > 2 ? args[2] : "jdbc:mysql://localhost:3306/kafka";
		String dbUser = args.length > 3 ? args[3] : "root";
		String dbPwd =  args.length > 4 ? args[4] : "password";
										
		if (System.getenv("KAFKA_BROKER_URL") != null) {
			bootstrapServers = System.getenv("KAFKA_BROKER_URL");
		}

		if (System.getenv("KAFKA_TOPIC") != null) {
			inputTopic = System.getenv("KAFKA_TOPIC");
		}
		
		if (System.getenv("DB_URL") != null) {
			dbUrl = System.getenv("DB_URL");
		}
		
		if (System.getenv("DB_USER") != null) {
			dbUrl = System.getenv("DB_USER");
		}
		
		if (System.getenv("DB_PWD") != null) {
			dbUrl = System.getenv("DB_PWD");
		}
				
		String clientId = "SampleKafkaConsumer";

		System.out.println("Starting consumer ..");
		AlertHandler consumer = new AlertHandler(bootstrapServers, inputTopic, clientId, dbUrl, dbUser, dbPwd);
		System.out.println("Running consumer ..");
		consumer.run();
		System.out.println("Consumer running now ..");
	}

	public AlertHandler(String url, String topic, String clientId, String dbUrl, String dbUser, String dbPwd) {
		this.topic = topic;
		this.clientId = clientId;

		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, url);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, clientId);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringDeserializer");

		consumer = new KafkaConsumer(props);
		
		dao = new AlertDAO(dbUrl, dbUser, dbPwd);
	}

	public void run() {
		while (true) {
			consumer.subscribe(Collections.singletonList(this.topic));
			ConsumerRecords<Integer, String> records = consumer.poll(1000);
			for (ConsumerRecord record : records) {
				
				String account = record.key() == null? "": "@:" + record.key().toString();
				String value = record.value() ==null? "":record.value().toString();
				//String details = account + " has " + value + " transactions within 1 minute" ;
				
				System.out.println("Received message: (" + account + ", " + value + ") at offset "
						+ record.offset());
				
				processAlert(account, 0, value);
			}
		}
	}

	public void processAlert(String account, Integer no_access, String details) {
		try {
			String code = "FREQUENT_TXN";
			if(details != null && details.contains("across channels")) {
				code = "TXN_ACROSS_CHANNELS";
			}
			dao.processAlert(code, null, account, no_access, details, "NEW");
			System.out.println("Alert record created in DB");
		} catch (Exception e) {
			System.out.println("Error in inserting alert: " + e.getMessage());
		}
	}

}
