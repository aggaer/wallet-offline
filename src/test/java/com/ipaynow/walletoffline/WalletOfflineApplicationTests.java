package com.ipaynow.walletoffline;

import cn.ipaynow.member.wallet.api.entity.*;
import cn.ipaynow.member.wallet.api.itf.WalletAuthCodeService;
import cn.ipaynow.member.wallet.api.itf.WalletBizService;
import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.Instant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WalletOfflineApplicationTests {
    @Resource
    private WalletBizService walletBizService;
    @Resource
    private WalletAuthCodeService walletAuthCodeService;
    private String transNo = "1234567892461";

    @Before
    public void init() {
        transNo = Instant.now().getEpochSecond() + "";
    }

    @Test
    public void contextLoads() {
        OfflinePaymentReqBean offlinePaymentReqBean = new OfflinePaymentReqBean();
        offlinePaymentReqBean.setNotifyUrl("www.baidu.com");
        offlinePaymentReqBean.setAmount(2L);
        offlinePaymentReqBean.setMchId("000000100537868");
        offlinePaymentReqBean.setTransNo(transNo);
        offlinePaymentReqBean.setUserId("100000240238");
        offlinePaymentReqBean.setPayAuthCode("42306105491750125568");
        offlinePaymentReqBean.setAppId("154501694429179");
        offlinePaymentReqBean.setStoreId("000000100537918");
        OfflinePaymentRespBean offlinePaymentRespBean = walletBizService.offlinePayment(offlinePaymentReqBean);
        Assert.assertNotNull(offlinePaymentRespBean);
        System.out.println(JSON.toJSONString(offlinePaymentRespBean, true));
    }

    @Test
    public void fetchAuthCode() {
        AuthCodeReqBean authCodeReqBean = new AuthCodeReqBean();
        authCodeReqBean.setUserId("100000240238");
        authCodeReqBean.setMchId("000000100537868");
        AuthCodeRespBean authCodeRespBean = walletAuthCodeService.gainAuthCode(authCodeReqBean);
        Assert.assertNotNull(authCodeRespBean);
        System.out.println(JSON.toJSONString(authCodeRespBean, true));
    }

    @Test
    public void query() {
        QueryResultReqBean queryResultReqBean = new QueryResultReqBean();
        queryResultReqBean.setPayAuthCode("42306105491750125568");
        queryResultReqBean.setUserId("100000240238");
        queryResultReqBean.setMchId("000000100537868");
        QueryResultRespBean queryResultRespBean = walletAuthCodeService.queryConsumeResByAuthCode(queryResultReqBean);
        Assert.assertNotNull(queryResultRespBean);
        System.out.println(queryResultRespBean.getStillNeedAmount());
        System.out.println(JSON.toJSONString(queryResultRespBean, true));
    }
}
