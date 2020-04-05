package com.sonic.contentcenter;

import com.sonic.contentcenter.dao.content.ShareMapper;
import com.sonic.contentcenter.domain.entity.content.Share;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * TestController
 *
 * @author Sonic
 * @since 2020/4/5
 */
@RestController
@RequiredArgsConstructor
public class TestController {

    private final ShareMapper shareMapper;

    @GetMapping("/test")
    public List<Share> testInert() {

        Share share = new Share();
        share.setCreateTime(new Date());
        share.setUpdateTime(new Date());
        share.setTitle("xxx");
        share.setCover("xxx");
        share.setAuthor("Sonic");
        share.setBuyCount(1);

        this.shareMapper.insertSelective(share);
        List<Share> shares = this.shareMapper.selectAll();
        return shares;
    }

}
