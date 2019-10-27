package com.lincorp.openapi.generator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.lincorp.openapi.MustacheWriter;

import io.swagger.v3.oas.models.OpenAPI;

final class Clazz {
    private Clazz() {}
    static final String templateName = "class.mustache";

    static byte[] gen(OpenAPI openAPI) {
        List<Object> methodList = openAPI.getPaths()
                              .entrySet()
                              .stream()
                              .flatMap(stringPathItemEntry -> stringPathItemEntry
                       .getValue()
                       .readOperationsMap()
                       .entrySet()
                       .stream()
                       .map(httpMethodOperationEntry -> {
                           final Map<String, String> map = new HashMap<>();
                           map.put("method", httpMethodOperationEntry.getKey().name());
                           map.put("operationId", httpMethodOperationEntry.getValue().getOperationId());
                           map.put("path", stringPathItemEntry.getKey());
                           return map;
                       }))
                              .map(map ->
                    new Object() {
                        public String method = map.get("method").toUpperCase().charAt(0) +
                                               map.get("method").substring(1).toLowerCase();
                        public String operationId = map.get("operationId");
                        public String path = map.get("path");
                    })
                              .collect(Collectors.toList());

        return MustacheWriter.write(templateName, new Object() {
            List<Object> methods = methodList;
        }).toString().getBytes();
    }
}
