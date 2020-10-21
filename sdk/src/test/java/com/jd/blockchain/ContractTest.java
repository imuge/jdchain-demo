package com.jd.blockchain;

import com.jd.blockchain.ledger.BlockchainKeyGenerator;
import com.jd.blockchain.ledger.BlockchainKeypair;
import com.jd.blockchain.ledger.PreparedTransaction;
import com.jd.blockchain.ledger.TransactionResponse;
import com.jd.blockchain.ledger.TransactionTemplate;
import com.jd.blockchain.utils.io.FileUtils;
import com.jd.chain.contract.SetKVContract;
import org.junit.Assert;
import org.junit.Test;

import static com.jd.blockchain.constant.Env.*;

public class ContractTest {

    /**
     * 有两种方式部署合约：
     *  1. contract-compile项目下，配置好pom里面的参数，执行 mvn clean deploy 即可
     *  2. 打包contract-compile项目生成 car包，参考testDeploy测试代码部署
     */
    @Test
    public void testDeploy() {
        TransactionTemplate txTemp = blockchainService.newTransaction(ledgerHash);
        // 生成合约账户
        BlockchainKeypair contractAccount = BlockchainKeyGenerator.getInstance().generate();
        System.out.println("合约地址：" + contractAccount.getAddress());
        // 部署合约
        txTemp.contracts().deploy(contractAccount.getIdentity(), FileUtils.readBytes("src/test/resources/contract-compile-1.3.0.RELEASE.car"));

        PreparedTransaction ptx = txTemp.prepare();
        ptx.sign(adminKey);
        TransactionResponse response = ptx.commit();
        Assert.assertTrue(response.isSuccess());
    }

    @Test
    public void testSetKV() {
        TransactionTemplate txTemp = blockchainService.newTransaction(ledgerHash);

        txTemp.contract("合约地址", SetKVContract.class)
                .setKV("数据账户地址", "key", "value", -1);

        PreparedTransaction ptx = txTemp.prepare();
        ptx.sign(adminKey);
        TransactionResponse response = ptx.commit();
        Assert.assertTrue(response.isSuccess());
    }

}
