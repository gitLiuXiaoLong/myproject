package com.study.example.rest.demo.controller;

import com.study.example.rest.demo.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PsersonController {

	@GetMapping("/pserson/{id}")
	public Person getPserson(@PathVariable Long id,
							 @RequestParam(required = false) String name) {
		Person p = new Person();
		p.setId(id);
		p.setName(name);
		return p;
	}


	@PostMapping(value = "/person/jsonToProperties",
		consumes = MediaType.APPLICATION_JSON_VALUE,	//请求类型
		produces = "application/properties+person" //返回类型
			 )
	public Person personJsonToProperties(@RequestBody Person person) {
		// @RequestBody 为json
		// 响应的类型为properties
		return person;
	}

	@PostMapping(value = "/person/propertiesToJson",
			produces = MediaType.APPLICATION_JSON_VALUE,	//请求类型
			consumes = "application/properties+person" //返回类型
	)
	public Person personPropertiesToJson(@RequestBody Person person) {
		// @RequestBody 为json
		// 响应的类型为properties
		return person;
	}

	/**
	 * 404 处理
	 * @param status
	 * @param request
	 * @param throwable
	 * @return
	 */
	@GetMapping("/404.html")
	public Object handlePageNotFound(HttpStatus status,
									 HttpServletRequest request,
									 Throwable throwable) {
		Map<String,Object> error = new HashMap<>();
		error.put("statusCode",request.getAttribute("javax.servlet.error.status_code"));
		error.put("requestUri",request.getAttribute("javax.servlet.error.request_uri"));
		return error;
	}
}
