package com.quarkus.bootcamp.nttdata.domain.enitty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Associate {
  protected String id;
  protected String serial;
  protected Integer pin;
  protected Integer cvv;
}
