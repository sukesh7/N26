package com.petstore.model.store;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inventory {
    @JsonProperty("approved")
    private Integer approved;
    @JsonProperty("placed")
    private Integer placed;
    @JsonProperty("delivered")
    private Integer delivered;
}
