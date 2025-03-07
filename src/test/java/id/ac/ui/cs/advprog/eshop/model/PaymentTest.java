package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentTest {
    private Map<String,String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<String,String>();
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        paymentData.put("bankAccount", "12345678901234");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(
                    "a73c5439-b9f8-4523-9654-eb128dc17491",
                    "bank-transfer",
                    "PENDING",
                    paymentData);
        });
    }

    @Test
    void testCreatePaymentValidStatus() {
        paymentData.put("cardNumber", "4111111111111111");
        Payment payment = new Payment(
                "a73c5439-b9f8-4523-9654-eb128dc17491",
                "by-credit-card",
                "PENDING",
                paymentData
        );
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        paymentData.put("cardNumber", "4111111111111111");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(
                    "a73c5439-b9f8-4523-9654-eb128dc17491",
                    "by-credit-card",
                    "PROCESSING",
                    paymentData
            );
        });
    }

    @Test
    void testCreatePaymentValidMethod() {
        paymentData.put("cardNumber", "4111111111111111");
        Payment payment = new Payment(
                "a73c5439-b9f8-4523-9654-eb128dc17491",
                "by-credit-card",
                "PENDING",
                paymentData
        );
        assertEquals("by-credit-card", payment.getMethod());
    }

    @Test
    void testSetStatusInvalid() {
        paymentData.put("cardNumber", "4111111111111111");
        Payment payment = new Payment(
                "a73c5439-b9f8-4523-9654-eb128dc17491",
                "by-credit-card",
                "PENDING",
                paymentData
        );
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("INVALID_STATE"));
    }

    @Test
    void testSetStatusToRejected() {
        paymentData.put("cardNumber", "4111111111111111");
        Payment payment = new Payment(
                "a73c5439-b9f8-4523-9654-eb128dc17491",
                "by-credit-card",
                "PENDING",
                paymentData
        );
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreateAllValidArguments() {
        paymentData.put("cardNumber", "4111111111111111");
        Payment payment = new Payment(
                "a73c5439-b9f8-4523-9654-eb128dc17491",
                "by-credit-card",
                "PENDING",
                paymentData
        );
        assertEquals("a73c5439-b9f8-4523-9654-eb128dc17491", payment.getId());
        assertEquals("by-credit-card", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
        assertEquals(paymentData, payment.getPaymentData());
    }
}