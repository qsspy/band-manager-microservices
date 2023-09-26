package com.qsspy.bands.application.members.port.output.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record BandMemberDTO(
        UUID id,
        String email,
        String firstName,
        String lastName
) { }
