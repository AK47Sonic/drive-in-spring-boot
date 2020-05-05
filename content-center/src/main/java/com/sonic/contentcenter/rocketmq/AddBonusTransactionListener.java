package com.sonic.contentcenter.rocketmq;

import com.sonic.contentcenter.dao.content.RocketmqTransactionLogMapper;
import com.sonic.contentcenter.domain.dto.content.ShareAuditDTO;
import com.sonic.contentcenter.domain.entity.content.RocketmqTransactionLog;
import com.sonic.contentcenter.service.content.ShareService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

/**
 * AddBonusTransactionListener
 *
 * @author Sonic
 * @since 2020/5/5
 */
@RocketMQTransactionListener(txProducerGroup = "tx-add-bonus-group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusTransactionListener implements RocketMQLocalTransactionListener {

    private final ShareService shareService;
    private final RocketmqTransactionLogMapper rocketmqTransactionLogMapper;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        MessageHeaders headers = msg.getHeaders();
        String transactionId = String.class.cast(headers.get(RocketMQHeaders.TRANSACTION_ID));
        Integer shareId = Integer.valueOf(String.class.cast(headers.get("share_id")));
        try {
            this.shareService.auditByIdWithRocketMqLog(shareId, ShareAuditDTO.class.cast(arg), transactionId);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        MessageHeaders headers = msg.getHeaders();
        String transactionId = String.class.cast(headers.get(RocketMQHeaders.TRANSACTION_ID));

        // select * from xxx where transaction_id = xxx
        RocketmqTransactionLog rocketmqTransactionLog = this.rocketmqTransactionLogMapper.selectOne(
                RocketmqTransactionLog.builder()
                        .transactionId(transactionId)
                        .build());

        if (rocketmqTransactionLog != null) {
            return  RocketMQLocalTransactionState.COMMIT;
        }

        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
