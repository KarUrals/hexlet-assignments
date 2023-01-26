package exercise;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String sourcePath1, String sourcePath2, String destPath) {
        System.out.println("Read file1");
        CompletableFuture<String> file1 = CompletableFuture.supplyAsync(() -> {
            Path filePath1 = Paths.get(sourcePath1).toAbsolutePath().normalize();

            String content = "";

            try {
                content = Files.readString(filePath1);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        System.out.println("Read file2");
        CompletableFuture<String> file2 = CompletableFuture.supplyAsync(() -> {
            Path filePath2 = Paths.get(sourcePath2).toAbsolutePath().normalize();

            String content = "";

            try {
                content = Files.readString(filePath2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return content;
        });

        System.out.println("Union files");
        Path filePath = Paths.get(destPath).toAbsolutePath().normalize();

        return file1.thenCombine(file2, (cont1, cont2) -> {
            String union = cont1 + cont2;
            try {
                Files.writeString(filePath, union, StandardOpenOption.CREATE);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return "ok!";
            // Обработка исключений
            // Если при работе задач возникли исключения
            // их можно обработать в методе exceptionally
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });
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

