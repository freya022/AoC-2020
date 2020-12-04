package com.freya02.aoc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.freya02.misc.Utils.measureTime;

public class Day2 {
	private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+) (\\w): (.+)");

	/** <code>true</code> if correct password */
	private static boolean isValidPart1(String line) {
		final Matcher matcher = PATTERN.matcher(line);

		if (matcher.find()) {
			final int min = Integer.parseInt(matcher.group(1));
			final int max = Integer.parseInt(matcher.group(2));
			final char targetChar = matcher.group(3).charAt(0);
			final String input = matcher.group(4);

//			if (!((min > 0 && min < 50) || (max > 0 && max < 50)) ) throw new Error(line);
//			if (matcher.group(3).length() > 1) throw new Error(line);
//			if (!Character.isLetter(targetChar)) throw new Error(line);
//			if (!input.chars().allMatch(Character::isLetter)) throw new Error(line);
//			if (!("%d-%d %s: %s".formatted(min, max, targetChar, input)).equals(line)) throw new Error(line);

//			long count = input.chars().filter(character -> character == targetChar).count();
			int count = 0;
			for (char c : input.toCharArray()) {
				if (c == targetChar) count++;
			}

			return count >= min && count <= max;
		} else {
			System.err.println("NOT FOUND FOR '" + line + "'");
			return false;
		}
	}

	/** <code>true</code> if correct password */
	private static boolean isValidPart2(String line) {
		final Matcher matcher = PATTERN.matcher(line);

		if (matcher.find()) {
			//Array starts at 1 in the example
			final int index1 = Integer.parseInt(matcher.group(1)) - 1;
			final int index2 = Integer.parseInt(matcher.group(2)) - 1;
			final char targetChar = matcher.group(3).charAt(0);
			final String input = matcher.group(4);

			int count = 0;
			if (input.charAt(index1) == targetChar) count++;
			if (input.charAt(index2) == targetChar) count++;

			return count == 1;
		} else {
			System.err.println("NOT FOUND FOR '" + line + "'");
			return false;
		}
	}

	private static void part1() {
		int count = 0;
		for (String inputLine : Utils.getInputLines()) {
			if (isValidPart1(inputLine)) {
				count++;
			}
		}

//		System.out.println("Correct passwords of part 1 : " + count);
	}

	private static void part2() {
		int count = 0;
		for (String inputLine : Utils.getInputLines()) {
			if (isValidPart2(inputLine)) {
				count++;
			}
		}

//		System.out.println("Correct passwords of part 2 : " + count);
	}

	public static void main(String[] args) {
		part1();
		part2();

		measureTime("Day 2 Part 1", 1000, Day2::part1);
		measureTime("Day 2 Part 2", 1000, Day2::part2);
	}
}