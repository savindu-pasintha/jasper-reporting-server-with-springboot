package com.codingboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class V1Report {
    String device;
    String time;
    String state;
    String duration;
    String description;
}
