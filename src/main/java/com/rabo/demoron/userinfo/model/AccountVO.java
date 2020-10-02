package com.rabo.demoron.userinfo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@JsonDeserialize(builder = AccountVO.AccountVOBuilder.class)
@Builder(builderClassName = "AccountVOBuilder", toBuilder = true)
public class AccountVO {

    private final String owner;
    private final Long balance;
    private final Date created;
    private final Date ended;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AccountVOBuilder {
    }
}
