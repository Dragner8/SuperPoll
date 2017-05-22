package com.example.service;

import java.util.Collection;

import com.example.model.Poll;
public interface PollService {
	
	Collection<Poll> findAll();
	
	Poll findOne(Long Id); 
	
	Poll create(Poll poll);
	
	Poll update(Poll poll);
	
	void delete(long Id);
}
