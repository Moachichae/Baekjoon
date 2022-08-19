import java.io.*;
import java.util.*;


class Node {
	private int x;
	private int y;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}

class Main {
	static int n, m;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static boolean[][] visit = new boolean[25][25];
	static int[][] arr;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] aparts = new int[25 * 25];
	static int apartNum = 0;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j) - '0';
				if (arr[i][j] == 1)
					visit[i][j] = true;
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visit[i][j]) {
					apartNum++;
					bfs(i, j);
				}
			}
		}

		Arrays.sort(aparts);
		System.out.println(apartNum);
		for (int i : aparts)
			if (i != 0)
				System.out.println(i);

		br.close();
	}

	static void bfs(int x, int y) {
		Queue<Node> q = new LinkedList<Node>();

		q.offer(new Node(x, y));
		visit[x][y] = false;

		while (!q.isEmpty()) {
			Node node = q.poll();
			aparts[apartNum]++;
			for (int i = 0; i < 4; i++) {
				int nx = node.getX() + dx[i];
				int ny = node.getY() + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n)
					continue;

				if (visit[nx][ny]) {
					q.offer(new Node(nx, ny));
					visit[nx][ny] = false;
				}
			}
		}

	}

}