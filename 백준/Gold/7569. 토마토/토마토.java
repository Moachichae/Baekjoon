import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int m;
        int n;
        int h;

        public Node(int m, int n, int h) {
            this.m = m;
            this.n = n;
            this.h = h;
        }
    }
    static boolean[][][] isVisit = new boolean[100][100][100];
    static int[][][] graph;
    static int M;
    static int N;
    static int H;
    static int[] dm = {1, 0, -1, 0, 0, 0};
    static int[] dn = {0, 1, 0, -1, 0, 0};
    static int[] dh = {0, 0, 0, 0, 1, -1};
    static Queue<Node> que = new LinkedList<>();
    static int unripe = 0; //덜 익은 토마토
    static int day = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        graph = new int[M][N][H];

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    graph[m][n][h] = Integer.parseInt(st.nextToken());
                    if (graph[m][n][h] == 1){
                        isVisit[m][n][h] = true;
                        que.offer(new Node(m, n, h));
                    }else if(graph[m][n][h] ==  0){
                        unripe ++;
                    }
                }
            }
        }

        bfs();


        System.out.println(unripe > 0 ? -1 : day);

    }


    static void bfs(){

        while (unripe > 0 && !que.isEmpty()){

            int count = que.size();
            for (int i = 0; i < count; i++) {
                Node node = que.poll();

                for (int k = 0; k < 6; k++) {
                    int nm = node.m + dm[k];
                    int nn = node.n + dn[k];
                    int nh = node.h + dh[k];

                    if (nm < 0 || nm >= M || nn < 0 || nn >= N || nh < 0 || nh >= H) continue;

                    if (!isVisit[nm][nn][nh] && graph[nm][nn][nh] == 0){
                        isVisit[nm][nn][nh] = true;
                        graph[node.m][node.n][node.h] = 1;
                        unripe --;
                        que.offer(new Node(nm, nn, nh));
                    }

                }
            }

            day++;
        }

    }

}