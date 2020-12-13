package com.freya02.aoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day13 {
	private static void part1() {
		final List<String> inputLines = Utils.getInputLines();
		final int targetTimestamp = Integer.parseInt(inputLines.get(0));
		final List<Integer> ids = Arrays.stream(inputLines.get(1).split(",")).filter(s -> !s.equals("x")).map(Integer::valueOf).collect(Collectors.toList());

		int minMod = Integer.MAX_VALUE;
		int minId = -1;
		for (int id : ids) {
			int mod = id - (targetTimestamp % id);

			if (minMod > mod) {
				minMod = mod;
				minId = id;
			}
		}

		System.out.println("minId = " + minId);
		System.out.println(minMod * minId);
	}

	private static void part2() {
		final List<String> inputLines = Utils.getInputLines();
		final List<Integer> ids = Arrays.stream(inputLines.get(1).split(",")).filter(s -> !s.equals("x")).map(Integer::valueOf).collect(Collectors.toList());

//		final double[] ns = new double[] {7, 13, 59, 31, 19};
		final double[] ns = new double[] {3, 5, 7};
		final double[] as = new double[] {1, 4, 6};
		final double[] yis = new double[ns.length];
		final double[] zis = new double[ns.length];
		double N = 1;
		for (double n : ns) {
			N *= n;
		}

		for (int i = 0; i < ns.length; i++) {
			yis[i] = N / ns[i];
		}

		for (int i = 0; i < ns.length; i++) {
			zis[i] = (1 / yis[i]) % ns[i];
		}

		double x = 0;
		for (int i = 0; i < ns.length; i++) {
			x += (as[i] * yis[i] * zis[i]);
		}

		System.out.println("x = " + x);
		System.out.println("N = " + N);
		System.out.println("x % N = " + x % N);
	}

	public static void main(String[] args) {
		part1();

		part2();
	}
}