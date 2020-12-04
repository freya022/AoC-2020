package com.freya02.aoc;

import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
	private static int get2020MultPart1(List<Integer> list) {
		for (int i : list) {
			for (int i2 : list) {
				if (i + i2 == 2020) {
					return i * i2;
				}
			}
		}

		return 0;
	}

	private static int get2020MultPart2(List<Integer> list) {
		for (int i : list) {
			for (int i2 : list) {
				for (int i3 : list) {
					if (i + i2 + i3 == 2020) {
						return i * i2 * i3;
					}
				}
			}
		}

		return 0;
	}

	private static void part1() {
		final List<Integer> list = Utils.getInputLinesStream().map(Integer::parseInt).collect(Collectors.toList());

		final int mult = get2020MultPart1(list);
		System.out.println("Multiplication result is " + mult);
	}

	private static void part2() {
		final List<Integer> list = Utils.getInputLinesStream().map(Integer::parseInt).collect(Collectors.toList());

		final int mult = get2020MultPart2(list);
		System.out.println("Multiplication result is " + mult);
	}

	public static void main(String[] args) {
		part1();

		part2();
	}
}