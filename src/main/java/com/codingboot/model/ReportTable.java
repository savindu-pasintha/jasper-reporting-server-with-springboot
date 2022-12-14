package com.codingboot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report_table")
public class ReportTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "path")
    private String path;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "api_end_point")
    private String apiEndPoint;

    @Column(name = "client_folder_name")
    private String clientFolderName;

}