import java.util.Scanner;

/**
 * Data Fun
 * @version [11/12/2024]
 */

public class DataFun extends beaUtils {
    private static final Scanner scanner = new Scanner(System.in);
    // Color Library
    private static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BLUE = "\033[0;34m";
    public static final String PURPLE = "\033[0;35m";
    public static final String CYAN = "\033[0;36m";
    public static final String WHITE = "\033[0;37m";

    private static void partOne(int integer, String CHOSEN_COLOR) {
        String isPositiveOrNegative;
        String isGreaterThan;
        String isOddOrEven;
        String isNobleGas;

        isPositiveOrNegative = integer >= 0 ?  CHOSEN_COLOR + "positive" + RESET : CHOSEN_COLOR + "negative" + RESET;

        isOddOrEven = integer % 2 == 0 ? CHOSEN_COLOR + "even" + RESET : CHOSEN_COLOR + "odd" + RESET;
        if (integer <= 10) {
            isGreaterThan = "";
        }
        else if (integer <= 100) {
            isGreaterThan = ","+CHOSEN_COLOR + "\nit is greater then 10" + RESET;
        }
        else if (integer <= 1000) {
            isGreaterThan = ",\nit is " + CHOSEN_COLOR + "greater then 100" + RESET;
        }
        else {
            isGreaterThan = CHOSEN_COLOR + ",\nit is greater then 1000" + RESET;
        }

        isNobleGas = switch (integer) {
            case 2 -> "the noble gas" + CHOSEN_COLOR +  " Helium" + RESET;
            case 10 -> "the noble gas" + CHOSEN_COLOR + " Neon" + RESET;
            case 18 -> "the noble gas" + CHOSEN_COLOR + "Argon" + RESET;
            case 36 -> "the noble gas" + CHOSEN_COLOR + "Krypton" + RESET;
            case 54 -> "the noble gas" + CHOSEN_COLOR + "Xenon" + RESET;
            case 86 -> "the noble gas" + CHOSEN_COLOR + "Radon" + RESET;
            default -> CHOSEN_COLOR + "not" + RESET + " the atomic number of a noble gas";
        };

        System.out.println(integer + " is a "+ isPositiveOrNegative + " number"+isGreaterThan+",\nit is "+isOddOrEven+",\nand it is "+isNobleGas+"\n");
    }

    private static void partTwo(char character, String CHOSEN_COLOR) {
        final char characterLowercase = Character.toLowerCase(character);
        final int asciiValue;
        final boolean isCharacterLowercase = Character.isLowerCase(character);

        String characterStatus;
        String isVowel;
        String suffix;
        int alphabeticalPlacement;

        asciiValue = character;

        if (Character.isDigit(character)) {
            characterStatus = " is a " + CHOSEN_COLOR + "number" + RESET + ",\n";
        } else if (isCharacterLowercase) {
            characterStatus = " is a " + CHOSEN_COLOR + "lower case" + RESET + " letter,\n";
        }
        else {
            characterStatus = " is an " + CHOSEN_COLOR + "upper case" + RESET + " letter,\n";
        }

        if (isCharacterLowercase) {
            alphabeticalPlacement = asciiValue - 96;
        }
        else {
            alphabeticalPlacement = asciiValue - 64;
        }

        isVowel = characterLowercase == 'a' || characterLowercase == 'e' || characterLowercase == 'i' || characterLowercase == 'o' || characterLowercase == 'u' ? "it is a " + CHOSEN_COLOR + "vowel" + RESET + ",\n" : "it is a " + CHOSEN_COLOR + "consonant" + RESET + ",\n";

        if (alphabeticalPlacement == 11 || alphabeticalPlacement == 12 || alphabeticalPlacement == 13) {
            suffix = "th";
        }
        else {
            suffix = switch (alphabeticalPlacement % 10) {
                case 1 -> "st";
                case 2 -> "nd";
                case 3 -> "rd";
                default -> "th";
            };
        }

        System.out.println(character + characterStatus + isVowel + "its ASCII value is " + CHOSEN_COLOR + asciiValue + RESET + ",\nand it is the " + CHOSEN_COLOR + alphabeticalPlacement + suffix + RESET + " letter of the alphabet");

    }

    private static String pollForColor() {
        String color;
        String colorLowercase;

        color = askForThing("Enter a color to act as a theme (EX: Blue, red, GREEN):", Scanner::next, scanner);
        colorLowercase = color.toLowerCase();
        color = switch (colorLowercase) {
            case "red" -> RED;
            case "green" -> GREEN;
            case "yellow" -> YELLOW;
            case "blue" -> BLUE;
            case "purple" -> PURPLE;
            case "cyan" -> CYAN;
            case "white" -> WHITE;
            default -> {
                System.out.println("Color not recognized, defaulting to white.");
                yield WHITE;
            }
        };
        return color;
    }

    public static void main(String[] args) {
        String chosenColor = pollForColor();
        partOne(askForThing("Enter your favorite integer: ", Scanner::nextInt, scanner), chosenColor);
        partTwo(askForThing("Enter your favorite character", Scanner::next, scanner).charAt(0), chosenColor);
    }
}