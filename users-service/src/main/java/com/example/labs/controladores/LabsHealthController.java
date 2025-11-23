package com.example.labs.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabsHealthController {
  @GetMapping("/labs/ping")
  public String ping() { return "labs ok"; }
}
