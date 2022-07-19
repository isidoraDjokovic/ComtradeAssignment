package com.dora.assignment.exception;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

  private final String message;
}
