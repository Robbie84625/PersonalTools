package com.robbie.backend.infra.dataprovider.accesstoken;

import com.robbie.backend.infra.dataprovider.accesstoken.model.AccessTokenInfo;
import com.robbie.backend.infra.exception.ValidException;

public interface TokenGetter {
  AccessTokenInfo getTokenInfo() throws ValidException;
}
