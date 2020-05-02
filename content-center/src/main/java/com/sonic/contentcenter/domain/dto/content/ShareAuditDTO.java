package com.sonic.contentcenter.domain.dto.content;

import com.sonic.contentcenter.domain.enums.AuditStatusEnum;
import lombok.Data;

/**
 * ShareAuditDTO
 *
 * @author Sonic
 * @since 2020/5/2
 */
@Data
public class ShareAuditDTO {

    /**
     * 审核状态
     */
    private AuditStatusEnum auditStatusEnum;
    private String reason;

}
