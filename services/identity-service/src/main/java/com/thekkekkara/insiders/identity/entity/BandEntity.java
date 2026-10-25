package com.thekkekkara.insiders.identity.entity;

import java.time.LocalDateTime;

public class BandEntity {

    private Long bandIdLong;
    private String bandNameString;
    private String bandDescriptionString;
    private LocalDateTime createdAtLocalDateTime;
    private LocalDateTime updatedAtLocalDateTime;

    public BandEntity() {
    }

    public BandEntity(
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

    public void setBandIdLong(Long bandIdLong) {
        this.bandIdLong = bandIdLong;
    }

    public String getBandNameString() {
        return this.bandNameString;
    }

    public void setBandNameString(String bandNameString) {
        this.bandNameString = bandNameString;
    }

    public String getBandDescriptionString() {
        return this.bandDescriptionString;
    }

    public void setBandDescriptionString(String bandDescriptionString) {
        this.bandDescriptionString = bandDescriptionString;
    }

    public LocalDateTime getCreatedAtLocalDateTime() {
        return this.createdAtLocalDateTime;
    }

    public void setCreatedAtLocalDateTime(LocalDateTime createdAtLocalDateTime) {
        this.createdAtLocalDateTime = createdAtLocalDateTime;
    }

    public LocalDateTime getUpdatedAtLocalDateTime() {
        return this.updatedAtLocalDateTime;
    }

    public void setUpdatedAtLocalDateTime(LocalDateTime updatedAtLocalDateTime) {
        this.updatedAtLocalDateTime = updatedAtLocalDateTime;
    }

}
