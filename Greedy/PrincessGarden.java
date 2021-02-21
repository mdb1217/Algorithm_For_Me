import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PrincessGarden {
	static class Flower implements Comparable<Flower> {
		int start_month;
		int start_day;
		int end_month;//끝나는 날에는 꽃을 볼 수 없음
		int end_day;
		
		public Flower(int a, int b, int c, int d) {
			start_month = a;
			start_day = b;
			end_month = c;
			end_day = d;
		}
		
		@Override
		public int compareTo(Flower target) {
			if(this.start_month > target.start_month)
				return 1;
			else if(this.start_month == target.start_month) {
				if(this.start_day > target.start_day)
					return 1;
				else if(this.start_day == target.start_day) {
					if(this.end_month > target.end_month)
						return -1;
					else if(this.end_month == target.end_month) {
						if(this.end_day > target.end_day)
							return -1;
						else if(this.end_day < target.end_day)
							return 1;
						else
							return 0;
					}
					else
						return 1;
				}
				else
					return -1;
			}
			else
				return -1;
	    }
		
	}
	
	public static void main(String[] args) throws IOException {
		// 그리디 알고리즘
		// 시작달 작은 순으로 정렬. 이 때 시작달이랑 날이 같으면 뒤에 끝달이 더 긴애가 앞에 오도록~
		// 우선순위 큐 사용
		PriorityQueue<Flower> queue = new PriorityQueue<>();
		int start_month;
		int start_day;
		int end_month;
		int end_day;
		int comp_start_month;
		int comp_start_day;
		int comp_end_month;
		int comp_end_day;
		int N;
		boolean success = false;
		int count = 0;//꽃 개수
		Flower f = null;
		StringTokenizer st;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			start_month = Integer.parseInt(st.nextToken());
			start_day = Integer.parseInt(st.nextToken());
			end_month = Integer.parseInt(st.nextToken());
			end_day = Integer.parseInt(st.nextToken());
			
			queue.add(new Flower(start_month, start_day, end_month, end_day));
		}
		
		start_month = 0;
		start_day = 0;
		end_month = 0;
		end_day = 0;
		while(!queue.isEmpty()) {//시작 꽃 찾기
			f = queue.poll();
			if(f.start_month == 3) {
				if(f.start_day > 1) {
					break;
				}
				else {
					if(f.end_month > end_month) {
						start_month = f.start_month;
						start_day = f.start_day;
						end_month = f.end_month;
						end_day = f.end_day;
					}
					else if(f.end_month == end_month) {
						if(f.end_day > end_day) {
							start_month = f.start_month;
							start_day = f.start_day;
							end_month = f.end_month;
							end_day = f.end_day;
						}
					}
				}
			}
			else if(f.start_month < 3) {
				if(f.end_month > end_month) {
					start_month = f.start_month;
					start_day = f.start_day;
					end_month = f.end_month;
					end_day = f.end_day;
				}
				else if(f.end_month == end_month) {
					if(f.end_day > end_day) {
						start_month = f.start_month;
						start_day = f.start_day;
						end_month = f.end_month;
						end_day = f.end_day;
					}
				}
			}
			else
				break;
		}
		++count;
		if(end_month > 11) 
			System.out.println(count);
		else if(end_month < 3) {
			System.out.println(0);
		}
		else {
			comp_start_month = start_month;
			comp_end_month = end_month;
			comp_start_day = start_day;
			comp_end_day = end_day;
			if(end_month == 3 && end_day == 1)
				System.out.println(0);
			else {
				while(true) {
					if(end_month < f.start_month) {
						start_month = comp_start_month;
						start_day = comp_start_day;
						end_month = comp_end_month;
						end_day = comp_end_day;
						++count;
						
						if(end_month > 11) {
							System.out.println(count);
							success = true;
							break;
						}
						else {
							if(end_month < f.start_month)
								break;
							else if(end_month == f.start_month) {
								if(f.start_day > end_day) {
									break;
								}
								else {
									comp_start_month = f.start_month;
									comp_start_day = f.start_day;
									comp_end_month = f.end_month;
									comp_end_day = f.end_day;
								}
							}
							else {
								comp_start_month = f.start_month;
								comp_start_day = f.start_day;
								comp_end_month = f.end_month;
								comp_end_day = f.end_day;
							}
						}
					}
					else if(f.start_month == end_month) {
						if(f.start_day > end_day) {
							start_month = comp_start_month;
							start_day = comp_start_day;
							end_month = comp_end_month;
							end_day = comp_end_day;
							
							++count;
							
							if(end_month > 11) {
								System.out.println(count);
								success = true;
								break;
							}
							else {
								if(end_month < f.start_month)
									break;
								else if(end_month == f.start_month) {
									if(f.start_day > end_day) {
										break;
									}
									else {
										comp_start_month = f.start_month;
										comp_start_day = f.start_day;
										comp_end_month = f.end_month;
										comp_end_day = f.end_day;
									}
								}
								else {
									comp_start_month = f.start_month;
									comp_start_day = f.start_day;
									comp_end_month = f.end_month;
									comp_end_day = f.end_day;
								}
							}
						}
						else {
							if(comp_end_month < f.end_month) {
								comp_start_month = f.start_month;
								comp_start_day = f.start_day;
								comp_end_month = f.end_month;
								comp_end_day = f.end_day;
							}
							else if(comp_end_month == f.end_month) {
								if(comp_end_day < f.end_day) {
									comp_start_month = f.start_month;
									comp_start_day = f.start_day;
									comp_end_month = f.end_month;
									comp_end_day = f.end_day;
								}
							}
						}
					}
					else {
						if(comp_end_month < f.end_month) {
							comp_start_month = f.start_month;
							comp_start_day = f.start_day;
							comp_end_month = f.end_month;
							comp_end_day = f.end_day;
						}
						else if(comp_end_month == f.end_month) {
							if(comp_end_day < f.end_day) {
								comp_start_month = f.start_month;
								comp_start_day = f.start_day;
								comp_end_month = f.end_month;
								comp_end_day = f.end_day;
							}
						}
					}
					if(!queue.isEmpty())
						f = queue.poll();
					else 
						break;
				}
				if(!success) {
					if(comp_end_month > 11) {
						++count;
						System.out.println(count);
					}
					else {
						System.out.println(0);
					}
				}
			}
		}
	}
}