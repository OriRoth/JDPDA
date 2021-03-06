package jdpda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import jdpda.DPDA;
import jdpda.Compiler;

@SuppressWarnings("static-method")
public class CompileAll {
	private static final String PATH = "./source/test/java/jdpda/generated/";

	public static final Map<String, String> files = new HashMap<>();
	public static final Object[][] APIClasses = { //
			{ "LAPlusBBAPI", LAPlusBB.M }, //
			{ "LAStarAPI", LAStar.M }, //
			{ "LAStarBAPI", LAStarB.M }, //
			{ "LBalancedParenthesesAPI", LBalancedParentheses.M }, //
			{ "LExtendedBalancedParenthesesAPI", LExtendedBalancedParentheses.M }, //
			{ "LLispParenthesesAPI", LLispParentheses.M }, //
	};
	static {
		for (Object[] APIClass : APIClasses)
			files.put((String) APIClass[0], "package jdpda.generated;\n\n"
					+ new Compiler<>((String) APIClass[0], (DPDA<?, ?, ?>) APIClass[1]).go());
	}

	@Test
	public void compileAll() throws IOException {
		System.out.println("Current output folder is " + PATH + ".");
		Path outputFolder = Paths.get(PATH);
		if (!Files.exists(outputFolder)) {
			Files.createDirectory(outputFolder);
			System.out.println("Folder " + outputFolder + " created successfully.");
		}
		for (String fileName : files.keySet()) {
			Path filePath = Paths.get(PATH + fileName + ".java");
			if (Files.exists(filePath))
				Files.delete(filePath);
			Files.write(filePath, Collections.singleton(files.get(fileName)), StandardOpenOption.CREATE,
					StandardOpenOption.WRITE);
			System.out.println("File " + fileName + ".java written successfully.");
		}
	}
}
