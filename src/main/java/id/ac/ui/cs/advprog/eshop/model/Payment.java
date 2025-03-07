package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    // Define valid payment methods and statuses as constants
    private static final Set<String> VALID_PAYMENT_METHODS;
    private static final Set<String> VALID_PAYMENT_STATUSES;

    static {
        // Initialize valid payment methods
        Set<String> methods = new HashSet<>();
        methods.add("by-credit-card");
        methods.add("by-voucher");
        VALID_PAYMENT_METHODS = Collections.unmodifiableSet(methods);

        // Initialize valid payment statuses
        Set<String> statuses = new HashSet<>();
        statuses.add("PENDING");
        statuses.add("SUCCESS");
        statuses.add("REJECTED");
        VALID_PAYMENT_STATUSES = Collections.unmodifiableSet(statuses);
    }

    /**
     * Creates a new Payment with the given parameters.
     *
     * @param id          The unique identifier for this payment
     * @param method      The payment method, must be one of the valid methods
     * @param status      The payment status, must be one of the valid statuses
     * @param paymentData Additional data for the payment
     * @throws IllegalArgumentException if method or status is invalid
     */
    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.paymentData = paymentData;
        this.setStatus(status);
        this.setMethod(method);
    }

    /**
     * Sets the payment status.
     *
     * @param status The new status, must be one of the valid statuses
     * @throws IllegalArgumentException if status is invalid
     */
    public void setStatus(String status) {
        validateStatus(status);
        this.status = status;
    }

    /**
     * Sets the payment method.
     *
     * @param method The payment method, must be one of the valid methods
     * @throws IllegalArgumentException if method is invalid
     */
    public void setMethod(String method) {
        validateMethod(method);
        this.method = method;
    }

    /**
     * Validates that the given status is among the valid payment statuses.
     *
     * @param status The status to validate
     * @throws IllegalArgumentException if status is invalid
     */
    private void validateStatus(String status) {
        if (!VALID_PAYMENT_STATUSES.contains(status)) {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
    }

    /**
     * Validates that the given method is among the valid payment methods.
     *
     * @param method The method to validate
     * @throws IllegalArgumentException if method is invalid
     */
    private void validateMethod(String method) {
        if (!VALID_PAYMENT_METHODS.contains(method)) {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }
    }
}