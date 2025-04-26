package com.java.billingservice.service;

import com.java.billingservice.dto.bill.BillRequestDTO;
import com.java.billingservice.dto.bill.BillResponseDTO;
import com.java.billingservice.exception.BillNotFoundException;
import com.java.billingservice.exception.BillingAccountNotFoundException;
import com.java.billingservice.mapper.bill.BillMapper;
import com.java.billingservice.model.Bill;
import com.java.billingservice.model.BillItem;
import com.java.billingservice.model.BillingAccount;
import com.java.billingservice.repository.BillRepository;
import com.java.billingservice.repository.BillingAccountRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BillService {

    private final BillItemService billItemService;
    private final BillRepository billRepository;
    private final BillingAccountRepository billingAccountRepository;

    public BillService(BillRepository billRepository, BillingAccountRepository billingAccountRepository, BillItemService billItemService) {
        this.billRepository = billRepository;
        this.billingAccountRepository = billingAccountRepository;
        this.billItemService = billItemService;
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

        BillingAccount billingAccount = billingAccountRepository.findByPatientId(UUID.fromString(billRequestDTO.getPatientId()))
                .orElseThrow(() -> new BillingAccountNotFoundException("BillingAccount with ID :" + billRequestDTO.getPatientId() + "not found"));

        List<BillItem> billItems = billItemService.createBillItemsFromTreatmentIds(billRequestDTO.getTreatmentIds());

        Long totalAmount = billItems.stream().mapToLong(BillItem::getPrice).sum();

        Bill bill = Bill.builder()
                .patientId(billingAccount.getPatientId())
                .createdDate(LocalDate.now())
                .amount(totalAmount)
                .status("CREATED")
                .billingAccount(billingAccount)
                .build();

        billItems.forEach(item -> item.setBill(bill));
        bill.setBillItems(billItems);

        billRepository.save(bill);

        return BillMapper.toBillResponseDTO(bill);
    }
}
