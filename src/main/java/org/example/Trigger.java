package org.example;

import org.example.model.Direction;
import org.example.model.Matrix;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Trigger {

    public static void main(String[] args) throws IOException {


        Matrix invader1 = new Matrix(Files.readAllLines(new File("src/main/resources/invader-1.txt").toPath(), StandardCharsets.UTF_8));
        Matrix invader2 = new Matrix(Files.readAllLines(new File("src/main/resources/invader-2.txt").toPath(), StandardCharsets.UTF_8));

        System.out.println(invader1);

        System.out.println(invader2);

    }
}
