/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package hexlet.code;

import picocli.CommandLine;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.")

public class App implements Callable {

    @CommandLine.Option(names = {"-f", "--format"}, description = "output format", defaultValue = "json")
    String format1;
    @CommandLine.Parameters(paramLabel = "filepath1",
            defaultValue = "./app/File1.json", description = "path to first file")
    Path path1;
    @CommandLine.Parameters(paramLabel = "filepath2",
            defaultValue = "./app/File2.json", description = "path to second file")
    Path path2;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }
    @Override
    public Object call() {

        String pathOfFile1 = path1.toAbsolutePath().normalize().toString();
        String pathOfFile2 = path2.toAbsolutePath().normalize().toString();

        File file1 = new File(pathOfFile1);
        File file2 = new File(pathOfFile2);
        String result = Differ.generate(file1, file2, format1);
        System.out.println(result);
        return result;
    }
}
