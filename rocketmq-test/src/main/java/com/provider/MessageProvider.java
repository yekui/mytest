package com.provider;

import com.MessageBase;
import com.common.ThreadUtil;
import com.vdian.vdianmq.client.MqClientImpl;
import com.vdian.vdianmq.client.MqException;
import com.vdian.vdianmq.client.producer.MqProducerImpl;
import com.vdian.vdianmq.client.producer.MqProducerImplV2;
import com.vdian.vdianmq.model.Message;
import com.vdian.vdianmq.model.remoting.SendResult;
import org.junit.Test;

public class MessageProvider extends MessageBase {
    @Test
    public void pushMsg() {
        MqProducerImplV2 mqProducerImplV2 = new MqProducerImplV2();
        mqProducerImplV2.setProducerGroup(producerGroup);
        mqProducerImplV2.setNamesrvAddr(namesrvAddr);
        mqProducerImplV2.init();

        Message message = new Message();
        message.setTopic(topic);
        for (int i = 0; i < 1; i++) {
            try {
                String msg = "hello MqProducerImplV2 dk test " + i;
                message.setBody((msg).getBytes());
                SendResult sendResult = mqProducerImplV2.send(message);
                if (sendResult.isSuccess()) {
                    System.out.println("msg push success:" + msg);
                }
            } catch (MqException e) {
                System.out.println(e);
            }
        }

      //  ThreadUtil.join();
    }



    @Test
    // 结论是新申请的rocketmq 统一走V2版本
    public void pushMsgV1() {
        MqClientImpl mqClient = new MqClientImpl();
        mqClient.setZkAddress("zk1.daily.idcvdian.com,zk2.daily.idcvdian.com,zk3.daily.idcvdian.com");
        mqClient.init();

        MqProducerImpl mqProducerImpl = new MqProducerImpl();
      // mqProducerImplV2.setProducerGroup(producerGroup);
        mqProducerImpl.setNamesrvAddr(namesrvAddr);
        mqProducerImpl.setMqClient(mqClient);
        mqProducerImpl.init();

        Message message = new Message();
        message.setTopic("item_category_update");

        for (int i = 0; i < 1; i++) {
            try {
                String msg = "hello MqProducerImplV1 dk test " + i;
                message.setBody((msg).getBytes());
                SendResult sendResult = mqProducerImpl.send(message);
                if (sendResult.isSuccess()) {
                    System.out.println("msg push success:" + msg);
                }
            } catch (MqException e) {
                System.out.println(e);
            }
        }

        ThreadUtil.join();
    }
}
