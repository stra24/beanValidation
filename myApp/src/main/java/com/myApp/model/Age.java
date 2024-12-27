package com.myApp.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class Age {

  @Min(7)
  @Max(20)
  private int value;
}
