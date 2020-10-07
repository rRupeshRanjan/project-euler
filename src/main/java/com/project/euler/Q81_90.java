package com.project.euler;

import com.project.euler.utils.Utility;

import java.io.IOException;
import java.util.*;

public class Q81_90 {
    public static void main(String[] args) throws IOException {
        Q81_90 q81_90 = new Q81_90();

        System.out.println("Question 81: " + q81_90.problem81());
        System.out.println("Question 82: " + q81_90.problem82());
        System.out.println("Question 83: " + q81_90.problem83());
        System.out.println("Question 85: " + problem85());
        System.out.println("Question 87: " + problem87());
    }

    private long problem81() throws IOException {
        int size = 80;
        int[][] nums = getMatrixFromFile("p081_matrix.txt", size);

        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(i==0 && j==0) {
                    continue;
                } else if(i == 0) {
                    nums[i][j] += nums[i][j - 1];
                } else if(j == 0) {
                    nums[i][j] += nums[i - 1][j];
                } else {
                    nums[i][j] += Math.min(nums[i-1][j], nums[i][j-1]);
                }
            }
        }

        return nums[size-1][size-1];
    }

    private long problem82() throws IOException {
        int size = 80;
        int[][] nums = getMatrixFromFile("p082_matrix.txt", size);
        int[] dp = new int[size];

        for (int i = 0; i < size; i++)
            dp[i] = nums[i][size - 1];

        for (int i = size - 2; i >= 0; i--) {
            // Traverse down
            dp[0] += nums[0][i];
            for (int j = 1; j < size; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + nums[j][i];
            }

            //Traverse up
            for (int j = size - 2; j >= 0; j--) {
                dp[j] = Math.min(dp[j], dp[j+1] + nums[j][i]);
            }
        }

        Arrays.sort(dp);
        return dp[0];
    }

    private long problem83() throws IOException {
        int size = 80;
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        int[][] nums = getMatrixFromFile("p083_matrix.txt", size);
        int[][] dist = new int[size][size];

        for(int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = nums[0][0];

        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int row1 = Integer.parseInt(a.split("-")[0]);
            int col1 = Integer.parseInt(a.split("-")[1]);
            int row2 = Integer.parseInt(b.split("-")[0]);
            int col2 = Integer.parseInt(b.split("-")[1]);

            return dist[row1][col1] - dist[row2][col2];
        });

        pq.add("0-0");
        while (!pq.isEmpty()) {
            String poll = pq.poll();
            int row = Integer.parseInt(poll.split("-")[0]);
            int col = Integer.parseInt(poll.split("-")[1]);

            for(int i=0; i<4; i++) {
                int nextRow = row + dx[i];
                int nextCol = col + dy[i];

                if(nextRow>=0 && nextRow<size && nextCol>=0 && nextCol<size) {
                    if (dist[nextRow][nextCol] > dist[row][col] + nums[nextRow][nextCol] ) {
                        if(dist[nextRow][nextCol] != Integer.MAX_VALUE)
                            pq.remove(nextRow + "-" + nextCol);

                        dist[nextRow][nextCol] = dist[row][col] + nums[nextRow][nextCol];
                        pq.add(nextRow + "-" + nextCol);
                    }
                }
            }
        }

        return dist[size-1][size-1];
    }

    private int[][] getMatrixFromFile(String filename, int size) throws IOException {
        String[] string = new Utility()
                .readFromInputStream(filename)
                .split("\n");

        int[][] nums = new int[size][size];

        for(int i=0; i<size; i++) {
            String[] temp = string[i].split(",");
            for(int j=0; j<size; j++) {
                nums[i][j] = Integer.parseInt(temp[j]);
            }
        }

        return nums;
    }

    private static int problem85() {
        int error = Integer.MAX_VALUE, target = 2000000;
        int x = 2000, y = 1, nearestArea = 0;

        while (x >= y) {
            int count = x * (x+1) * y * (y+1) /4;

            if(error > Math.abs(count - target)) {
                nearestArea = x*y;
                error = Math.abs(count - target);
            }

            if(count > target) x--;
            else y++;
        }

        return nearestArea;
    }

    private static int problem87() {
        long limit = 50000000;
        HashSet<Double> result = new HashSet<>();
        boolean[] primeBooleans = Utility.getPrimes(7071);
        List<Integer> primes = new ArrayList<>();
        for(int i=0; i<primeBooleans.length; i++) {
            if(primeBooleans[i])
                primes.add(i);
        }

        for (int i=0; i<primes.size(); i++) {
            for (int j=0; j<73; j++) {
                for (int k=0; k<23; k++) {
                    double sum = Math.pow(primes.get(i), 2) +
                            Math.pow(primes.get(j), 3) +
                            Math.pow(primes.get(k), 4);

                    if (sum < limit) result.add(sum);
                    else break;
                }
            }
        }

        return result.size();
    }
}
