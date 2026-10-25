package com.thekkekkara.insiders.identity.domain;

import java.time.LocalDateTime;

public class Band {

    private final Long bandIdLong;
    private final String bandNameString;
    private final String bandDescriptionString;
    private final LocalDateTime createdAtLocalDateTime;
    private final LocalDateTime updatedAtLocalDateTime;

    public Band(
            Long bandIdLong,
            String bandNameString,
            String bandDescriptionString,
            LocalDateTime createdAtLocalDateTime,
            LocalDateTime updatedAtLocalDateTime) {
        this.bandIdLong = bandIdLong;
        this.bandNameString = bandNameString;
        this.bandDescriptionString = bandDescriptionString;
        this.createdAtLocalDateTime = createdAtLocalDateTime;
        this.updatedAtLocalDateTime = updatedAtLocalDateTime;
    }

    public Long getBandIdLong() {
        return this.bandIdLong;
    }

    public String getBandNameString() {
        return this.bandNameString;
    }

    public String getBandDescriptionString() {
        return this.bandDescriptionString;
    }

    public LocalDateTime getCreatedAtLocalDateTime() {
        return this.createdAtLocalDateTime;
    }

    public LocalDateTime getUpdatedAtLocalDateTime() {
        return this.updatedAtLocalDateTime;
    }

}
