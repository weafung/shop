package com.weafung.shop;

import com.weafung.shop.model.dto.AccountInfoDTO;

/**
 * @author weifengshih
 */
public class RequestHolder {

    private static final ThreadLocal<AccountInfoDTO> userHolder = new ThreadLocal<>();

    public static void add(AccountInfoDTO accountInfoDTO){
        userHolder.set(accountInfoDTO);
    }

    public static AccountInfoDTO getCurrentUser(){
//        AccountInfo accountInfo = new AccountInfo();
//        accountInfo.setAccountId("test");
//        return accountInfo;
        return userHolder.get();
    }


    public static void remove(){
        userHolder.remove();
    }
}
