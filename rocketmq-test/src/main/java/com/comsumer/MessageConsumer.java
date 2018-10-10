package com.comsumer;

import com.MessageBase;
import com.common.ThreadUtil;
import com.vdian.vdianmq.client.consumer.ConsumeListener;
import com.vdian.vdianmq.client.consumer.ConsumeRetryPolicy;
import com.vdian.vdianmq.client.consumer.Context;
import com.vdian.vdianmq.client.consumer.Status;
import com.vdian.vdianmq.client.consumer.pull.PullConsumerV2;
import com.vdian.vdianmq.model.Message;
import com.vdian.vdianmq.utils.HexUtil;
import org.junit.Test;

public class MessageConsumer extends MessageBase {

    // 一台机器只能有一个conusmer同一个topic
    public void pullMsg() {

        PullConsumerV2 pullConsumerV2 = new PullConsumerV2();

        pullConsumerV2.setBroadcast(true);
        pullConsumerV2.setNamesrvAddr(namesrvAddr);
        pullConsumerV2.setConsumerGroup(consumerGroup);
        pullConsumerV2.init();

        pullConsumerV2.subscribe(topic, new ConsumeListener() {
            @Override
            public ConsumeRetryPolicy retryPolicy() {
                return null;
            }

            @Override
            public Status on(Message message, Context context) {
                String msgId = HexUtil.toHexString(message.getMessageId());
                String messageContent = new String(message.getBody());
                System.out.println(" -->receive msg success. msgId: " + msgId + " messageContent : " + messageContent);
                return Status.SUCCESS;
            }
        });

        System.out.println("subscribe finished.");

        ThreadUtil.join();
    }

    @Test
    //MessageModel.BROADCASTING
    public void pushBroadcastMsg() {
        PullConsumerV2 pullConsumerV2 = new PullConsumerV2();
        pullConsumerV2.setNamesrvAddr(namesrvAddr);
        pullConsumerV2.setConsumerGroup(consumerGroup);
        pullConsumerV2.setBroadcast(true);

        pullConsumerV2.init();

        pullConsumerV2.subscribe(topic, new ConsumeListener() {
            @Override
            public ConsumeRetryPolicy retryPolicy() {
                return null;
            }

            @Override
            public Status on(Message message, Context context) {
                String msgId = HexUtil.toHexString(message.getMessageId());
                String messageContent = new String(message.getBody());
                System.out.println("receive msg success. msgId: " + msgId + " messageContent : " + messageContent);
                return Status.SUCCESS;
            }
        });

        System.out.println("subscribe finished.");

        ThreadUtil.join();
    }


    @Test
    //MessageModel.BROADCASTING
    public void pullConsumerV2() {
        PullConsumerV2 pullConsumerV2 = new PullConsumerV2();
        pullConsumerV2.setNamesrvAddr(namesrvAddr);
        pullConsumerV2.setConsumerGroup(consumerGroup);
        pullConsumerV2.setBroadcast(true);
        pullConsumerV2.setConsumeMessageBatchMaxSize(10);

        pullConsumerV2.init();

        pullConsumerV2.subscribe(topic, new ConsumeListener() {
            @Override
            public ConsumeRetryPolicy retryPolicy() {
                return null;
            }

            @Override
            public Status on(Message message, Context context) {
                String msgId = HexUtil.toHexString(message.getMessageId());
                String messageContent = new String(message.getBody());
                System.out.println("receive msg success. msgId: " + msgId + " messageContent : " + messageContent);
                return Status.SUCCESS;
            }
        });

        System.out.println("subscribe finished.");
    }


}
