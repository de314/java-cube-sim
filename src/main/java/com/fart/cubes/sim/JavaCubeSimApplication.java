package com.fart.cubes.sim;

import java.util.*;
import java.util.stream.Collectors;

//@SpringBootApplication
public class JavaCubeSimApplication {

	public static final int W = 0;
	public static final int G = 1;
	public static final int o = 2;
	public static final int R = 3;
	public static final int B = 4;
	public static final int Y = 5;

	public static final Map<Integer, Character> LETTER_MAP = new HashMap<Integer, Character>() {{
		this.put(W, 'W');
		this.put(G, 'G');
		this.put(o, 'O');
		this.put(R, 'R');
		this.put(B, 'B');
		this.put(Y, 'Y');
	}};



	public static void main(String[] args) {
//		SpringApplication.run(JavaCubeSimApplication.class, args);
		Cube2 cube2 = new Cube2();
		System.out.println("Solved: " + cube2.isSolved());
		System.out.println(cube2.pretty());
		perfTest(1_000_000_000L);
	}

	private static void perfTest(long iters) {
		Cube2 cube2 = new Cube2();
		long solvedCount = 0L;
		long startTime = System.currentTimeMillis();
		long lastSolved = 0;
		List<Long> solveSpans = new LinkedList<>();
		Random r = new Random(0L);
		for (int i = 0; i < iters; i++) {
			int moveIndex = r.nextInt(12);
			switch (moveIndex) {
				case 0: cube2.right(); break;
				case 1: cube2.left(); break;
				case 2: cube2.up(); break;
				case 3: cube2.down(); break;
				case 4: cube2.front(); break;
				case 5: cube2.back(); break;
				case 6: cube2.rightP(); break;
				case 7: cube2.leftP(); break;
				case 8: cube2.upP(); break;
				case 9: cube2.downP(); break;
				case 10: cube2.frontP(); break;
				case 11: cube2.backP(); break;
			}
//			System.out.println(moveIndex);
//			System.out.println(cube2.pretty());
			if (cube2.isSolved()) {
				solvedCount++;
				solveSpans.add(i - lastSolved);
				lastSolved = i;
			}
		}
		solveSpans.add(iters - lastSolved);
		long duration = System.currentTimeMillis() - startTime;
		System.out.println(String.format("iterations = %,d random moves", iters));
		System.out.println(String.format("duration = %,.2f seconds", duration / 1000.0));
		System.out.println(String.format("solvedCount = %,d", solvedCount));
		System.out.println(String.format("rate = %,.2f moves/sec", iters * 1000.0 / duration));
		LongSummaryStatistics solveSummary = solveSpans.stream().collect(Collectors.summarizingLong(i -> i));
		System.out.println(String.format("min = %,d", solveSummary.getMin()));
		System.out.println(String.format("max = %,d", solveSummary.getMax()));
		System.out.println(String.format("avg = %,.2f", solveSummary.getAverage()));
		System.out.println(cube2.pretty());
	}
}
