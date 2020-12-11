package com.freya02.aoc;

import java.util.List;
import java.util.stream.Collectors;

//Adapter can be 1, 2 or 3 lower than it's rating
//Device can accept 1, 2 or 3 higher than rating
//Outlet is 0 volt
public class Day10 {
	private static final List<Integer> list = Utils.getInputLines().stream().map(Integer::valueOf).sorted(Integer::compareTo).collect(Collectors.toList());
	private static int size;

	private static void part1() {
		final int maxDeviceVoltage = list.stream().max(Integer::compareTo).get();
		int onediff = 0;
		int threediff = 1; //Start at 1 because device is always higher by 3

		int currVolt = 0;
		for (int i : list) {
			switch (i - currVolt) {
				case 1 -> onediff++;
				case 3 -> threediff++;
			}

			currVolt = i;
		}
		switch (maxDeviceVoltage - currVolt) {
			case 1 -> onediff++;
			case 3 -> threediff++;
		}

		System.out.println(onediff * threediff);
	}

	private static int[] arr;
	private static int solutions = 0;
	private static void part2() {
		arr = new int[list.size()];
		for (int i = 0, listSize = list.size(); i < listSize; i++) {
			arr[i] = list.get(i);
		}
		size = list.size();

		start(0, -1);

		System.out.println("NUMBER OF MOTHER FUCKING POSSIBILITIES : " + solutions);
	}

	@SuppressWarnings("FieldCanBeLocal") //wrong
	private static int flag = 0;
	private static void start(int currVolt, int i) {
		for (int j = i + 1; j < size; j++) {
			final int num = arr[j];
			if (num - currVolt <= 3) {
				flag = 1;

				start(num, j);

				if (flag != 0) {
					solutions++;
				}

				flag = 0;
			} else {
				break;
			}
		}
	}

	public static void main(String[] args) {
		part1();

		part2();
	}
}