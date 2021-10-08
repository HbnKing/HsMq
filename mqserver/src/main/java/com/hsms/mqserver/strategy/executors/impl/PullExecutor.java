package com.hsms.mqserver.strategy.executors.impl;

import com.hsmq.data.HsReq;
import com.hsmq.data.HsResp;
import com.hsmq.data.message.Pull;
import com.hsmq.data.message.PullMessage;
import com.hsmq.data.message.PullMessageResp;
import com.hsmq.data.message.SendMessage;
import com.hsmq.enums.OperationEnum;
import com.hsmq.enums.ResultEnum;
import com.hsms.mqserver.strategy.executors.BaseExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：河神
 * @date ：Created in 2021/6/9 1:50 下午
 */
public class PullExecutor extends BaseExecutor<Pull> {

    @Override
    public HsResp<?> executor(HsReq<Pull> hsReq) {

        Pull pull = hsReq.getData();
        List<SendMessage> sendMessages = messageStore.pullMessage(pull);


        List<PullMessage> pullMessages = new ArrayList<>();
        if (sendMessages!=null){
            for (SendMessage sendMessage : sendMessages) {
                PullMessage pullMessage = new PullMessage();
                pullMessage.setMsgId(sendMessage.getMsgId());
                pullMessage.setBody(sendMessage.getBody());
                pullMessage.setKey(sendMessage.getKey());
                pullMessage.setTopic(sendMessage.getTopic());
                pullMessage.setTag(sendMessage.getTag());
                pullMessage.setOffset(sendMessage.getOffset());

                pullMessages.add(pullMessage);
            }
        }


        PullMessageResp pullMessageResp = new PullMessageResp();
        pullMessageResp.setPullMessages(pullMessages);
//        pullMessageResp.setLastOffset();

        pullMessageResp.setTopic(pull.getTopic());
        pullMessageResp.setQueueId(pull.getQueueId());


        HsResp<PullMessageResp> resp = new HsResp<>();
        resp.setData(pullMessageResp);
        resp.setOperation(OperationEnum.Resp.getOperation());
        resp.setResult(ResultEnum.SendOK.getCode());
        return resp;
    }

    @Override
    public HsResp<?> executor0(HsReq<?> hsReq) {
        if (hsReq.getData() instanceof Pull){
            HsReq<Pull> data = new HsReq<>();
            data.setData((Pull)hsReq.getData());
            data.setOperation(hsReq.getOperation());
            return executor(data);
        }
        return HsResp.typeError();
    }
}
