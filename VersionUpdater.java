import java.io.*;
import java.nio.file.*;

//Question 2
public class VersionUpdater {

    private static final String SCONSTRUCT_FILENAME = "SConstruct";
    private static final String VERSION_FILENAME = "VERSION";

    public static void main(String[] args)
    {
        try
        {
            String buildNum = getEnvironmentVariable("BuildNum");
            String sourcePath = getEnvironmentVariable("SourcePath");

            Path basePath = Paths.get(sourcePath, "develop", "global", "src");

            updateFileLine(basePath.resolve(SCONSTRUCT_FILENAME), "point=\\d+", "point=" + buildNum);

            updateFileLine(basePath.resolve(VERSION_FILENAME), "ADLMSDK_VERSION_POINT=\\s*\\d+", "ADLMSDK_VERSION_POINT=" + buildNum);

            System.out.println("Version update completed successfully.");

        }
        catch (Exception e)
        {
            System.err.println("Error during version update: " + e.getMessage());
        }
    }

    private static String getEnvironmentVariable(String name)
    {
        String value = System.getenv(name);
        if (value == null || value.isEmpty())
        {
            throw new IllegalArgumentException("Environment variable '" + name + "' is not set.");
        }
        return value;
    }

    private static void updateFileLine(Path filePath, String pattern, String replacement) throws IOException
    {
        String content = new String(Files.readAllBytes(filePath));
        String updatedContent = content.replaceAll(pattern, replacement);
        Files.write(filePath, updatedContent.getBytes());
    }
}