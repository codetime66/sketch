package stelo.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import stelo.util.SteloLogger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Service
public class MessageService {

	private static SteloLogger<MessageService> LOG = new SteloLogger<>(MessageService.class);

        @Value("${stelo.queue.name.link-terminal-seller-dlq}")
        private String queueNameDlq;

	@Autowired
        RabbitTemplate rabbitTemplate;

	public void send(String message, String error){
                //JSONObject json = new JSONObject(message);
		//json.put("error", error);
		//LOG.info("Enviando mensagem de falha do vinculo de terminal com vendedor: "+json.toString());
		//rabbitTemplate.convertAndSend(queueNameDlq, json.toString());
                LOG.info("Enviando mensagem de falha do vinculo de terminal com vendedor: "+message);
                rabbitTemplate.convertAndSend(queueNameDlq, message, m -> {
                      m.getMessageProperties().getHeaders().put("erro_msg", error);
                      return m;
                });
	}

}

