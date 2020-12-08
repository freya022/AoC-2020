package com.freya02.aoc;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Day8 {
	private static void part1() {
		List<String> inputLines = Utils.getInputLines();

		tryWith(inputLines);
	}

	private static void part2() {
		List<String> inputLines = Utils.getInputLines();

		for (int i = 0, inputLinesSize = inputLines.size(); i < inputLinesSize; i++) {
			String inputLine = inputLines.get(i);

			final String opcode = inputLine.split(" ")[0];
			final String val = inputLine.split(" ")[1];

			boolean rerun = switch (opcode) {
				case "nop" -> {
					inputLines.set(i, "jmp " + val);
					boolean rerun1 = tryWith(inputLines);
					inputLines.set(i, "nop " + val);

					yield rerun1;
				}
				case "jmp" -> {
					inputLines.set(i, "nop " + val);
					boolean rerun1 = tryWith(inputLines);
					inputLines.set(i, "jmp " + val);

					yield rerun1;
				}
				default -> true;
			};

			if (!rerun) {
				System.out.printf("Replaced instruction at %d worked : '%s'%n", i, inputLines.get(i));
				break;
			}
		}
	}

	/** <code>true</code> = rerun */
	private static boolean tryWith(List<String> inputLines) {
		int acc = 0;
		Set<Integer> done = new LinkedHashSet<>();
		for (int i = 0, inputLinesSize = inputLines.size(); i < inputLinesSize;) {
			String inputLine = inputLines.get(i);

			if (!done.add(i)) {
				System.err.println("Stopped at instruction " + i + " : " + inputLine);
				System.err.println("acc = " + acc);
				return true;
			}

			final String opcode = inputLine.split(" ")[0];
			final String val = inputLine.split(" ")[1];

			int inc = switch (opcode) {
				case "acc" -> {
					acc += Integer.parseInt(val);
					yield 1;
				}
				case "jmp" -> Integer.parseInt(val);
				default -> 1; //nop
			};

			i += inc;
		}

		System.out.println("acc = " + acc);

		return false;
	}

	public static void main(String[] args) {
		part1();

		part2();
	}
}