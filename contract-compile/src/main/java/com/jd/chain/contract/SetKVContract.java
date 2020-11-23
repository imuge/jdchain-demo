package com.jd.chain.contract;

import com.jd.blockchain.contract.Contract;
import com.jd.blockchain.contract.ContractEvent;

@Contract
public interface SetKVContract {

    /**
     * 设置KV
     *
     * @param address 数据账户地址
     * @param key     键
     * @param value   值
     * @param version 版本
     */
    @ContractEvent(name = "setkv")
    void setKV(String address, String key, String value, long version);

    /**
     * 注册用户
     *
     * @param seed 种子，不小于32个字符
     */
    @ContractEvent(name = "registerUser")
    void registerUser(String seed);

    /**
     * 注册数据账户
     *
     * @param seed 种子，不小于32个字符
     */
    @ContractEvent(name = "registerDataAccount")
    void registerDataAccount(String seed);

    /**
     * 注册事件账户
     *
     * @param seed 种子，不小于32个字符
     */
    @ContractEvent(name = "registerEventAccount")
    void registerEventAccount(String seed);

    /**
     * 发布事件
     *
     * @param address  事件账户地址
     * @param topic    消息名称
     * @param content  内容
     * @param sequence 当前消息名称下最大序号（初始为-1）
     */
    @ContractEvent(name = "publishEvent")
    void publishEvent(String address, String topic, String content, long sequence);
}
