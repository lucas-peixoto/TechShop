package br.com.paymentservice.payment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<PaymentView> save(CreatePaymentRequest createPaymentRequest) {
        Payment payment = paymentService.create(createPaymentRequest);
        return ResponseEntity.ok(new PaymentView(payment));
    }
}
