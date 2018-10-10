package com.comsumer;

import com.MessageBase;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class BroadcastConsumer1 extends MessageBase {

    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.setNamesrvAddr(namesrvAddr);

        consumer.subscribe(topic, "");
        consumer.registerMessageListener(
                new MessageListenerOrderly() {
                    @Override
                    public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                        System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                        return ConsumeOrderlyStatus.SUCCESS;
                    }
                }
        );

        //        consumer.registerMessageListener(new MessageListenerConcurrently() {
        //            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        //                System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
        //                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        //            }
        //        });

        consumer.start();
        System.out.printf("Broadcast Consumer1 Started.%n");
    }
}
