package com.lincorp.openapi.generator;

import java.io.FileOutputStream;
import java.io.IOException;

import io.swagger.v3.oas.models.OpenAPI;

public class GeneratorRoot {
    private GeneratorRoot() {}

    public static void armeria(OpenAPI openAPI, String destPath) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(destPath)) {
            outputStream.write("".getBytes());

            // 1차 목표: 하나의 서비스 클래스 만들기
            // generate comment
            outputStream.write(Comment.gen(openAPI));
            // generate import
            outputStream.write(Import.gen(openAPI));
            // generate Class
            outputStream.write(Clazz.gen(openAPI));
            // generate Methos with annotation
        }
    }
}
