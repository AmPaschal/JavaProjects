import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConvertNumbers {



    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter a number to convert: ");

        boolean next = true;
        while (next){
            String number = scanner.nextLine();

            if (number.toUpperCase().equals("C")){
                next = false;
                continue;
            }

            try {
                Long.parseLong(number);
            } catch (NumberFormatException ex){

                System.out.println("Please, enter another number (Press C to cancel): ");
                continue;

            }

            if (number.length() > 15){
                System.out.println("Number is too large...  \nMaximum number of digits allowed is 15");
                System.out.println("Please, enter another number (Press C to cancel): ");

            }

            String word = getFullString(number);
            System.out.println(word);

            System.out.println("Please, enter another number (Press C to cancel): ");
        }



    }

    private static String getFullString(String number){
        List<String> listParts = getListOfNumbers(number);
        StringBuilder builder = new StringBuilder();

        for (int i = listParts.size() - 1; i >= 0 ; i--){
            String part = listParts.get(i);
            builder.append(getNumber(part)).append(" ").append(getDenotation().get(i));

            if (i > 0){
                builder.append(", ");
            }
        }

        return builder.toString();
    }

    private static List<String> getListOfNumbers(String number){
        int length = number.length();
        List<String> list = new ArrayList<>();
        StringBuilder part = new StringBuilder();

        for (int i = length - 1; i >= 0; i--){
            part.append(number.charAt(i));

            if (part.length() == 3 || i == 0){
                list.add(part.reverse().toString());
                part = new StringBuilder();
            }


        }

        return list;
    }

    private static String getNumber(String number){

        if (number.length() < 3){
            return getNumber("0" + number);
        } else {
            return getThreeDigitString(number);
        }
    }

    private static String getThreeDigitString(String number) {

        StringBuilder builder = new StringBuilder();
        boolean teen = false;

        for (int i = 0; i < 3; i++){
            char digitChar = number.charAt(i);
            int digit = Character.getNumericValue(digitChar);
            if (i == 1 && digit == 1){
                teen = true;
                continue;
            }
            if (digit != 0) {
                builder.append(getString(i, digit, teen));

                if (i == 0){
                    builder.append("and ");
                }
            }




        }

        return builder.toString();


    }

    private static String getString(int position, int digit, boolean teen) {
        HashMap<Integer, String> hashMap = getHashMapForConversion();
        HashMap<Integer, String> tensHashMap = getTensHashMapForConversion();
        HashMap<Integer, String> teenHashMap = getTeenHashMapForConversion();
        String string = "";

        switch (position){
            case 0:
                string = hashMap.get(digit) + " hundred ";
                break;

            case 1:
                string = tensHashMap.get(digit) + " ";
                break;

            case 2:
                if (teen){
                    string = teenHashMap.get(digit);
                } else {
                    string = hashMap.get(digit);
                }
                break;


        }

        return string;
    }

    private static HashMap<Integer, String> getHashMapForConversion() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "one");
        hashMap.put(2, "two");
        hashMap.put(3, "three");
        hashMap.put(4, "four");
        hashMap.put(5, "five");
        hashMap.put(6, "six");
        hashMap.put(7, "seven");
        hashMap.put(8, "eight");
        hashMap.put(9, "nine");
        hashMap.put(0, "");

        return hashMap;
    }

    private static HashMap<Integer, String> getTensHashMapForConversion() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(2, "twenty");
        hashMap.put(3, "thirty");
        hashMap.put(4, "forty");
        hashMap.put(5, "fifty");
        hashMap.put(6, "sixty");
        hashMap.put(7, "seventy");
        hashMap.put(8, "eighty");
        hashMap.put(9, "ninty");

        return hashMap;
    }

    private static HashMap<Integer, String> getTeenHashMapForConversion() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, "eleven");
        hashMap.put(2, "twelve");
        hashMap.put(3, "thirteen");
        hashMap.put(4, "fourteen");
        hashMap.put(5, "fifteen");
        hashMap.put(6, "sixteen");
        hashMap.put(7, "seventeen");
        hashMap.put(8, "eighteen");
        hashMap.put(9, "nineteen");

        return hashMap;
    }

    private static HashMap<Integer, String> getDenotation(){
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(0, "");
        hashMap.put(1, "thousand");
        hashMap.put(2, "million");
        hashMap.put(3, "billion");
        hashMap.put(4, "trillion");

        return hashMap;
    }
}
