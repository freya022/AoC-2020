package com.freya02.aoc;

import java.util.List;

public class Day3 {
	private static void part1() {
		final List<String> lines = Utils.getInputLines();

		final int width = lines.get(0).length();
		final int height = lines.size();

		final int xStep = 3;
		final int yStep = 1;

		int trees = getTrees(lines, width, height, xStep, yStep);

		System.out.println("trees = " + trees);
	}

	private static int getTrees(List<String> lines, int width, int height, int xStep, int yStep) {
		int x = 0;
		int trees = 0;

		for (int y = yStep; y < height; y += yStep) {
			x += xStep;

			while (x >= width) {
				x -= width;
			}

			if (lines.get(y).charAt(x) == '#') {
				trees++;
			}
		}
		return trees;
	}

	private static void part2() {
		final List<String> lines = Utils.getInputLines();

		final int width = lines.get(0).length();
		final int height = lines.size();

		int trees =
				getTrees(lines, width, height, 1, 1)
				* getTrees(lines, width, height, 3, 1)
				* getTrees(lines, width, height, 5, 1)
				* getTrees(lines, width, height, 7, 1)
				* getTrees(lines, width, height, 1, 2);

		System.out.println("trees = " + trees);
	}

	public static void main(String[] args) {
		part1();

		part2();
	}
}