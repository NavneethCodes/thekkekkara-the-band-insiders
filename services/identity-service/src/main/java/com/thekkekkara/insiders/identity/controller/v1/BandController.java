package com.thekkekkara.insiders.identity.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thekkekkara.insiders.identity.domain.Band;
import com.thekkekkara.insiders.identity.dto.band.BandResponse;
import com.thekkekkara.insiders.identity.dto.band.CreateBandRequest;
import com.thekkekkara.insiders.identity.repository.mapper.BandRowMapper;
import com.thekkekkara.insiders.identity.service.BandService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/bands")
public class BandController {

    private final BandService service;
    private final BandRowMapper mapper;

    public BandController(BandService service, BandRowMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<BandResponse> createBand(
            @Valid @RequestBody CreateBandRequest request) {
        Band band = this.mapper.toDomain(request);
        Band createBand = this.service.createBand(band);
        BandResponse response = this.mapper.toResponse(createBand);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/bandId")
    public ResponseEntity<BandResponse> getBandById(
            @RequestParam Long bandIdLong) {
        Band band = this.service.getBandById(bandIdLong);
        BandResponse response = this.mapper.toResponse(band);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/bandName")
    public ResponseEntity<BandResponse> getBandByName(
            @RequestParam String bandNameString) {
        Band band = this.service.getBandByName(bandNameString);
        BandResponse response = this.mapper.toResponse(band);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping("/{bandIdLong}")
    public ResponseEntity<BandResponse> updateBand(
            @PathVariable Long bandIdLong,
            @Valid @RequestBody CreateBandRequest request) {
        Band band = new Band(
                bandIdLong,
                request.bandNameString(),
                request.bandDescriptionString(),
                null,
                null);
        Band updateBand = this.service.updateBand(band);
        BandResponse response = this.mapper.toResponse(updateBand);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @DeleteMapping("/{bandIdLong}")
    public ResponseEntity<Void> deleteBand(
            @PathVariable Long bandIdLong) {
        this.service.deleteBandById(bandIdLong);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
