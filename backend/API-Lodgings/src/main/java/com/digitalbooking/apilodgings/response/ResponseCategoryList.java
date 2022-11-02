package com.digitalbooking.apilodgings.response;

import com.digitalbooking.apilodgings.entity.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseCategoryList {

    private final int count;
    private final List<Category> categories;

    public ResponseCategoryList(int count, List<Category> categories) {
        this.count = count;
        this.categories = categories;
    }

    public ResponseCategoryList(){
        count = 0;
        categories = new ArrayList<>();
    }
}
