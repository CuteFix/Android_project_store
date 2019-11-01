package com.example.projectthursday.Items;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilterItem {

    private String name;
    private List<FilterUnderItem> filterUnderItems;

}
