package mhkif.yc.myrh.controller;


import lombok.RequiredArgsConstructor;
import mhkif.yc.myrh.config.security.authenticators.AuthenticatedApplicant;
import mhkif.yc.myrh.config.security.jwt.JwtService;
import mhkif.yc.myrh.dto.HttpRes;
import mhkif.yc.myrh.dto.requests.AuthReq;
import mhkif.yc.myrh.dto.requests.JobSeekerReq;
import mhkif.yc.myrh.dto.responses.JobSeekerRes;
import mhkif.yc.myrh.dto.responses.QuestionRes;
import mhkif.yc.myrh.mapper.JobSeekerMapper;
import mhkif.yc.myrh.model.Question;
import mhkif.yc.myrh.service.IJobSeekerFilterService;
import mhkif.yc.myrh.service.IJobSeekerService;
import jakarta.validation.Valid;
import mhkif.yc.myrh.service.IQuestionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("myrh/api/v1/jobSeekers")
@RequiredArgsConstructor
public class JobSeekerController {

    private final IJobSeekerService service;
    private final IJobSeekerFilterService jobSeekerFilterService;
    private final IQuestionService questionService;
    private final JwtService jwtService;
    private final JobSeekerMapper mapper;


    @PostMapping("/auth/register")
    public ResponseEntity<HttpRes> save(@Valid @RequestBody JobSeekerReq req) {

        JobSeekerRes response = service.create(req);
        AuthenticatedApplicant authenticatedEntity = new AuthenticatedApplicant(mapper.resToEntity(response));
        var token = jwtService.generateToken(authenticatedEntity);
        return ResponseEntity.created(URI.create("")).body(
                HttpRes.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .statusCode(HttpStatus.CREATED.value())
                        .path("myrh/api/v1/jobSeekers")
                        .status(HttpStatus.CREATED)
                        .message("JobSeeker has been Created")
                        .developerMessage("JobSeeker  has been Created")
                        .data(Map.of("response", response, "token", token))
                        .build()
        );
    }

    @PostMapping("/auth/login")
    public ResponseEntity<HttpRes> auth(@Valid @RequestBody AuthReq auth){
        JobSeekerRes response = service.auth(auth.getEmail(), auth.getPassword());
        AuthenticatedApplicant authenticatedEntity = new AuthenticatedApplicant(mapper.resToEntity(response));
        var token = jwtService.generateToken(authenticatedEntity);

        return ResponseEntity.accepted().body(
                HttpRes.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .path("myrh/api/v1/jobSeekers")
                        .status(HttpStatus.ACCEPTED)
                        .message("JobSeeker has been authenticated")
                        .developerMessage("JobSeeker  has been authenticated")
                        .data(Map.of("response", response, "token", token))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<Page<JobSeekerRes>> getAll(@RequestParam int page, @RequestParam int size ) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<JobSeekerRes>> filterAll(
            @RequestParam  Map<String,String> params) {
        return ResponseEntity.ok(jobSeekerFilterService.filterAll(params));
    }



    @GetMapping("{id}")
    public ResponseEntity<JobSeekerRes> get(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
        
    }

    @GetMapping("test-verifier/{profileId}")
    public ResponseEntity<HttpRes> testVerifier(@Valid @PathVariable int profileId){
        List<QuestionRes> questions = questionService.getQuestionsByProfile(profileId);
        return ResponseEntity.accepted().body(
                HttpRes.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .path("myrh/api/v1/jobSeekers/test-verifier")
                        .status(HttpStatus.ACCEPTED)
                        .message("Questions has been loaded")
                        .developerMessage("Questions has been loaded")
                        .data(Map.of("response", questions))
                        .build()
        );
    }
    
}
