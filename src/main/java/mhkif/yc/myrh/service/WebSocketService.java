package mhkif.yc.myrh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public WebSocketService(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void sendNotificationToJobApplicant(final String topicSuffix , Object object){
        log.info("Job Seeker is about to notify job applicant in this topic :  "+"/topic/"+topicSuffix);
        this.simpMessagingTemplate.convertAndSend("/topic/"+topicSuffix,object);
    }
}
