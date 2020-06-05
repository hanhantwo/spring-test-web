package com.cn.strategy;

import java.util.Comparator;

/**
 * @ClassName Sorter
 * @Description 排序
 * @Author zhanghongjun
 * @Date 2020-06-05 20:54
 * @Version 1.0
 */
public class Sorter<T> {

    public void sort(T[] arr, Comparator<T> cc) {

        for (int i = 0; i < arr.length - 1; i++) {

            int minPos = i;
            for (int j =i+1; j < arr.length; j++) {
                minPos = cc.compare(arr[j],arr[minPos]) == -1 ? j : minPos;
            }
            swap(arr, i, minPos);
        }
    }

    void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
