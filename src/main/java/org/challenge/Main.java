package org.challenge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.challenge.logic.MatrixGenerator;
import org.challenge.logic.WinCombinationAnalyzer;
import org.challenge.model.GameConfigModel;

public class Main {
    public static void main(String[] args) throws IOException {

         /*String json = Files.readString(Path.of("src/main/resources/config.json")); // Your JSON string here

        ObjectMapper mapper = new ObjectMapper();
        GameConfigModel config = mapper.readValue(json, GameConfigModel.class);

        System.out.println(config);

        String[][] matrix = new MatrixGenerator(config).generateMatrix();
        System.out.println(Arrays.deepToString(matrix));

        System.out.println(new WinCombinationAnalyzer(matrix, config).calculateTotalReward(100.0));*/

        if (args.length != 4 || !args[0].equals("--config") || !args[2].equals("--betting-amount")) {
            System.out.println("4 command line arguments not found. E.g. --config config.json --betting-amount 100");

        } else {
            System.out.println("Input 1: " + args[0] + " " + args[1]);
            System.out.println("Input 2: " + args[2] + " " + args[3]);

            String json = Files.readString(Path.of(args[1]));
            ObjectMapper mapper = new ObjectMapper();
            GameConfigModel config = mapper.readValue(json, GameConfigModel.class);

            System.out.println("---");

            String[][] matrix = new MatrixGenerator(config).generateMatrix();
            System.out.println("Generated Matrix:");
            System.out.println(Arrays.deepToString(matrix));

            String outputJson = mapper.writeValueAsString(
                    new WinCombinationAnalyzer(matrix, config).calculateTotalReward(Double.valueOf(args[3])));
            System.out.println("Output: " + outputJson);
        }
    }
}