package com.robbie.personaltools.infra.dataprovider.accesstoken.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serial;
import java.io.Serializable;
import lombok.Data;

@Data
public class AccessTokenInfo implements Serializable {
  @Serial private static final long serialVersionUID = 1L;

  @JsonProperty("member_id")
  private String memberId;
}
