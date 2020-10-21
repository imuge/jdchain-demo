package com.jd.blockchain;

import com.jd.blockchain.ledger.BlockchainKeyGenerator;
import com.jd.blockchain.ledger.BlockchainKeypair;
import com.jd.blockchain.ledger.PreparedTransaction;
import com.jd.blockchain.ledger.TransactionResponse;
import com.jd.blockchain.ledger.TransactionTemplate;
import com.jd.blockchain.utils.Bytes;
import org.junit.Assert;
import org.junit.Test;

import static com.jd.blockchain.constant.Env.*;

public class EventTest {

    @Test
    public void testRegisterEventAccount() {
        TransactionTemplate txTemp = blockchainService.newTransaction(ledgerHash);
        BlockchainKeypair eventAccount = BlockchainKeyGenerator.getInstance().generate();
        System.out.println("事件账户地址：" + eventAccount.getAddress());

        txTemp.eventAccounts().register(eventAccount.getIdentity());

        PreparedTransaction ptx = txTemp.prepare();
        ptx.sign(adminKey);
        TransactionResponse response = ptx.commit();
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testPublishEvent() {
        TransactionTemplate txTemp = blockchainService.newTransaction(ledgerHash);

        txTemp.eventAccount(Bytes.fromBase58("时间账户地址")).publish("事件名", "事件内容", -1);

        PreparedTransaction ptx = txTemp.prepare();
        TransactionResponse response = ptx.commit();
        Assert.assertTrue(response.isSuccess());
    }

}
