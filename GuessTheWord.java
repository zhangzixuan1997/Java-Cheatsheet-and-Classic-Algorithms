//Fill in your name and student number
//Name: Sean Zhang
//Student Number: 260873386

// do NOT touch the following import statement
import java.util.Random;
import java.util.Scanner;

public class GuessTheWord {

    private static final String[] words = {"perfect", "country", "pumpkin", "special", "freedom", "picture", "husband",
            "monster", "seventy", "nothing", "sixteen", "morning", "journey", "history", "amazing", "dolphin", "teacher",
            "forever", "kitchen", "holiday", "welcome", "diamond", "courage", "silence", "someone", "science", "revenge",
            "harmony", "problem", "awesome", "penguin", "youtube", "blanket", "musical", "thirteen", "princess", "assonant",
            "thousand", "language", "chipotle", "business", "favorite", "elephant", "children", "birthday", "mountain",
            "football", "kindness", "abdicate", "treasure", "strength", "together", "memories", "darkness", "sandwich",
            "calendar", "marriage", "building", "function", "squirrel", "tomorrow", "champion", "sentence", "daughter",
            "hospital", "identical", "chocolate", "beautiful", "happiness", "challenge", "celebrate", "adventure",
            "important", "consonant", "dangerous", "irregular", "something", "knowledge", "pollution", "wrestling",
            "pineapple", "adjective", "secretary", "ambulance", "alligator", "congruent", "community", "different",
            "vegetable", "influence", "structure", "invisible", "wonderful", "nutrition", "crocodile", "education",
            "beginning", "everything", "basketball", "weathering", "characters", "literature", "perfection", "volleyball",
            "homecoming", "technology", "maleficent", "watermelon", "appreciate", "relaxation", "abominable", "government",
            "strawberry", "retirement", "television", "silhouette", "friendship", "loneliness", "punishment", "university",
            "confidence", "restaurant", "abstinence", "blackboard", "discipline", "helicopter", "generation", "skateboard",
            "understand", "leadership", "revolution"};

    // this method takes an integer as input and returns a radom String from the array above.
    // you can use it, but do NOT modified neither the method NOR the above array.
    public static String getRandomWord(int seed) {
        Random gen = new Random(seed);
        int randomIndex = gen.nextInt(words.length);
        return words[randomIndex];
    }

    //========================
    // Enter your code below
// main method
    public static void main(String[] args) {
        play(5);
    }
// Method to check if the guess is valid
//The method returns true if such character is a lower-case letter of the English alphabet, false otherwise.
    public static boolean isValidGuess(char guessByPlayer) {
        if (guessByPlayer >= 97 && guessByPlayer <= 122) {
            return true;
        } else {
            return false;
        }
    }
//Method to generate the array of guesses
    public static int[] generateArrayOfGuesses(String wordToBeGuessed) {
        int lengthOfWord = wordToBeGuessed.length();
        int[] ArrayOfGuesses = new int[lengthOfWord];
        for (int i = 0; i < lengthOfWord; i++) {
            ArrayOfGuesses[i] = 0;
        }
        return ArrayOfGuesses;
    }
    //Method to check the guess and update the array if needed
    public static boolean checkAndUpdate(String secretWord, int[] arrayOfGuesses, char lastGuess) {
        int word_length = secretWord.length();
        boolean check = false;
        for (int i = 0; i < word_length; i++) {
            if (lastGuess == secretWord.charAt(i)) {
                arrayOfGuesses[i] = 1;
                check = true;
            }
        }
        return check;
    }
//Method to get the String to display
    public static String getWordToDisplay(String secretWord, int[] arrayOfGuesses, char lastGuess) {
        int lenghOfArray = arrayOfGuesses.length;
        String wordToBeSeen = "";
        for (int i = 0; i < lenghOfArray; i++) {
            if (arrayOfGuesses[i] == 1) {
                if(secretWord.charAt(i)!=lastGuess){
                    wordToBeSeen+=secretWord.charAt(i);
                }else{
                    char upperChar = (char)(lastGuess-32);
                    wordToBeSeen+=upperChar;
                }
            } else {
                wordToBeSeen += "-";
            }
        }
        return wordToBeSeen;
    }
//Method to check if the word has been completely guessed
    public static boolean isWordGuessed(int[] arrayOfGuesses) {
        for (int i = 0; i < arrayOfGuesses.length; i++) {
            if (arrayOfGuesses[i] == 0) {
                return false;
            }
        }
        return true;
    }
    //Method to simulate a game
    public static void play(int inputInteger) {
        String secretWord = getRandomWord(inputInteger);
        int[] arrayOfGuesses = generateArrayOfGuesses(secretWord);
        System.out.println("Welcome to Guess The Word ! ");
        System.out.println("Your secret word has been generated. It has " + arrayOfGuesses.length + " characters. You have 10 lives. Good Luck!");
        int livesLeft = 10;
        String wordToDisplay;
        while (true) {
            Scanner s = new Scanner(System.in);
            String guess = s.next();
            char[] guess2 = guess.toCharArray();
            char guessedChar = guess2[0];
            //Make sure it is a valid guess
            if (guess.length() > 1) {
                System.out.println("You can only enter one single character. Try again! ");
            } else if (!isValidGuess(guessedChar)) {
                System.out.println("The word must be a lower case letter of English alphabet. Try again.");
                //check and update
            } else if (checkAndUpdate(secretWord, arrayOfGuesses, guessedChar)) {
                System.out.println("Good job! the word contains the character " + "'" + guessedChar + "'");
                wordToDisplay = getWordToDisplay(secretWord, arrayOfGuesses, guessedChar);
                System.out.println(wordToDisplay);
                //check whether the word has been guessed
                if (isWordGuessed(arrayOfGuesses)) {
                    System.out.println("Congratulations you guessed the secret word");
                    break;
                }
            } else {
                livesLeft -= 1;
                // check whether the lives left are positive
                if (livesLeft > 0) {
                    System.out.println("You have " + livesLeft + " lives left.There is no such character. Try again! ");
                    wordToDisplay = getWordToDisplay(secretWord, arrayOfGuesses, guessedChar);
                    System.out.println(wordToDisplay);
                } else {
                    System.out.println("You have no lives left, better luck next time. The secret word is " + "\"" + secretWord + "\"");
                    break;
                }

            }
        }

    }
}

