package com.thekkekkara.insiders.identity.dto.band;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateBandRequest(
                @NotBlank(message = "Band name cannot be blank") @Size(max = 150, message = "Band name must not exceed 150 characters") String bandNameString,

                @Size(max = 500, message = "Band Description must not exceed 500 characters") String bandDescriptionString) {

}
