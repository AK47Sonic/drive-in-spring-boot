package com.sonic.usercenter.domain.dto.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserAddBonusMsgDTO
 *
 * @author Sonic
 * @since 2020/5/3
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAddBonusMsgDTO {
    private Integer userId;
    private Integer bonus;

}
