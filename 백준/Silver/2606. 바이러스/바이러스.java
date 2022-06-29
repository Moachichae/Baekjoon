import java.io.*;
import java.util.*;

public class Main {

    static List<List<Integer>> graph = new ArrayList<>();
    static boolean[] visited = new boolean[101];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int node = Integer.parseInt(br.readLine());
        int edge = Integer.parseInt(br.readLine());

        for (int i = 0; i <= node; i++) {
            graph.add(new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);            
            graph.get(b).add(a);
        }
        br.close();

        dfs(1);
        System.out.println(answer);
    }

    static void dfs(int start) {
        visited[start] = true;

        for (int i = 0; i < graph.get(start).size(); i++) {
            int n = graph.get(start).get(i);
            if (!visited[n]) {
                answer++;
                dfs(n);
            }
        }
    }

}