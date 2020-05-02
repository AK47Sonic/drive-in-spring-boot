package com.sonic.contentcenter.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * AuditStatusEnum
 *
 * @author Sonic
 * @since 2020/5/2
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {

    NOT_YET,
    PASS,
    REJECT

}
