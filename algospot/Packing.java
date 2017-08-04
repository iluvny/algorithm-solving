package packing_01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
2
6 10
laptop 4 7
camera 2 10
xbox 6 6
grinder 4 7
dumbell 2 5
encyclopedia 10 4
6 17
laptop 4 7
camera 2 10
xbox 6 6
grinder 4 7
dumbell 2 5
encyclopedia 10 4
 */
public class Packing {

    public static int N;
    public static int W;
    public static String[] names;
    public static int[] weights;
    public static int[] values;
    public static int[][] table;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int testcase = 0; testcase < TC; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            weights = new int[N + 1];
            values = new int[N + 1];
            names = new String[N + 1];
            
            table = new int[N + 1][W + 1];

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());

                String name = st.nextToken();
                int weight = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());
                
                names[i] = name;
                weights[i] = weight;
                values[i] = value;
            }
            
            int max = knapsack(N, W);
            System.out.println(max);
        }


        br.close();
    }
    
    public static int knapsack(int index, int weight) {
        if (index < 1 || weight <= 0) {
            return 0;
        }
        
        if (table[index][weight] != 0) {
            return table[index][weight];
        }
        
        table[index][weight] = table[index - 1][weight];
        
        if (weight >= weights[index]) {
            table[index][weight] = 
                    Math.max(table[index - 1][weight], knapsack(index - 1, weight - weights[index]) + values[index]);
        } else {
            table[index][weight] = table[index - 1][weight];
        }
        
        return table[index][weight];
    }


    private static int knapsack(int n, int w, int[] wt, int[] val) {
        if (n <= 0 || w <= 0) {
            return 0;
        }

        if ((n > 1) && (table[n - 1][w] == 0)) {
            table[n - 1][w] = knapsack(n - 1, w, wt, val);
        }

        if ((n > 1 && w > wt[n]) && (table[n - 1][w - wt[n]] == 0)) {
            table[n - 1][w - wt[n]] = knapsack(n - 1, w - wt[n], wt, val);
        }

        if ((wt[n] <= w) && (table[n - 1][w] < table[n - 1][w - wt[n]] + val[n])) {
            table[n][w] = table[n - 1][w - wt[n]] + val[n];
        } else {
            table[n][w] = table[n - 1][w];
        }
        return table[n][w];
    }
}
