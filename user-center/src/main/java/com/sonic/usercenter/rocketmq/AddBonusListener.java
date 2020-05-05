package com.sonic.usercenter.rocketmq;

import com.sonic.usercenter.dao.log.BonusEventLogMapper;
import com.sonic.usercenter.dao.user.UserMapper;
import com.sonic.usercenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.sonic.usercenter.domain.entity.log.BonusEventLog;
import com.sonic.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * AddBonusListener
 *
 * @author Sonic
 * @since 2020/5/3
 */
@Service
@Slf4j
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {

    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public void onMessage(UserAddBonusMsgDTO message) {
        log.info("---------- Process message --------------------");

//        加用户积分
        Integer userId = message.getUserId();
        Integer bonus = message.getBonus();
        User user = this.userMapper.selectByPrimaryKey(userId);
        user.setBonus(user.getBonus() + message.getBonus());
        this.userMapper.updateByPrimaryKeySelective(user);
//      记录日志
        this.bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userId).value(bonus).event("contribute").createTime(new Date())
                .description("投稿加积分")
                .build());
        log.info("bonus is added successfully...");

    }
}
