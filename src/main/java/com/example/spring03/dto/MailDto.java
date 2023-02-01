package com.example.spring03.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class MailDto {
    private String email;
    private String title;
    private String message;
}