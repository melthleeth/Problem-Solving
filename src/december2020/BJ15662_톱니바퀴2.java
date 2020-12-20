package december2020;
import java.util.*;
import java.io.*;

// 2020.12.19

public class BJ15662_톱니바퀴2 {
	static final int LEFT = 0, RIGHT = 1;
	static int T, K;
	static int[][] cog;
	static int[][] idx;
	static int[][] order;
	
	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(getAnswer());
	}
	
	public static void print() {
		 for (int i = 1; i <= T; i++) {
			 for (int j = 0; j < 8; j++) 
				 System.out.print(cog[i][j] + " ");
			 System.out.println();
		 }
	}
	
	public static void printIdx() {
		System.out.println("L, R");
		for (int i = 1; i <= T; i++) {
			for (int j = 0; j < 2; j++) 
				System.out.print(idx[i][j] + " ");
			System.out.println();
		}
	}
	
	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//br = new BufferedReader(new StringReader(src));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		cog = new int[T + 1][10];
		idx = new int[T + 1][2];
		for (int i = 1; i <= T; i++) {
			String[] temp = br.readLine().split("");
			
			for (int j = 0; j < 8; j++)
				cog[i][j] = Integer.parseInt(temp[j]);
			
			
			idx[i][LEFT] = 6;
			idx[i][RIGHT] = 2;
		}
		
		K = Integer.parseInt(br.readLine());
		order = new int[K][2];
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			order[k][0] = Integer.parseInt(st.nextToken());
			order[k][1] = Integer.parseInt(st.nextToken());
		}
	}
	
	public static void solve() {
		for (int k = 0; k < K; k++) {
			Queue<Cog> q = new LinkedList<>();
			Queue<Cog> temp = new LinkedList<>();
			boolean[] visited = new boolean[T + 1];
			
			q.offer(new Cog(order[k][0], order[k][1]));
			temp.offer(new Cog(order[k][0], order[k][1]));
			while(!q.isEmpty()) {
				int num = q.peek().num;
				int d = q.peek().d;
				q.poll();
				
				if (num < 1 || num > T || visited[num]) continue;
				visited[num] = true;
				
				if (num != 1 && cog[num][idx[num][LEFT]] != cog[num - 1][idx[num - 1][RIGHT]]) {
					q.offer(new Cog(num - 1, -d));
					if (!visited[num - 1]) temp.offer(new Cog(num - 1, -d));
				}
				
				if (num != T && cog[num][idx[num][RIGHT]] != cog[num + 1][idx[num + 1][LEFT]]) {
					q.offer(new Cog(num + 1, -d));
					if (!visited[num + 1]) temp.offer(new Cog(num + 1, -d));
				}
			}
			
			while(!temp.isEmpty()) {
				int num = temp.peek().num;
				int d = temp.peek().d;
				temp.poll();
				//System.out.println("[num, d] = " + num + ", " + d);
				idx[num][LEFT] = (idx[num][LEFT] - d + 8) % 8;
				idx[num][RIGHT] = (idx[num][RIGHT] - d + 8) % 8;
			}
			
			//printIdx();
		}
		
	}
	
	public static int getAnswer() {
		int sum = 0;
		for (int i = 1; i <= T; i++) {
			int noon = (idx[i][RIGHT] + 6) % 8;
			if (cog[i][noon] == 1) sum ++;
		}
		
		return sum;
	}
	
	static class Cog {
		int num;
		int d;
		
		public Cog(int num, int d) {
			this.num = num;
			this.d = d;
		}

	}
	
	static String src = "4\r\n" + 
			"10010011\r\n" + 
			"01010011\r\n" + 
			"11100011\r\n" + 
			"01010101\r\n" + 
			"8\r\n" + 
			"1 1\r\n" + 
			"2 1\r\n" + 
			"3 1\r\n" + 
			"4 1\r\n" + 
			"1 -1\r\n" + 
			"2 -1\r\n" + 
			"3 -1\r\n" + 
			"4 -1";
}
