package aisco.donation.pgateway.ewallet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EWalletImpl extends DEWallet {
    private List<Object> ewalletDonations;
    
    public EWalletImpl() {
        System.out.println("Donation via E-Wallet Payment");
        ewalletDonations = new ArrayList<>();
    }
    
    public EWalletImpl(String name, String email, String phone, int amount, String walletType, String walletId) {
        super(name, email, phone, amount, walletType, walletId);
    }
    
    @Override
    public void addDonation() {
        ewalletDonations.add(new EWalletImpl("Budi", "budi@mail.com", "081234567890", 300000, "GoPay", "081234567890"));
        ewalletDonations.add(new EWalletImpl("Ani", "ani@mail.com", "087654321098", 450000, "OVO", "087654321098"));
    }
    
    @Override
    public void getDonation() {
        System.out.println(Arrays.asList(ewalletDonations));
    }
    
    @Override
    public String toString() {
        return "- E-Wallet Payment Donation from " + name + ": " + amount +
                ", Wallet Type: " + walletType + ", Wallet ID: " + walletId + "\n";
    }
}

