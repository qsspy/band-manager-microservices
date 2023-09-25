package com.qsspy.bands.application.common.port.output;


import com.qsspy.bands.domain.band.Band;

public interface BandSaveRepository {

    /**
     * Saves band in repository
     *
     * @param band band to be saved
     */
    void save(final Band band);
}
