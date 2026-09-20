package com.example.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service 
@Profile("test")
public class MockPaymentGateway implements PaymentGateway {
    @Override
    public Boolean chargeFees(int lateFees){
        System.out.println("Mock Charged $ "+ lateFees +" late fee");
        return  true;
    }
}
