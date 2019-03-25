package com.ipaynow.walletoffline

import cn.ipaynow.member.wallet.api.entity.QueryResultReqBean
import com.alibaba.fastjson.JSON
import org.apache.rocketmq.client.producer.DefaultMQProducer
import org.apache.rocketmq.common.message.Message

/**
 * {"payAppId":"154606529764260","queryResultReqBean":{"mchId":"000000100537868","payAuthCode":"42303973611609260032","userId":"100000240238"},"storeId":"000000100537945"}
 */

const val NAMESRV_ADDR = "192.168.0.26:9876"

fun main(args: Array<String>) {
    val reqBean = QueryResultReqBean()
    reqBean.payAuthCode = "42303995100131753984"
    reqBean.mchId = "000000100537868"
    reqBean.userId = "100000240238"
    val pollingRequest = AuthCodePollingRequest(reqBean, "000000100537945", "154606529764260")
    val producer = DefaultMQProducer("authcode-msg-query")
    producer.namesrvAddr = NAMESRV_ADDR
    val pendingMsg = Message("wallets", "authcode-query", JSON.toJSONString(pollingRequest).toByteArray())
    producer.start()
    val sendResult = producer.send(pendingMsg)
    println(sendResult)
    producer.shutdown()
}
