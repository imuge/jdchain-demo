package com.jd.blockchain.constant;

import com.jd.blockchain.crypto.HashDigest;
import com.jd.blockchain.crypto.KeyGenUtils;
import com.jd.blockchain.ledger.BlockchainKeypair;
import com.jd.blockchain.sdk.BlockchainService;
import com.jd.blockchain.sdk.client.GatewayServiceFactory;

public class Env {

    public static BlockchainKeypair adminKey;
    public static HashDigest ledgerHash;
    public static BlockchainService blockchainService;

    static {
        adminKey = new BlockchainKeypair(
                KeyGenUtils.decodePubKey("公钥"),
                KeyGenUtils.decodePrivKey(
                        "私钥",
                        "私钥密码")
        );
        blockchainService = GatewayServiceFactory.connect(
                "网关IP",
                8080, // 网关端口
                false,
                adminKey
        ).getBlockchainService();
        ledgerHash = blockchainService.getLedgerHashs()[0];
    }

}
