package com.freya02.aoc;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.LongStream;

public class Day14 {
	private static final Pattern PATTERN = Pattern.compile("((?<mask>mask = (.*))|(?<mem>mem\\[(\\d+)] = (.*)))");

	private static long applyMask(String mask, long decimalVal) {
		char[] charArray = mask.toCharArray();
		final StringBuilder s = new StringBuilder(putPadding(Long.toBinaryString(decimalVal)));
		for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
			char c = charArray[i];

			if (c != 'X') {
				s.setCharAt(i, c);
			}
		}

		return Long.parseLong(s.toString(), 2);
	}

	@NotNull
	private static String putPadding(String binStr) {
		return "0".repeat(36 - binStr.length()) + binStr; //always 36 bits, mask's length
	}

	private static long doPart(BiFunction<String, Integer, long[]> addressFunction, BiFunction<String, Long, Long> valueFunction) {
		final Map<Long, Long> mem = new HashMap<>();
		String maskStr = "";

		final Matcher matcher = PATTERN.matcher(Utils.getInput());
		while (matcher.find()) {
			if (matcher.group("mask") != null) {
				maskStr = matcher.group(3);
			} else {
				final int index = Integer.parseInt(matcher.group(5));
				final long value = Long.parseLong(matcher.group(6));
				for (long address : addressFunction.apply(maskStr, index)) {
					mem.put(address, valueFunction.apply(maskStr, value));
				}
			}
		}

		return mem.values().stream().mapToLong(Long::longValue).sum();
	}

	private static void part1() {
		final long sum = doPart((s, integer) -> new long[]{integer}, Day14::applyMask);
		System.out.println("mem.sum() = " + sum);

		if (7997531787333L != sum) {
			System.err.println("error p1");
		}
	}

	private static long[] applyMaskAddress(String mask, long decimalVal) {
		char[] charArray = mask.toCharArray();
		final StringBuilder builder = new StringBuilder(putPadding(Long.toBinaryString(decimalVal)));
		final List<Integer> floatingIndexes = new ArrayList<>();
		for (int i = 0, charArrayLength = charArray.length; i < charArrayLength; i++) {
			char c = charArray[i];

			if (c != '0') {
				builder.setCharAt(i, c);

				if (c == 'X') {
					floatingIndexes.add(i);
				}
			}
		}

		return LongStream.range(0, 2 << floatingIndexes.size() - 1).map(seqBits -> {
			final String randomBits = putPadding(Long.toBinaryString(seqBits));

			for (int j = 0, floatingIndexesSize = floatingIndexes.size(); j < floatingIndexesSize; j++) {
				builder.setCharAt(floatingIndexes.get(j), randomBits.charAt(randomBits.length() - j - 1));
			}

			return Long.parseLong(builder.toString(), 2);
		}).toArray();
	}

	private static void part2() {
		final long sum = doPart(Day14::applyMaskAddress, (s, l) -> l);
		System.out.println("mem.sum() = " + sum);

		if (3564822193820L != sum) {
			System.err.println("error p2");
		}
	}

	public static void main(String[] args) {
		part1();

		part2();
	}
}