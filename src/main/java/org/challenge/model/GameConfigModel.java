package org.challenge.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GameConfigModel {
    private Integer columns;
    private Integer rows;
    private Map<String, Symbol> symbols;
    private Probabilities probabilities;
    @JsonProperty("win_combinations")
    private Map<String, WinCombination> winCombinations;

    @JsonIgnore
    private String selectedBonusSymbol;


    public List<String> convertProbabilitiesToSymbols(int row, int column) {
        Map<String, Integer> stringIntegerMap = probabilities.getStandardSymbols().stream()
                .filter(it -> it.getRow() == row && it.getColumn() == column)
                .findFirst().orElseThrow().getSymbols();
        return convertProbabilitiesToSymbols(stringIntegerMap);
    }

    public List<String> convertProbabilitiesToSymbols(Map<String, Integer> stringIntegerMap) {
        List<String> symbolsByProbability = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : stringIntegerMap.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                symbolsByProbability.add(entry.getKey());
            }
        }
        return symbolsByProbability;
    }
}

