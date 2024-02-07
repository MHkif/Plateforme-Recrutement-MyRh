package mhkif.yc.myrh.repository;

import mhkif.yc.myrh.enums.OfferStatus;
import mhkif.yc.myrh.model.Company;
import mhkif.yc.myrh.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;

public interface OfferRepo extends JpaRepository<Offer, Integer>, JpaSpecificationExecutor<Offer> {

    Page<Offer> findAllByStatus( Pageable pageable, OfferStatus status, Specification<Offer> spec );

    Collection<Offer> findAllByCompany(Company company);
}
