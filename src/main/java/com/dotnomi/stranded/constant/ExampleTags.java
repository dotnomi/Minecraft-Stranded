package com.dotnomi.stranded.constant;

public enum ExampleTags {
    NUMBER("number"),
    STRING("string");

    private final String value;

    ExampleTags(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
