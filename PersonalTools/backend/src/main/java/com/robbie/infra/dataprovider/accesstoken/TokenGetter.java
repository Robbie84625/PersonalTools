package com.robbie.infra.dataprovider.accesstoken;

import com.robbie.infra.dataprovider.accesstoken.model.AccessTokenInfo;
import com.robbie.infra.exception.ValidException;

public interface TokenGetter {
  AccessTokenInfo getTokenInfo() throws ValidException;
}
