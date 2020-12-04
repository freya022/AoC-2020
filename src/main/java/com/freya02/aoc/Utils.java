package com.freya02.aoc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
	private static Stream<String> getInputLinesStream(Class<?> callerClass) {
		return new BufferedReader(new InputStreamReader(callerClass.getResourceAsStream(callerClass.getSimpleName() + ".txt"))).lines();
	}

	public static List<String> getInputLines() {
		final Class<?> callerClass = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();

		return getInputLinesStream(callerClass).collect(Collectors.toList());
	}

	public static Stream<String> getInputLinesStream() {
		final Class<?> callerClass = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();

		return getInputLinesStream(callerClass);
	}

	public static String getInput() {
		final Class<?> callerClass = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();

		try {
			return Files.readString(com.freya02.misc.Utils.getJarFilesystem(callerClass).resolve(callerClass.getPackageName().replace(".", "/")).resolve(callerClass.getSimpleName() + ".txt"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
