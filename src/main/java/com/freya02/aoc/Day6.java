package com.freya02.aoc;

import java.util.HashMap;
import java.util.Map;

public class Day6 {
	private static void part1() {
		int totalAnswers = 0;
		for (String group : Utils.getInput().split("\n\n")) {
			final long answers = group.chars().filter(Character::isLetter).distinct().count();
			totalAnswers += answers;
		}

		System.out.println("totalAnswers = " + totalAnswers);
	}

	private static void part2() {
		int totalAnswers = 0;
		for (String group : Utils.getInput().split("\n\n")) {
			Map<Character, Integer> countMap = new HashMap<>();

			final String[] persons = group.split("\n");
			final int groupLength = persons.length;
			for (String group2 : persons) {
				for (char c : group2.toCharArray()) {
					countMap.merge(c, 1, (oldValue, defaultValue) -> oldValue + 1);
				}
			}

			long answers = countMap.values().stream().filter(val -> val == groupLength).count();
			totalAnswers += answers;
		}

		System.out.println("totalAnswers = " + totalAnswers);
	}

	public static void main(String[] args) {
		part1();

		part2();
	}
}