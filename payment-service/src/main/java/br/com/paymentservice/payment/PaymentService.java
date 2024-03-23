package br.com.paymentservice.payment;

import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment findById(Long id) {
        return paymentRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Payment create(CreatePaymentRequest createPaymentRequest) {
        Payment payment = new Payment(createPaymentRequest.cartId(), createPaymentRequest.amount(), createPaymentRequest.paymentMethod());
        return paymentRepository.save(payment);
    }
}
