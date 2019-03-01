package com.stonehammer.hammer.serviceImpl;

import com.stonehammer.hammer.entity.Lives_detail;
import com.stonehammer.hammer.repository.Lives_detailRepository;
import com.stonehammer.hammer.service.Lives_detailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Lives_detailServiceImpl implements Lives_detailService {

    @Autowired
    private Lives_detailRepository lives_detailRepository;

    @Override
    public Lives_detail addLives_detail(Lives_detail lives_detail){
        return lives_detailRepository.save(lives_detail);
    }

    @Override
    public List<Lives_detail> getAllLives_detail(){
        return lives_detailRepository.findAll();
    }

    @Override
    public List<Lives_detail> findAllByLives_id(Integer lives_id){ return lives_detailRepository.findAllByLives_id(lives_id);}

    @Override
    public List<Lives_detail> getLives_detailByWords(String words) {
        return lives_detailRepository.findLives_detailByWords(words);
    }

    @Override
    public Lives_detail updateLives_detail(Lives_detail lives_detail){
        return lives_detailRepository.save(lives_detail);
    }

    @Override
    public void deleteLives_detail(Integer detail_id){
//        lives_detailRepository.deleteById(detail_id);
        lives_detailRepository.deleteByDetail_id(detail_id);
    }

    @Override
    public Lives_detail getDetailById(Integer detail_id){
        return lives_detailRepository.findById(detail_id).get();
    }
}
