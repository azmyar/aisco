package aisco.donation.pgateway.virtualaccount;

import aisco.donation.core.Donation;

public abstract class DVirtualAccount implements Donation {
    protected String name;
    protected String email;
    protected String phone;
    protected int amount;
    protected String bankName;
    protected String vaNumber;
    
    public DVirtualAccount() {
        
    }
    
    public DVirtualAccount(String name, String email, String phone, int amount, String bankName, String vaNumber) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.bankName = bankName;
        this.vaNumber = vaNumber;
    }
    
    public abstract void addDonation();
    public abstract void getDonation();
}

