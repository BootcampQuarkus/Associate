package com.quarkus.bootcamp.nttdata.infraestruture.entity.token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociationD {
  protected String key;
  protected ValueD value;
}
