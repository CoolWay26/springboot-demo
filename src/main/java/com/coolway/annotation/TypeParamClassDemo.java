package com.coolway.annotation;

public class TypeParamClassDemo<@TypeParamDemo T> {
    public <@TypeParamDemo T> T test1() {
        return null;
    }
}
