package aisco.product.relawandermawan;

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

public class RelawanDermawan {
    private static Program activityProgram;
    private static Program operationalProgram;
    private static FinancialReport financialreportFinancialReport;
    private static FinancialReport incomeFinancialReport;
    private static FinancialReport expenseFinancialReport;
    private static Donation donationDonation;
    private static Donation bankTransferDonation;
    
    private static List<FinancialReport> incomeRecords = new ArrayList<>();
    private static List<FinancialReport> expenseRecords = new ArrayList<>();

    public static void main(String[] args) {    	
        initializePrograms();
        initializeFinancialReports();
        initializeDonations();
        
        System.out.println("=== RelawanDermawan Donation Platform ===");
        System.out.println("Running with Bank Transfer Payment Gateway");
        
        System.out.println("\n=== Available Programs ===");
        displayPrograms();
        
        System.out.println("\n=== Processing Donations ===");
        processBankTransferDonations();
        
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
        
        incomeFinancialReport = new aisco.financialreport.income.FinancialReportImpl(baseReport, "Bank Transfer");
        
        expenseFinancialReport = new aisco.financialreport.expense.FinancialReportImpl(baseReport);
        
        populateSampleFinancialData();
    }
    
    private static void populateSampleFinancialData() {
        incomeRecords.add(new FinancialReportImpl("INC001", "2025-03-28", 1000000, "Donation from Rudi", activityProgram, "INC-001"));
        incomeRecords.add(new FinancialReportImpl("INC002", "2025-03-28", 850000, "Donation from Siti", operationalProgram, "INC-002"));
        
        expenseRecords.add(new FinancialReportImpl("EXP001", "2025-03-28", 200000, "Office Supplies", operationalProgram, "EXP-001"));
        expenseRecords.add(new FinancialReportImpl("EXP002", "2025-03-28", 350000, "Program Materials", activityProgram, "EXP-002"));
    }
    
    private static void initializeDonations() {
        Object[] donationParams = new Object[]{"General Donation", 0};
        donationDonation = DonationFactory.createDonation("General", donationParams);
        
        Object[] bankTransferParams = new Object[]{
            "Rudi", 
            "rudi@mail.com", 
            "081234567890", 
            1000000, 
            "BCA", 
            "1234567890", 
            "Rudi Hartono"
        };
        bankTransferDonation = DonationFactory.createDonation("TransferBank", bankTransferParams);
    }
    
    private static void displayPrograms() {
        if (activityProgram != null) {
            System.out.println("Activity Program: " + activityProgram.toString());
        }
        
        if (operationalProgram != null) {
            System.out.println("Operational Program: " + operationalProgram.toString());
        }
    }
    
    private static void processBankTransferDonations() {
        if (bankTransferDonation != null) {
            System.out.println("Bank Transfer Donations:");
            System.out.println(bankTransferDonation.toString());
            
            Object[] customDonationParams = new Object[]{
                "Andi", 
                "andi@mail.com", 
                "087654321098", 
                1500000, 
                "Mandiri", 
                "9876543210", 
                "Andi Wijaya"
            };
            Donation customDonation = DonationFactory.createDonation("TransferBank", customDonationParams);
            
            System.out.println("\nAdding custom donation:");
            System.out.println(customDonation.toString());
            
            incomeRecords.add(new FinancialReportImpl(
                "INC003", 
                "2025-03-28", 
                1500000, 
                "Bank Transfer Donation from Andi", 
                activityProgram, 
                "INC-003"
            ));
            
            Object[] anotherDonationParams = new Object[]{
                "Lina", 
                "lina@mail.com", 
                "089876543210", 
                750000, 
                "BNI", 
                "5432109876", 
                "Lina Susanti"
            };
            Donation anotherDonation = DonationFactory.createDonation("TransferBank", anotherDonationParams);
            
            System.out.println("\nAdding another donation:");
            System.out.println(anotherDonation.toString());
            
            incomeRecords.add(new FinancialReportImpl(
                "INC004", 
                "2025-03-28", 
                750000, 
                "Bank Transfer Donation from Lina", 
                operationalProgram, 
                "INC-004"
            ));
        } else {
            System.out.println("Bank Transfer payment gateway not properly initialized.");
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

