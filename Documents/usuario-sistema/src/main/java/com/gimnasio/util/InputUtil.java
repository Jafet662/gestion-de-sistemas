package com.gimnasio.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputUtil {
    private final Scanner scanner = new Scanner(System.in);
    public String nextLine(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine().trim();
    }
    public int nextInt(String label) {
        while (true) {
            System.out.print(label + ": ");
            String v = scanner.nextLine().trim();
            try { return Integer.parseInt(v); } catch (Exception ignored) {}
        }
    }
    public BigDecimal nextBigDecimal(String label) {
        while (true) {
            System.out.print(label + ": ");
            String v = scanner.nextLine().trim();
            try { return new BigDecimal(v); } catch (Exception ignored) {}
        }
    }
}

