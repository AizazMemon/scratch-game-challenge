package org.challenge.model;

import lombok.Value;

@Value
public class MatchedWinCombination {
    String symbol;
    String winCombinationName;
    Double rewardMultiplier;
}
