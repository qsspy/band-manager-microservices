package com.qsspy.bands.infrastucture.port.repository;

import com.qsspy.bands.domain.band.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
interface JpaBandRepository extends JpaRepository<Band, UUID> {

    Optional<Band> findByIdValue(final UUID uuid);
}
