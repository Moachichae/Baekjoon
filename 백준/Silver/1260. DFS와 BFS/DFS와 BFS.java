import java.io.*;
import java.util.*;

public class Main {

    static boolean[] isVisit = new boolean[1001];
    static List<List<Integer>> graph = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            graph.get(nodeA).add(nodeB);
            graph.get(nodeB).add(nodeA);
        }
        for (int i = 1; i <= N; i++){
            Collections.sort(graph.get(i));
        }
        System.out.println(dfs(V));
        isVisit = new boolean[1001];;
        System.out.println(bfs(V));

    }

    static StringBuilder dfs(int start) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        stack.push(start);
        while (!stack.isEmpty()){
            int visit = stack.pop();
            if (isVisit[visit]) continue;

            isVisit[visit] = true;
            sb.append(visit + " ");
            for (int i = graph.get(visit).size() -1; i >= 0; i--) {
                int node = graph.get(visit).get(i);
                if (isVisit[node]) continue;

                stack.push(node);
            }

        }
        return sb;
    }


    static StringBuilder bfs(int start) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> que = new LinkedList<>();
        que.offer(start);
        isVisit[start] = true;
        while (!que.isEmpty()){
            int visit = que.poll();
            sb.append(visit + " ");

            for(int i = 0; i < graph.get(visit).size(); i++) {
                int node = graph.get(visit).get(i);
                if (isVisit[node]) continue;

                isVisit[node] = true;
                que.offer(node);
            }

        }
        return sb;
    }

}