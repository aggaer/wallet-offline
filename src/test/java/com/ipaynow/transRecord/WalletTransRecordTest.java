package com.ipaynow.transRecord;

import com.alibaba.fastjson.JSON;
import com.ipaynow.walletoffline.WalletOfflineApplication;
import com.ipaynow.yishouyun.goods.bean.PendingResult;
import com.ipaynow.yishouyun.wallet.dto.ConsumeRecordFetchRequest;
import com.ipaynow.yishouyun.wallet.dto.RechargeRecordFetchRequest;
import com.ipaynow.yishouyun.wallet.dto.payment.PayOrderDTO;
import com.ipaynow.yishouyun.wallet.dto.payment.RechargeOrderDTO;
import com.ipaynow.yishouyun.wallet.service.WalletTransRecordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Jerry
 * @Date 2019/3/26 10:58
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WalletOfflineApplication.class)
public class WalletTransRecordTest {
    @Resource
    private WalletTransRecordService walletTransRecordService;

    @Test
    public void fetchConsumeRecords() {
        ConsumeRecordFetchRequest request = new ConsumeRecordFetchRequest();
//        request.setPageSize(10);
//        request.setCurrentPage(0);
        PendingResult<List<PayOrderDTO>> records = walletTransRecordService.fetchConsumeRecords(request);
        System.out.println(JSON.toJSONString(records.get(), true));
    }

    @Test
    public void fetchRechargeRecords() {
        RechargeRecordFetchRequest request = new RechargeRecordFetchRequest();
        PendingResult<List<RechargeOrderDTO>> records = walletTransRecordService.fetchRechargeRecords(request);
        System.out.println(JSON.toJSONString(records.get(), true));
    }

    @Test
    public void findConsumeDetailById(){
        PendingResult<PayOrderDTO> records = walletTransRecordService.findConsumeDetailById("1001000252");
        System.out.println(JSON.toJSONString(records.get(), true));
    }

    @Test
    public void findRechargeDetailById(){
        PendingResult<RechargeOrderDTO> records = walletTransRecordService.findRechargeDetailById("1000170098");
        System.out.println(JSON.toJSONString(records.get(), true));
    }
}
