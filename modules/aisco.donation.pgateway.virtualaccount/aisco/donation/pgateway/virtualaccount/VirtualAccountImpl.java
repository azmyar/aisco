package aisco.donation.pgateway.virtualaccount;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VirtualAccountImpl extends DVirtualAccount {
    private List<Object> vaDonations;
    
    public VirtualAccountImpl() {
        System.out.println("Donation via Virtual Account");
        vaDonations = new ArrayList<>();
    }
    
    public VirtualAccountImpl(String name, String email, String phone, int amount, String bankName, String vaNumber) {
        super(name, email, phone, amount, bankName, vaNumber);
    }
    
    @Override
    public void addDonation() {
        vaDonations.add(new VirtualAccountImpl("Deni", "deni@mail.com", "081234567890", 600000, "BNI", "8888123456789"));
        vaDonations.add(new VirtualAccountImpl("Maya", "maya@mail.com", "087654321098", 750000, "BRI", "7777987654321"));
    }
    
    @Override
    public void getDonation() {
        System.out.println(Arrays.asList(vaDonations));
    }
    
    @Override
    public String toString() {
        return "- Virtual Account Donation from " + name + ": " + amount +
                ", Bank: " + bankName + ", VA Number: " + vaNumber + "\n";
    }
}

