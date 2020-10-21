package com.jd.blockchain.constant;

import com.jd.blockchain.ledger.Event;
import com.jd.blockchain.ledger.SystemEvent;
import com.jd.blockchain.sdk.EventContext;
import com.jd.blockchain.sdk.UserEventListener;
import com.jd.blockchain.sdk.UserEventPoint;
import com.jd.blockchain.utils.io.BytesUtils;

import static com.jd.blockchain.constant.Env.blockchainService;
import static com.jd.blockchain.constant.Env.ledgerHash;

public class EventListener {

    public static void main(String[] args) {
        blockchainService.monitorSystemEvent(ledgerHash,
                SystemEvent.NEW_BLOCK_CREATED, 0, (eventMessages, eventContext) -> {
                    for (Event eventMessage : eventMessages) {
                        // content中存放的是当前链上最新高度
                        System.out.println("new block " + eventMessage.getSequence());
                    }
                });

        blockchainService.monitorUserEvent(ledgerHash, "LdeNk8MJ4WzHM4Z2aE3MxyMfhqM2uzDjsdLu4", "event1", 0, new UserEventListener<UserEventPoint>() {
            @Override
            public void onEvent(Event eventMessage, EventContext<UserEventPoint> eventContext) {
                System.out.println(eventMessage.getName() + " - " + eventMessage.getSequence() + " - " + BytesUtils.toString(eventMessage.getContent().getBytes().toBytes()));
            }
        });
    }

}
