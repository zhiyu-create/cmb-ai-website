package com.example.cmbaiwebsite.letCodeTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /**
     * 给定一个数组和一个目标和，从数组中找两个数字相加等于目标和，输出这两个数字的下标。
     * @param args
     */
    public static void main(String[] args) {
        int nums[] = {1,2,3,4};
        int[] targetIndex = getTargetIndex(nums, 5);
        System.out.println(targetIndex);
    }

    private static int[] getTargetIndex(int nums[], int target) {
        //初始化一个数据容器，用于返回
        int ans[] =new int[2];
        //拿到第一个数据与第二个数据相加等于target就获取下标放到ans中用于返回
        for (int i = 0; i<nums.length; i++) {
            for (int j = (i+1);j<nums.length;j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1]= j;
                }
            }
        }
        return ans;
    }

    //
    private static int[] getTargetIndex1(int nums[], int target) {
        Map<Integer, Integer> map = new HashMap<>();

        return null;
    }
}
