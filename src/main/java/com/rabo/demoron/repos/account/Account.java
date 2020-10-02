package com.rabo.demoron.repos.account;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = Account.AccountBuilder.class)
@Builder(builderClassName = "AccountBuilder", toBuilder = true)
public class Account {

    private final String owner;
    private final Long balance;
    private final Date created;//TODO oldschool :-) use java.time.
    private final Date ended;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AccountBuilder {
    }
}
