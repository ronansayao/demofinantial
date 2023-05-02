package com.example.demofinantial.domain.externalDto;

import lombok.Getter;

@Getter
public class PaymentResponse {
    private RequestInformation requestInfo;
    private PaymentInformation paymentInfo;
}
