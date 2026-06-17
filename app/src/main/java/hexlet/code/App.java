package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Compares two configuration files and shows a difference."
)
public final class App implements Callable<Integer> {
    @Parameters(index = "0", paramLabel = "filepath1", description = "path to first file")
    private String firstFilePath;

    @Parameters(index = "1", paramLabel = "filepath2", description = "path to second file")
    private String secondFilePath;

    @Option(
            names = {"-f", "--format"},
            paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish"
    )
    private String format;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Differ.generate(firstFilePath, secondFilePath, format));
        return 0;
    }
}
