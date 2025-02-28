package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class IdGenerator {
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}