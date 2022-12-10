package com.rp.sec03;

import com.rp.assignment3.FileReaderService;
import com.rp.courseutil.Util;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Lec09FileReaderServiceAssignment {
    public static void main(String[] args) {
        FileReaderService fileReaderService = new FileReaderService();

        Path path = Paths.get("src/main/resources/assignment/sec01/read.txt");
        fileReaderService.getFileContents(path).subscribe(Util.subscriber());


    }
}
