import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge {
    public int to;
    public double noise;
    
    public Edge(int to, double noise) {
        this.to = to;
        this.noise = noise;
    }
}

class Node implements Comparable<Node> {
    public int index;
    public double noise;
    
    public Node(int index, double noise) {
        this.index = index;
        this.noise = noise;
    }
    
    @Override
    public int compareTo(Node o) {
        return Double.compare(this.noise, o.noise);
    }
}

public class Routing {
    static ArrayList<Edge> edges[];
    static double result[];
    static PriorityQueue<Node> pq = new PriorityQueue<Node>();

    public static void main(String[] args) throws Exception {
//        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream("sample_input.txt"));
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            // initialize map
            edges = new ArrayList[V];
            for (int j = 0; j < V; j++) {
                edges[j] = new ArrayList<Edge>();
            }
            
            for (int j = 0; j < E; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                double noise = Double.parseDouble(st.nextToken());
                
                edges[from].add(new Edge(to, noise));
                edges[to].add(new Edge(from, noise));
            }
            
            
            // PriorityQueue ÃÊ±âÈ­
            pq.clear();
            pq.add(new Node(0, 1.0));
            result = new double[V];
            
            while(!pq.isEmpty()) {
                Node current = pq.poll();
                
                for (Edge edge : edges[current.index]) {
                    if (edge.to == 0) {
                        continue;
                    }
                    if (result[edge.to] == 0) {
                        result[edge.to] = current.noise * edge.noise;
                        pq.add(new Node(edge.to, result[edge.to]));
                    } else {
                        double prevVal = result[edge.to];
                        if (prevVal > current.noise * edge.noise) {
                            result[edge.to] = current.noise * edge.noise;
                            pq.add(new Node(edge.to, result[edge.to]));
                        }
                    }
                }
            }
            System.out.printf("%.10f\n", result[V - 1]);
        }
        
        br.close();
        //System.out.println(System.currentTimeMillis() - start + "ms");
    }

}
