package com.rp.assignment1;

import com.rp.courseutil.Util;

public class Runner {
    public static void main(String[] args) {
        FileService fileService = new FileService();
        fileService.readFileAndReturnContent("read.txt").subscribe(System.out::println, Util.onError(), Util.onComplete());

        fileService.createFileAndWriteContent("write.txt", "Hello my name is Abdullah").subscribe(System.out::println, Util.onError(), Util.onComplete());
    }
}