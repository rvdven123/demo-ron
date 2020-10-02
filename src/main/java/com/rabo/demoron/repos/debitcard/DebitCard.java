package com.rabo.demoron.repos.debitcard;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.rabo.demoron.repos.CardStatus;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = DebitCard.DebitCardBuilder.class)
@Builder(builderClassName = "DebitCardBuilder", toBuilder = true)
public class DebitCard {

    private final String id;
    private final CardStatus status;
    private final int cardNumber;
    //TODO other properties.

    @JsonPOJOBuilder(withPrefix = "")
    public static class DebitCardBuilder {
    }
}
