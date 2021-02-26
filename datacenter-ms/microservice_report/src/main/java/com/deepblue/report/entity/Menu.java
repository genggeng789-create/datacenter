package com.deepblue.report.entity;

import lombok.Data;

import java.util.List;

@Data
public class Menu{
    List<Menu> list;
    String src;
}
