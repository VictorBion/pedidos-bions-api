package com.acai.bions_api.dtos;

import java.util.List;

public record UserDto(String username,
                      String password,
                      List<String> roles) {
}
