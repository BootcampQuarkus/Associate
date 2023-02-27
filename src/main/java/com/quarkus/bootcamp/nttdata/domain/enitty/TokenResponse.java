package com.quarkus.bootcamp.nttdata.domain.enitty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {
  protected String token;
  protected String customerId;
}
