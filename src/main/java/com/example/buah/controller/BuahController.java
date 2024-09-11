package com.example.buah.controller;

import com.example.buah.entity.Buah;
import com.example.buah.service.BuahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buah")
public class BuahController {

    @Autowired
    private BuahService buahService;

    @GetMapping
    public List<Buah> getAllBuah() {
        return buahService.getAllBuah();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buah> getBuahById(@PathVariable Long id) {
        return buahService.getBuahById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Buah createBuah(@RequestBody Buah buah) {
        return buahService.saveBuah(buah);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buah> updateBuah(@PathVariable Long id, @RequestBody Buah buah) {
        return buahService.getBuahById(id)
                .map(existingBuah -> {
                    buah.setId(existingBuah.getId());
                    return ResponseEntity.ok(buahService.saveBuah(buah));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuah(@PathVariable Long id) {
        return buahService.getBuahById(id)
                .map(buah -> {
                    buahService.deleteBuah(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

