package com.study.example.rest.demo.HttpMessages;

import com.study.example.rest.demo.model.Person;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Properties;

public class PropertyPersonHttpMessageConverter extends AbstractHttpMessageConverter<Person> {

	public PropertyPersonHttpMessageConverter (){
		super(MediaType.valueOf("application/properties+person"));
		setDefaultCharset(Charset.forName("UTF-8"));
	}

	@Override
	protected boolean supports (Class<?> aClass) {
		return aClass.isAssignableFrom(Person.class);
	}

	/**
	 * 将请求内容中的properties 转成 pserson
	 * @param aClass
	 * @param httpInputMessage
	 * @return
	 * @throws IOException
	 * @throws HttpMessageNotReadableException
	 */
	@Override
	protected Person readInternal (Class<? extends Person> aClass,
								   HttpInputMessage httpInputMessage)
			throws IOException, HttpMessageNotReadableException {
		InputStream inputStream = httpInputMessage.getBody();

		//将请求中的内容转换成properties
		Properties properties = new Properties();
		properties.load(new InputStreamReader(inputStream,getDefaultCharset()));
		//将propert转换成person
		Person pserson = new Person();
		pserson.setId(Long.valueOf(properties.getProperty("person.id")));
		pserson.setName(properties.getProperty("person.name"));
		return pserson;
	}

	@Override
	protected void writeInternal (Person person,
								  HttpOutputMessage httpOutputMessage)
			throws IOException, HttpMessageNotWritableException {

		OutputStream outputStream = httpOutputMessage.getBody();
		Properties properties = new Properties();
		properties.setProperty("person.id",String.valueOf(person.getId()));
		properties.setProperty("person.name",person.getName());

		properties.store(new OutputStreamWriter(outputStream,getDefaultCharset()),"written by web server");
	}


}
