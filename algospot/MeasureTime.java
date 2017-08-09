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
    
    // input[0..pos]�� �κ����� ���Ѵ� 
    public long sum(int pos) {
        // index�� 1���� �����ϱ� ������ ����
        ++pos;
        long ret = 0;
        while(pos > 0) {
            ret += tree[pos];
            
            // ���� ������ ã�� ���� ���� ��Ʈ�� �����
            // ���� 7(111)������ ���� ���ϱ����ؼ��� 6(110) + 4(100) �� ���ؾ��Ѵ�
            pos &= (pos - 1);
        }
        return ret;
    }
    
    // input[pos]�� val�� ���Ѵ�
    // pos�� �����ϴ� �����鿡 ��� val�� ���Ѵ�
    // ���� �����ʿ� �ִ� 1�� ��Ʈ�� �����θ� ��� ���ϸ�  pos�� �����ϴ� ��� ������ ���� �� �ִ�.
    // ��, 6(110)���� ���� ���� �����ʿ� �ִ� 1�� ��Ʈ�� 10 �̰� 110 + 10 = 1000 �̴�
    public void add(int pos, int val) {
        ++pos;
        while(pos < tree.length) {
            tree[pos] += val;
            pos += (pos & -pos);
        }
    }
}

public class MeasureTime {

    public static int N;   // �迭�� ����
    public static int[] input; // �迭�� ����
    
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
