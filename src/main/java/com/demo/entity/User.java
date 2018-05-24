package com.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class User implements Serializable {
    private Long id;
    private String userName;
    private String password;
    private Date birthday;
}
