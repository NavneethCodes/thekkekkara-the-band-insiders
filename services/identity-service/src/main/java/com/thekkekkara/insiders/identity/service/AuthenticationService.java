
package com.thekkekkara.insiders.identity.service;

import com.thekkekkara.insiders.identity.dto.auth.LoginRequest;
import com.thekkekkara.insiders.identity.dto.auth.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest loginRequest);

}
