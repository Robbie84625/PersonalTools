package com.robbie.personaltools.infra.dataprovider.accesstoken;

import com.robbie.personaltools.infra.dataprovider.accesstoken.model.AccessTokenInfo;
import com.robbie.personaltools.infra.exception.ValidException;

public interface TokenVerifier {
  AccessTokenInfo verify(String token) throws ValidException;
}
