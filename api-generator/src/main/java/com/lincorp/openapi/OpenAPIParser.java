package com.lincorp.openapi;

import org.springframework.stereotype.Service;

import com.lincorp.openapi.validator.ValidationRoot;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.parser.OpenAPIV3Parser;

@Service
public class OpenAPIParser {
    public OpenAPI openAPI(String filePath) throws Exception {
        return ValidationRoot.validate(new OpenAPIV3Parser().read(filePath));
    }
}
