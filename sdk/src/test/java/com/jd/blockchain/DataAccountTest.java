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

public class DataAccountTest {


    @Test
    public void testRegisterDataAccount() {
        TransactionTemplate txTemp = blockchainService.newTransaction(ledgerHash);
        BlockchainKeypair dataAccount = BlockchainKeyGenerator.getInstance().generate();
        System.out.println("数据账户地址：" + dataAccount.getAddress());

        txTemp.dataAccounts().register(dataAccount.getIdentity());

        PreparedTransaction ptx = txTemp.prepare();
        ptx.sign(adminKey);
        TransactionResponse response = ptx.commit();
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testSetKV() {
        TransactionTemplate txTemp = blockchainService.newTransaction(ledgerHash);

        txTemp.dataAccount(Bytes.fromBase58("数据账户地址"))
                .setText("key", "value", -1)
        ;

        PreparedTransaction ptx = txTemp.prepare();
        ptx.sign(adminKey);
        TransactionResponse response = ptx.commit();
        Assert.assertTrue(response.isSuccess());
    }

}
