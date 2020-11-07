package com.example.demo.utils;

import java.util.Arrays;

/**
 * @Author: yanjie
 * @Description:
 * @Date: 2019/05/23 19:18
 */
public class PrimeUtil {
    /**
     * 获取小于max的所有素数
     * @param max
     * @return
     */
    public static int[] getPrimes(int max) {
        if (max <= 2) {
            return new int[]{};
        }
        int[] primes = new int[max];
        int count = 0;
        for (int i = 2; i < max; i++) {
            if (isPrime(i)) {
                primes[count++] = i;
            }

        }
        primes = Arrays.copyOf(primes, count);
        return primes;


    }

    private static boolean isPrime(int num) {
        for (int i = 2; i < num / 2 + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
