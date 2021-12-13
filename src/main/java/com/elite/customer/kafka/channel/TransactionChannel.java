package com.elite.customer.kafka.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

@SuppressWarnings("deprecation")
public interface TransactionChannel {

	String OUTPUT = "customer-out";

	@Output(OUTPUT)
	MessageChannel outboundTransation();

}
