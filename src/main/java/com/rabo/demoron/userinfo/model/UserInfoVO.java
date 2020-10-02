package com.rabo.demoron.userinfo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(builder = UserInfoVO.UserInfoVOBuilder.class)
@Builder(builderClassName = "UserInfoVOBuilder", toBuilder = true)
public class UserInfoVO {

    private final List<AccountVO> accounts;
    private final List<DebitCardVO> debitCards;

    @JsonPOJOBuilder(withPrefix = "")
    public static class UserInfoVOBuilder {
    }
}
