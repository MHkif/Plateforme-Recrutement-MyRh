package mhkif.yc.myrh.service.impl;

import mhkif.yc.myrh.service.JobApplicationChangesManager;
import mhkif.yc.myrh.service.JobSeekerSubscriber;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class JobApplicationChangesManagerImpl implements JobApplicationChangesManager {

    private final List<JobSeekerSubscriber> jobSeekerSubscribers = new ArrayList<>();
    @Override
    public void addJobApplication(JobSeekerSubscriber jobSeekerSubscriber) {
        this.jobSeekerSubscribers.add(jobSeekerSubscriber);
    }

    @Override
    public void removeJobApplication() {

    }

    @Override
    public void notifyJobApplication(Object object) {
        for (JobSeekerSubscriber jobSeekerSubscriber : jobSeekerSubscribers) {
            jobSeekerSubscriber.handleNotification(object);
        }
    }
}
