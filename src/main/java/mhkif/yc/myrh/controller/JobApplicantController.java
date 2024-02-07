package mhkif.yc.myrh.controller;

import mhkif.yc.myrh.dto.HttpRes;
import mhkif.yc.myrh.dto.requests.CompanyJobApplicantReq;
import mhkif.yc.myrh.dto.requests.JobApplicantReq;
import mhkif.yc.myrh.dto.responses.JobApplicantRes;
import mhkif.yc.myrh.model.JobApplicantId;
import mhkif.yc.myrh.service.IJobApplicantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("myrh/api/v1/jobApplicants")
@CrossOrigin("*")
@RequiredArgsConstructor
public class JobApplicantController {

    private final IJobApplicantService service;

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JobApplicantRes> save(@ModelAttribute @Valid JobApplicantReq req) {
        JobApplicantRes response = service.create(req);
        return ResponseEntity.ok(response);
    }

    @GetMapping("")
    public ResponseEntity<Page<JobApplicantRes>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("companies/{companyId}")
    public ResponseEntity<List<JobApplicantRes>> getAllByCompany(@PathVariable int companyId) {
        return ResponseEntity.ok(service.getAllByCompany(companyId));
    }


    @GetMapping("{offerId}/{jobSeekerId}")
    public ResponseEntity<JobApplicantRes> get(@PathVariable int offerId, @PathVariable int jobSeekerId) {
        JobApplicantId id = new JobApplicantId();
        id.setOffer_id(offerId);
        id.setJobSeeker_id(jobSeekerId);
        return ResponseEntity.ok(service.getById(id));
    }

    @PatchMapping("/companies/status")
    public ResponseEntity<HttpRes> updateStatus(
            @RequestBody CompanyJobApplicantReq req) {
        JobApplicantRes response = this.service.updateStatus(req);
        return ResponseEntity.accepted().body(
                HttpRes.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .path("myrh/api/v1/jobApplicants")
                        .status(HttpStatus.ACCEPTED)
                        .message("jobApplicant has been Updated")
                        .developerMessage("jobApplicant  has been Updated")
                        .data(Map.of("response", response))
                        .build()
        );
    }

}
