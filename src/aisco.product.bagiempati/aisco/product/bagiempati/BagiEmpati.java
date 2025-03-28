package aisco.product.bagiempati;

import aisco.program.ProgramFactory;
import aisco.program.core.Program;
import aisco.financialreport.FinancialReportFactory;
import aisco.financialreport.core.FinancialReport;
import aisco.financialreport.core.FinancialReportImpl;
import aisco.financialreport.core.FinancialReportComponent;

import java.util.ArrayList;
import java.util.List;

public class BagiEmpati {
    private static Program activityProgram;
    private static Program operationalProgram;
    private static FinancialReport financialreportFinancialReport;
    private static FinancialReport incomeFinancialReport;
    private static FinancialReport expenseFinancialReport;
    
    private static List<FinancialReport> incomeRecords = new ArrayList<>();
    private static List<FinancialReport> expenseRecords = new ArrayList<>();

    public static void main(String[] args) {
        initializePrograms();
        initializeFinancialReports();
        
        System.out.println("=== BagiEmpati Platform ===");
        System.out.println("Running without any donation/payment feature");
        
        System.out.println("\n=== Available Programs ===");
        displayPrograms();

        System.out.println("\n=== Financial Reports ===");
        generateFinancialReports();
    }

    private static void initializePrograms() {
        Object[] programParams = new Object[]{"Activity Program", "Community Training"};
        activityProgram = ProgramFactory.createProgram("Activity", programParams);

        programParams = new Object[]{"Operational Program", "Admin Services"};
        operationalProgram = ProgramFactory.createProgram("Operational", programParams);
    }

    private static void initializeFinancialReports() {
        FinancialReportComponent baseReport = new FinancialReportImpl("FR002", "2025-03-28", 0, "Base Report", activityProgram, "COA002");

        financialreportFinancialReport = baseReport;

        incomeFinancialReport = new aisco.financialreport.income.FinancialReportImpl(baseReport, "Manual Entry");
        expenseFinancialReport = new aisco.financialreport.expense.FinancialReportImpl(baseReport);

        populateSampleFinancialData();
    }

    private static void populateSampleFinancialData() {
        incomeRecords.add(new FinancialReportImpl("INC006", "2025-03-28", 1200000, "Corporate Sponsorship", operationalProgram, "INC-006"));
        incomeRecords.add(new FinancialReportImpl("INC007", "2025-03-28", 900000, "Government Grant", activityProgram, "INC-007"));

        expenseRecords.add(new FinancialReportImpl("EXP003", "2025-03-28", 400000, "Training Kits", activityProgram, "EXP-003"));
        expenseRecords.add(new FinancialReportImpl("EXP004", "2025-03-28", 350000, "Team Lunch", operationalProgram, "EXP-004"));
    }

    private static void displayPrograms() {
        if (activityProgram != null) {
            System.out.println("Activity Program: " + activityProgram.toString());
        }

        if (operationalProgram != null) {
            System.out.println("Operational Program: " + operationalProgram.toString());
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
            System.out.println("Net Income: " + incomeFinancialReport.total(incomeRecords));
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
