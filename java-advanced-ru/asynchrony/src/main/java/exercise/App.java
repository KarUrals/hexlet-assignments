package exercise;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String sourcePath1, String sourcePath2, String destPath) {
        System.out.println("Read file1");
        CompletableFuture<String> file1 = CompletableFuture.supplyAsync(() -> {
            Path filePath1 = Paths.get(sourcePath1).toAbsolutePath().normalize();

            if (Files.exists((filePath1))) {
                try {
                    return Files.readString(filePath1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else try {
                throw new NoSuchFileException(sourcePath2);
            } catch (NoSuchFileException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Read file2");
        CompletableFuture<String> file2 = CompletableFuture.supplyAsync(() -> {
            Path filePath2 = Paths.get(sourcePath2).toAbsolutePath().normalize();

            if (Files.exists((filePath2))) {
                try {
                    return Files.readString(filePath2);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else try {
                throw new NoSuchFileException(sourcePath1);
            } catch (NoSuchFileException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Union files");
        CompletableFuture<String> unionFile = file1.thenCombine(file2, (content1, content2) -> {
            String unionString = content1 + content2;

            Path filePath = Paths.get(destPath).toAbsolutePath().normalize();
            File file = new File(filePath.toString());

            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(unionString);
                fileWriter.close();
                return unionString;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            // Обработка исключений
            // Если при работе задач возникли исключения
            // их можно обработать в методе exceptionally
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });

        return unionFile;
    }

    public static CompletableFuture<Long> getDirectorySize(String path) {
        return CompletableFuture.supplyAsync(() -> {
            Long size;
            try {
                size = Files.walk(Paths.get(path).toAbsolutePath().normalize(), 1)
                        .filter(Files::isRegularFile)
                        .mapToLong(p -> {
                            try {
                                return Files.size(p);
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return size;

        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        String sourceFile1 = "src/main/resources/file1.txt";
        String sourceFile2 = "src/main/resources/file2.txt";
        String destFile = "src/main/resources/result.txt";
        App.unionFiles(sourceFile1, sourceFile2, destFile);
        // END
    }
}

