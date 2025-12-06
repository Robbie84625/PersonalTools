package com.robbie.backend.infra.dataprovider.accesstoken;

import com.robbie.backend.infra.dataprovider.accesstoken.model.AccessTokenInfo;
import com.robbie.backend.infra.exception.ValidException;

public interface TokenVerifier {
  AccessTokenInfo verify(String token) throws ValidException;
}
