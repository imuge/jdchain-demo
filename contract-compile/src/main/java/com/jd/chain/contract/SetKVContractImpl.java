package com.jd.chain.contract;

import com.jd.blockchain.contract.ContractEventContext;
import com.jd.blockchain.contract.EventProcessingAware;
import com.jd.blockchain.utils.Bytes;

public class SetKVContractImpl implements EventProcessingAware, SetKVContract {

    private ContractEventContext eventContext;

    @Override
    public void setKV(String address, String key, String value, long version) {
        try {
            eventContext.getLedger().dataAccount(Bytes.fromBase58(address)).setText(key, value, version);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeEvent(ContractEventContext eventContext) {
        this.eventContext = eventContext;
    }

    @Override
    public void postEvent(ContractEventContext eventContext, Exception error) {

    }
}
