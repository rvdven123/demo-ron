package com.rabo.demoron.repos.poa;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.rabo.demoron.repos.Authorization;
import com.rabo.demoron.repos.Direction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(builder = PowerOfAttorney.PowerOfAttorneyBuilder.class)
@Builder(builderClassName = "PowerOfAttorneyBuilder", toBuilder = true)
public class PowerOfAttorney {

        private final String id;
        private final String grantor;
        private final String grantee;
        private final String account;
        private final Direction direction;
        private final List<Authorization> authorizations;
        private final List<CardReference> cards;

        @JsonPOJOBuilder(withPrefix = "")
        public static class PowerOfAttorneyBuilder {
        }

}



