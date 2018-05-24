package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class User implements Serializable {
    private Long id;
    private String userName;
    private String password;
    private Timestamp birthday;
}
