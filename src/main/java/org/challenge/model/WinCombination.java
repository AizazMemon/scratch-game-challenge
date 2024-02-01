package org.challenge.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WinCombination {
    @JsonProperty("reward_multiplier")
    private Double rewardMultiplier;
    private String when;
    private Integer count;
    private String group;
    @JsonProperty("covered_areas")
    private List<List<String>> coveredAreas;
}
