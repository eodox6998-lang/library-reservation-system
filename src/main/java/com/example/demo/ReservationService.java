package com.example.demo;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.io.File;
import org.springframework.stereotype.Service;


import tools.jackson.core.type.TypeReference;

import jakarta.annotation.PostConstruct;
import tools.jackson.databind.ObjectMapper;

@Service
   
public class ReservationService {

    private final Clock clock;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final NotificationService notification;
    private final AuditLogger auditlogger;
    private final PaymentGateway payment;
    private final ObjectMapper objectMapper;

    List<Reservation> reserve = new ArrayList<>();
    
    ReservationService(BookRepository bookRepository, MemberRepository memberRepository, AuditLogger audit, Clock clock, NotificationService notification, ObjectMapper objectMapper, PaymentGateway payment) {
        this.auditlogger = audit;
        this.payment = payment;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.clock = clock;
        this.notification = notification;
        this.objectMapper = objectMapper;
    }

    public List<Reservation> findAll(){
        return reserve;
    }

    @PostConstruct
    public void read(){
        File file = new File("reservation.json");

        try {
            this.reserve = objectMapper.readValue(file, new TypeReference<List<Reservation>>(){});    
        } catch (Exception e) {
            
        }
    }

    public void saveTofile(Reservation reservation){
        objectMapper.writeValue(new File("reservation.json"), reservation);
    }

    private int n = 1;
    public Reservation reserveBook(int book, int member){
        Reservation reservation = null;
        if(bookRepository.findBook(book).isAvaliable() && memberRepository.findMember(member) != null){
            int reservationId = n++;
            reservation = new Reservation(reservationId, bookRepository.findBook(book), memberRepository.findMember(member), Reservation.Status.PENDING, null);
            reserve.add(reservation);
            bookRepository.findBook(book).setAvaliable(false);
            saveTofile(reservation);
            notification.printReservation(bookRepository.findBook(book), memberRepository.findMember(member));    
            auditlogger.log("Book "+bookRepository.findBook(book).getTitle()+" reserved by Member "+memberRepository.findMember(member).getId() +", status PENDING");
        }   
        return reservation;
    }

    public void confirmReservation(int book, int member){
        Reservation r = reserve.get(findReservation(book, member));
        r.setDueDate(LocalDateTime.now().minusDays(1));
        r.setState(Reservation.Status.CONFIRMED);
        saveTofile(r);
        notification.printConfirmation(bookRepository.findBook(book), memberRepository.findMember(member));
        auditlogger.log("Reservation "+ r.getId()+" confirmed, due date "+LocalDateTime.now().plusSeconds(1));
    }

    public int findReservation(int book , int member){
        int a = 0;
        for(int i = 0; i < reserve.size(); i++){
            if(Integer.valueOf(reserve.get(i).getBook().getId()).equals(book) && Integer.valueOf(reserve.get(i).getMember().getId()).equals(member)){
                a = i;
                break;
            }
        }
        return a;
    }

    public void cancelBook(int book, int member){
        Reservation r = reserve.get(findReservation(book, member));
        r.setState(Reservation.Status.CANCALLED);
        saveTofile(r);
        notification.printCancallation(r);
        auditlogger.log("Reservation "+r.getId()+" cancelled");
        reserve.remove(findReservation(book, member));
        
    }

    public void returnBook(int b, int member){
        Reservation r = reserve.get(findReservation(b, member));
        r.setState(Reservation.Status.CANCALLED);
        Book book = bookRepository.findBook(b);
        book.setAvaliable(true);
        saveTofile(r);
        notification.printReturn(book);
        auditlogger.log("Reservation "+r.getId()+" returned");
        if(clock.instant().atZone(ZoneId.systemDefault()).toLocalDateTime().isAfter(r.getDueDate())){
          //  int date = clock.instant().compareTo(r.getDueDate().toInstant(null));
            payment.chargeFees(10);
        } 
    }
}