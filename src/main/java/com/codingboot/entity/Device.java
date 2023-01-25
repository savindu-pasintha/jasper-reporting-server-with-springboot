package com.codingboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    int id;
    int parent;
    String name;
    String token;
    String enable;

    String device;
    String time;
    String state;
    String duration;
    String description;
}
