package com.thekkekkara.insiders.identity.service;

import java.util.List;

import com.thekkekkara.insiders.identity.domain.Role;
import com.thekkekkara.insiders.identity.domain.User;

public interface BandMembershipService {

        boolean isUserMemberOfBand(
                        Long userIdLong,
                        Long bandIdLong);

        List<User> getBandMembers(
                        Long bandIdLong,
                        Long requestedUserIdLong);

        List<Role> getUserRolesInBand(
                        Long userIdLong,
                        Long bandIdLong);

        boolean isUserOwnerOfBand(
                        Long userIdLong,
                        Long bandIdLong);

        boolean isUserManagerOfBand(
                        Long userIdLong,
                        Long bandIdLong);

        void addMemberToBand(
                        Long userIdLong,
                        Long bandIdLong,
                        Long roleIdLong);

        void removeMemberFromBand(
                        Long userIdLong,
                        Long bandIdLong);
}