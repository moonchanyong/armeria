package com.lincorp.openapi.validator;

import java.util.Objects;

import io.swagger.v3.oas.models.OpenAPI;

public final class ValidationRoot {
    private ValidationRoot() {}

    public static OpenAPI validate(OpenAPI openAPI) throws Exception {
        Objects.requireNonNull(openAPI);

        /*
          각 Object의 Specification들이 validate
         */
        return openAPI;
    }
}

