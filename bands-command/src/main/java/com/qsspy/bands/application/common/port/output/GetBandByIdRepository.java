package com.qsspy.bands.application.common.port.output;

import com.qsspy.bands.domain.band.Band;

import java.util.Optional;
import java.util.UUID;

public interface GetBandByIdRepository {

    /**
     * Fetches band by id
     *
     * @param bandId band identifier
     * @return band if exists
     */
    Optional<Band> findById(final UUID bandId);
}
