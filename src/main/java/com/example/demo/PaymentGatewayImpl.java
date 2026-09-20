package com.example.demo;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Service
public class PaymentGatewayImpl implements PaymentGateway{
    
    @Override
    public Boolean chargeFees(int lateFees){
        System.out.println("Charged $ "+ lateFees +" late fee");
        return  true;
    }
    
}
