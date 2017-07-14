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
            
            // ����Ž�� ���
            printPostOrder(front, mid);
            System.out.println();
        }
    }
    
    public static void printPostOrder(List<Integer> preorder, List<Integer> inorder) {
        int preSize = preorder.size();
        
        // preorder�� ����ٸ� ����
        if (preSize == 0) {
            return;
        }
        
        // ��Ʈ�� preorder�� �� �� ����
        int root = preorder.get(0);
        
        // ����Ž�� ������� ��Ʈ�� ���� ���� Ʈ���� ����� ���Ѵ�. (���� ����Ʈ�� ������ == ��Ʈ�� index) 
        int L = find(inorder, root);
        
        // ������ ���� Ʈ�� ũ��� ��ü ������ - ��Ʈ index - 1 
//        int R = preSize - L - 1;
        
        // 1. ���� ���� Ʈ�� ���
        printPostOrder(preorder.subList(1, L + 1), inorder.subList(0, L));
        // 2. ������ ���� Ʈ�� ���
        printPostOrder(preorder.subList(L + 1, preSize), inorder.subList(L + 1, preSize));
        
        // 3. ��Ʈ ���
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
