package com.sonic.contentcenter.controller.content;

import com.sonic.contentcenter.domain.dto.content.ShareAuditDTO;
import com.sonic.contentcenter.domain.entity.content.Share;
import com.sonic.contentcenter.service.content.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ShareAdminController
 *
 * @author Sonic
 * @since 2020/5/2
 */

@RestController
@Slf4j
@RequestMapping("/admin/shares")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareAdminController {

    private final ShareService shareService;

    @PutMapping("/audit/{id}")
    public Share auditById(@PathVariable("id") Integer id, @RequestBody ShareAuditDTO auditDTO) {
        // TODO 认证，授权
        return this.shareService.auditById(id, auditDTO);
    }

}
