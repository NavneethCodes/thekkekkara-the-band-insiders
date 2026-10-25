package com.thekkekkara.insiders.identity.dto.band;

import java.time.LocalDateTime;

public record BandResponse(
                Long bandIdLong,
                String bandNameString,
                String bandDescriptionString,
                LocalDateTime createdAtLocalDateTime,
                LocalDateTime updatedAtLocalDateTime) {

}
