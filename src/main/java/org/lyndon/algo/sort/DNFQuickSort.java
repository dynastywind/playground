package org.lyndon.algo.sort;

import org.lyndon.utils.PrintUtils;

import java.util.Random;

public class DNFQuickSort {

    public void sort(Integer[] arr) {
        Random rand = new Random();
        sort(rand, arr, 0, arr.length - 1);
    }

    private void sort(Random rand, Integer[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int lt = low, i = low, gt = high;
        int pivot = arr[low + rand.nextInt(high - low + 1)];
        while (i <= gt) {
            if (arr[i] < pivot) {
                swap(arr, i++, lt++);
            } else if (arr[i] > pivot) {
                swap(arr, i, gt--);
            } else {
                i++;
            }
        }
        sort(rand, arr, low, lt - 1);
        sort(rand, arr, gt + 1, high);
    }

    private void swap(Integer[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        var sort = new DNFQuickSort();
        Integer[] arr = new Integer[]{-4, 0, 7, 4, 9, -5, -1, 0, -7, -1};
        sort.sort(arr);
        PrintUtils.printArray(arr);
    }

}
