package com.jd.blockchain;

import com.jd.blockchain.ledger.BlockchainKeyGenerator;
import com.jd.blockchain.ledger.BlockchainKeypair;
import com.jd.blockchain.ledger.PreparedTransaction;
import com.jd.blockchain.ledger.TransactionResponse;
import com.jd.blockchain.ledger.TransactionTemplate;
import org.junit.Assert;
import org.junit.Test;

import static com.jd.blockchain.constant.Env.*;

public class UserTest {

    @Test
    public void testRegisterUser() {
        TransactionTemplate txTemp = blockchainService.newTransaction(ledgerHash);
        BlockchainKeypair user = BlockchainKeyGenerator.getInstance().generate();
        System.out.println("用户地址：" + user.getAddress());

        txTemp.users().register(user.getIdentity());

        PreparedTransaction ptx = txTemp.prepare();
        TransactionResponse response = ptx.commit();
        ptx.sign(adminKey);
        Assert.assertTrue(response.isSuccess());
    }

}
