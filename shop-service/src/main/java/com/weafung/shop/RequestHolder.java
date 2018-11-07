package com.weafung.shop;

import com.weafung.shop.model.po.AccountInfo;

/**
 * @author weifengshih
 */
public class RequestHolder {

    private static final ThreadLocal<AccountInfo> userHolder = new ThreadLocal<>();

    public static void add(AccountInfo accountInfo){
        userHolder.set(accountInfo);
    }

    public static AccountInfo getCurrentUser(){
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccountId("test");
        return accountInfo;
//        return userHolder.get();
    }


    public static void remove(){
        userHolder.remove();
    }
}
