package com.special_lecture.common.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DtoConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convert(Object source, Class<T> targetClass) {
        return objectMapper.convertValue(source, targetClass);
    }
}