package com.github.st0rm1O1.common;

@FunctionalInterface
public interface ImplProvider<T> {
    void execute(T value);
}