package com.ipaynow.walletoffline

import cn.ipaynow.member.wallet.api.entity.ConsumeQueryReqBean
import cn.ipaynow.member.wallet.api.entity.OnlinePaymentReqBean
import cn.ipaynow.member.wallet.api.itf.WalletBizService
import com.alibaba.fastjson.JSON
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import javax.annotation.Resource

@SpringBootTest
@RunWith(SpringRunner::class)
class TestOnlinePayment {
    @Resource
    lateinit var walletBizService: WalletBizService

    @Test
    fun queryConsume() {
        val consumeQueryReqBean = ConsumeQueryReqBean()
        consumeQueryReqBean.mchId = "000000100537842"
        consumeQueryReqBean.orderNo = "c300701201903211834070005200"
        val consumeQueryRespBean = walletBizService.consumeQuery(consumeQueryReqBean)
        Assert.assertNotNull(consumeQueryRespBean)
        println(JSON.toJSONString(consumeQueryRespBean, true))
    }

    @Test
    fun payment() {
        val onlinePaymentReqBean = OnlinePaymentReqBean()
        onlinePaymentReqBean.orderNo = "c300701201903191641140004757"
        onlinePaymentReqBean.tn = "bbd65c3fc1760ebb116a6161b1561690"
        onlinePaymentReqBean.mchId = "000000100537842"
        onlinePaymentReqBean.userId = "100000240614"
        val onlinePaymentRespBean = walletBizService.onlinePayment(onlinePaymentReqBean)
        println(JSON.toJSONString(onlinePaymentRespBean, true))
    }
}