package br.com.dcruzb.product.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorMessage {
	private String message;
	private String userMessage;
	private LocalDateTime dateTime;
}
