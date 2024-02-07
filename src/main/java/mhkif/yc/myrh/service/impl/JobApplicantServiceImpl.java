package mhkif.yc.myrh.service.impl;

import mhkif.yc.myrh.dto.requests.CompanyJobApplicantReq;
import mhkif.yc.myrh.dto.requests.JobApplicantReq;
import mhkif.yc.myrh.dto.requests.JobSeekerReq;
import mhkif.yc.myrh.dto.responses.JobApplicantRes;
import mhkif.yc.myrh.exception.BadRequestException;
import mhkif.yc.myrh.mapper.JobApplicantMapper;
import mhkif.yc.myrh.mapper.JobSeekerMapper;
import mhkif.yc.myrh.mapper.OfferMapper;
import mhkif.yc.myrh.model.JobApplicant;
import mhkif.yc.myrh.model.JobApplicantId;
import mhkif.yc.myrh.model.JobSeeker;
import mhkif.yc.myrh.repository.CompanyRepo;
import mhkif.yc.myrh.repository.JobApplicantRepo;
import mhkif.yc.myrh.repository.JobSeekerRepo;
import mhkif.yc.myrh.repository.OfferRepo;
import mhkif.yc.myrh.service.IJobApplicantService;
import mhkif.yc.myrh.service.JobApplicationChangesManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class JobApplicantServiceImpl implements IJobApplicantService {

    private final JobApplicantRepo repository;
    private final OfferRepo offerRepo;
    private final JobSeekerRepo jobSeekerRepo;
    private final JobApplicantMapper mapper;
    private final OfferMapper offerMapper;
    private final JobSeekerMapper jobSeekerMapper;
    private final CloudinaryService cloudinaryService;
    private final CompanyRepo companyRepo;
    private final JobApplicationChangesManager jobApplicationChangesManager;


    @Override
    public JobApplicantRes getById(JobApplicantId id) {
        JobApplicant jobApplicant = repository.findById(id).orElseThrow(() -> new IllegalStateException("Job Applicant not found"));
        return mapper.toRes(jobApplicant);
    }


    @Override
    public Page<JobApplicantRes> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).map(mapper::toRes);
    }


    @Override
    public List<JobApplicantRes> getAllByCompany(int companyId) {

        if (!companyRepo.existsById(companyId)) {
            throw new EntityNotFoundException("Company Not Found");
        }
        return repository.findAllByCompany(companyId).stream().map(
                // mapper::toRes,
                jobApp -> {
                    JobApplicantRes res = new JobApplicantRes();
                    res.setId(jobApp.getId());
                    res.setOffer(this.offerMapper.toRes(offerRepo.findById(jobApp.getId().getOffer_id()).get()));
                    res.setJobSeeker(this.jobSeekerMapper.toRes(jobSeekerRepo.findById(jobApp.getId().getJobSeeker_id()).get()));
                    res.setCreatedDate(jobApp.getCreatedDate());
                    res.setResume(jobApp.getResume());
                    res.setIsViewed(jobApp.getIsViewed());
                    res.setStatus(jobApp.getStatus());
                    return res;
                }
        ).toList();


}


    @Override
    public JobApplicantRes create(JobApplicantReq request) {
        if (!jobSeekerRepo.existsByEmail(request.getJobSeeker().getEmail())) {
            // throw new IllegalStateException("JobSeeker Not Exist");

            JobSeekerReq jobSeekerReq = new JobSeekerReq();
            jobSeekerReq.setFirst_name(request.getJobSeeker().getFirst_name());
            jobSeekerReq.setLast_name(request.getJobSeeker().getLast_name());
            jobSeekerReq.setEmail(request.getJobSeeker().getEmail());

            JobSeeker jobSeeker = jobSeekerRepo.save(jobSeekerMapper.reqToEntity(jobSeekerReq));

            if (!offerRepo.existsById(request.getId().getOffer_id())) {
                throw new EntityNotFoundException("Offer Not Exist");
            }

            JobApplicantId jobApplicantId = new JobApplicantId();
            jobApplicantId.setJobSeeker_id(jobSeeker.getId());
            jobApplicantId.setOffer_id(request.getId().getOffer_id());

            request.setId(jobApplicantId);

            JobApplicant jobApplicantMapped = mapper.reqToEntity(request);
            jobApplicantMapped.setResume(cloudinaryService.uploadFile(request.getResume(), "resumes"));

            JobApplicant jobApplicant = repository.save(jobApplicantMapped);


            return mapper.toRes(jobApplicant);
        }

        if (!offerRepo.existsById(request.getId().getOffer_id())) {
            throw new EntityNotFoundException("Offer Not Exist");
        }

        this.jobSeekerRepo.findByEmail(request.getJobSeeker().getEmail()).ifPresent(jobSeeker -> {
            JobApplicantId jobApplicantId = new JobApplicantId();
            jobApplicantId.setJobSeeker_id(jobSeeker.getId());
            jobApplicantId.setOffer_id(request.getId().getOffer_id());
            request.setId(jobApplicantId);
        });
        JobApplicant jobApplicantMapped = mapper.reqToEntity(request);
        jobApplicantMapped.setResume(cloudinaryService.uploadFile(request.getResume(), "resumes"));

        JobApplicant jobApplicant = repository.save(jobApplicantMapped);
        return mapper.toRes(jobApplicant);
    }

    @Override
    public JobApplicantRes updateStatus(CompanyJobApplicantReq req) {
        JobApplicantId id = new JobApplicantId();
        id.setJobSeeker_id(req.getJobSeekerId());
        id.setOffer_id(req.getOfferId());
        JobApplicant jobApplicant = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Job Applicant not found"));

        if (!companyRepo.existsById(req.getCompanyId())) {
            throw new EntityNotFoundException("Company Not Found");
        } else if (!offerRepo.existsById(req.getOfferId())) {
            throw new EntityNotFoundException("Offer Not Found");
        } else if (!jobSeekerRepo.existsById(req.getJobSeekerId())) {
            throw new EntityNotFoundException("JobSeeker Not Found");
        }else if(req.getCompanyId() == offerRepo.findById(req.getOfferId()).get().getCompany().getId()){        
            jobApplicant.setStatus(req.getStatus());
        } else {
            throw new BadRequestException("You Do not have permission update the offer's status ");
        }

        try {
            JobApplicantRes res = new JobApplicantRes();
            res.setId(jobApplicant.getId());
            res.setOffer(this.offerMapper.toRes(offerRepo.findById(id.getOffer_id()).get()));
            res.setJobSeeker(this.jobSeekerMapper.toRes(jobSeekerRepo.findById(id.getJobSeeker_id()).get()));
            res.setCreatedDate(jobApplicant.getCreatedDate());
            res.setResume(jobApplicant.getResume());
            res.setIsViewed(jobApplicant.getIsViewed());
            res.setStatus(jobApplicant.getStatus());

            //  : Notify Tha Applicant with the status of his Application Request
            //  : Now We need To notify The JobSeeker (Applicant)
            this.jobApplicationChangesManager.notifyJobApplication(res);


            return res;
        } catch (BadRequestException e) {
            throw new BadRequestException("Invalid Status");
        }
    }

    @Override
    public JobApplicantRes update(JobApplicantId id, JobApplicantRes request) {
        return null;
    }

    @Override
    public void deleteById(JobApplicantId id) {

    }
}
