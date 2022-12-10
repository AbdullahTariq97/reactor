package com.rp.assignment1;

import reactor.core.publisher.Mono;

import java.io.*;
import java.util.function.Supplier;

public class FileService {

    private static final String FILE_DIRECTORY = "src/main/resources/assignment/sec01/";

    public Mono<String> readFileAndReturnContent(String fileName){
        return Mono.fromSupplier(readFileLogic(fileName));
    }

    private Supplier<String> readFileLogic(String fileName) {
        return () -> {
            System.out.println("entering logic for reading file");
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_DIRECTORY + fileName));
                StringBuilder stringBuilder = new StringBuilder();
                while ((bufferedReader.readLine()) != null) {
                    stringBuilder.append(bufferedReader.readLine() + "\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    public Mono<Void> createFileAndWriteContent(String fileName, String contentToWrite) {
        return Mono.fromRunnable(writeFileLogic(fileName, contentToWrite));
    }

    private Runnable writeFileLogic(String fileName, String contentToWrite) {
        return () -> {
            System.out.println("entering logic for writing to a file");
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_DIRECTORY + fileName));
                bufferedWriter.write(contentToWrite);
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        };
    }
}
