package com.example.pharmacy.dao;

import com.example.pharmacy.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private static List<Supplier> suppliers = new ArrayList<>();
    private static int idCounter = 1;

    public static void addSupplier(Supplier supplier) {
        supplier.setId(idCounter++);
        suppliers.add(supplier);
    }

    public static List<Supplier> getAllSuppliers() {
        return new ArrayList<>(suppliers);
    }

    public static Supplier getSupplierById(int id) {
        for (Supplier supplier : suppliers) {
            if (supplier.getId() == id) {
                return supplier;
            }
        }
        return null;
    }

    public static void removeSupplier(int id) {
        suppliers.removeIf(supplier -> supplier.getId() == id);
    }
}
