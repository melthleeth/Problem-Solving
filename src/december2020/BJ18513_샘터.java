package december2020;
import java.util.*;
import java.io.*;

// 2020.12.09

public class BJ18513_샘터 {
	static final int LOWER_BOUND = -100000000;
	static final int UPPER_BOUND = 100000000;
	static int N, K, offset;
	static long unhappiness;
	static Queue<Integer> q = new LinkedList<>();
	static Map<Integer, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new StringReader(src));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int spring = Integer.parseInt(st.nextToken());
			map.put(spring, -1);
			q.offer(spring);
		}
		
		outer:
		while(K > 0) {
			int size = q.size();
			offset += 1;
			for (int i = 0; i < size; i++) {
				int current = q.poll();
				if (current - offset == LOWER_BOUND || current + offset == UPPER_BOUND) continue;
				if (map.get(current - offset) != null && map.get(current + offset) != null) continue;
				if (map.get(current - offset) == null) {
					map.put(current - offset, offset);
					unhappiness += offset;
					K--;
				}
				if (K == 0) break outer;
				
				if (map.get(current + offset) == null) {
					map.put(current + offset, offset);
					unhappiness += offset;
					K--;
				}
				if (K == 0) break outer;
				
				q.offer(current);
			}
		}
		
		bw.write(String.valueOf(unhappiness));
		br.close();
		bw.close();
	}
	
	
	static String src = "2 5\r\n" + 
			"0 3";
}
