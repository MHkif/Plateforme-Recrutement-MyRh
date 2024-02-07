package mhkif.yc.myrh.service;

import mhkif.yc.myrh.dto.responses.JobSeekerOfferInsightsResponse;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Map;

public interface IOfferInsightsService {

    //Avoir des statistiques des offres d'emploi par candidats

    JobSeekerOfferInsightsResponse getCandidatesOfferInsights(int seekerId);
    Page<JobSeekerOfferInsightsResponse> getCandidatesOfferInsights(int page, int size);
    Collection<JobSeekerOfferInsightsResponse> getAllCandidatesOfferInsights(String  id , Map<String,String> params );
}
