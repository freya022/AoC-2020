package com.freya02.aoc;

import java.util.ArrayList;
import java.util.List;

public class Day4 {
	private static void part1() {
		final String input = Utils.getInput();

		int validPassports = 0;
		for (String s : input.split("\n\n")) {
			List<String> keys = new ArrayList<>();
			for (String s1 : s.split("[ \n]")) {
				final int i = s1.indexOf(':');

				final String key = s1.substring(0, i);
//				final String value = s1.substring(i + 1);

				keys.add(key);
			}

			if (keys.containsAll(List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"/*, "cid"*/ /*optional*/))) {
				validPassports++;
			}
		}

		System.out.println("validPassports = " + validPassports);
	}

	private static void part2() {
		final String input = Utils.getInput();

		int validPassports = 0;
		for (String s : input.split("\n\n")) {
			List<String> keys = new ArrayList<>();
			for (String s1 : s.split("[ \n]")) {
				final int i = s1.indexOf(':');

				final String key = s1.substring(0, i);
				final String value = s1.substring(i + 1);

				boolean valid;
				try {
					valid = switch (key) {
						case "byr" -> value.length() == 4 && Integer.parseInt(value) >= 1920 && Integer.parseInt(value) <= 2002;
						case "iyr" -> value.length() == 4 && Integer.parseInt(value) >= 2010 && Integer.parseInt(value) <= 2020;
						case "eyr" -> value.length() == 4 && Integer.parseInt(value) >= 2020 && Integer.parseInt(value) <= 2030;
						case "hgt" -> {
							final int height = Integer.parseInt(value.substring(0, value.length() - 2));
							yield switch (value.substring(value.length() - 2)) {
								case "cm" -> height >= 150 && height <= 193;
								case "in" -> height >= 59 && height <= 76;
								default -> false;
							};
						}
						case "hcl" -> value.matches("#[0-9a-f]{6}");
						case "ecl" -> value.matches("(amb|blu|brn|gry|grn|hzl|oth)");
						case "pid" -> value.length() == 9 && Integer.parseInt(value) != 0;
						case "cid" -> true;
						default -> false;
					};
				} catch (NumberFormatException e) {
					valid = false;
				}

				if (valid) {
					keys.add(key);
				}
			}

			if (keys.containsAll(List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"/*, "cid"*/ /*optional*/))) {
				validPassports++;
			}
		}

		System.out.println("validPassports = " + validPassports);
	}

	public static void main(String[] args) {
		part1();

		part2();
	}
}