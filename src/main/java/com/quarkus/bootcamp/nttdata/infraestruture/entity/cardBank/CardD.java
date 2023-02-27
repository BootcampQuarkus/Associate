package com.quarkus.bootcamp.nttdata.infraestruture.entity.cardBank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardD {
  protected Long id;
  protected String serial;
  protected Integer pin;
  protected Integer month;
  protected Integer year;
  protected Integer cvv;
  protected Long customerId;
  protected Long productId;
  protected Long cardTypeId;
  protected CardTypeD cardType;

}
