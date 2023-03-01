import java.util.Scanner;

/*
 * This project checks user Entered password
 * and verifies the password either is invalid with
 * the reason why it's invalid or if the password is valid.
 *
 */
public class PasswordValidator {

    public static void main(String[] args) {
        displayMenu();

        char choice = getInput();

        while(choice != 'Q') {
            if(choice == 'D') {
                displayPasswordRequirement();
            }
            else if(choice == 'V') {
                verifyPassword();
            }
            else {
                System.out.println("Invalid choice. Please try another choice");
            }
            displayMenu();
            choice = getInput();
        }
        System.out.println("Quitting the programm!");
    }


    public static void verifyPassword() {
        String password = getPassword();

        if(checkLetters(password)) {
            if(checkDigits(password)) {
                if(checkSpecial(password)) {
                    System.out.println("Valid password");
                }
            }
        }
    }

    public static boolean checkLetters(String code) {
        if(checkLength(code)) {
            if(checkUpper(code)) {
                if(checkLower(code)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkLength(String code) {
        if(code.length() < 8 || code.length() > 12) {
            System.out.println("invalid - must be between 8 and 12 characters");
            return false;
        }
        return true;
    }

    public static boolean checkUpper(String code) {
        int count = 0;
        for(int i = 0; i < code.length(); i++) {
            char upper = code.charAt(i);
            if(Character.isUpperCase(upper)) {
                count++;
            }
        }
        if(count == 0) {
            System.out.println("invalid - must contain at least one upperrcase letter");
            return false;
        }
        return true;
    }

    public static boolean checkLower(String code) {
        int count = 0;
        for(int i = 0; i < code.length(); i++) {
            char lower = code.charAt(i);
            if(Character.isLowerCase(lower)) {
                count++;
            }
        }
        if(count < 1) {
            System.out.println("invalid - must contain at least one lowercase letter");
            return false;
        }

        return true;
    }

    public static boolean checkDigits(String code) {
        int count = countDigits(code);
        if(hasOne(count)) {
            if(consecutive(code)) {
                if(startsAndEndsWith(code)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static int countDigits(String code) {
        int countDigit = 0;
        for(int i = 0; i < code.length(); i++) {
            char digit = code.charAt(i);
            if(Character.isDigit(digit)) {
                countDigit++;
            }
        }
        return countDigit;
    }

    public static boolean hasOne(int count) {
        if(count == 0) {
            System.out.println("invalid - must contain at least one numerical digit");
            return false;
        }
        return true;
    }

    public static boolean consecutive(String code) {
        for(int i = 0; i < code.length(); i++) {
            char next = code.charAt(i);
            if(Character.isDigit(next)) {
                if(i < code.length() - 1) {
                    if(Character.isDigit(code.charAt(i + 1))) {
                        System.out.println("invalid - cannot contain consecutive numeric digits");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean startsAndEndsWith(String code) {
        if(Character.isDigit(code.charAt(0))) {
            System.out.println("invalid - cannot begin or end with numeric digit");
            return false;
        }
        if(Character.isDigit(code.charAt(code.length() - 1))) {
            System.out.println("invalid - cannot begin or end with numeric digit");
            return false;
        }

        return true;
    }

    public static boolean checkSpecial(String code) {
        int[] counts = checkSpecials(code);
        if(checkOne(counts)) {
            if(checkThree(counts)) {
                if(checkRepeat(counts)){
                    return true;
                }
            }
        }
        return false;
    }

    public static int[] checkSpecials(String code) {
        int count = 0;
        char[] specials = {'@','$','%','^','#'};
        int[] counts = new int[5];
        for(int i = 0; i < code.length(); i++) {
            for(int j = 0; j < specials.length; j++) {
                if(code.charAt(i) == specials[j]) {
                    counts[j]++;
                }
            }
        }
        return counts;
    }

    public static boolean checkOne(int[] counts) {
        for(int i = 0; i < counts.length; i++) {
            if(counts[i] > 0) {
                return true;
            }
        }
        System.out.println("invalid - contain at least one special character");
        return false;
    }

    public static boolean checkRepeat(int[] counts) {
        for(int i = 0; i < counts.length; i++) {
            if(counts[i] > 1) {
                System.out.println("invalid - cannot have repeated special characters");
                return false;
            }
        }
        return true;
    }

    public static boolean checkThree(int[] counts) {
        int countSpecials = 0;
        for(int i = 0; i < counts.length; i++) {
            countSpecials += counts[i];
        }
        if(countSpecials < 4) {
            return true;
        }
        System.out.println("invalid - cannot contain more than three special characters");
        return false;
    }

    public static char getInput() {
        Scanner console = new Scanner(System.in);
        String choice = console.next().toUpperCase();
        return choice.charAt(0);
    }

    public static String getPassword() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter your password: ");
        String password = console.next();
        return password;
    }

    public static void displayMenu() {
        System.out.println();
        System.out.println("Enter your choice:");
        System.out.println("\tD Display password Requirement");
        System.out.println("\tV Verify Password");
        System.out.println("\tQ Quit");
    }

    public static void displayPasswordRequirement() {
        System.out.println("A valid password will:");
        System.out.println("\tbe between 8 to 12 charcaters");
        System.out.println("\tcontain atleast one lowercase letter(a - Z)");
        System.out.println("\tontain atleast one lowercase letter(A - Z)");
        System.out.println("\tcontain atleast one numerical(0 - 9");
        System.out.println("\tnot begin or end with a numerical digit");
        System.out.println("\tnot contain two consecutive numerical digits");
        System.out.println("\twhen considering the 5 special Characters @,#.$,%,^");
        System.out.println("\t\tcontain at least one special character");
        System.out.println("\t\tnot contain more than 3 special charcaters");
        System.out.println("\t\tnot have any single special character repeated in the password");
    }

}