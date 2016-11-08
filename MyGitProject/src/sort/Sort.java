package sort;

import java.util.Calendar;
import java.util.Random;

public class Sort {
	
	public static Random rand = new Random(System.currentTimeMillis());
	
	public static void SortAlgor(int[] data, int sortType, int type) {
		if(sortType == Type.QUICK_SORT) {
			QuickSort(data, 0, data.length - 1, type);			
		} else if(sortType == Type.INSERT_SORT) {
			InsertSort(data, 0, data.length - 1, type);
		}
	}
	
	public static void ThreadQuickSort(int[] data, int type) {
		ThreadQuickSort tqs = new ThreadQuickSort(data, 0, data.length - 1, type, 0);
		tqs.start();
		try {
			tqs.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void InsertSort(int[] data, int start, int end, int type) {
		for(int i = start + 1; i <= end; i++) {
			int key = data[i];
			int j = i - 1;
			if(type == Type.ASC) {
				while(i >= start && data[j]  > key) {
					data[i + 1] = i;
					i--;
				}
			} else if (type == Type.DEC) {
				while(i >= start && data[j]  < key) {
					data[i + 1] = i;
					i--;
				}
			}
			data[i + 1] = key;
		}
	}

	public static void QuickSort(int[] data, int start, int end, int type) {
		
		if(end - start < 200) {
			Sort.InsertSort(data, start, end, type);
			return;
		}
		
		int pivot = rand.nextInt(end - start) + start;
		
		int temp = data[end];
		data[end] = data[pivot];
		data[pivot] = temp;
		
		
		pivot = end;
		int left = start;
		int right = end - 1;
		
		if(type == Type.ASC) {
			while(true) {
				while(left < end && data[left] <= data[pivot]) {
					left++;
				}
				while(start <= right && data[pivot] < data[right]) {
					right--;
				}
				if(left <= right) {
					temp = data[left];
					data[left] = data[right];
					data[right] = temp;
				} else {
					temp = data[left];
					data[left] = data[pivot];
					data[pivot] = temp;
					break;
				}
			}
		} else if(type == Type.DEC) {
			while(true) {
				while(left < end && data[left] > data[pivot]) {
					left++;
				}
				while(start <= right && data[pivot] >= data[right]) {
					right--;
				}
				if(left <= right) {
					temp = data[left];
					data[left] = data[right];
					data[right] = temp;
				} else {
					temp = data[left];
					data[left] = data[pivot];
					data[pivot] = temp;
					break;
				}
			}
		}
		
		
		if(start < left - 1) {
			QuickSort(data, start, left - 1, type);
		}
		if(left + 1 < end) {
			QuickSort(data, left + 1, end, type);
		}
	}
	
	public static void main(String[] args) {
		int[] data = new int[1000000];

		for(int i = 0; i < data.length; i++) {
			data[i] = rand.nextInt(20);
		}
		Calendar startTime = Calendar.getInstance();
		System.out.println(startTime.getTime());
		
		//Sort.SortAlgor(data, Type.QUICK_SORT, Type.ASC);
		Sort.ThreadQuickSort(data, Type.ASC);
		Calendar endTime = Calendar.getInstance();
		System.out.println(endTime.getTime());
		long dif = endTime.getTimeInMillis() - startTime.getTimeInMillis();
		dif /= 1000;
		long sec = dif % 60;
		long min = (dif / 60) % 60;
		long hour = (dif / 60) / 60;
		System.out.println(hour+":"+min+":"+sec);
		
		//Sort.SortAlgor(data, Type.QUICK_SORT, Type.DEC);
		//Sort.ThreadQuickSort(data, Type.DEC);
		
		System.out.println();
	}
}
class ThreadQuickSort extends Thread {
	int[] data;
	int start;
	int end;
	int type;
	int depth;
	public ThreadQuickSort(int[] data, int start, int end, int type, int depth) {
		this.data = data;
		this.start = start;
		this.end = end;
		this.type = type;
		this.depth = depth;
	}
	public void run() {
		if(end - start < 200) {
			Sort.InsertSort(data, start, end, type);
			return;
		}
		
		int pivot = Sort.rand.nextInt(end - start) + start;
		
		int temp = data[end];
		data[end] = data[pivot];
		data[pivot] = temp;
		
		pivot = end;
		int left = start;
		int right = end - 1;
		
		if(type == Type.ASC) {
			while(true) {
				while(left < end && data[left] <= data[pivot]) {
					left++;
				}
				while(start <= right && data[pivot] < data[right]) {
					right--;
				}
				if(left <= right) {
					temp = data[left];
					data[left] = data[right];
					data[right] = temp;
				} else {
					temp = data[left];
					data[left] = data[pivot];
					data[pivot] = temp;
					break;
				}
			}
			
		} else if (type == Type.DEC) {
			while(true) {
				while(left < end && data[left] > data[pivot]) {
					left++;
				}
				while(start <= right && data[pivot] >= data[right]) {
					right--;
				}
				if(left <= right) {
					temp = data[left];
					data[left] = data[right];
					data[right] = temp;
				} else {
					temp = data[left];
					data[left] = data[pivot];
					data[pivot] = temp;
					break;
				}
			}
		} else {
			System.err.println("Option Key is wrong!!");
			return;
		}
		
		if (depth < 9) {
			ThreadQuickSort tqs1 = new ThreadQuickSort(data, start, left-1, this.type, this.depth + 1);
			ThreadQuickSort tqs2 = new ThreadQuickSort(data, left + 1, end, this.type, this. depth + 1);
		
			if(start < left - 1) {
				tqs1.start();
			}
			if(left + 1 < end) {
				tqs2.start();
			}
		
			try {
				tqs1.join();
				tqs2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			Sort.QuickSort(data, start, left - 1, type);
			Sort.QuickSort(data, left + 1, end, type);
		}
	}
}