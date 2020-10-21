package com.jd.chain.contract;

import com.jd.blockchain.contract.Contract;
import com.jd.blockchain.contract.ContractEvent;
import com.jd.blockchain.ledger.BlockchainIdentity;

@Contract
public interface SetKVContract {

    @ContractEvent(name = "setkv")
    void setKV(String address, String key, String value, long version);
}
