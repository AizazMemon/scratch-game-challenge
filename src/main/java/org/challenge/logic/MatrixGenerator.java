package org.challenge.logic;

import java.util.List;
import java.util.Random;

import org.challenge.model.GameConfigModel;

public class MatrixGenerator {

    private final Random random = new Random();
    private final GameConfigModel config;

    public MatrixGenerator(GameConfigModel config) {
        this.config = config;
    }

    public String[][] generateMatrix() {
        int rows = config.getRows();
        int columns = config.getColumns();
        String[][] matrix = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                List<String> symbols = config.convertProbabilitiesToSymbols(i, j);
                matrix[i][j] = symbols.get(random.nextInt(symbols.size()));
            }
        }

        insertBonusSymbols(matrix);
        return matrix;
    }

    private void insertBonusSymbols(String[][] matrix) {
        List<String> bonusSymbols = config.convertProbabilitiesToSymbols(config.getProbabilities().getBonusSymbols()
                .getSymbols());
        int numberOfBonusSymbols = calculateNumberOfBonusSymbols();

        for (int i = 0; i < numberOfBonusSymbols; i++) {
            int row = random.nextInt(config.getRows());
            int column = random.nextInt(config.getColumns());
            String bonusSymbol = bonusSymbols.get(random.nextInt(bonusSymbols.size()));
            matrix[row][column] = bonusSymbol;
            config.setSelectedBonusSymbol(bonusSymbol);
        }
    }

    private int calculateNumberOfBonusSymbols() {
        return 1;
    }
}
