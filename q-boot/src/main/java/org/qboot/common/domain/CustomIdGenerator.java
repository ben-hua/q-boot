package org.qboot.common.domain;

import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@IdGeneratorType(UUIDv7Generator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface CustomIdGenerator {
    String type(); // false : return String
}