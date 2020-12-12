package com.freya02.aoc;

public class Day12 {
	private static void part1() {
		int angle = 0;
		double x = 0;
		double y = 0;

		for (String line : Utils.getInputLines()) {
			final char dirChar = line.charAt(0);
			final int num = Integer.parseInt(line.substring(1));

			switch (dirChar) {
				case 'F' -> {
					x += Math.cos(Math.toRadians(angle)) * num;
					y += Math.sin(Math.toRadians(angle)) * num;
				}
				case 'N' -> y += num;
				case 'S' -> y -= num;
				case 'E' -> x += num;
				case 'W' -> x -= num;
				case 'L' -> angle += num;
				case 'R' -> angle -= num;
				default -> throw new IllegalArgumentException("Unknown dirChar " + dirChar);
			}
		}

		System.out.println("Distance = " + Math.round(Math.abs(x) + Math.abs(y)));
	}

	private static void part2() {
		double x = 0;
		double y = 0;
		double wx = 10;
		double wy = 1;

		for (String line : Utils.getInputLines()) {
			final char dirChar = line.charAt(0);
			int num = Integer.parseInt(line.substring(1));

			switch (dirChar) {
				case 'F' -> {
					x += wx * num;
					y += wy * num;
				}
				case 'N' -> wy += num;
				case 'S' -> wy -= num;
				case 'E' -> wx += num;
				case 'W' -> wx -= num;
				case 'L' -> {
					while (num > 0) {
						double oldWy = wy;

						wy = wx * Math.sin(Math.toRadians(90));
						wx = oldWy * Math.sin(Math.toRadians(-90));

						num -= 90;
					}
				}
				case 'R' -> {
					while (num > 0) {
						double oldWy = wy;

						wy = wx * Math.sin(Math.toRadians(-90));
						wx = oldWy * Math.sin(Math.toRadians(90));

						num -= 90;
					}
				}
				default -> throw new IllegalArgumentException("Unknown dirChar " + dirChar);
			}
		}

		System.out.println("Distance = " + Math.round(Math.abs(x) + Math.abs(y)));
	}

	public static void main(String[] args) {
		part1();

		part2();
	}
}