package aisco.donation.pgateway.transferbank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransferBankImpl extends DTransferBank {
    private List<Object> bankDonations;
    
    public TransferBankImpl() {
        System.out.println("Donation via Bank Transfer");
        bankDonations = new ArrayList<>();
    }
    
    public TransferBankImpl(String name, String email, String phone, int amount, String bankName, String accountNumber, String accountName) {
        super(name, email, phone, amount, bankName, accountNumber, accountName);
    }
    
    @Override
    public void addDonation() {
        bankDonations.add(new TransferBankImpl("Rudi", "rudi@mail.com", "081234567890", 1000000, "BCA", "1234567890", "Rudi Hartono"));
        bankDonations.add(new TransferBankImpl("Siti", "siti@mail.com", "087654321098", 850000, "Mandiri", "0987654321", "Siti Nurhaliza"));
    }
    
    @Override
    public void getDonation() {
        System.out.println(Arrays.asList(bankDonations));
    }
    
    @Override
    public String toString() {
        return "- Bank Transfer Donation from " + name + ": " + amount +
                ", Bank: " + bankName + ", Account: " + accountNumber + " (" + accountName + ")\n";
    }
}

