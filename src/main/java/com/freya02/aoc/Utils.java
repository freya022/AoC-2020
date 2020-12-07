package com.freya02.aoc;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Utils {
	public static List<String> getInputLines() {
		final Class<?> callerClass = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();

		try {
			return Files.readAllLines(getInputPath(callerClass));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Stream<String> getInputLinesStream() {
		final Class<?> callerClass = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();

		try {
			return Files.lines(getInputPath(callerClass));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getInput() {
		final Class<?> callerClass = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass();

		try {
			return Files.readString(getInputPath(callerClass));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@NotNull
	private static Path getInputPath(Class<?> callerClass) {
		try {
			return com.freya02.misc.Utils.getJarFilesystem(callerClass).resolve(callerClass.getPackageName().replace(".", "/")).resolve(callerClass.getSimpleName() + ".txt");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
