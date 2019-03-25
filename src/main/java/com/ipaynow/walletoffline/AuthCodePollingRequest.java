package com.ipaynow.walletoffline;

import cn.ipaynow.member.wallet.api.entity.QueryResultReqBean;

import java.io.Serializable;

/**
 * @Author Jerry
 * @Date 2019/3/15 14:20
 **/
public class AuthCodePollingRequest implements Serializable {
    private static final long serialVersionUID = 153452597039852842L;

    private QueryResultReqBean queryResultReqBean;
    private String storeId;
    private String payAppId;

    public AuthCodePollingRequest() {
    }

    public AuthCodePollingRequest(QueryResultReqBean queryResultReqBean, String storeId, String payAppId) {
        this.queryResultReqBean = queryResultReqBean;
        this.storeId = storeId;
        this.payAppId = payAppId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public QueryResultReqBean getQueryResultReqBean() {
        return queryResultReqBean;
    }

    public void setQueryResultReqBean(QueryResultReqBean queryResultReqBean) {
        this.queryResultReqBean = queryResultReqBean;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getPayAppId() {
        return payAppId;
    }

    public void setPayAppId(String payAppId) {
        this.payAppId = payAppId;
    }
}
