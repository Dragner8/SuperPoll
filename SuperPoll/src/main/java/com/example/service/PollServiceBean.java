package com.example.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


import org.springframework.stereotype.Service;

import com.example.model.Poll;

@Service
public class PollServiceBean implements PollService {
	
	private static Long nextId;
	private static Map<Long, Poll> pollMap;
	
	private static Poll save(Poll poll){
		if(pollMap == null){
			pollMap = new HashMap<Long, Poll>();
			nextId = new Long(1);	
		}
		// if update..
		if(poll.getId() != null){
			Poll oldPerson = pollMap.get(poll.getId());
			if(oldPerson == null){
				return null;
			}
			pollMap.remove(poll.getId());
			pollMap.put(poll.getId(), poll);
			return poll;
		}
		//if create.. 
		poll.setId(nextId);
		nextId += 1;
		pollMap.put(poll.getId(), poll);
		return poll;
	}
	
	
	private static boolean remove(Long id){
		Poll deletedPoll = pollMap.remove(id);
		
		if(deletedPoll ==null){
			return false;
		}
		
		return true;
		
	}
	static{
		Poll p1 = new Poll();
		p1.setText("czy lubisz banany?");
		save(p1);
		
		Poll p2 = new Poll();
		p2.setText("jakiego koloru jest czarny dugopis?");
		save(p2);
		Poll p3 = new Poll();
		p3.setText("");
		save(p3);
	}
	
	@Override
	public Collection<Poll> findAll() {
		Collection<Poll> polls = pollMap.values();
		return polls;
	}
	@Override
	public Poll findOne(Long id) {
		Poll poll = pollMap.get(id);
		return poll;
	}

	@Override
	public Poll create(Poll poll) {
		Poll savedPoll = save(poll);
		return savedPoll;
	}

	@Override
	public Poll update(Poll poll) {
		Poll updatedPoll=save(poll);
		return updatedPoll;
	}

	@Override
	public void delete(long id) {
		remove(id);
	}

}
