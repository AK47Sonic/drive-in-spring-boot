package com.sonic.contentcenter.service.content;

import com.sonic.contentcenter.dao.content.ShareMapper;
import com.sonic.contentcenter.domain.dto.content.ShareDTO;
import com.sonic.contentcenter.domain.dto.user.UserDTO;
import com.sonic.contentcenter.domain.entity.content.Share;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * ShareService
 *
 * @author Sonic
 * @since 2020/4/5
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {

    private final ShareMapper shareMapper;

    private final RestTemplate restTemplate;

    public ShareDTO findById(Integer id) {
        Share share = this.shareMapper.selectByPrimaryKey(id);
        Integer userId = share.getUserId();

        ResponseEntity<UserDTO> forEntity = restTemplate.getForEntity("http://localhost:8081/users/{id}", UserDTO.class, userId);
        UserDTO userDTO = forEntity.getBody();

        ShareDTO shareDTO = new ShareDTO();
        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setWxNickname(userDTO.getWxNickname());

        return shareDTO;
    }

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
//        String forObject = restTemplate.getForObject("http://localhost:8081/users/{id}", String.class, 1);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8081/users/{id}", String.class, 1);
        System.out.println(forEntity.getBody());

        /**
         * 200 OK
         * 500 服务端异常
         * 502 bad gateway
         */
        System.out.println(forEntity.getStatusCode());
    }

}
