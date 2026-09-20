package com.example.demo;
import java.util.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@SpringBootApplication
@RestController
@RequestMapping("/api")
/*cd "c:\Users\Anonymous\KBC\Demo\demo"
.\mvnw.cmd spring-boot:run   */

public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private final ReservationService reservationService;
	DemoApplication(ReservationService reservationService){
		this.reservationService = reservationService;
	}
	
	@PostMapping("/reserve")
    public Reservation reserveReservation(@RequestBody ReserveRequest req) {
        Reservation r = reservationService.reserveBook(req.getBookId(), req.getMemberId());
		return r;
    }

	@PostMapping("/reservations/{id}/{member}/cancal")
	public String cancelReservation(@PathVariable ReserveRequest req){
		reservationService.cancelBook(req.getBookId(), req.getMemberId());
		return "Cancaled";
	}

	@PostMapping("/reservations/{id}/{member}/return")
	public void returningBook(@RequestBody ReserveRequest req){
		reservationService.returnBook(req.getBookId(), req.getMemberId());
	}

	@PostMapping("/reservations/{id}/{member}/confirm")
	public void confirmReservation(@RequestBody ReserveRequest req){
		reservationService.confirmReservation(req.getBookId(), req.getMemberId());
	}

	@GetMapping("/reservations")
    public List<Reservation> showReservations() {
		return reservationService.findAll();
    }
}