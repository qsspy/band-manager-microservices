package com.qsspy.bands.domain.band.dto;

import java.util.UUID;

public record BandCreationData(
        UUID creatorId,
        String bandName
) { }
