package com.lincorp.openapi.generator;

import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.lincorp.openapi.MustacheWriter;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;

final class Import {
    private Import() {}

    static byte[] gen(OpenAPI openAPI) {
        StringWriter writer = new StringWriter();
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache lmport = mf.compile("");

        List httpMethodList = openAPI.getPaths()
                                             .values()
                                             .stream()
                                             .map(PathItem::readOperationsMap)
                                             .flatMap(httpMethodOperationMap ->
                                                              httpMethodOperationMap.keySet().stream())
                                             .map(Enum::name)
                                             .distinct()
                                             .map(str -> str.toUpperCase().charAt(0) + str.substring(1).toLowerCase())
                                             .map(str -> new Object(){
                                                 public String httpMethod = str;
                                             })
                                             .collect(Collectors.toList());

        return MustacheWriter.write("import.mustache", new Object() {
            public List httpMethods = httpMethodList;
        }).toString().getBytes();
    }
}
