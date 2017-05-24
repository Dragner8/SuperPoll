  package com.example.web.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.example.model.*;
import com.example.service.PollService;




@RestController
public class PollControler {
	
	@Autowired
	private PollService pollService;
	
	@RequestMapping(value="/api/polls", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Poll>> getPolls(){
		
		Collection<Poll> polls = pollService.findAll();
		return new ResponseEntity<Collection<Poll>>(polls, HttpStatus.OK); 
	}
	
	@RequestMapping(value="/api/polls/{id}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poll> getPoll(@PathVariable("id") Long id){
		
		Poll poll = pollService.findOne(id);
		if(poll == null){
			return new ResponseEntity<Poll>(HttpStatus.NOT_FOUND);
		}
	
		return new ResponseEntity<Poll>(poll, HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/api/polls", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poll> createPoll(@RequestBody Poll poll){
		
		Poll savedPoll = pollService.create(poll);
		return new ResponseEntity<Poll>(savedPoll, HttpStatus.CREATED);
 	}
	
	@RequestMapping(value="/api/polls/{id}", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poll> updatePoll(@RequestBody Poll poll){
		Poll updatedPoll=pollService.update(poll);
		if(updatedPoll == null){
			return new ResponseEntity<Poll>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	return new ResponseEntity<Poll>(poll, HttpStatus.OK); 
	}
	
	
	@RequestMapping(value="/api/polls/{id}", method = RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Poll> deletePoll(@PathVariable Long id){
		 
		pollService.delete(id);
	
		return new ResponseEntity<Poll>(HttpStatus.NO_CONTENT);
	
	}
	
	
	
}
