package com.qsspy.bands.application.members.port.output.dto;

import java.util.UUID;

public record BandMemberDTO(
        UUID id,
        String email,
        String firstName,
        String lastName
) { }
