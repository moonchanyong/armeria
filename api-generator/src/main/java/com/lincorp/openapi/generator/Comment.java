package com.lincorp.openapi.generator;

import com.lincorp.openapi.MustacheWriter;

import io.swagger.v3.oas.models.OpenAPI;

final class Comment {
    private Comment() {}

    static byte[] gen(OpenAPI openAPI) {
        return MustacheWriter.write("comment.mustache", new Object() {
            public String version = openAPI.getOpenapi();
            public String applicationName = openAPI.getInfo().getTitle();
            public String docVersion = openAPI.getInfo().getVersion();
        }).toString().getBytes();
    }
}
