package com.lincorp.openapi;

import java.io.StringWriter;
import java.io.Writer;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public final class MustacheWriter {
    private static final MustacheFactory mf = new DefaultMustacheFactory();

    public static StringWriter write(String templateName, Object payload) {
        final StringWriter writer = new StringWriter();
        final Mustache mustache = mf.compile(templateName);
        return (StringWriter) mustache.execute(writer, payload);
    }
}
