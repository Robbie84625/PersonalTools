package com.robbie.infra.dataprovider.accesstoken;

import com.robbie.infra.dataprovider.accesstoken.model.AccessTokenInfo;
import com.robbie.infra.exception.ValidException;

public interface TokenVerifier {
  AccessTokenInfo verify(String token) throws ValidException;
}
