package com.kforce.assignment;

import java.util.PriorityQueue;
import java.util.Scanner;

public class BestFitTest {
	
	
	
	
	 enum Size { SMALL, MEDIUM, LARGE }

	    public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);

	        // Three min-heaps for SMALL, MEDIUM, LARGE lockers
	        PriorityQueue<Integer>[] available = new PriorityQueue[3];
	        for (int i = 0; i < 3; i++) {
	            available[i] = new PriorityQueue<>();
	        }

	        // ----------------------------
	        // STEP 1: INPUT LOCKERS
	        // ----------------------------
	        System.out.print("Enter number of lockers: ");
	        int N = sc.nextInt();

	        for (int i = 0; i < N; i++) {
	            System.out.print("Enter LockerID and Size (SMALL/MEDIUM/LARGE): ");

	            int lockerId = sc.nextInt();
	            String sizeStr = sc.next().toUpperCase();

	            Size size = parseSize(sizeStr);
	            available[size.ordinal()].add(lockerId);
	        }

	        // ----------------------------
	        // STEP 2: INPUT PACKAGES
	        // ----------------------------
	        System.out.print("Enter number of packages: ");
	        int M = sc.nextInt();

	        for (int i = 0; i < M; i++) {

	            System.out.print("Enter PackageID and Size (SMALL/MEDIUM/LARGE): ");
	            String pkgId = sc.next();
	            String sizeStr = sc.next().toUpperCase();

	            Size pkgSize = parseSize(sizeStr);

	            boolean assigned = false;

	            // Try SMALL→MEDIUM→LARGE based on package size
	            for (int s = pkgSize.ordinal(); s < 3; s++) {
	                if (!available[s].isEmpty()) {
	                    int assignedLocker = available[s].poll();
	                    System.out.println("Package with id "+pkgId + " assigned to Locker with id " + assignedLocker);
	                    assigned = true;
	                    break;
	                }
	            }

	            if (!assigned) {
	                System.out.println(pkgId + " WAITLIST");
	            }
	        }

	        sc.close();
	    }

	    // ----------------------------
	    // HELPER: Convert string → enum
	    // ----------------------------
	    private static Size parseSize(String size) {
	        switch (size) {
	            case "SMALL": return Size.SMALL;
	            case "MEDIUM": return Size.MEDIUM;
	            case "LARGE": return Size.LARGE;
	            default:
	                throw new IllegalArgumentException("Invalid size: " + size);
	        }
	    }
}


