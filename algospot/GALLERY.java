/*
 * algospot
 * 문제 ID: GALLERY
 * 링크: https://algospot.com/judge/problem/read/GALLERY
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
    
    // 노드의 세 가지 상태
    // 리프 노드는 UNWATCHED, 리프의 부모노드는 INSTALLED
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
            
            // 양방향
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
                        // 아무 노드와 연결되지않은 경우
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
        
        // 세 가지 상태로 구분되는 자식 노드들의 갯수
        int[] childrenState = new int[3];
        
        for (int i = 0; i < len; i++) {
            int child = node.get(i);
            if (!visited[child]) {
                // dfs의 리턴이 상태. 상태로 구분되는 자식 노드의 갯수를 증가시킨다
                childrenState[dfs(child)]++;
            }
        }
        
        // 자식노드들 중에 UNWATCHED가 있다면 자신의 노드는 INSTALLED
        // 카메라를 추가했으니 count++
        if (childrenState[UNWATCHED] > 0) {
            count++;
            return INSTALLED;
        }
        
        // 자식 노드들 중에 INSTALLED가 있다면 자신의 노드는 WATCHED
        // 감시되고 있으니 count를 증가시킬 필요 없음
        if (childrenState[INSTALLED] > 0) {
            return WATCHED;
        }
        
        // 리프 노드는 카메라를 설치하지않음 
        return UNWATCHED;
    }
}
