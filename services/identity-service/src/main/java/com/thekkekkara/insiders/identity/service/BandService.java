package com.thekkekkara.insiders.identity.service;

import com.thekkekkara.insiders.identity.domain.Band;

public interface BandService {

    Band createBand(Band band);

    Band getBandById(Long bandIdLong);

    Band getBandByName(String bandNameString);

    boolean doesBandExistByBandName(String bandNameString);

    Band updateBand(Band band);

    void deleteBandById(Long bandIdLong);
}