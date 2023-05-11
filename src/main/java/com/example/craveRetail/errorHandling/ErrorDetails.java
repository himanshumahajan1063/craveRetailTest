package com.example.craveRetail.errorHandling;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ErrorDetails {

    private String errorCode;
    private String title;
    private String errorDetails;
}
