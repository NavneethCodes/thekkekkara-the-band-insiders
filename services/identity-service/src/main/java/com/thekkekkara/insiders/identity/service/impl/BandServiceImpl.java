package com.thekkekkara.insiders.identity.service.impl;

import org.springframework.stereotype.Service;

import com.thekkekkara.insiders.identity.domain.Band;
import com.thekkekkara.insiders.identity.entity.BandEntity;
import com.thekkekkara.insiders.identity.exception.ResourceNotFoundException;
import com.thekkekkara.insiders.identity.repository.BandRepository;
import com.thekkekkara.insiders.identity.repository.mapper.BandRowMapper;
import com.thekkekkara.insiders.identity.service.BandService;

@Service
public class BandServiceImpl implements BandService {

    private final BandRepository repository;
    private final BandRowMapper bandMapper;

    public BandServiceImpl(
            BandRepository repository,
            BandRowMapper bandMapper) {
        this.repository = repository;
        this.bandMapper = bandMapper;
    }

    @Override
    public Band createBand(Band band) {

        if (doesBandExistByBandName(band.getBandNameString())) {
            throw new IllegalArgumentException("Band already exists.");
        }

        Band newBand = new Band(
                null,
                band.getBandNameString(),
                band.getBandDescriptionString(),
                null,
                null);

        BandEntity entity = this.bandMapper.toEntity(newBand);

        BandEntity savedEntity = this.repository.save(entity);

        return this.bandMapper.toDomain(savedEntity);
    }

    @Override
    public boolean doesBandExistByBandName(String bandNameString) {
        return this.repository.existsByBandName(bandNameString);
    }

    @Override
    public Band getBandById(Long bandIdLong) {

        return this.repository.findById(bandIdLong)
                .map(this.bandMapper::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Band not found with ID: " + bandIdLong));
    }

    @Override
    public Band getBandByName(String bandNameString) {

        return this.repository.findByBandName(bandNameString)
                .map(this.bandMapper::toDomain)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Band not found with the name: " + bandNameString));
    }

    @Override
    public Band updateBand(Band band) {

        if (band.getBandIdLong() == null) {
            throw new IllegalArgumentException(
                    "Band ID must be provided for update.");
        }

        this.getBandById(band.getBandIdLong());

        BandEntity entity = this.bandMapper.toEntity(band);

        BandEntity updatedEntity = this.repository.update(entity);

        return this.bandMapper.toDomain(updatedEntity);
    }

    @Override
    public void deleteBandById(Long bandIdLong) {

        this.getBandById(bandIdLong);

        this.repository.deleteById(bandIdLong);
    }
}