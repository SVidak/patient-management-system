package com.java.billingservice.service;

import com.java.billingservice.dto.bill.BillRequestDTO;
import com.java.billingservice.dto.bill.BillResponseDTO;
import com.java.billingservice.exception.BillNotFoundException;
import com.java.billingservice.exception.BillingAccountNotFoundException;
import com.java.billingservice.mapper.bill.BillMapper;
import com.java.billingservice.model.Bill;
import com.java.billingservice.repository.BillRepository;
import com.java.billingservice.repository.BillingAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final BillingAccountRepository billingAccountRepository;

    public BillService(BillRepository billRepository, BillingAccountRepository billingAccountRepository) {
        this.billRepository = billRepository;
        this.billingAccountRepository = billingAccountRepository;
    }

    public List<BillResponseDTO> getBills() {
        List<Bill> bills = billRepository.findAll();

        return bills.stream().map(BillMapper::toBillResponseDTO).toList();
    }

    public BillResponseDTO getBillById(UUID id) {
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new BillNotFoundException("Bill with ID :" + id + "not found"));

        return BillMapper.toBillResponseDTO(bill);
    }

    public BillResponseDTO createBill(BillRequestDTO billRequestDTO) {

        Bill newBill = BillMapper.toBill(billRequestDTO);
        newBill.setCreatedDate(LocalDate.now());
        newBill.setBillingAccount(billingAccountRepository.findByPatientId(UUID.fromString(billRequestDTO.getPatientId()))
                .orElseThrow(() -> new BillingAccountNotFoundException("BillingAccount with Patient ID :" + billRequestDTO.getPatientId() + "not found")));

        billRepository.save(newBill);

        return BillMapper.toBillResponseDTO(newBill);
    }
}
