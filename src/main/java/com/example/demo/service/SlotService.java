package com.example.demo.service;

import com.example.demo.model.entity.Slot;

import java.util.List;
import java.util.Optional;

public interface SlotService {
    List<Slot> getAllSlots();

    boolean addSlot(Slot slot);

    void updateSlot(Slot slot);

    List<Slot> searchSlot(String keyword);

    void deleteSlot(Long id);

    Optional<Slot> getSlot(Long id);
}
