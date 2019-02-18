package com.stonehammer.hammer.serviceImpl;

import com.stonehammer.hammer.entity.Interest;
import com.stonehammer.hammer.repository.InterestRepository;
import com.stonehammer.hammer.service.InterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterestServiceImpl implements InterestService {
    @Autowired
    private InterestRepository interestRepository;

    @Override
    public Interest addInterest(Interest interest) {
        return interestRepository.save(interest);
    }

    @Override
    public List<Interest> getAllInterest() {
        return interestRepository.findAll();
    }

    @Override
    public Interest updateInterest(Interest interest) {
        return interestRepository.save(interest);
    }

    @Override
    public void deleteInterest(Integer interest_id) {
        interestRepository.deleteById(interest_id);
    }

    @Override
    public Interest getInterestById(Integer interest_id) {
        return interestRepository.findById(interest_id).get();
    }
}
