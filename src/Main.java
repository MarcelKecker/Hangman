import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    String[] wordList = {
            "hello",
            "xylophone",
            "motion",
            "contractor",
            "source",
            "word",
            "quiz",
            "hangman",
            "furious",
            "window",
            "blanket",
            "phone",
            "contagious",
            "venture",
            "qualification",
            "item",
            "glide",
            "terrify",
            "disgrace",
            "concert",
            "policy",
            "crisis",
            "method",
            "aisle",
            "bacon",
            "ceiling",
            "operational",
            "payment",
            "winner",
            "affect",
            "reform",
            "bring",
            "camera",
            "regret",
            "manage",
            "manner",
            "conscious",
            "enjoy",
            "petty",
            "rabbit",
            "overlook",
            "context",
            "harass",
            "mention",
            "allow",
            "powder",
            "restrain",
            "lead",
            "penalty",
            "original",
            "agriculture",
            "transport",
            "crash",
            "shame",
            "resource",
            "europe",
            "tribe",
            "deport",
            "increase",
            "exposure",
            "determined",
            "screw",
            "favor",
            "stage",
            "platform",
            "punch",
    };
    Scanner scanner;
    ArrayList<String> wrongGuesses;
    int lives;
    ArrayList<Boolean> lettersGuessed;
    boolean gameFinished;
    public Main() {
        scanner = new Scanner(System.in);
        wrongGuesses = new ArrayList<>();
        lettersGuessed = new ArrayList<>();
        play(startNewGame());
    }

    public void play(String word) {
        while (!gameFinished) {
            printGame(word);
            String input = scanner.nextLine();
            evaluateGuess(input, word);
        }
        boolean playAgain = askPlayAgain();
        if (playAgain) {
            String newWord = startNewGame();
            play(newWord);
        }
    }

    public String generateWord() {
        int wordIndex = (int) (Math.random() * wordList.length);
        return wordList[wordIndex];
    }

    private void printGame(String word) {
        System.out.println("Guess the random generated word");
        for (int i = 0; i < word.length(); i++) {
            if (lettersGuessed.get(i)) {
                System.out.print(word.charAt(i) + " ");
            } else {
                System.out.print("_ ");
            }
        }
        System.out.println("\n\nRemaining Lives: " + lives + "\n");
        if (!wrongGuesses.isEmpty()) {
            System.out.print("What you guessed: ");
            for (int i = 0; i < wrongGuesses.size(); i++) {
                System.out.print(wrongGuesses.get(i) + "  ");
            }
            System.out.println("");
        }
        System.out.println("Type your Guess");
    }

    private void evaluateGuess(String guess, String word) {
        if (guess.length() == 0) {
            System.out.println("Please guess the word or guess a letter in the word");
        } else if (guess.toLowerCase().equals(word)) {
            System.out.println("That was the word! You won:)");
            gameFinished = true;
        } else if (guess.length() > 1) {
            System.out.println("That was not the word :(\n");
            lives--;
        } else {
            for (int i = 0; i < word.length(); i++) {
                 if(Character.toString(word.charAt(i)).equals(guess)) {
                     lettersGuessed.set(i, true);
                     System.out.print("That letter is in the word! ");
                     if (allLettersGuessed()) {
                         gameFinished = true;
                         System.out.println("You won :) The word was " + word + "\n");
                     } else {
                         System.out.print("\n\n");
                     }
                 }
            }
            if (!word.contains(guess)) {
                wrongGuesses.add(guess);
                System.out.println("That letter is not in the word :(\n");
                lives--;
            }
        }
        if (lives < 0) {
            System.out.println("You ran out of lives :( The word was " + word + "\n");
            gameFinished = true;
        }
    }

    private boolean allLettersGuessed() {
        for (int i = 0; i < lettersGuessed.size(); i++) {
             if (!lettersGuessed.get(i)){
                 return false;
             }
        }
        return true;
    }

    private boolean askPlayAgain() {
        System.out.println("Would you like to play again? (y|n)");
        String response = scanner.nextLine();
        if (response.equals("y")) {
            return true;
        } else if (response.equals("n")) {
            return false;
        } else {
            System.out.println("Please type y or n");
            return askPlayAgain();
        }
    }

    private String startNewGame() {
        lives = 7;
        gameFinished = false;
        wrongGuesses.removeAll(wrongGuesses);
        String word = generateWord();
        lettersGuessed.removeAll(lettersGuessed);
        for (int i = 0; i < word.length(); i++) {
            lettersGuessed.add(false);
        }
        return word;
    }

    public static void main(String[] args) {
        new Main();
    }
}