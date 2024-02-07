package mhkif.yc.myrh.specifications;

import mhkif.yc.myrh.enums.StudyLevel;
import mhkif.yc.myrh.model.Offer;
import org.springframework.data.jpa.domain.Specification;

public class OfferSpecifications {

    public static Specification<Offer> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%"+title.toLowerCase()+"%");

    }

    public static Specification<Offer> hasDescription(String description) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%"+description.toLowerCase()+"%");
    }

    public static Specification<Offer> hasDomain(String domain) {
        return (root, query, criteriaBuilder) ->
        criteriaBuilder.like(criteriaBuilder.lower(root.get("profile").get("description")), "%" + domain.toLowerCase() + "%");
    }

    public static Specification<Offer> hasCity(String city) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("city").get("name")), city.toLowerCase());
    }


    public static Specification<Offer> hasLevel(StudyLevel level) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("level"), level);
    }

    public static Specification<Offer> hasJob(String job) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(criteriaBuilder.lower(root.get("job").get("description")), job.toLowerCase());
    }


    // Add more specifications as needed
}