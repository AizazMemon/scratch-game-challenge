package org.challenge.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Probabilities {
    @JsonProperty("standard_symbols")
    private List<StandardSymbolProbability> standardSymbols;
    @JsonProperty("bonus_symbols")
    private Probability bonusSymbols;
}
