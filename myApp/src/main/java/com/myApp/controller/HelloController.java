package com.myApp.controller;

import com.myApp.model.Age;
import com.myApp.model.Human;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class HelloController {

  private MessageSource messageSource;

  @ModelAttribute("human")
  public Human prepareModel() {
    Human human = new Human();
    human.setAge(new Age());
    return human;
  }

  @GetMapping("/")
  public String input(Model model) {
    model.addAttribute("message", "プロフィールを入力してください。");
    return "input";
  }

  @PostMapping("/result")
  public String result(Model model, @Validated @ModelAttribute Human human,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      List<String> errorMessageList = new ArrayList<>();
      if (bindingResult.getFieldError("name") != null) {
        String name = messageSource.getMessage("name", null, Locale.getDefault());
        errorMessageList.add(
            messageSource.getMessage(
                "ALPHA_NUMERIC_ONLY_ERROR_MESSAGE",
                new Object[]{name},
                Locale.getDefault()));
      }
      if (bindingResult.getFieldError("age.value") != null) {
        String age = messageSource.getMessage("age.value", null, Locale.getDefault());
        errorMessageList.add(
            messageSource.getMessage(
                "Min",
                new Object[]{
                    age,
                    bindingResult.getFieldError("age.value").getArguments()[1]
                },
                Locale.getDefault()));
      }
      model.addAttribute("errorMessageList", errorMessageList);
      return "input";
    }
    model.addAttribute("human", human);
    return "result";
  }
}
