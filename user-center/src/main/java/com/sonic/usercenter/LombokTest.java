package com.sonic.usercenter;

import lombok.Data;

/**
 * LombokTest
 *
 * @author Sonic
 * @since 2020/4/5
 */
public class LombokTest {
    public static void main(String[] args) {

    }

//    @Getter
//    @Setter
//    @ToString
//    @EqualsAndHashCode
//    @NoArgsConstructor
//    @AllArgsConstructor
//    @RequiredArgsConstructor
//    @Builder
    @Data
    class UserRegisterDTO{
        private String email;
        private String password;
        private String confirmPasword;
    }

}
