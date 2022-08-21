import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int m;
        int n;
        int h;

        int day;

        public Node(int m, int n, int h,int day) {
            this.m = m;
            this.n = n;
            this.h = h;
            this.day = day;
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
    static int answer = 0;

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
                    if (!isVisit[m][n][h] && graph[m][n][h] == 1){
                        isVisit[m][n][h] = true;
                        que.offer(new Node(m, n, h,0));
                    }
                }
            }
        }

        bfs();

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (graph[m][n][h] == 0){
                        answer = -1;
                    }
                }
            }
        }

        System.out.println(answer);

    }


    static void bfs(){

        while (!que.isEmpty()){

            Node node = que.poll();
            graph[node.m][node.n][node.h] = 1;
            answer = Math.max(answer, node.day);
            for (int i = 0; i < 6; i++) {
                int nm = node.m + dm[i];
                int nn = node.n + dn[i];
                int nh = node.h + dh[i];

                if (nm < 0 || nm >= M || nn < 0 || nn >= N || nh < 0 || nh >= H) continue;

                if (!isVisit[nm][nn][nh] && graph[nm][nn][nh] == 0){
                    isVisit[nm][nn][nh] = true;
                    que.offer(new Node(nm, nn, nh, node.day + 1));
                }

            }
        }

    }



}