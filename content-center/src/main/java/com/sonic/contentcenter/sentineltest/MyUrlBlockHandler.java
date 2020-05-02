package com.sonic.contentcenter.sentineltest;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * MyUrlBlockHandler
 *
 * @author Sonic
 * @since 2020/5/1
 */
@Component
public class MyUrlBlockHandler implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
        ErrorMsg msg = null;
        if (ex instanceof FlowException) {
            msg = ErrorMsg.builder().status(100).msg("限流了").build();
        } else if (ex instanceof DegradeException) {
            msg = ErrorMsg.builder().status(101).msg("降级了").build();
        } else if (ex instanceof ParamFlowException) {
            msg = ErrorMsg.builder().status(102).msg("热点参数").build();
        } else if (ex instanceof SystemBlockException) {
            msg = ErrorMsg.builder().status(103).msg("系统规则负载").build();
        } else {
            msg = ErrorMsg.builder().status(104).msg("来源授权规则不通过").build();
        }
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        response.setCharacterEncoding(Charsets.UTF_8.name());
        response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        response.setContentType("application/json;charset=utf-8");
        new ObjectMapper()
                .writeValue(response.getWriter(), msg);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class ErrorMsg {
    private Integer status;
    private String msg;
}
