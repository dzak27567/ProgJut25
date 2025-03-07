package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Arrays;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.paymentData = paymentData;
        this.setStatus(status);
        this.setMethod(method);
    }

    public void setStatus(String status) {
        String[] statusList = {"PENDING", "SUCCESS", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        }
        else {
            this.status = status;
        }
    }

    public void setMethod(String method) {
        String[] methodList = {"by-credit-card", "by-voucher"};
        if (Arrays.stream(methodList).noneMatch(item -> (item.equals(method)))) {
            throw new IllegalArgumentException();
        }
        else {
            this.method = method;
        }
    }
}