package TextToHexConverter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Converts a given string from the user into hex or ascii format.

public class HexConverter {

    private static Scanner inputScanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Ask user for input.
        String input = getString();
        System.out.println("Input string: " + input);

        // Ask user for conversion type.
        int conversionType = getConversionType();

        // Convert.
        String output = "";
        if(conversionType == 0){
            System.out.println("[ Convert to ASCII ]");
           output = convertToAlpha(input);
        } else {
            System.out.println("[ Convert to Hex ]");
            output = convertToHex(input);
        }

        // Print output.
        System.out.println("Output string: " + output); 

/*         // Tests.
        runTests(); */

    }

    // Run test conversions.
    public static void runTests(){

        // Tests //
        // No spaces.
        System.out.println(convertToAlpha("68656c6c6f"));
        // Multiple spaces.
        System.out.println(convertToAlpha("68  65 6c     6c 6f "));
        // Unnecessary leading or following characters.
        System.out.println(convertToAlpha("$68 %65 &6c 6ch #6f"));
        // Invalid digits.
        System.out.println(convertToAlpha("68 65 6j 6c 6f"));
        // Uppercase.
        System.out.println(convertToAlpha("68 65 6C 6c 6F"));
        // Symbols.
        System.out.println(convertToAlpha("68_ 65 .6c 6c 6f^"));
        // Space.
        System.out.println(convertToAlpha("68 65 6c 6c 20 6f"));
        // New line character.
        System.out.println(convertToAlpha("68 65 6c 6c 0a 6f"));

    }

    // Get input.
    public static String getString(){

        String str = "";

        do{

            System.out.println("Enter your string: ");
            str = inputScanner.nextLine();
            if (str.isEmpty()){
                System.out.println("Invalid input, please try again.");
            }

        } while (str.isEmpty());
 
        // Validate.
        return str;

    }
   
    // Get conversion type.
    public static int getConversionType(){

        int input = -1;
        System.out.println("What type of conversion?: Hex to ASCII (0) | ASCII to Hex (1):" );

        do{
        System.out.println("Enter your option: ");
        try{
         input = Integer.parseInt(inputScanner.nextLine());
        } catch(NumberFormatException e){
            // Do nothing.
        }
    
        if(input == 0 || input == 1){
            break;
        }

        System.out.println("Invalid option, please try again.");

        } while (true);
    
        return input;
       
    }

    // Convert characters to hex.
    private static String convertToHex(String input){

        // Hold new string.
        StringBuilder newString = new StringBuilder();
        // Convert string into its separate characters.
        char[] chars = input.toCharArray();
        // Read the characters.
        for (char c : chars){
            // Convert character to hex.
            String newC = Integer.toHexString(c);
            // Add character to new string.
            newString.append(newC).append(" ");
        }
        // Return converted string.
        return newString.toString();
    }

    // Convert hex to ascii string.
    private static String convertToAlpha(String input){

        // Hold new string.
        StringBuilder newString = new StringBuilder();
        
        // Remove spaces and trailing whitespace.
        input = input.strip().replaceAll(" ", "");

        // Validate the input format to find pairs of valid hex characters.
        Pattern hexFormat = Pattern.compile("[0-9,a-f]{2}", Pattern.CASE_INSENSITIVE);

        // Get matches and add to arraylist.
        ArrayList<String> matches = new ArrayList<>();
        Matcher matcher = hexFormat.matcher(input);
        while(matcher.find()){
            matches.add(matcher.group());
        }

        // If no matches found return the original string.
        if (matches.size() == 0){
            System.out.println("Are you sure you provided a hex value?");
            return input;
        }

        // Take hex values and convert to characters.
        for(String value: matches){
            // Convert the hex value into a decimal.
            int decimalValue = Integer.parseInt(value, 16);
            // Take the decimal value and return it as a character.
            char charValue = (char) decimalValue;
            // Add to string.
            newString.append(charValue);
        }
        
        // Return converted string.
        return newString.toString();

    }

}
