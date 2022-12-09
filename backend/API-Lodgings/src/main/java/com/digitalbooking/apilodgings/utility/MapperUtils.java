package com.digitalbooking.apilodgings.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class MapperUtils {

    public static <T1, T2> void mapListEntity(List<T1> rawListEntity, List<T2> convertedListEntity, Class<T2> classType, ObjectMapper mapper) {

        for (T1 item : rawListEntity) {
            convertedListEntity.add(mapper.convertValue(item, classType));
        }
    }

    public static <T1, T2> T2 mapEntity(T1 rawEntity, Class<T2> classType, ObjectMapper mapper) {
        return mapper.convertValue(rawEntity, classType);
    }

}
