package com.freya02.aoc;

import java.util.TreeSet;

public class Day5 {
	private static void part1() {
		int maxSeatId = Integer.MIN_VALUE;

		for (String line : Utils.getInputLines()) {
			int endRow = getEnd(line, 127, 0, 7, 'F', 'B');
			int endColumn = getEnd(line, 7, 7, 10, 'L', 'R');

			int seatId = endRow * 8 + endColumn;

			System.out.printf("row = %s, column = %s, seatId = %s%n", endRow, endColumn, seatId);

			maxSeatId = Math.max(seatId, maxSeatId);
		}

		System.out.println("maxSeatId = " + maxSeatId);
	}

	private static int getEnd(String line, int max, int charBegin, int charEnd, char lowerPartChar, char upperPartChar) {
		int start = 0;
		int end = max;

		char[] chars = new char[charEnd - charBegin];
		line.getChars(charBegin, charEnd, chars, 0);
		for (char cchar : chars) {
			if (cchar == lowerPartChar) {
				end = (int) Math.floor((end + start) / 2.0);
			} else if (cchar == upperPartChar) {
				start = (int) Math.ceil((end + start) / 2.0);
			}
		}

		return end;
	}

	private static void part2() {
		TreeSet<Integer> ids = new TreeSet<>();

		for (String line : Utils.getInputLines()) {
			int endRow = getEnd(line, 127, 0, 7, 'F', 'B');
			int endColumn = getEnd(line, 7, 7, 10, 'L', 'R');

			int seatId = endRow * 8 + endColumn;

			ids.add(seatId);
		}

		while (ids.size() > 1) {
			int id = ids.pollFirst();

			if (id + 1 != ids.first()) {
				System.out.println("id = " + id + " has no next id. Our ID should be " + (id + 1));
			}
		}
	}

	public static void main(String[] args) {
		part1();

		part2();
	}
}