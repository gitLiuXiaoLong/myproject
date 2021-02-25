package com.study.example.rest.demo.model;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class Person implements Serializable {

	private Long id;

	private String name;

	private String sex;

}
