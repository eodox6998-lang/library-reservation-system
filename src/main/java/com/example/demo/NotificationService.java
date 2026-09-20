package com.example.demo;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    
    public String printConfirmation(Book book, Member member){
        return "Reservation confirmed by "+ book.getTitle()+" by "+member.getName();
    }
    public String printReservation(Book book, Member member){
        return "Resevation has been made for book: "+book.getTitle()+" by "+member.getName();
    }
    public String printCancallation(Reservation res){
        return  "Reservation cancalled by "+ res.getId();
    }
    public String printReturn(Book book){
        return "Book "+book.getTitle()+" has been returned";
    }
}
