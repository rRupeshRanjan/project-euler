package com.project.euler;

import com.project.euler.utils.Utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Q21_30 {

    public static void main(String[] args) throws IOException {
        Q21_30 q21_30 = new Q21_30();

        System.out.println("Q21: " + problem21());
        System.out.println("Q22: " + q21_30.problem22());
        System.out.println("Q25: " + problem25());
        System.out.println("Q26: " + problem26());

    }

    private static int problem21() {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=1; i<10000; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for(int j=2; j<Math.sqrt(i); j++) {
                if(i%j == 0) {
                    list.add(j);
                    list.add(i/j);
                }
            }
            int sum1 = list.stream().mapToInt(num -> num).sum();
            if(sum1 < 10000 && sum1 != i)
                map.put(i, sum1);
        }

        for(int num: map.keySet()) {
            int b = map.getOrDefault(num, 0);
            if(map.containsKey(b) && map.get(b) == num)
                sum += num + b;
        }

        return sum/2;
    }

    private long problem22() throws IOException {
        InputStream resource = getClass().getClassLoader().getResourceAsStream("p022_names.txt");
        String[] input = readFromInputStream(resource).replace("\"","").split(",");
        Arrays.sort(input);

        long sum = 0;
        int i = 1;
        for(String str: input) {
            int currSum = 0;
            for(char ch: str.toCharArray())
                currSum += ch - 64;
            sum += (currSum * i);
            i++;
        }

        return sum;
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private static long problem25() {
        StringBuilder a = new StringBuilder("1"), b = new StringBuilder("1");

        long index = 2;
        while(b.length() < 1000) {
            index++;
            StringBuilder temp = b;
            b = Utility.sumDigits(a, b);
            a = temp;
        }

        return index;
    }

    private static int problem26() {
        int maxD = 0, prevLength = Integer.MIN_VALUE;
        for(int i=3; i<1000; i++) {
            int lengthOfRecurringCycle = Utility.lengthOfRecurringCycle(1, i);
            if(lengthOfRecurringCycle > prevLength) {
                maxD = i;
                prevLength = lengthOfRecurringCycle;
            }
        }
        return maxD;
    }



}
