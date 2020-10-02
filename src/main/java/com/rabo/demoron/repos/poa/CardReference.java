package com.rabo.demoron.repos.poa;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.rabo.demoron.repos.CardType;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(builder = CardReference.CardReferenceBuilder.class)
@Builder(builderClassName = "CardReferenceBuilder", toBuilder = true)
public class CardReference {

    private final CardType type;
    private final String id;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CardReferenceBuilder {
    }
}
