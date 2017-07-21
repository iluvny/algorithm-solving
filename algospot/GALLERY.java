/*
 * algospot
 * ���� ID: GALLERY
 * ��ũ: https://algospot.com/judge/problem/read/GALLERY
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class GALLERY {

    public static ArrayList<Integer>[] nodes;
    public static boolean[] visited;
    public static int count;
    
    // ����� �� ���� ����
    // ���� ���� UNWATCHED, ������ �θ���� INSTALLED
    public static int UNWATCHED = 0;
    public static int WATCHED = 1;
    public static int INSTALLED = 2;
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int TC  = Integer.parseInt(br.readLine());
        
        for (int testcase = 0; testcase < TC; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int G = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            
            nodes = new ArrayList[G];
            visited = new boolean[G];
            count = 0;
            
            for (int i = 0; i < G; i++) {
                nodes[i] = new ArrayList<Integer>();
            }
            
            // �����
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                int g1 = Integer.parseInt(st.nextToken());
                int g2 = Integer.parseInt(st.nextToken());
                
                nodes[g1].add(g2);
                nodes[g2].add(g1);
            }
            
            for (int i = 0; i < G; i++) {
                if (!visited[i]) {
                    if (dfs(i) == UNWATCHED) {
                        // �ƹ� ���� ����������� ���
                        count++;
                    }
                }
            }
            
            bw.write(count + "\n");
        }
        
        br.close();
        bw.close();
    }
    
    public static int dfs(int index) {
        visited[index] = true;
        
        ArrayList<Integer> node = nodes[index];
        int len = node.size();
        
        // �� ���� ���·� ���еǴ� �ڽ� ������ ����
        int[] childrenState = new int[3];
        
        for (int i = 0; i < len; i++) {
            int child = node.get(i);
            if (!visited[child]) {
                // dfs�� ������ ����. ���·� ���еǴ� �ڽ� ����� ������ ������Ų��
                childrenState[dfs(child)]++;
            }
        }
        
        // �ڽĳ��� �߿� UNWATCHED�� �ִٸ� �ڽ��� ���� INSTALLED
        // ī�޶� �߰������� count++
        if (childrenState[UNWATCHED] > 0) {
            count++;
            return INSTALLED;
        }
        
        // �ڽ� ���� �߿� INSTALLED�� �ִٸ� �ڽ��� ���� WATCHED
        // ���õǰ� ������ count�� ������ų �ʿ� ����
        if (childrenState[INSTALLED] > 0) {
            return WATCHED;
        }
        
        // ���� ���� ī�޶� ��ġ�������� 
        return UNWATCHED;
    }
}
