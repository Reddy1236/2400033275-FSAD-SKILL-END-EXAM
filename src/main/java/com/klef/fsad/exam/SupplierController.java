package com.klef.fsad.exam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierRepository supplierRepository;

    public SupplierController(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @PostMapping
    public ResponseEntity<Supplier> insertSupplier(@RequestBody Supplier supplier) {
        if (supplier.getDate() == null) {
            supplier.setDate(LocalDate.now());
        }

        Supplier savedSupplier = supplierRepository.save(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSupplier(
            @RequestParam int id,
            @RequestBody Supplier supplierData
    ) {
        return supplierRepository.findById(id)
                .map(supplier -> {
                    if (supplierData.getName() != null && !supplierData.getName().isBlank()) {
                        supplier.setName(supplierData.getName());
                    }

                    if (supplierData.getStatus() != null && !supplierData.getStatus().isBlank()) {
                        supplier.setStatus(supplierData.getStatus());
                    }

                    return ResponseEntity.<Object>ok(supplierRepository.save(supplier));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Supplier not found with ID: " + id)));
    }

    @GetMapping
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable int id) {
        return supplierRepository.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Supplier not found with ID: " + id)));
    }
}
