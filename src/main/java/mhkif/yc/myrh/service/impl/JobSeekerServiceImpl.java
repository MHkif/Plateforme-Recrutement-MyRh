package mhkif.yc.myrh.service.impl;

import mhkif.yc.myrh.dto.requests.JobSeekerReq;
import mhkif.yc.myrh.dto.responses.JobSeekerRes;
import mhkif.yc.myrh.enums.UserStatus;
import mhkif.yc.myrh.exception.BadRequestException;
import mhkif.yc.myrh.exception.InternalServerException;
import mhkif.yc.myrh.mapper.JobSeekerMapper;
import mhkif.yc.myrh.model.JobSeeker;
import mhkif.yc.myrh.repository.JobSeekerRepo;
import mhkif.yc.myrh.service.IJobSeekerFilterService;
import mhkif.yc.myrh.service.IJobSeekerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;


@Service
public class JobSeekerServiceImpl implements IJobSeekerService , IJobSeekerFilterService {

    //: IT'S SO MUCH BETTER TO NAME THE JOB SEEKER REPOSITORY ->JobSeekerRepository than repository
    private final JobSeekerRepo repository;

    private final JobSeekerMapper mapper;

    @Autowired
    public JobSeekerServiceImpl(JobSeekerRepo repository, JobSeekerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public JobSeekerRes getById(Integer id) {
        JobSeeker jobSeeker = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("jobSeeker not found"));
        return mapper.toRes(jobSeeker);
    }

    @Override
    public Page<JobSeekerRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }

    @Override
    public JobSeekerRes create(JobSeekerReq request) {
        if (!repository.existsByEmail(request.getEmail())) {
            JobSeeker jobSeeker = repository.save(mapper.reqToEntity(request));
            return mapper.toRes(jobSeeker);
        }

        JobSeeker jobSeeker = repository.findByEmail(request.getEmail()).get();
        if (!jobSeeker.isEnabled()) {
            jobSeeker.setPassword(request.getPassword());
            jobSeeker.setImage(request.getImage());
            repository.save(jobSeeker);
            return mapper.toRes(jobSeeker);
        }else {
            throw new InternalServerException("Email Already Taken");
        }
    }

    @Override
    public JobSeekerRes auth(String email, String password) {
        JobSeeker jobSeeker = repository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("JobSeeker not found with this email"));
        if (!Objects.equals(jobSeeker.getPassword(), password)) {
            throw new BadRequestException("Incorrect Password");
        }
        return mapper.toRes(jobSeeker);
    }

    @Override
    public JobSeekerRes update(Integer id, JobSeekerRes request) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Page<JobSeekerRes> filterAll(Map<String, String> params) {
        //  : 7-01-2024 filter jobSeeker by their state online or offline ?status=online/offline

        //  String status= params.getOrDefault("status".toLowerCase(),"online");
        String status= params.containsKey("status".toLowerCase())?params.get("status").toUpperCase():"";
        int page = Integer.parseInt(params.getOrDefault("page","0"));
        int size = Integer.parseInt(params.getOrDefault("size","10"));


        try{
            UserStatus userStatus = UserStatus.valueOf(status);
            return repository.getAllByStatus(userStatus,PageRequest.of(page,size)).map(mapper::toRes);
        }catch (Exception e){
            throw new IllegalStateException("Illegal user status used to filter jobSeekers {ONLINE, OFFLINE , BUSY ,ALL}");
        }

    }
}
