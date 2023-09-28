package com.qsspy.gateway.bands.command;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

//@Component
@Slf4j
class NoopBandsCommandConnector implements BandsCommandConnector {


    @Override
    public void createBand(UUID userId, CreateBandRequestBody request) {
        log.info("Calling 'createBand' method");
    }

    @Override
    public void changeBandDefaultPrivileges(UUID bandId, ChangeBandPrivilegesRequestBody request) {
        log.info("Calling 'changeBandDefaultPrivileges' method");
    }

    @Override
    public void changeBandMemberPrivileges(UUID bandId, UUID memberId, ChangeBandMemberPrivilegesRequestBody request) {
        log.info("Calling 'changeBandMemberPrivileges' method");
    }

    @Override
    public void addBandMember(UUID bandId, AddBandMemberRequestBody request) {
        log.info("Calling 'addBandMember' method");
    }

    @Override
    public void removeMemberFromBand(UUID bandId, RemoveBandMemberRequestBody request) {
        log.info("Calling 'removeMemberFromBand' method");
    }
}
