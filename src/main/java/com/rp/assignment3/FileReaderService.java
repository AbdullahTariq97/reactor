package com.rp.assignment3;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class FileReaderService {

    private Callable<BufferedReader> openReader(Path path){
        return () -> Files.newBufferedReader(path);
    }

    private BiFunction<BufferedReader, SynchronousSink<String>, BufferedReader> read(){
        return (br,ss) ->{
            try {
                String line = br.readLine();
                System.out.println("reading --- " + line);
                if(!Objects.isNull(line)){
                    ss.next(line);
                } else {
                    ss.complete();
                }
            } catch (IOException e) {
                ss.error(e);
            }
            return br; // the same buffered reader gets returned to this lambda as argument as the one that was returned after doing readLine operation
            // therefore it moves to the next line in the next iteration
        };
    }

    private Consumer<BufferedReader> closeReader(){
        return bufferedReader -> {
            try {
                bufferedReader.close();
                System.out.println("closed file");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    public Flux<String> getFileContents(Path path){
        return Flux.generate(
                openReader(path),
                read(),
                closeReader()
        );
    }
}
