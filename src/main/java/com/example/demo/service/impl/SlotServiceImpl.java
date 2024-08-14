package com.example.demo.service.impl;

import com.example.demo.model.entity.Slot;
import com.example.demo.repository.SlotRepository;
import com.example.demo.service.SlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService {
    private final SlotRepository slotRepository;

    @Override
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    @Override
    public boolean addSlot(Slot slot) {
        try {
            slotRepository.save(slot);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateSlot(Slot slot) {

    }

    @Override
    public List<Slot> searchSlot(String keyword) {
        return List.of();
    }

    @Override
    public void deleteSlot(Long id) {
        slotRepository.deleteById(id);
    }

    public Optional<Slot> getSlot(Long id) {
        return slotRepository.findById(id);
    }
}
