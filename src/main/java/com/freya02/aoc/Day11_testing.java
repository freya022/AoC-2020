package com.freya02.aoc;

import java.util.List;
import java.util.function.BiFunction;

public class Day11_testing {
	private static char[][] seats;
	private static int height, width;

	private static char getSeat(int x, int y) {
		if (x >= width || x < 0) return 0;
		if (y >= height || y < 0) return 0;
		return seats[y][x];
	}

	private static int getAdjacent(int x, int y) {
		int adj = 0;

		for (int xOffset = -1; xOffset <= 1; xOffset++) {
			for (int yOffset = -1; yOffset <= 1; yOffset++) {
				if (xOffset == 0 && yOffset == 0) continue;

				if (getSeat(x + xOffset, y + yOffset) == '#') {
					adj++;
				}
			}
		}

		return adj;
	}

	private static int getOccupiedByDirection(int x, int y, int dirX, int dirY) {
		while (true) {
			x += dirX;
			y += dirY;

			final char seat = getSeat(x, y);

			if (seat == '#') {
				return 1;
			} else if (seat == 'L' || seat == 0) {
				return 0;
			}
		}
	}

	private static int getAdjacent2(int x, int y) {
		int adj = 0;

		adj += getOccupiedByDirection(x, y, 1, 1);
		adj += getOccupiedByDirection(x, y, 1, 0);
		adj += getOccupiedByDirection(x, y, 1, -1);

		adj += getOccupiedByDirection(x, y, 0, 1);
		adj += getOccupiedByDirection(x, y, 0, -1);

		adj += getOccupiedByDirection(x, y, -1, 1);
		adj += getOccupiedByDirection(x, y, -1, 0);
		adj += getOccupiedByDirection(x, y, -1, -1);

		return adj;
	}

	private static char[][] newSeatsArray() {
		return new char[height][width];
	}

	private static boolean performRound(BiFunction<Integer, Integer, Integer> adjacentFunc, int requiredToLeave) {
		final char[][] newSeats = newSeatsArray();

		boolean change = false;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				final char seat = getSeat(x, y);
				final char newSeat;

				if (seat == 'L' && adjacentFunc.apply(x, y) == 0) {
					change = true;
					newSeat = '#';
				} else if (seat == '#' && adjacentFunc.apply(x, y) >= requiredToLeave) {
					change = true;
					newSeat = 'L';
				} else newSeat = seat;

				newSeats[y][x] = newSeat;
			}
		}

		seats = newSeats;

		return change;
	}

	private static void print(String assertStr) {
		final StringBuilder builder = new StringBuilder();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				builder.append(getSeat(x, y));
			}
			builder.append('\n');
		}

		final String resultPrinting = builder.toString().trim();
		if (!assertStr.isBlank()) {
			if (resultPrinting.equals(assertStr.trim())) {
				System.out.println("correct");
			} else {
				System.err.println("incorrect");
			}
		}
		System.out.println(resultPrinting);
	}

	private static void part1() {
		print("""
				L.LL.LL.LL
				LLLLLLL.LL
				L.L.L..L..
				LLLL.LL.LL
				L.LL.LL.LL
				L.LLLLL.LL
				..L.L.....
				LLLLLLLLLL
				L.LLLLLL.L
				L.LLLLL.LL""");

		System.out.println("-".repeat(50));

		performRound(Day11_testing::getAdjacent, 4);

		print("""
				#.##.##.##
				#######.##
				#.#.#..#..
				####.##.##
				#.##.##.##
				#.#####.##
				..#.#.....
				##########
				#.######.#
				#.#####.##""");

		System.out.println("-".repeat(50));

		performRound(Day11_testing::getAdjacent, 4);

		print("""
				#.LL.L#.##
				#LLLLLL.L#
				L.L.L..L..
				#LLL.LL.L#
				#.LL.LL.LL
				#.LLLL#.##
				..L.L.....
				#LLLLLLLL#
				#.LLLLLL.L
				#.#LLLL.##""");

		System.out.println("-".repeat(50));

		performRound(Day11_testing::getAdjacent, 4);

		print("""
				#.##.L#.##
				#L###LL.L#
				L.#.#..#..
				#L##.##.L#
				#.##.LL.LL
				#.###L#.##
				..#.#.....
				#L######L#
				#.LL###L.L
				#.#L###.##""");

		System.out.println("-".repeat(50));

		performRound(Day11_testing::getAdjacent, 4);

		print("""
				#.#L.L#.##
				#LLL#LL.L#
				L.L.L..#..
				#LLL.##.L#
				#.LL.LL.LL
				#.LL#L#.##
				..L.L.....
				#L#LLLL#L#
				#.LLLLLL.L
				#.#L#L#.##""");

		System.out.println("-".repeat(50));

		performRound(Day11_testing::getAdjacent, 4);

		print("""
				#.#L.L#.##
				#LLL#LL.L#
				L.#.L..#..
				#L##.##.L#
				#.#L.LL.LL
				#.#L#L#.##
				..L.L.....
				#L#L##L#L#
				#.LLLLLL.L
				#.#L#L#.##""");
	}

	private static void part2() {
		System.out.println("-".repeat(50));

		performRound(Day11_testing::getAdjacent2, 5);

		print("""
				#.##.##.##
				#######.##
				#.#.#..#..
				####.##.##
				#.##.##.##
				#.#####.##
				..#.#.....
				##########
				#.######.#
				#.#####.##""");
	}

	public static void main(String[] args) {
		final List<String> lines = Utils.getInputLines();
		height = lines.size();
		width = lines.get(0).length();

		resetCharInput(lines);

		part1();

		resetCharInput(lines);

		part2();
	}

	private static void resetCharInput(List<String> lines) {
		seats = newSeatsArray();
		for (int i = 0, linesSize = lines.size(); i < linesSize; i++) {
			String line = lines.get(i);

			seats[i] = line.toCharArray();
		}
	}
}