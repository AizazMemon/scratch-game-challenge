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

    /**
     * Generates a matrix of symbols based on the configuration.
     * The matrix size is determined by the number of rows and columns specified in the configuration.
     * Symbols are randomly selected for each cell based on the probabilities defined in the configuration.
     * Bonus symbols may also be inserted randomly into the matrix.
     *
     * @return A 2D array representing the generated matrix of symbols.
     */
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

    /**
     * Inserts bonus symbols into the matrix based on the configuration.
     * The number of bonus symbols inserted is determined by the number of bonus symbols specified in the configuration.
     * Bonus symbols are randomly placed in the matrix.
     *
     * @param matrix The 2D array representing the matrix of symbols.
     */
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
