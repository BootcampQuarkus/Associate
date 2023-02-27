package com.quarkus.bootcamp.nttdata.infraestruture.entity.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueD {
  protected String createAt;
  protected String userId;
}