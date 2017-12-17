package io.zipcoder.herion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

//        Below code based on my solution to code review question given by a potential employer during Zip Code Wilmington course
//        but decided to take my solution one step further with extra functionality in spare time
//        Was setting code up to read and execute external code input with below code but did not complete... for now code
//        is verified via unit tests in MainTest, below code is not fully functional

        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(line);

        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(input);

        ConvertMoney converter = new ConvertMoney();
        //sb unfortunately necessary because it is taken as argument for the moneyNumberToWords method... considered cleaning this up
        // overloading that method where one would take only input and pass it off to the the other with input and necessary sb
        //but did not want to risk running out of allotted time
        StringBuilder sb = new StringBuilder();
        converter.moneyNumberToWords(input, sb);

    }

}