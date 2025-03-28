package aisco.donation.pgateway.kartukredit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KartuKreditImpl extends DKartuKredit {
    private List<Object> ccDonations;
    
    public KartuKreditImpl() {
        System.out.println("Donation via Credit Card Payment");
        ccDonations = new ArrayList<>();
    }
    
    public KartuKreditImpl(String name, String email, String phone, int amount, String cardType, String cardNumber, String expiryDate) {
        super(name, email, phone, amount, cardType, cardNumber, expiryDate);
    }
    
    @Override
    public void addDonation() {
        ccDonations.add(new KartuKreditImpl("Sari", "sari@mail.com", "081234567890", 500000, "Visa", "4111-1111-1111-1111", "12/25"));
        ccDonations.add(new KartuKreditImpl("Joko", "joko@mail.com", "087654321098", 750000, "Mastercard", "5555-5555-5555-4444", "10/26"));
    }
    
    @Override
    public void getDonation() {
        System.out.println(Arrays.asList(ccDonations));
    }
    
    @Override
    public String toString() {
        return "- Credit Card Payment Donation from " + name + ": " + amount +
                ", Card Type: " + cardType + ", Card Number: " + cardNumber.substring(0, 4) + "-xxxx-xxxx-xxxx" + "\n";
    }
}