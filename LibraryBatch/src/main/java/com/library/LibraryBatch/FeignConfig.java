package com.library.LibraryBatch;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;

@Configuration
public class FeignConfig {

	@Bean
	public BasicAuthRequestInterceptor mBasicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("utilisateur", "mdp");
	}

	@Bean
	public Decoder feignDecoder() {
		return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
	}

	public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
		final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(
				new PhpMappingJackson2HttpMessageConverter());
		return new ObjectFactory<HttpMessageConverters>() {
			@Override
			public HttpMessageConverters getObject() throws BeansException {
				return httpMessageConverters;
			}
		};
	}

	public class PhpMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
		PhpMappingJackson2HttpMessageConverter() {

			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

			this.setObjectMapper(objectMapper);

			List<MediaType> mediaTypes = new ArrayList<>();
			mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
			mediaTypes.add(MediaType.APPLICATION_JSON);
			
			setSupportedMediaTypes(mediaTypes);
		}
	}

}
