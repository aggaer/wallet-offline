package com.ipaynow.online;

import cn.ipaynow.member.wallet.api.entity.ConsumeQueryReqBean;
import cn.ipaynow.member.wallet.api.entity.ConsumeQueryRespBean;
import cn.ipaynow.member.wallet.api.itf.WalletBizService;
import com.alibaba.fastjson.JSON;
import com.ipaynow.walletoffline.WalletOfflineApplication;
import com.ipaynow.yishouyun.order.dto.OrderBaseDto;
import com.ipaynow.yishouyun.order.dto.payment.IpayNowPaymentRequest;
import com.ipaynow.yishouyun.order.dto.payment.IpayNowPaymentResponse;
import com.ipaynow.yishouyun.order.dto.payment.PayParams;
import com.ipaynow.yishouyun.order.service.OrderPayService;
import com.ipaynow.yishouyun.order.service.OrderServiceRefactor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author Jerry
 * @Date 2019/3/25 10:42
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WalletOfflineApplication.class)
public class OnlinePayment {
    @Resource
    private OrderPayService orderPayService;
    @Resource
    private OrderServiceRefactor orderServiceRefactor;
    @Resource
    private WalletBizService walletBizService;

    @Test
    public void testPay() {
        IpayNowPaymentRequest paymentRequest = IpayNowPaymentRequest.builder()
                .appId("154606529764260")
                .mhtOrderNo(String.valueOf(System.currentTimeMillis()))
                .mhtOrderName("小程序下单")
                .mhtOrderAmt(100L)
                .oriMhtOrderAmt(100L)
                .mhtOrderDetail("c400201201903051625100072756")
                .discountAmt(0L)
                .payChannelType("103")
                .mhtOrderStartTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
                .mhtStoreId("000000100537868")
                .mhtSubAppId("wx7626168ccba72567")
                .consumerId("100000220146").build();
        IpayNowPaymentResponse paymentResponse = orderPayService.handleIpayNowPayment(paymentRequest, "KlVkfQfkKwgiOP5LDxmr3OYQJULz1sCm");
        Assert.assertNotNull(paymentResponse);
        System.out.println(JSON.toJSONString(paymentResponse));
    }

    @Test
    public void preOrder() {
        String orderText = "{\"buyerAdditionalInformation\":\"\",\"carriageDetail\":{\"logisticsType\":0,\"receiverCity\":\"武汉市\",\"receiverDistrict\":\"洪山区\",\"receiverProvince\":\"湖北省\"},\"discountDetail\":[],\"logisticsType\":0,\"logisticsWishTime\":\"12:27\",\"merchantId\":\"000000100537842\",\"orderItems\":[{\"itemImageUrl\":\"http://ipaynow-filecenter.oss-cn-beijing.aliyuncs.com/image/beb9fab6-3f62-4ee3-a1cc-9d6a26d9163c.jpg\",\"itemNum\":1,\"itemPrice\":1,\"productId\":\"1001370549\",\"productName\":\"小猪佩奇馒头\",\"skuId\":\"10001622\"}],\"payType\":103,\"storeId\":\"000000100537843\",\"userId\":\"100000250004\"}";
        OrderBaseDto orderBaseDto = JSON.parseObject(orderText, OrderBaseDto.class);
        String payPrams = "{\"appId\":\"154501694429179\",\"appKey\":\"kt2fsUaZjtOODvLyuOACWDBtBrght2kF\",\"mhtSubAppId\":\"wx79491d76a8f3c1ab\",\"openid\":\"o611W4_yFX_5O2tRdyb7gS2pQpYc\"}";
        PayParams payParams = JSON.parseObject(payPrams, PayParams.class);
        IpayNowPaymentResponse response = orderServiceRefactor.preOrder(orderBaseDto, payParams);
        Assert.assertNotNull(response);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void consumeQuery(){
        ConsumeQueryReqBean consumeQueryReqBean = new ConsumeQueryReqBean();
        consumeQueryReqBean.setMchId("000000100537578");
        consumeQueryReqBean.setTransNo("c200216201903261135061316760");
        ConsumeQueryRespBean consumeQueryRespBean = walletBizService.consumeQuery(consumeQueryReqBean);
        System.out.println(JSON.toJSONString(consumeQueryRespBean));
    }
}
