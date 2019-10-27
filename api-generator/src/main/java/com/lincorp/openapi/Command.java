package com.lincorp.openapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;

import com.lincorp.openapi.generator.GeneratorRoot;

@Component
@ShellComponent
public class Command {

    private final OpenAPIParser openAPIParser;

    @Autowired
    public Command(OpenAPIParser parser) {
        openAPIParser = parser;
    }

    @ShellMethod("Generate AnnotatedService by your OpenAPI document.")
    public String gen(
            @ShellOption String path,
            @ShellOption String dstPath
    ) {
        try {
            GeneratorRoot.armeria(openAPIParser.openAPI(path), dstPath);
            return "done.";
        } catch (Exception e) {
            return "Something is worong." + e;
        }
    }
}
