import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph(8);
        LinkedList<Integer> a = new LinkedList<>();
        a.addAll(Arrays.asList(1,3));
        LinkedList<Integer> b = new LinkedList<>();
        b.addAll(Arrays.asList(0,2,4));
        LinkedList<Integer> c = new LinkedList<>();
        c.addAll(Arrays.asList(1,5));
        LinkedList<Integer> d = new LinkedList<>();
        d.addAll(Arrays.asList(0,4));
        LinkedList<Integer> e = new LinkedList<>();
        e.addAll(Arrays.asList(1,3,5,6));
        LinkedList<Integer> f = new LinkedList<>();
        f.addAll(Arrays.asList(2,4,7));
        LinkedList<Integer> g = new LinkedList<>();
        g.addAll(Arrays.asList(4,7));
        LinkedList<Integer> h = new LinkedList<>();
        h.addAll(Arrays.asList(5,6));

        LinkedList<Integer> adj[] = new LinkedList[8];
        adj[0] = a;
        adj[1] = b;
        adj[2] = c;
        adj[3] = d;
        adj[4] = e;
        adj[5] = f;
        adj[6] = g;
        adj[7] = h;
        graph.setAdj(adj);

        boolean[] visited = new boolean[graph.getV()];
        Queue<Integer> queue = new LinkedList<>();

        graph.bfs(queue, visited, 0, 7);
        graph.print(0, 7);
    }
}

class Graph { // 无向图
    private int v; // 顶点的个数
    private LinkedList<Queue<Integer>> answers;
    private int shortest;
    private LinkedList<Integer> adj[]; // 邻接表

    public LinkedList<Integer>[] getAdj() {
        return adj;
    }

    public void setAdj(LinkedList<Integer>[] adj) {
        this.adj = adj;
    }

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }

        this.answers = new LinkedList<>();
        this.shortest = 9999;
    }

    public int getV() {
        return this.v;
    }

    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }

    public void bfs(Queue<Integer> queue, boolean[] visited, int s, int t) {

        if (s == t) {
            return;
        }

        visited[s] = true;
        queue.add(s);

        for (int i = 0; i < adj[s].size(); ++i) {
            int q = adj[s].get(i);
            if (!visited[q]) {
                //prev[q] = s;
                if (q == t) {
                    queue.add(t);
                    if (queue.size() <= shortest ){
                        if (queue.size() < shortest){
                            shortest = queue.size();
                            answers.clear();
                            answers.add(queue);
                        } else if (queue.size() == shortest){
                            shortest = queue.size();
                            answers.add(queue);
                        }
                    }
                    return;
                }

                Queue<Integer> newQueue = new LinkedList<>(queue);
                boolean[] newVisited = new boolean[v];
                newVisited = (boolean[]) visited.clone();

                bfs(newQueue, newVisited, q ,t);
            }
        }
    }

    public void print(int s, int t) { // 递归打印 s->t 的路径
        for (Queue<Integer> q : answers) {
            for (int e : q) {
                System.out.print(e + "\t");
                // do something with e
            }
            System.out.print("\n");
        }
    }
}
