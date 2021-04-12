import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sudoku {
	
	static boolean success = false;
	static StringBuilder sb = new StringBuilder();
	static boolean SquareVisited[][] = new boolean[10][10];
	static boolean LinearLineVisited[][] = new boolean[10][10];
	static boolean VerticalLineVisited[][] = new boolean[10][10];
	static char Map[][] = new char [10][10];
	public static void main(String[] args) throws IOException {
		// 백트래킹
		char a;
		int square_num;
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 9; ++i) {
			s = br.readLine();
			
			square_num = (i / 3) * 3 + 1;
			for(int j = 1; j <= 9; ++j) {
				a = s.charAt(j - 1);
				if(a - '0' > 0) {
					LinearLineVisited[i + 1][a - '0'] = true;
					VerticalLineVisited[j][a - '0'] = true;
					SquareVisited[square_num][a - '0'] = true;
				}
				Map[i + 1][j] = a;
				
				if(j % 3 == 0)
					++square_num;
			}
		}
		
		tracking(0, 0, 0);
	}

	static void tracking(int start_square, int square, int d) {
		int x = d / 9 + 1;
		int y = d % 9 + 1;
		int current_square = square;
		int start = start_square;
		
		if(y % 3 == 1)
			++current_square;
		
		if(d == 81) {
			System.out.print(sb.toString());
			success = true;
		}
		else {
			if(Map[x][y] > '0') {
				sb.append(Map[x][y]);
				if(y == 9) {
					sb.append('\n');
					if(x % 3 == 0)
						start += 3;
					current_square = start;
				}
				
				tracking(start, current_square, d + 1);
				
				if(y == 9)
					sb.deleteCharAt(sb.length() - 1);
				sb.deleteCharAt(sb.length() - 1);
			}
			else {
				for(int i = 1; i <= 9; i++) {
					if(!LinearLineVisited[x][i] && !VerticalLineVisited[y][i] && !SquareVisited[current_square][i]) {
						LinearLineVisited[x][i] = true;
						VerticalLineVisited[y][i] = true;
						SquareVisited[current_square][i] = true;
						
						sb.append(i);
						if(y == 9) {
							sb.append('\n');
							if(x % 3 == 0)
								start = current_square;
							current_square = start;
						}
							
						tracking(start, current_square, d + 1);
						if(success)
							break;
						
						if(y == 9) {
							sb.deleteCharAt(sb.length() - 1);
							if(x % 3 == 0)
								start -= 3;
							else
								current_square += 3;
						}
						sb.deleteCharAt(sb.length() - 1);
						
						LinearLineVisited[x][i] = false;
						VerticalLineVisited[y][i] = false;
						SquareVisited[current_square][i] = false;
					}
				}
			}
		}
	}
}
