package com.freya02.aoc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day7 {
	private static final Map<String, List<String>> bagMap = new LinkedHashMap<>();
	private static final Map<String, List<Bag>> bagMap2 = new LinkedHashMap<>();
	private static final Pattern LINE_PATTERN = Pattern.compile("(.+) bags contain (.+)\\.");
	private static final Pattern CONTAINS_PATTERN = Pattern.compile("(\\d*) (.+) bags?");

	private static class Bag {
		private final String name;
		private final int count;

		private Bag(String name, int count) {
			this.name = name;
			this.count = count;
		}
	}

	private static void part1() {
		int bags = 0;
		for (String bagName : bagMap.keySet()) {
			final AtomicInteger goldCount = new AtomicInteger();
			recurse(goldCount, bagName);
//			System.out.println("goldCount = " + goldCount);
			if (goldCount.get() > 0) {
				bags++;
			}
		}

		System.out.println("bags = " + bags);
	}

	private static void parseInput() {
		for (String inputLine : Utils.getInputLines()) {
			final Matcher lineMatcher = LINE_PATTERN.matcher(inputLine);
			if (lineMatcher.find()) {
				final String left = lineMatcher.group(1);
				final String right = lineMatcher.group(2);

				for (String s : right.split(", ")) {
					final Matcher containsMatcher = CONTAINS_PATTERN.matcher(s);

					if (containsMatcher.find()) {
						final String number = containsMatcher.group(1);
						final String name = containsMatcher.group(2);

						final List<String> list = bagMap.computeIfAbsent(left, k -> new ArrayList<>());
						final List<Bag> list2 = bagMap2.computeIfAbsent(left, k -> new ArrayList<>());
						if (!name.equals("other")) {
							list.add(name);
							list2.add(new Bag(name, Integer.parseInt(number)));
						}
					} else {
						System.err.println("NO CONTAINS GROUP " + s);
					}
				}
			} else {
				System.err.println("NO LINE GROUP " + inputLine);
			}
		}
	}

	private static void recurse(AtomicInteger goldCount, String bag) {
		final boolean shiny_gold = bagMap.get(bag).contains("shiny gold");
		if (shiny_gold) {
			goldCount.addAndGet(1);
		} else {
			for (String bbag : bagMap.get(bag)) {
				recurse(goldCount, bbag);
			}
		}
	}

	private static void part2() {
		final AtomicInteger bags = new AtomicInteger();

		count(bags, new Bag("shiny gold", 1), 1);

		System.out.println("bags = " + bags);
	}

	private static void count(AtomicInteger bagsCount, Bag bag, int multiplier) {
		for (Bag bbag : bagMap2.get(bag.name)) {
			bagsCount.addAndGet(bbag.count * multiplier);
			count(bagsCount, bbag, bbag.count * multiplier);
		}
	}

	public static void main(String[] args) {
		parseInput();

		part1();

		part2();
	}
}