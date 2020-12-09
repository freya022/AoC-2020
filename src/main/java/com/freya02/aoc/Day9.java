package com.freya02.aoc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day9 {
	private static long part1() {
		List<Long> inputLines = Utils.getInputLines().stream().map(Long::valueOf).collect(Collectors.toList());
		return getFirstNonAddable(inputLines);
	}

	private static long getFirstNonAddable(List<Long> inputLines) {
		for (int i = 25, inputLinesSize = inputLines.size(); i < inputLinesSize; i++) {
			long inputLine = inputLines.get(i);

			boolean found = false;
			calcLoop:
			for (int j = 0; j <= 25; j++) {
				for (int k = 0; k <= 25; k++) {
					if (k != j && inputLines.get(i - j) + inputLines.get(i - k) == inputLine) {
						found = true;
						break calcLoop;
					}
				}
			}

			if (!found) {
				return inputLine;
			}
		}

		return -1;
	}

	private static void part2() {
		List<Long> inputLines = Utils.getInputLines().stream().map(Long::valueOf).collect(Collectors.toList());

		long target = getFirstNonAddable(inputLines);
		for (int i = 0; i < inputLines.size(); i++) {
			List<Long> longs = new ArrayList<>(100);
			long sum = 0;
			for (int j = i; j < inputLines.size() - i; j++) {
				sum += inputLines.get(j);
				longs.add(inputLines.get(j));
				if (sum >= target) {
					break;
				}
			}

			if (sum == target) {
				System.out.println(longs.stream().max(Long::compare).get() + longs.stream().min(Long::compare).get());

				break;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Not found for " + part1());

		part2();
	}
}