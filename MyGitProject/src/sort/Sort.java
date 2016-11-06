package sort;

import java.util.Random;

public class Sort<T> {
	
	public static void Sort(int[] data, int type, int asc_dec) {
		if(type == Type.QUICK_SORT) {
			if(asc_dec == Type.ASC) {
				QuickSortAsc(data, 0, data.length - 1);
			}
		}
	}
	
	public void Sort(T[] data, int type) {
		if(type == Type.QUICK_SORT) {
			
		}
	}
	
	private static void QuickSortAsc(int[] data, int start, int end) {
		int pivot = end;
		int left = start;
		int right = end - 1;
		
		if(end - start <= 1) {
			return;
		}
		
		while(left < right) {
			while(left < end && data[left] <= data[pivot]) {
				left++;
			}
			while(start < right && data[pivot] <= data[right]) {
				right--;
			}
			if(left < right) {
				int temp = data[left];
				data[left] = data[right];
				data[right] = temp;
			}
		}
		
		int temp = data[left];
		data[left] = data[pivot];
		data[pivot] = temp;
		
		QuickSortAsc(data, start, left - 1);
		QuickSortAsc(data, left + 1, end);
	}
	
	private void QuickSortAsc(T[] data, int start, int end) {
		int pivot = end;
		
		int left = start;
		int right = end - 1;
		
		while(left < right) {
			
		}
	}
	
	public static void main(String[] args) {
		int[] data = new int[20];
		Random rand = new Random(System.currentTimeMillis());
		
		for(int i = 0; i < data.length; i++) {
			data[i] = rand.nextInt(20);
		}
		
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] +" ");
		}
		System.out.println();
		
		Sort.Sort(data, Type.QUICK_SORT, Type.ASC);
		
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i] +" ");
		}
		System.out.println();
		
		
	}
}