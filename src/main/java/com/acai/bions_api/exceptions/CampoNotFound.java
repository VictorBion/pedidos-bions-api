package com.acai.bions_api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@AllArgsConstructor
@Getter
@Setter
public class CampoNotFound {

    private int status;

    private String mensagem;


}
