package com.reactive.vidoestreaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class VideoStreamingService {
	
	String FORMAT ="classpath:videos/%s.mp4";
	
	@Autowired
	private ResourceLoader resourceLoader;

	public Mono<Resource> getVideoStreaming(String title) {
		return Mono.fromSupplier(() -> resourceLoader.getResource(String.format(FORMAT, title)));
	}
	
	
	

}
