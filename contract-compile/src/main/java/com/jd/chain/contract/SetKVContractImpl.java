package com.jd.chain.contract;

import com.jd.blockchain.contract.ContractEventContext;
import com.jd.blockchain.contract.EventProcessingAware;
import com.jd.blockchain.crypto.AsymmetricKeypair;
import com.jd.blockchain.crypto.Crypto;
import com.jd.blockchain.crypto.CryptoAlgorithm;
import com.jd.blockchain.crypto.SignatureFunction;
import com.jd.blockchain.ledger.BlockchainKeypair;
import com.jd.blockchain.utils.Bytes;

public class SetKVContractImpl implements EventProcessingAware, SetKVContract {

    private ContractEventContext eventContext;

    @Override
    public void setKV(String address, String key, String value, long version) {
        eventContext.getLedger().dataAccount(Bytes.fromBase58(address)).setText(key, value, version);
    }

    @Override
    public void registerUser(String seed) {
        CryptoAlgorithm algorithm = Crypto.getAlgorithm("ed25519");
        SignatureFunction signFunc = Crypto.getSignatureFunction(algorithm);
        AsymmetricKeypair cryptoKeyPair = signFunc.generateKeypair(seed.getBytes());
        BlockchainKeypair keypair = new BlockchainKeypair(cryptoKeyPair.getPubKey(), cryptoKeyPair.getPrivKey());
        eventContext.getLedger().users().register(keypair.getIdentity());
    }

    @Override
    public void registerDataAccount(String seed) {
        CryptoAlgorithm algorithm = Crypto.getAlgorithm("ed25519");
        SignatureFunction signFunc = Crypto.getSignatureFunction(algorithm);
        AsymmetricKeypair cryptoKeyPair = signFunc.generateKeypair(seed.getBytes());
        BlockchainKeypair keypair = new BlockchainKeypair(cryptoKeyPair.getPubKey(), cryptoKeyPair.getPrivKey());
        eventContext.getLedger().dataAccounts().register(keypair.getIdentity());
    }

    @Override
    public void registerEventAccount(String seed) {
        CryptoAlgorithm algorithm = Crypto.getAlgorithm("ed25519");
        SignatureFunction signFunc = Crypto.getSignatureFunction(algorithm);
        AsymmetricKeypair cryptoKeyPair = signFunc.generateKeypair(seed.getBytes());
        BlockchainKeypair keypair = new BlockchainKeypair(cryptoKeyPair.getPubKey(), cryptoKeyPair.getPrivKey());
        eventContext.getLedger().eventAccounts().register(keypair.getIdentity());
    }

    @Override
    public void publishEvent(String address, String topic, String content, long sequence) {
        eventContext.getLedger().eventAccount(Bytes.fromBase58(address)).publish(topic, content, sequence);
    }

    @Override
    public void beforeEvent(ContractEventContext eventContext) {
        this.eventContext = eventContext;
    }

    @Override
    public void postEvent(ContractEventContext eventContext, Exception error) {

    }
}
