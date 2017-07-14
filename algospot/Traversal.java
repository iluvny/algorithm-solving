import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Traversal {

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("sample_input.txt"));
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        
        for (int test = 0; test < TC; test++) {
            int N = Integer.parseInt(br.readLine());
            
            // initialize front, mid list
            ArrayList<Integer> front = new ArrayList<Integer>();
            ArrayList<Integer> mid = new ArrayList<Integer>();
            
            StringTokenizer frontST = new StringTokenizer(br.readLine());
            StringTokenizer middleST = new StringTokenizer(br.readLine());
            
            for (int i = 0; i < N; i++) {
                front.add(Integer.parseInt(frontST.nextToken()));
                mid.add(Integer.parseInt(middleST.nextToken()));
            }
            
            // 후위탐색 출력
            printPostOrder(front, mid);
            System.out.println();
        }
    }
    
    public static void printPostOrder(List<Integer> preorder, List<Integer> inorder) {
        int preSize = preorder.size();
        
        // preorder가 비었다면 종료
        if (preSize == 0) {
            return;
        }
        
        // 루트는 preorder의 맨 앞 숫자
        int root = preorder.get(0);
        
        // 중위탐색 결과에서 루트의 왼쪽 서브 트리의 사이즈를 구한다. (왼쪽 서브트리 사이즈 == 루트의 index) 
        int L = find(inorder, root);
        
        // 오른쪽 서브 트리 크기는 전체 사이즈 - 루트 index - 1 
//        int R = preSize - L - 1;
        
        // 1. 왼쪽 서브 트리 출력
        printPostOrder(preorder.subList(1, L + 1), inorder.subList(0, L));
        // 2. 오른쪽 서브 트리 출력
        printPostOrder(preorder.subList(L + 1, preSize), inorder.subList(L + 1, preSize));
        
        // 3. 루트 출력
        System.out.print(root + " ");
    }
    
    public static int find(List<Integer> list, int num) {
        int size = list.size();
        
        for (int i = 0; i < size; i++) {
            if (list.get(i) == num) {
                return i;
            }
        }
        return -1;
    }
}
