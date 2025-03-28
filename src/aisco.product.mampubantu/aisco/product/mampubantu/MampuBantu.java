package aisco.product.mampubantu;

import aisco.program.ProgramFactory;
import aisco.program.core.Program;
import aisco.financialreport.FinancialReportFactory;
import aisco.financialreport.core.FinancialReport;
import aisco.financialreport.core.FinancialReportImpl;
import aisco.financialreport.core.FinancialReportComponent;
import aisco.donation.DonationFactory;
import aisco.donation.core.Donation;

import java.util.ArrayList;
import java.util.List;

public class MampuBantu {
    private static Program activityProgram;
    private static Program operationalProgram;
    private static FinancialReport financialreportFinancialReport;
    private static FinancialReport incomeFinancialReport;
    private static FinancialReport expenseFinancialReport;
    private static Donation donationDonation;
    private static Donation pgatewayDonation;
    
    private static List<FinancialReport> incomeRecords = new ArrayList<>();
    private static List<FinancialReport> expenseRecords = new ArrayList<>();

    public static void main(String[] args) {    	
        initializePrograms();
        initializeFinancialReports();
        initializeDonations();
        
        System.out.println("=== MampuBantu Donation Platform ===");
        System.out.println("Running with E-Wallet Payment Gateway");
        
        System.out.println("\n=== Available Programs ===");
        displayPrograms();
        
        System.out.println("\n=== Processing Donations ===");
        processEWalletDonations();
        
        System.out.println("\n=== Financial Reports ===");
        generateFinancialReports();
    }
    
    private static void initializePrograms() {
        Object[] programParams = new Object[]{"Activity Program", "Education"};
        activityProgram = ProgramFactory.createProgram("Activity", programParams);
        
        programParams = new Object[]{"Operational Program", "Office"};
        operationalProgram = ProgramFactory.createProgram("Operational", programParams);
    }
    
    private static void initializeFinancialReports() {
        FinancialReportComponent baseReport = new FinancialReportImpl("FR001", "2025-03-28", 0, "Base Report", activityProgram, "COA001");
        
        financialreportFinancialReport = baseReport;
        
        incomeFinancialReport = new aisco.financialreport.income.FinancialReportImpl(baseReport, "E-Wallet");
        
        expenseFinancialReport = new aisco.financialreport.expense.FinancialReportImpl(baseReport);
        
        populateSampleFinancialData();
    }
    
    private static void populateSampleFinancialData() {
        incomeRecords.add(new FinancialReportImpl("INC001", "2025-03-28", 500000, "Donation from Budi", activityProgram, "INC-001"));
        incomeRecords.add(new FinancialReportImpl("INC002", "2025-03-28", 300000, "Donation from Ani", operationalProgram, "INC-002"));
        
        expenseRecords.add(new FinancialReportImpl("EXP001", "2025-03-28", 200000, "Office Supplies", operationalProgram, "EXP-001"));
        expenseRecords.add(new FinancialReportImpl("EXP002", "2025-03-28", 350000, "Program Materials", activityProgram, "EXP-002"));
    }
    
    private static void initializeDonations() {
        Object[] donationParams = new Object[]{"General Donation", 0};
        donationDonation = DonationFactory.createDonation("General", donationParams);
        
        Object[] ewalletParams = new Object[]{
            "Dewi", 
            "dewi@mail.com", 
            "081122334455", 
            500000, 
            "DANA", 
            "081122334455"
        };
        pgatewayDonation = DonationFactory.createDonation("EWallet", ewalletParams);
    }
    
    private static void displayPrograms() {
        if (activityProgram != null) {
            System.out.println("Activity Program: " + activityProgram.toString());
        }
        
        if (operationalProgram != null) {
            System.out.println("Operational Program: " + operationalProgram.toString());
        }
    }
    
    private static void processEWalletDonations() {
        if (pgatewayDonation != null) {
            System.out.println("E-Wallet Donations:");
            
            Object[] customDonationParams = new Object[]{
                "Maya", 
                "maya@mail.com", 
                "081122334455", 
                750000, 
                "OVO", 
                "081122334455"
            };
            Donation customDonation = DonationFactory.createDonation("EWallet", customDonationParams);
            
            System.out.println("\nAdding custom donation:");
            System.out.println(customDonation.toString());
            
            incomeRecords.add(new FinancialReportImpl(
                "INC003", 
                "2025-03-28", 
                750000, 
                "E-Wallet Donation from Maya", 
                activityProgram, 
                "INC-003"
            ));
        } else {
            System.out.println("E-Wallet payment gateway not properly initialized.");
        }
    }
    
    private static void generateFinancialReports() {
        if (financialreportFinancialReport != null) {
            financialreportFinancialReport.printHeader();
            int totalIncome = incomeFinancialReport.total(incomeRecords);
            int totalExpense = expenseFinancialReport.total(expenseRecords);
            System.out.println("Net Balance: " + (totalIncome - totalExpense));
        }
        
        if (incomeFinancialReport != null) {
            incomeFinancialReport.printHeader();
            for (FinancialReport record : incomeRecords) {
                System.out.println(record.toString());
            }
            int netIncome = incomeFinancialReport.total(incomeRecords);
            System.out.println("Net Income (after fees): " + netIncome);
            
            if (incomeFinancialReport instanceof aisco.financialreport.income.FinancialReportImpl) {
                aisco.financialreport.income.FinancialReportImpl incomeReport = 
                    (aisco.financialreport.income.FinancialReportImpl) incomeFinancialReport;
                System.out.println("Payment Method: " + incomeReport.getPaymentMethod());
            }
        }
        
        if (expenseFinancialReport != null) {
            expenseFinancialReport.printHeader();
            for (FinancialReport record : expenseRecords) {
                System.out.println(record.toString());
            }
            expenseFinancialReport.total(expenseRecords);
        }
    }
}

