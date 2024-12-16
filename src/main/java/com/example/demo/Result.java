package com.example.demo;

public class Result {
    public static int minNum(int samDaily, int kellyDaily, int difference) {
        if (kellyDaily <= samDaily) {
            return -1;
        }

        // Initialize days counter
        int days = 0;

        // Simulate each day until Kelly surpasses Sam
        while (difference >= 0) {
            days++;
            difference -= (kellyDaily - samDaily);
        }

        return days;

    }
}
