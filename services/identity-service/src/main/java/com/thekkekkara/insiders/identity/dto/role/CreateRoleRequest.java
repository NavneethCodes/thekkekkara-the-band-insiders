package com.thekkekkara.insiders.identity.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRoleRequest(

        @NotNull Long bandIDLong,

        @NotBlank @Size(max = 50) String roleNameString,

        @Size(max = 255) String roleDescriptionString) {

}
