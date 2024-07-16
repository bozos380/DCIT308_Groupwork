package com.example.pharmacy;

import com.example.pharmacy.dao.DrugDAO;
import com.example.pharmacy.dao.SupplierDAO;
import com.example.pharmacy.model.Drug;
import com.example.pharmacy.model.Purchase;
import com.example.pharmacy.model.Supplier;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Main class for the Pharmacy Management System.
 * Initializes the database, sets up the main frame, and manages the different panels.
 */
public class Main {
    private static JFrame frame;
    private static JPanel mainPanel;
    private static JPanel currentPanel;

    /**
     * The entry point of the Pharmacy Management System.
     * Initializes the database and sets up the main GUI frame.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // Initialize Database
        Database.initialize();

        // Setup Main Frame
        frame = new JFrame("Pharmacy Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 800);

        mainPanel = new JPanel(new BorderLayout());
        frame.getContentPane().add(mainPanel);

        setupMenuBar();

        // Initially show the View Drugs section
        showViewDrugsPanel();

        frame.setVisible(true);
    }

    /**
     * Sets up the menu bar with options for managing drugs and suppliers.
     */
    private static void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu drugMenu = new JMenu("Drugs");

        JMenuItem addDrugItem = new JMenuItem("Add Drug");
        addDrugItem.addActionListener(e -> showAddDrugPanel());
        drugMenu.add(addDrugItem);

        JMenuItem viewDrugsItem = new JMenuItem("View Drugs");
        viewDrugsItem.addActionListener(e -> showViewDrugsPanel());
        drugMenu.add(viewDrugsItem);

        JMenuItem searchDrugItem = new JMenuItem("Search Drugs");
        searchDrugItem.addActionListener(e -> showSearchDrugPanel());
        drugMenu.add(searchDrugItem);

        menuBar.add(drugMenu);

        JMenu supplierMenu = new JMenu("Suppliers");

        JMenuItem addSupplierItem = new JMenuItem("Add Supplier");
        addSupplierItem.addActionListener(e -> showAddSupplierPanel());
        supplierMenu.add(addSupplierItem);

        JMenuItem viewSuppliersItem = new JMenuItem("View Suppliers");
        viewSuppliersItem.addActionListener(e -> showViewSuppliersPanel());
        supplierMenu.add(viewSuppliersItem);

        menuBar.add(supplierMenu);

        frame.setJMenuBar(menuBar);
    }

    /**
     * Displays the Add Drug panel where users can add new drugs.
     */
    private static void showAddDrugPanel() {
        if (currentPanel != null) {
            mainPanel.remove(currentPanel);
        }

        currentPanel = new JPanel();
        currentPanel.setLayout(new GridLayout(6, 2));

        JLabel drugNameLabel = new JLabel("Drug Name:");
        JTextField drugNameField = new JTextField(20);
        JLabel drugManufacturerLabel = new JLabel("Manufacturer:");
        JTextField drugManufacturerField = new JTextField(20);
        JLabel drugPriceLabel = new JLabel("Price:");
        JTextField drugPriceField = new JTextField(10);
        JLabel drugQuantityLabel = new JLabel("Quantity:");
        JTextField drugQuantityField = new JTextField(5);
        JLabel supplierLabel = new JLabel("Supplier:");
        JComboBox<Supplier> supplierComboBox = new JComboBox<>();
        for (Supplier supplier : SupplierDAO.getAllSuppliers()) {
            supplierComboBox.addItem(supplier);
        }

        JButton addDrugButton = new JButton("Add Drug");
        addDrugButton.addActionListener(e -> {
            String name = drugNameField.getText();
            String manufacturer = drugManufacturerField.getText();
            double price = Double.parseDouble(drugPriceField.getText());
            int quantity = Integer.parseInt(drugQuantityField.getText());
            Supplier supplier = (Supplier) supplierComboBox.getSelectedItem();

            if (supplier != null) {
                Drug drug = new Drug(name, manufacturer, price, quantity, supplier.getId());
                DrugDAO.addDrug(drug);
                JOptionPane.showMessageDialog(frame, "Drug added successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a supplier!");
            }

            drugNameField.setText("");
            drugManufacturerField.setText("");
            drugPriceField.setText("");
            drugQuantityField.setText("");
        });

        currentPanel.add(drugNameLabel);
        currentPanel.add(drugNameField);
        currentPanel.add(drugManufacturerLabel);
        currentPanel.add(drugManufacturerField);
        currentPanel.add(drugPriceLabel);
        currentPanel.add(drugPriceField);
        currentPanel.add(drugQuantityLabel);
        currentPanel.add(drugQuantityField);
        currentPanel.add(supplierLabel);
        currentPanel.add(supplierComboBox);
        currentPanel.add(new JLabel());
        currentPanel.add(addDrugButton);

        mainPanel.add(currentPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * Displays the View Drugs panel where users can view all drugs.
     */
    private static void showViewDrugsPanel() {
        if (currentPanel != null) {
            mainPanel.remove(currentPanel);
        }

        currentPanel = new JPanel();
        currentPanel.setLayout(new BorderLayout());

        List<Drug> drugs = DrugDAO.getAllDrugs();
        String[] columnNames = {"ID", "Name", "Manufacturer", "Price", "Quantity", "Supplier ID"};
        String[][] data = new String[drugs.size()][6];

        for (int i = 0; i < drugs.size(); i++) {
            Drug drug = drugs.get(i);
            data[i][0] = String.valueOf(drug.getId());
            data[i][1] = drug.getName();
            data[i][2] = drug.getManufacturer();
            data[i][3] = String.valueOf(drug.getPrice());
            data[i][4] = String.valueOf(drug.getQuantity());
            data[i][5] = String.valueOf(drug.getSupplierId());
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                int drugId = Integer.parseInt(data[selectedRow][0]);
                showDrugPurchaseHistoryPanel(drugId);
            }
        });

        currentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(currentPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * Displays the Purchase History panel for a specific drug.
     *
     * @param drugId the ID of the drug to view the purchase history for
     */
    private static void showDrugPurchaseHistoryPanel(int drugId) {
        Drug drug = DrugDAO.getDrugById(drugId);

        if (drug != null) {
            JPanel purchaseHistoryPanel = new JPanel(new BorderLayout());

            List<Purchase> purchases = DrugDAO.getPurchaseHistory(drugId);
            String[] columnNames = {"ID", "Buyer", "Date", "Time", "Quantity"};
            String[][] data = new String[purchases.size()][5];

            for (int i = 0; i < purchases.size(); i++) {
                Purchase purchase = purchases.get(i);
                data[i][0] = String.valueOf(purchase.getId());
                data[i][1] = purchase.getBuyer();
                data[i][2] = purchase.getDateTime().toLocalDate().toString();
                data[i][3] = purchase.getDateTime().toLocalTime().toString();
                data[i][4] = String.valueOf(purchase.getQuantity());
            }

            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);

            purchaseHistoryPanel.add(scrollPane, BorderLayout.CENTER);

            JOptionPane.showMessageDialog(frame, purchaseHistoryPanel, "Purchase History", JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Displays the Add Supplier panel where users can add new suppliers.
     */
    private static void showAddSupplierPanel() {
        if (currentPanel != null) {
            mainPanel.remove(currentPanel);
        }

        currentPanel = new JPanel();
        currentPanel.setLayout(new GridLayout(3, 2));

        JLabel supplierNameLabel = new JLabel("Supplier Name:");
        JTextField supplierNameField = new JTextField(20);
        JLabel contactInfoLabel = new JLabel("Contact Info:");
        JTextField contactInfoField = new JTextField(20);

        JButton addSupplierButton = new JButton("Add Supplier");
        addSupplierButton.addActionListener(e -> {
            String name = supplierNameField.getText();
            String contactInfo = contactInfoField.getText();

            Supplier supplier = new Supplier(name, contactInfo);
            SupplierDAO.addSupplier(supplier);
            JOptionPane.showMessageDialog(frame, "Supplier added successfully!");

            supplierNameField.setText("");
            contactInfoField.setText("");
        });

        currentPanel.add(supplierNameLabel);
        currentPanel.add(supplierNameField);
        currentPanel.add(contactInfoLabel);
        currentPanel.add(contactInfoField);
        currentPanel.add(new JLabel());
        currentPanel.add(addSupplierButton);

        mainPanel.add(currentPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * Displays the View Suppliers panel where users can view all suppliers.
     */
    private static void showViewSuppliersPanel() {
        if (currentPanel != null) {
            mainPanel.remove(currentPanel);
        }

        currentPanel = new JPanel();
        currentPanel.setLayout(new BorderLayout());

        List<Supplier> suppliers = SupplierDAO.getAllSuppliers();
        String[] columnNames = {"ID", "Name", "Contact Info"};
        String[][] data = new String[suppliers.size()][3];

        for (int i = 0; i < suppliers.size(); i++) {
            Supplier supplier = suppliers.get(i);
            data[i][0] = String.valueOf(supplier.getId());
            data[i][1] = supplier.getName();
            data[i][2] = supplier.getContactInfo();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        currentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(currentPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * Displays the Search Drug panel where users can search for drugs based on different criteria.
     */
    private static void showSearchDrugPanel() {
        if (currentPanel != null) {
            mainPanel.remove(currentPanel);
        }

        currentPanel = new JPanel();
        currentPanel.setLayout(new GridLayout(5, 2));

        JLabel drugNameLabel = new JLabel("Drug Name:");
        JTextField drugNameField = new JTextField(20);
        JLabel drugManufacturerLabel = new JLabel("Manufacturer:");
        JTextField drugManufacturerField = new JTextField(20);
        JLabel drugPriceLabel = new JLabel("Price:");
        JTextField drugPriceField = new JTextField(10);
        JLabel supplierLabel = new JLabel("Supplier:");
        JComboBox<Supplier> supplierComboBox = new JComboBox<>();
        for (Supplier supplier : SupplierDAO.getAllSuppliers()) {
            supplierComboBox.addItem(supplier);
        }

        JButton searchDrugButton = new JButton("Search Drug");
        searchDrugButton.addActionListener(e -> {
            String name = drugNameField.getText();
            String manufacturer = drugManufacturerField.getText();
            double price = drugPriceField.getText().isEmpty() ? -1 : Double.parseDouble(drugPriceField.getText());
            Supplier supplier = (Supplier) supplierComboBox.getSelectedItem();

            List<Drug> searchResults = DrugDAO.searchDrugs(name, manufacturer, price, supplier != null ? supplier.getId() : -1);
            showSearchResultsPanel(searchResults);
        });

        currentPanel.add(drugNameLabel);
        currentPanel.add(drugNameField);
        currentPanel.add(drugManufacturerLabel);
        currentPanel.add(drugManufacturerField);
        currentPanel.add(drugPriceLabel);
        currentPanel.add(drugPriceField);
        currentPanel.add(supplierLabel);
        currentPanel.add(supplierComboBox);
        currentPanel.add(new JLabel());
        currentPanel.add(searchDrugButton);

        mainPanel.add(currentPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    /**
     * Displays the search results for the drug search panel.
     *
     * @param searchResults the list of drugs that match the search criteria
     */
    private static void showSearchResultsPanel(List<Drug> searchResults) {
        if (currentPanel != null) {
            mainPanel.remove(currentPanel);
        }

        currentPanel = new JPanel();
        currentPanel.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Name", "Manufacturer", "Price", "Quantity", "Supplier ID"};
        String[][] data = new String[searchResults.size()][6];

        for (int i = 0; i < searchResults.size(); i++) {
            Drug drug = searchResults.get(i);
            data[i][0] = String.valueOf(drug.getId());
            data[i][1] = drug.getName();
            data[i][2] = drug.getManufacturer();
            data[i][3] = String.valueOf(drug.getPrice());
            data[i][4] = String.valueOf(drug.getQuantity());
            data[i][5] = String.valueOf(drug.getSupplierId());
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        currentPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(currentPanel, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
