import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Airport {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// ±×¸®µð
		boolean success = false;
		int G, P;
		int g;
		int cnt = 0;
		int able_gate_list[];
		boolean visited_gate[];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		able_gate_list = new int[G + 1];
		visited_gate = new boolean[G + 1];
		
		for(int i = 1; i <= G; i++)
			able_gate_list[i] = i;
		
		for(int i = 0; i < P; ++i) {
			g = Integer.parseInt(br.readLine());
			
			if(!success) {
				if(able_gate_list[g] != 0) {
					while(true) {
						if(!visited_gate[able_gate_list[g]]) {
							visited_gate[able_gate_list[g]] = true;
							--able_gate_list[able_gate_list[g]];
							able_gate_list[g] = able_gate_list[able_gate_list[g]];
							++cnt;
							break;
						}
						else {
							able_gate_list[g] = able_gate_list[able_gate_list[g]];
							if(able_gate_list[g] == 0) {
								success = true;
								break;
							}
						}
					}	
				}
				else
					success = true;
			}
		}
		
		System.out.println(cnt);
	}

}
