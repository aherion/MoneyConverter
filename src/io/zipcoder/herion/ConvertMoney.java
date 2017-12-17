package io.zipcoder.herion;

class ConvertMoney {


    String moneyNumberToWords(String input, StringBuilder sb) {

        //below code for use if input is double not string
        //String rawNumber = String.format("%.2f", input);
        //String splitInput[] = rawNumber.split("([.])");

        //receive input and split by . to handle dollars and cents separately below
        String splitInput[] = input.split("([.])");
        String leftOfDecimal = splitInput[0];
        String rightOfDecimal = splitInput[1];

        //loop through the dollars value on left of decimal
        int position = leftOfDecimal.length();
        for (int i = 0; i < leftOfDecimal.length(); i++) {

            //Ascertain current number to keep track of location in string and decrement down with each loop (decrement below switch)
            String currentNumber = leftOfDecimal.substring(i, i + 1);

            //position represents which number place we are on in the given number (e.g. ones, tens, hundreds)
            //series of switch/cases to handle the logic for these three cases... the way any number is phrased in spoken English
            //boils down to how you phrase ones, tens, and hundreds, with some variation (below methods handle this logic)
            switch (position) {

                case 1:
                case 4:
                case 7:
                case 10:
                    convertOnesPlace(leftOfDecimal, currentNumber, i, position, sb);
                    break;
                case 2:
                case 5:
                case 8:
                case 11:
                    convertTensPlace(leftOfDecimal, currentNumber, i, sb);
                    break;
                case 3:
                case 6:
                case 9:
                case 12:
                    convertHundredsPlace(leftOfDecimal, currentNumber, sb);
                    break;
                default:
                    //Would have liked to have thrown a custom exception here but ran out of time allotted
                    System.out.println("Invalid number format");
                    break;
            }

            //decrement position in loop to continue to next number place
            position--;
        }

        //The following loop will apply similar logic as above but for the cents on right of decimal... but before we do that we want
        //to add "DollarsAnd" before stating the cents value
        sb.append("DollarsAnd");

        for (int i = 0; i < rightOfDecimal.length(); i++) {

            String currentNumber = rightOfDecimal.substring(i, i + 1);
            switch (i) {

                case 0:
                    convertTensPlace(rightOfDecimal, currentNumber, i, sb);
                    break;
                case 1:
                    convertOnesPlace(rightOfDecimal, currentNumber, i, 0, sb);
                    break;
            }

        }
        //Add Cents on to end of phrase except when there is one cent (grammar is key!)
        sb.append("Cents");
        String returnValue = sb.toString();
        if (rightOfDecimal.substring(0, 2).equals("01")) {
            returnValue = returnValue.replace("Cents", "Cent");
        }
        return returnValue;
    }

    private static String convertOneThruNine(String number) {

        String returnValue = "";

        //In retrospect, really should have used enums for pretty much all of the following methods/logic for more concise code but the switch/cases
        //do work
        //The need to tack on a one thru nine when speaking a number between 1 and 1,000,000 will come up in numerous cases
        //so the below logic was put into this method to be called in numerous other methods to avoid repeating code
        switch (number) {

            case "1":
                returnValue = "One";
                break;
            case "2":
                returnValue = "Two";
                break;
            case "3":
                returnValue = "Three";
                break;
            case "4":
                returnValue = "Four";
                break;
            case "5":
                returnValue = "Five";
                break;
            case "6":
                returnValue = "Six";
                break;
            case "7":
                returnValue = "Seven";
                break;
            case "8":
                returnValue = "Eight";
                break;
            case "9":
                returnValue = "Nine";
                break;
            default:
                break;

        }

        return returnValue;
    }


    private static void convertOnesPlace(String entireNumber, String currentNumber, int index, int currentPosition, StringBuilder sb) {

        //if the current number is not 0 and the previous number of string is not a 1 (in other words if the number we're looking at
        //is NOT 10 thru 19 then we apply the rule for one thru nine above
        if (!currentNumber.equals("0")) {
            String previousNumber = entireNumber.substring(index - 1, index);
            if (!previousNumber.equals("1")) {
                sb.append(convertOneThruNine(currentNumber));
            }
        }

        //The following positions in the number string represent the natural point where you would say "thousand" or "million" when
        //speaking the number so we append them to the building string at this point
        if (currentPosition == 4) {
            sb.append("Thousand");
        } else if (currentPosition == 7) {
            sb.append("Million");
        } else if (currentPosition == 10) {
            sb.append("Billion");
        }
    }

    private static void convertTensPlace(String entireNumber, String currentNumber, int index, StringBuilder sb) {

        //if current number does not equal 0 and the next number of the string DOES equal 1 then we know it is 10 thru 19 so we append it
        if (!currentNumber.equals("0")) {
            if (currentNumber.equals("1")) {
                String nextNumber = entireNumber.substring(index + 1, index + 2);
                currentNumber += nextNumber;
                switch (currentNumber) {

                    case "10":
                        sb.append("Ten");
                        break;
                    case "11":
                        sb.append("Eleven");
                        break;
                    case "12":
                        sb.append("Twelve");
                        break;
                    case "13":
                        sb.append("Thirteen");
                        break;
                    case "14":
                        sb.append("Fourteen");
                        break;
                    case "15":
                        sb.append("Fifteen");
                        break;
                    case "16":
                        sb.append("Sixteen");
                        break;
                    case "17":
                        sb.append("Seventeen");
                        break;
                    case "18":
                        sb.append("Eighteen");
                        break;
                    case "19":
                        sb.append("Nineteen");
                        break;
                    default:
                        break;

                }

                //otherwise we know that it is a 20-99 since we are in the tens place (the aforementioned ones logic handles tacking on the
                // ones to the below tens values too
            } else {

                switch (currentNumber) {

                    case "2":
                        sb.append("Twenty");
                        break;
                    case "3":
                        sb.append("Thirty");
                        break;
                    case "4":
                        sb.append("Forty");
                        break;
                    case "5":
                        sb.append("Fifty");
                        break;
                    case "6":
                        sb.append("Sixty");
                        break;
                    case "7":
                        sb.append("Seventy");
                        break;
                    case "8":
                        sb.append("Eighty");
                        break;
                    case "9":
                        sb.append("Ninety");
                        break;
                    default:
                        break;


                }
            }
        }
    }

    private static void convertHundredsPlace(String entireNumber, String currentNumber, StringBuilder sb) {

        //using the aforementioned ones place logic with "HundredAnd" appended we can cover all cases of hundreds in the number phrase
        if (!currentNumber.equals("0")) {
            sb.append(convertOneThruNine(currentNumber));

        }

        sb.append("HundredAnd");
    }
}
