package com.legaoyi.exchange.processor.handler;

import java.math.BigDecimal;
import java.util.Map;

import com.legaoyi.domain.GpsHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.legaoyi.exchange.processor.util.Constants;
import com.legaoyi.exchange.processor.util.DefaultMessageBuilder;
import com.legaoyi.exchange.processor.util.ExchangeMessage;
import com.legaoyi.exchange.processor.util.ServerRuntimeContext;
import org.springframework.web.client.RestTemplate;

@Component(Constants.ELINK_MESSAGE_STORER_BEAN_PREFIX + "1200" + Constants.ELINK_MESSAGE_STORER_MESSAGE_HANDLER_BEAN_SUFFIX)
public class Jt809_1200_MessageHandler extends MessageHandler {

    private static final Logger logger = LoggerFactory.getLogger(Jt809_1200_MessageHandler.class);

    @Autowired
    @Qualifier("commonDownstreamMessageSendHandler")
    private CommonDownstreamMessageSendHandler commonDownstreamMessageSendHandler;

    @Autowired
    private RestTemplate restTemplate;

    @SuppressWarnings("unchecked")
    @Override
    public void handle(ExchangeMessage exchangeMessage) throws Exception {
/*        logger.info("******下级平台1200消息总入口，handle 1200 message={}", exchangeMessage);*/

        GpsHistory gpsHistory = new GpsHistory();
        Map<String, Object> message0 = (Map<String, Object>) exchangeMessage.getMessage();

        Map<String, Object> message = (Map<String, Object>) message0.get("messageBody");
        if (message.containsKey("state")) {
            gpsHistory.setState((Integer) message.get("state"));
        }

        if (message.containsKey("dateTime")) {
            String str = (String) message.get("dateTime");
            String[] words = str.split("\\s+");
            gpsHistory.setDate(words[0].replaceAll("-", ""));
            gpsHistory.setTime(words[1].replaceAll(":", ""));
        }

        if (message.containsKey("vehicleNo")) {
            gpsHistory.setVehicleNo(String.valueOf(message.get("vehicleNo")));
        }

        if (message.containsKey("vehicleColor")) {
            gpsHistory.setVehicleColor((Integer) message.get("vehicleColor"));
        }

        if (message.containsKey("datalype")) {
            gpsHistory.setType(String.valueOf(message.get("datalype")));
        }

        if (message.containsKey("encrypt")) {
            gpsHistory.setExcrypt(String.valueOf(message.get("encrypt")));
        }

        if (message.containsKey("lng")) {
            gpsHistory.setLon(BigDecimal.valueOf((Double) message.get("lng")));
        }
        if (message.containsKey("lat")) {
            gpsHistory.setLat(BigDecimal.valueOf((Double) message.get("lat")));
        }

        if (message.containsKey("vec1")) {
            gpsHistory.setVec1((Integer) message.get("vec1"));
        }

        if (message.containsKey("vec2")) {
            gpsHistory.setVec2((Integer) message.get("vec2"));
        }

        if (message.containsKey("vec3")) {
            gpsHistory.setVec3((Integer) message.get("vec3"));
        }
        if (message.containsKey("direction")) {
            gpsHistory.setDirection((Integer) message.get("direction"));
        }

        if (message.containsKey("altitude")) {
            gpsHistory.setAltitude((Integer) message.get("altitude"));
        }

        if (message.containsKey("alarm")) {
            gpsHistory.setAlarm((Integer) message.get("alarm"));
        }

        if (message.containsKey("length")) {
            gpsHistory.setDis(String.valueOf(message.get("length")));
        }

        String result = restTemplate.postForObject("http://localhost:9002/business/historyInfo/addByInterface", gpsHistory, String.class);
        System.out.println("插入: " + gpsHistory);
        System.out.println("result: " + result);

        Map<String, Object> messageHeader = (Map<String, Object>) message0.get(Constants.MAP_KEY_MESSAGE_HEADER);
        Map<?, ?> messageBody = (Map<?, ?>) message0.get(Constants.MAP_KEY_MESSAGE_MESSAGE_BODY);

        String messageId = (String) messageHeader.get(Constants.MAP_KEY_MESSAGE_ID);
        String dataType = null;
        if (messageBody != null) {
            dataType = (String) messageBody.get("dataType");
        }

        // 调用消息处理handler
        if (dataType != null) {
            try {
                MessageHandler messageHandler =
                        (MessageHandler) ServerRuntimeContext.getBean(Constants.ELINK_MESSAGE_STORER_BEAN_PREFIX.concat(messageId).concat("_").concat(dataType).concat(Constants.ELINK_MESSAGE_STORER_MESSAGE_HANDLER_BEAN_SUFFIX));
                messageHandler.handle(exchangeMessage);
            } catch (NoSuchBeanDefinitionException e) {
                // 模拟自动回复消息，业务平台需根据业务情况处理，todo
                ExchangeMessage resp = DefaultMessageBuilder.buildRespMessage(exchangeMessage);
                if (resp != null) {
                    commonDownstreamMessageSendHandler.handle(resp);
                }
            }
        }
    }
}
