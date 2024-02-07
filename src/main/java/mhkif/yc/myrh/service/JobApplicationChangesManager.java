package mhkif.yc.myrh.service;

public interface JobApplicationChangesManager {
    void addJobApplication(JobSeekerSubscriber jobSeekerSubscriber);
    void removeJobApplication();
    void notifyJobApplication(Object object);

}
