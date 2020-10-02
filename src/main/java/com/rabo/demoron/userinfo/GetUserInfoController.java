package com.rabo.demoron.userinfo;

import com.rabo.demoron.userinfo.model.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class GetUserInfoController {

    private final GetUserInfo getUserInfo;

    @Autowired
    GetUserInfoController(GetUserInfo getUserInfo){
        this.getUserInfo = getUserInfo;
    }

    @GetMapping(path = "/userinfo", produces = {"application/json"})
    public UserInfoVO userInfo(Principal user){
        return getUserInfo.execute(user.getName());
    }

}
