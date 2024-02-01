package org.challenge.model;

import java.util.Map;
import lombok.Data;

@Data
public class StandardSymbolProbability {
    private Integer column;
    private Integer row;
    private Map<String, Integer> symbols;
}
