package com.myApp.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Human {

  @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ユーザー名は英数字のみ使用できます")
  private String name;
  @Valid
  private Age age;
}
