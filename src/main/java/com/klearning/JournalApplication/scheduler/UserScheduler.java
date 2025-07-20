package com.klearning.JournalApplication.scheduler;

import com.klearning.JournalApplication.cache.AppCache;
import com.klearning.JournalApplication.entity.JournalEntity;
import com.klearning.JournalApplication.entity.User;
import com.klearning.JournalApplication.service.EmailService;
import com.klearning.JournalApplication.service.SentimentAnalysisService;
import com.klearning.JournalApplication.service.UserRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appCache;

    @Scheduled(cron = "0 0 9 * * SUN")
    public void fetchUsersAndSendSaEmail() {
        List<User> users = userRepository.getUserSA();
        for(User user : users) {
            List<JournalEntity> journalEntityList = user.getJournalEntityList();
            List<String> filteredEntriesList = journalEntityList.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x ->  x.getContent()).collect(Collectors.toList());
            String entry = String.join((CharSequence) " ", (CharSequence) filteredEntriesList);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "Sentiment for last 7 days", sentiment);
        }
    }

    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        appCache.init();
    }
}
