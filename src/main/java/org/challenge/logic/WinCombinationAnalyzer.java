package org.challenge.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.challenge.model.GameConfigModel;
import org.challenge.model.MatchedWinCombination;
import org.challenge.model.OutputPojo;
import org.challenge.model.Symbol;
import org.challenge.model.WinCombination;

public class WinCombinationAnalyzer {
    private final String[][] matrix;
    private final GameConfigModel config;

    private final List<MatchedWinCombination> matchedWinCombinations = new ArrayList<>();

    public WinCombinationAnalyzer(String[][] matrix, GameConfigModel config) {
        this.matrix = matrix;
        this.config = config;
    }

    public OutputPojo calculateTotalReward(Double betAmount) {
        double totalReward;

        // Check for each win condition
        config.getWinCombinations().forEach(this::checkWinCondition);

        totalReward = matchedWinCombinations.stream().map(matchedWinCombination -> {
            Symbol symbol = config.getSymbols().get(matchedWinCombination.getSymbol());
            return betAmount * symbol.getRewardMultiplier() * matchedWinCombination.getRewardMultiplier();
        }).mapToDouble(Double::doubleValue).sum();

        // Add Bonus rewards
        Symbol bonusSymbol = config.getSymbols().get(config.getSelectedBonusSymbol());
        if (!matchedWinCombinations.isEmpty() && bonusSymbol.getImpact().equals("extra_bonus")) {
            totalReward += bonusSymbol.getExtra();
        } else if (!matchedWinCombinations.isEmpty() && bonusSymbol.getImpact().equals("multiply_reward")) {
            totalReward *= bonusSymbol.getRewardMultiplier();
        }

        /*
         (bet_amount x reward(symbol_A) x reward(same_symbol_5_times) x reward(same_symbols_vertically)) +
         (bet_amount x reward(symbol_B) x reward(same_symbol_3_times) x reward(same_symbols_vertically))
         (+/x) reward(+1000)
         =
         (100 x5 x5 x2) + (100 x3 x1 x2) +1000 = 5000 + 600 + 1000 = 6600
        * */
        return new OutputPojo(
                matrix,
                totalReward,
                matchedWinCombinations.stream().collect(
                        Collectors.toMap(MatchedWinCombination::getSymbol, MatchedWinCombination::getWinCombinationName)
                ),
                config.getSelectedBonusSymbol()
        );
    }

    private boolean checkWinCondition(String name, WinCombination winCombination) {
        // Implement logic to check the specific win condition
        return switch (winCombination.getGroup()) {
            case "same_symbols" -> checkSameSymbols(name, winCombination);
            case "linear_symbols" -> checkLinearSymbols(winCombination.getGroup());
            default -> false;
        };
    }

    public boolean checkSameSymbols(String name, WinCombination winCombination) {
        List<String> arraysString = Stream.of(matrix).flatMap(Stream::of).toList();

        return config.getSymbols().keySet()
                .stream()
                .filter(symbol -> Collections.frequency(arraysString, symbol) == winCombination.getCount())
                .peek(symbol -> matchedWinCombinations.add(new MatchedWinCombination(symbol, name,
                        winCombination.getRewardMultiplier())))
                .findFirst()
                .isPresent();
    }

    public boolean checkLinearSymbols(String group) {
        // Implement logic to check for linear symbols (horizontal, vertical, diagonal)
        return false;
    }
}