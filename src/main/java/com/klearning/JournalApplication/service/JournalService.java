package com.klearning.JournalApplication.service;


import com.klearning.JournalApplication.entity.JournalEntity;
import com.klearning.JournalApplication.entity.User;
import com.klearning.JournalApplication.repository.JournalRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntity journalEntity) {
        journalRepository.save(journalEntity);
    }

    @Transactional
    public void saveEntry(JournalEntity journalEntity, String userName) {
        try{
            User user = userService.findByUserName(userName);
            journalEntity.setDate(LocalDateTime.now());
            JournalEntity saved = journalRepository.save(journalEntity);
            user.getJournalEntityList().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println("Exception_while_saving =" +e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<JournalEntity> getAll() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId Id) {
        return journalRepository.findById(Id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntityList().removeIf(x -> x.getId().equals(id));
            if(removed) {
                userService.saveUser(user);
                journalRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An Error occured while deleting the entry..!",e);
        }

        return removed;
    }

    public JournalEntity updateById(ObjectId id,JournalEntity myEntry) {
        return null;
    }

    public List<JournalEntity> findByUserName(String userName) {
      return  null;
    }

}
