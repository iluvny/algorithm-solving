package measuretime_01;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class FenwickTree {
    private int[] tree;
    
    public FenwickTree(int n) {
        tree = new int[n + 1];
    }
    
    // input[0..pos]의 부분합을 구한다 
    public long sum(int pos) {
        // index는 1부터 시작하기 때문에 증가
        ++pos;
        long ret = 0;
        while(pos > 0) {
            ret += tree[pos];
            
            // 다음 구간을 찾기 위해 최종 비트를 지운다
            // 만약 7(111)까지의 합을 구하기위해서는 6(110) + 4(100) 을 구해야한다
            pos &= (pos - 1);
        }
        return ret;
    }
    
    // input[pos]에 val을 더한다
    // pos를 포함하는 구간들에 모두 val을 더한다
    // 제일 오른쪽에 있는 1인 비트에 스스로를 계속 더하면  pos를 포함하는 모든 구간을 만날 수 있다.
    // 즉, 6(110)에서 가장 제일 오른쪽에 있는 1인 비트는 10 이고 110 + 10 = 1000 이다
    public void add(int pos, int val) {
        ++pos;
        while(pos < tree.length) {
            tree[pos] += val;
            pos += (pos & -pos);
        }
    }
}

public class MeasureTime {

    public static int N;   // 배열의 길이
    public static int[] input; // 배열의 원소
    
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int TC = Integer.parseInt(br.readLine());
        
        for (int testcase = 0; testcase < TC; testcase++) {
            N = Integer.parseInt(br.readLine());
            input = new int[N];
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }
            
            System.out.println(solve());
        }
        br.close();
    }
    
    private static long solve() {
        FenwickTree tree = new FenwickTree(1000000);
        long ret = 0;
        
        for (int i = 0; i < N; i++) {
            ret += tree.sum(999999) - tree.sum(input[i]);
            tree.add(input[i], 1);
        }
        return ret;
    }

}
