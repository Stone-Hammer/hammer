package com.stonehammer.hammer.serviceImpl;

import com.stonehammer.hammer.entity.Lives_news;
import com.stonehammer.hammer.repository.Lives_newsRepository;
import com.stonehammer.hammer.service.Lives_newsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Lives_newsServiceImpl implements Lives_newsService {

    @Autowired
    private Lives_newsRepository lives_newsRepository;

    @Override
    public Lives_news addLives(Lives_news lives){ return lives_newsRepository.save(lives); }

    @Override
    public List<Lives_news> getAllLives(){
//        return lives_newsRepository.findAll();
        return lives_newsRepository.findAllOrderByTimeDesc();
    }

    @Override
    public List<Lives_news> getLivesByIndex(int start_index, int length) {
        return lives_newsRepository.findAllOrderByTimeDesc(start_index,length);
    }

    @Override
    public List<Lives_news> getLivesByWords(String words) {
        return lives_newsRepository.findLives_newsByWords(words);
    }

    @Override
    public Lives_news updateLives(Lives_news lives){ return lives_newsRepository.save(lives); }

    @Override
    public void deleteLives(Integer lives_id){
//        lives_newsRepository.deleteById(lives_id);
        lives_newsRepository.deleteByLives_id(lives_id);
    }

    @Override
    public Lives_news getLivesById(Integer lives_id){ return lives_newsRepository.findById(lives_id).get();}

}
