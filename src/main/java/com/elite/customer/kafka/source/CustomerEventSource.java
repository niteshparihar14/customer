package com.elite.customer.kafka.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;

import com.elite.customer.entity.User;
import com.elite.customer.kafka.channel.TransactionChannel;
import com.elite.customer.model.CustomerEvent;
import com.elite.customer.model.CustomerEvent.Status;


@Component
public class CustomerEventSource {

	@Autowired
	private TransactionChannel transactionChannel;

	public void publishLoanEvent(User user, Long loanId, Status status) {

		CustomerEvent event = new CustomerEvent(user.getId(), loanId, user.getEmailId(), user.getPhone(),
				status);

		MessageChannel messageChannel = transactionChannel.outboundTransation();
		messageChannel.send(MessageBuilder.withPayload(event)
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON).build());
	}
}
