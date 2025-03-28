package aisco.product.hobibergabi;

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

public class HobiBerbagi {
    private static Program activityProgram;
    private static Program operationalProgram;
    private static FinancialReport financialreportFinancialReport;
    private static FinancialReport incomeFinancialReport;
    private static FinancialReport expenseFinancialReport;
    private static Donation donationDonation;
    private static Donation virtualAccountDonation;
    
    private static List<FinancialReport> incomeRecords = new ArrayList<>();
    private static List<FinancialReport> expenseRecords = new ArrayList<>();

    public static void main(String[] args) {    	
        initializePrograms();
        initializeFinancialReports();
        initializeDonations();
        
        System.out.println("=== HobiBerbagi Donation Platform ===");
        System.out.println("Running with Virtual Account Payment Gateway");
        
        System.out.println("\n=== Available Programs ===");
        displayPrograms();
        
        System.out.println("\n=== Processing Donations ===");
        processVirtualAccountDonations();
        
        System.out.println("\n=== Financial Reports ===");
        generateFinancialReports();
    }
    
    private static void initializePrograms() {
        Object[] programParams = new Object[]{"Activity Program", "Community Service"};
        activityProgram = ProgramFactory.createProgram("Activity", programParams);
        
        programParams = new Object[]{"Operational Program", "Administration"};
        operationalProgram = ProgramFactory.createProgram("Operational", programParams);
    }
    
    private static void initializeFinancialReports() {
        FinancialReportComponent baseReport = new FinancialReportImpl("FR001", "2025-03-28", 0, "Base Report", activityProgram, "COA001");
        
        financialreportFinancialReport = baseReport;
        
        incomeFinancialReport = new aisco.financialreport.income.FinancialReportImpl(baseReport, "Virtual Account");
        
        expenseFinancialReport = new aisco.financialreport.expense.FinancialReportImpl(baseReport);
        
        populateSampleFinancialData();
    }
    
    private static void populateSampleFinancialData() {
        incomeRecords.add(new FinancialReportImpl("INC001", "2025-03-28", 600000, "Donation from Deni", activityProgram, "INC-001"));
        incomeRecords.add(new FinancialReportImpl("INC002", "2025-03-28", 750000, "Donation from Maya", operationalProgram, "INC-002"));
        
        expenseRecords.add(new FinancialReportImpl("EXP001", "2025-03-28", 250000, "Office Supplies", operationalProgram, "EXP-001"));
        expenseRecords.add(new FinancialReportImpl("EXP002", "2025-03-28", 400000, "Program Materials", activityProgram, "EXP-002"));
    }
    
    private static void initializeDonations() {
        Object[] donationParams = new Object[]{"General Donation", 0};
        donationDonation = DonationFactory.createDonation("General", donationParams);
        
        Object[] virtualAccountParams = new Object[]{
            "Deni", 
            "deni@mail.com", 
            "081234567890", 
            600000, 
            "BNI", 
            "8888123456789"
        };
        virtualAccountDonation = DonationFactory.createDonation("VirtualAccount", virtualAccountParams);
    }
    
    private static void displayPrograms() {
        if (activityProgram != null) {
            System.out.println("Activity Program: " + activityProgram.toString());
        }
        
        if (operationalProgram != null) {
            System.out.println("Operational Program: " + operationalProgram.toString());
        }
    }
    
    private static void processVirtualAccountDonations() {
        if (virtualAccountDonation != null) {
            System.out.println("Virtual Account Donations:");
            System.out.println(virtualAccountDonation.toString());
            
            Object[] customDonationParams = new Object[]{
                "Budi", 
                "budi@mail.com", 
                "087654321098", 
                800000, 
                "BCA", 
                "9999987654321"
            };
            Donation customDonation = DonationFactory.createDonation("VirtualAccount", customDonationParams);
            
            System.out.println("\nAdding custom donation:");
            System.out.println(customDonation.toString());
            
            incomeRecords.add(new FinancialReportImpl(
                "INC003", 
                "2025-03-28", 
                800000, 
                "Virtual Account Donation from Budi", 
                activityProgram, 
                "INC-003"
            ));
            
            Object[] anotherDonationParams = new Object[]{
                "Sinta", 
                "sinta@mail.com", 
                "089876543210", 
                500000, 
                "Mandiri", 
                "7777123456789"
            };
            Donation anotherDonation = DonationFactory.createDonation("VirtualAccount", anotherDonationParams);
            
            System.out.println("\nAdding another donation:");
            System.out.println(anotherDonation.toString());
            
            incomeRecords.add(new FinancialReportImpl(
                "INC004", 
                "2025-03-28", 
                500000, 
                "Virtual Account Donation from Sinta", 
                operationalProgram, 
                "INC-004"
            ));
            
            Object[] thirdDonationParams = new Object[]{
                "Reza", 
                "reza@mail.com", 
                "081122334455", 
                1000000, 
                "BRI", 
                "6666987654321"
            };
            Donation thirdDonation = DonationFactory.createDonation("VirtualAccount", thirdDonationParams);
            
            System.out.println("\nAdding third donation:");
            System.out.println(thirdDonation.toString());
            
            incomeRecords.add(new FinancialReportImpl(
                "INC005", 
                "2025-03-28", 
                1000000, 
                "Virtual Account Donation from Reza", 
                activityProgram, 
                "INC-005"
            ));
        } else {
            System.out.println("Virtual Account payment gateway not properly initialized.");
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

