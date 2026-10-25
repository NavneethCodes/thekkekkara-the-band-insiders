
package com.thekkekkara.insiders.identity.repository.mapper;

import org.springframework.stereotype.Component;

import com.thekkekkara.insiders.identity.domain.Band;
import com.thekkekkara.insiders.identity.dto.band.BandResponse;
import com.thekkekkara.insiders.identity.dto.band.CreateBandRequest;
import com.thekkekkara.insiders.identity.entity.BandEntity;

@Component
public class BandRowMapper {

    public Band toDomain(CreateBandRequest request) {
        return new Band(
                null,
                request.bandNameString(),
                request.bandDescriptionString(),
                null,
                null);
    }

    public Band toDomain(BandEntity entity) {
        return new Band(
                entity.getBandIdLong(),
                entity.getBandNameString(),
                entity.getBandDescriptionString(),
                entity.getCreatedAtLocalDateTime(),
                entity.getUpdatedAtLocalDateTime());
    }

    public BandEntity toEntity(Band band) {
        return new BandEntity(
                band.getBandIdLong(),
                band.getBandNameString(),
                band.getBandDescriptionString(),
                band.getCreatedAtLocalDateTime(),
                band.getUpdatedAtLocalDateTime());
    }

    public BandResponse toResponse(Band band) {
        return new BandResponse(
                band.getBandIdLong(),
                band.getBandNameString(),
                band.getBandDescriptionString(),
                band.getCreatedAtLocalDateTime(),
                band.getUpdatedAtLocalDateTime());
    }
}
