package mhkif.yc.myrh.controller;

import mhkif.yc.myrh.dto.requests.OfferReq;
import mhkif.yc.myrh.dto.responses.JobSeekerOfferInsightsResponse;
import mhkif.yc.myrh.dto.responses.OfferRes;
import mhkif.yc.myrh.enums.StudyLevel;
import mhkif.yc.myrh.service.IOfferInsightsService;
import mhkif.yc.myrh.service.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("myrh/api/v1/offers")
@CrossOrigin("*")
public class OfferController {
    private final IOfferService service;
    private final IOfferInsightsService insightsService;


    @Autowired
    public OfferController(IOfferService service, IOfferInsightsService insightsService) {

        this.service = service;
        this.insightsService = insightsService;
    }

    @PostMapping("")
    public ResponseEntity<OfferRes> save(@RequestBody OfferReq req) {
        OfferRes response = service.create(req);
        return ResponseEntity.ok(response);
    }

/*
    @GetMapping("")
    public ResponseEntity<Page<OfferRes>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }
 */
    @GetMapping("")
    public ResponseEntity<Page<OfferRes>>
    search(@RequestParam(required = false , defaultValue = "1") int page , @RequestParam(required = false,defaultValue = "10") int size,@RequestParam(required = false) String title, @RequestParam(required = false) String description, @RequestParam(required = false) String domain,@RequestParam(required = false) String city,@RequestParam(required = false) StudyLevel level,@RequestParam(required = false) String job) {
        return ResponseEntity.ok(service.
                search(page, size, title, description, domain, city, level, job)
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<OfferRes> get(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<OfferRes> update(@PathVariable int id, @RequestBody OfferRes offer) {
        OfferRes response = this.service.update(id, offer);
        return ResponseEntity.ok(response);
    }

    //Partial update visibility
    @PatchMapping("/{offerId}/visibility/{visibility}")
    public ResponseEntity<OfferRes> updateVisibility(
            @PathVariable int offerId, @PathVariable String visibility) {
        OfferRes response = this.service.updateVisibility(offerId, visibility);
        return ResponseEntity.ok(response);
    }

    //: Avoir des statistiques des offres d'emploi par candidats
    @GetMapping("/insights/jobSeeker/{candidateID}")
    public ResponseEntity<JobSeekerOfferInsightsResponse> getCandidatesOfferInsights(@PathVariable int candidateID) {
        return ResponseEntity.ok(this.insightsService.getCandidatesOfferInsights(candidateID));
    }
    @GetMapping("/insights/jobSeeker/company/{companyID}")
    public ResponseEntity<Collection<JobSeekerOfferInsightsResponse>> getAllCandidatesOfferInsights(
            @PathVariable String companyID,
            @RequestParam(required = false) Map<String,String> params
            ) {
        return ResponseEntity.ok(this.insightsService.getAllCandidatesOfferInsights(
                companyID, params));
    }




}
