package com.rabo.demoron.userinfo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = com.rabo.demoron.userinfo.model.DebitCardVO.DebitCardVOBuilder.class)
@Builder(builderClassName = "DebitCardVOBuilder", toBuilder = true)
public class DebitCardVO {

    //private final CardStatus status; do not explose status. Only active
    private final int cardNumber;
    //TODO other properties.

    @JsonPOJOBuilder(withPrefix = "")
    public static class DebitCardVOBuilder {
    }
}