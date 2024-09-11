package com.example.buah.service;

import com.example.buah.entity.Buah;
import com.example.buah.repository.BuahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BuahService {

    @Autowired
    private BuahRepository buahRepository;

    public List<Buah> getAllBuah() {
        return buahRepository.findAllActiveBuah();
    }

    public Optional<Buah> getBuahById(Long id) {
        return buahRepository.findByIdAndActive(id);
    }

    public Buah saveBuah(Buah buah) {
        return buahRepository.save(buah);
    }

    public void deleteBuah(Long id) {
        Optional<Buah> buahOpt = buahRepository.findByIdAndActive(id);
        if (buahOpt.isPresent()) {
            Buah buah = buahOpt.get();
            buah.setDeletedAt(new Date());
            buahRepository.save(buah);
        }
    }
}


