package com.ipaynow.walletoffline;

import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.Charset;

/**
 * @Author Jerry
 * @Date 2019/3/8 10:47
 **/
public class PayMsgTest {
    private static final String NAMESRV_ADDR = "192.168.0.26:9876";

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("authcode-msg-query");
        consumer.setNamesrvAddr(NAMESRV_ADDR);
        consumer.subscribe("wallets", "authcode-query");
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            for (MessageExt message : msgs) {
                System.out.println(message.getTags());
                System.out.println(JSON.parseObject(new String(message.getBody(), Charset.defaultCharset())));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }

}
