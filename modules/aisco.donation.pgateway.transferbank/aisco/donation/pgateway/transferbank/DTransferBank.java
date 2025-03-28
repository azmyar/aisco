package aisco.donation.pgateway.transferbank;

import aisco.donation.core.Donation;

public abstract class DTransferBank implements Donation {
    protected String name;
    protected String email;
    protected String phone;
    protected int amount;
    protected String bankName;
    protected String accountNumber;
    protected String accountName;
    
    public DTransferBank() {
        
    }
    
    public DTransferBank(String name, String email, String phone, int amount, String bankName, String accountNumber, String accountName) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
    }
    
    public abstract void addDonation();
    public abstract void getDonation();
}

