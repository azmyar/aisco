package aisco.donation.pgateway.kartukredit;

import aisco.donation.core.Donation;

public abstract class DKartuKredit implements Donation {
    protected String name;
    protected String email;
    protected String phone;
    protected int amount;
    protected String cardType;
    protected String cardNumber;
    protected String expiryDate;
    
    public DKartuKredit() {
        
    }
    
    public DKartuKredit(String name, String email, String phone, int amount, String cardType, String cardNumber, String expiryDate) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }
    
    public abstract void addDonation();
    public abstract void getDonation();
}