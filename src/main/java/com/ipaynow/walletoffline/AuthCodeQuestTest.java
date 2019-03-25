package com.ipaynow.walletoffline;

import cn.ipaynow.member.wallet.api.entity.QueryResultReqBean;
import com.alibaba.fastjson.JSON;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @Author Jerry
 * @Date 2019/3/21 11:58
 **/
public class AuthCodeQuestTest {
    private static final String NAMESRV_ADDR = "192.168.0.26:9876";

    public static void main(String[] args) {
        QueryResultReqBean resultReqBean = new QueryResultReqBean();
        resultReqBean.setPayAuthCode("");
        resultReqBean.setMchId("000000100537868");
        resultReqBean.setUserId("100000240238");
        AuthCodePollingRequest pollingRequest = new AuthCodePollingRequest(resultReqBean, "000000100537918", "154501694429179");
        DefaultMQProducer producer = new DefaultMQProducer("authcode-msg-query");
        producer.setNamesrvAddr(NAMESRV_ADDR);
        Message pendingMsg = new Message("wallets", "authcode-query", JSON.toJSONString(pollingRequest).getBytes());
        try {
            producer.start();
            SendResult sendResult = producer.send(pendingMsg);
            System.out.println(sendResult);
        } catch (MQClientException | RemotingException | InterruptedException | MQBrokerException ignored) {
        } finally {
            producer.shutdown();
        }
    }
}
