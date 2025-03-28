package aisco.donation.pgateway.ewallet;

import aisco.donation.core.Donation;

public abstract class DEWallet implements Donation {
    protected String name;
    protected String email;
    protected String phone;
    protected int amount;
    protected String walletType;
    protected String walletId;
    
    public DEWallet() {
        
    }
    
    public DEWallet(String name, String email, String phone, int amount, String walletType, String walletId) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.amount = amount;
        this.walletType = walletType;
        this.walletId = walletId;
    }
    
    public abstract void addDonation();
    public abstract void getDonation();
}

