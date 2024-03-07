package br.com.paymentservice.payment;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<PaymentView> findPaymentById(@PathVariable Long id) {
        Payment payment = paymentService.findById(id);

        return ResponseEntity.ok(new PaymentView(payment));
    }

    @PostMapping("/payment")
    public ResponseEntity<PaymentView> save(@RequestBody @Valid CreatePaymentRequest createPaymentRequest) {
        Payment payment = paymentService.create(createPaymentRequest);
        return ResponseEntity.ok(new PaymentView(payment));
    }
}
