package com.sonic.springswagger.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 *
 * @author Sonic
 * @since 2020/4/29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("User DTO")
public class User {

    @ApiModelProperty("user id")
    private Integer userId;

    @ApiModelProperty("user name")
    private String userName;

}
