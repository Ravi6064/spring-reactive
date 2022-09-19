/**
 * 
 */
package com.reactive.vidoestreaming.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.vidoestreaming.service.VideoStreamingService;

import reactor.core.publisher.Mono;

/**
 * @author B0216853
 *
 */
@RestController
public class VideoStreamingController {
	
	
	@Autowired
	private VideoStreamingService videoStreamingService;
	
	@GetMapping(value = "/video/{title}", produces = "video/mp4")
	public Mono<Resource> getVideoStreaming(@PathVariable String title, @RequestHeader("Range") String bytes){
		System.out.println("Bytes info " +bytes);
		return videoStreamingService.getVideoStreaming(title);
	}

}
