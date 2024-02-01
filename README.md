#1. Objective:
Need to create a scratch game where users place bets, and based on the generated matrix of symbols and predefined winning combinations, User either win or lose.

#2. Symbols:
There are two types of symbols: Standard Symbols and Bonus Symbols.

**Standard Symbols:** These symbols determine if the user wins or loses based on predefined winning combinations.

**Bonus Symbols:** These symbols enhance the reward when certain conditions are met. They can multiply the final reward or add extra bonus amounts.

#3. Configuration:
The game's behavior is defined in a JSON configuration file, which includes:
* Number of columns and rows in the game matrix.
* Definitions of standard and bonus symbols, along with their properties like reward multiplier, type, and impact.
* Probabilities of each symbol appearing in different cells of the matrix.
* Definitions of winning combinations and their corresponding reward multipliers.

#4. Game Logic:
**Matrix Generation:** Generate a 2D matrix of symbols based on the configuration and probabilities.

**Reward Calculation:** Calculate the reward based on the generated matrix, the user's betting amount, and the applied winning combinations and bonus symbols.

**Handling Bonus Symbols:** Apply bonus symbols if any winning combinations are matched in the matrix.

#5. Input and Output:
**Input:** User's betting amount.

**Output:** The generated matrix, final reward amount, applied winning combinations, and applied bonus symbol (if any).

#6. Run the Application

## Requirements ##

- JDK >= 17
- Maven
- libraries: Jackson and Lombok
```bash
 java -jar <your-jar-file> --config config.json --betting-amount 100
```
E.g:
```bash
java -jar target/scratch-game-1.jar --config src/main/resources/config.json --betting-amount 100
```
