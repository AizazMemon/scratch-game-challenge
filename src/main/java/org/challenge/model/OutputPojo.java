package org.challenge.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class OutputPojo {
    String[][] matrix;
    Double reward;
    @JsonProperty("applied_winning_combinations")
    Map<String, String> appliedWinningCombinations;
    @JsonProperty("applied_bonus_symbol")
    String appliedBonusSymbol;
}
