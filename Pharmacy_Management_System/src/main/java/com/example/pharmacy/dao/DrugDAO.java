package com.example.pharmacy.dao;

import com.example.pharmacy.model.Drug;
import com.example.pharmacy.model.Purchase;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for managing Drug records in the database.
 */
public class DrugDAO {
    private static List<Drug> drugs = new ArrayList<>();
    private static int idCounter = 1;

    /**
     * Adds a new drug to the database.
     *
     * @param drug the Drug object to be added
     */
    public static void addDrug(Drug drug) {
        drug.setId(idCounter++);
        drugs.add(drug);
        SupplierDAO.getSupplierById(drug.getSupplierId()).addDrug(drug);
    }

    /**
     * Retrieves all drugs from the database.
     *
     * @return a list of all Drug objects
     */
    public static List<Drug> getAllDrugs() {
        return new ArrayList<>(drugs);
    }

    public static void removeDrug(int id) {
        drugs.removeIf(drug -> drug.getId() == id);
    }

    /**
     * Searches for drugs based on various criteria.
     *
     * @param name         the name of the drug to search for
     * @param manufacturer the manufacturer of the drug to search for
     * @param price        the price of the drug to search for
     * @param supplierId   the ID of the supplier to search for
     * @return a list of Drug objects that match the search criteria
     */
    public static List<Drug> searchDrugs(String name, String manufacturer, double price, int supplierId) {
        List<Drug> results = new ArrayList<>();
        for (Drug drug : drugs) {
            boolean matches = true;
            if (name != null && !drug.getName().contains(name)) matches = false;
            if (manufacturer != null && !drug.getManufacturer().contains(manufacturer)) matches = false;
            if (price >= 0 && drug.getPrice() != price) matches = false;
            if (supplierId >= 0 && drug.getSupplierId() != supplierId) matches = false;
            if (matches) results.add(drug);
        }
        return results;
    }

    /**
     * Retrieves a drug by its ID.
     *
     * @param id the ID of the drug to retrieve
     * @return the Drug object with the specified ID, or null if not found
     */
    public static Drug getDrugById(int id) {
        for (Drug drug : drugs) {
            if (drug.getId() == id) {
                return drug;
            }
        }
        return null;
    }

    public static void addPurchase(Purchase purchase) {
        Drug drug = getDrugById(purchase.getDrugId());
        if (drug != null) {
            drug.addPurchase(purchase);
        }
    }

    /**
     * Retrieves the purchase history for a specific drug.
     *
     * @param drugId the ID of the drug to retrieve the purchase history for
     * @return a list of Purchase objects representing the purchase history of the drug
     */
    public static List<Purchase> getPurchaseHistory(int drugId) {
        Drug drug = getDrugById(drugId);
        if (drug != null) {
            return drug.getPurchaseHistory();
        }
        return new ArrayList<>();
    }

    public static void updateThresholds(int drugId, int minThreshold, int maxThreshold) {
        // Implement database update logic here
    }


}


