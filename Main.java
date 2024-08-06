package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {
    private static Records<CarWashService> carWashServiceRecords = new Records<>();
    private static Records<CarWashService> customCarWashServiceRecords = new Records<>();
    private static LinkedList<Customer> customers = new LinkedList<>();
    private static LinkedList<Car> cars = new LinkedList<>();
    private static Group carWashGroup = new Group();
    private static JComboBox<String> serviceComboBox;
    private static JTextArea invoicesTextArea;

    public static void main(String[] args) {
        setupInitialData();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void setupInitialData() {
        // Add initial services to carWashServiceRecords
        carWashServiceRecords.add(new CarWashService(1, "Basic Wash", "Includes exterior wash", 20.0, VehicleType.CAR));
        carWashServiceRecords.add(new CarWashService(2, "Premium Wash", "Includes exterior and interior cleaning", 30.0, VehicleType.CAR));
        carWashServiceRecords.add(new CarWashService(3, "Deluxe Wash", "Includes exterior wash and wax", 40.0, VehicleType.CAR));
        carWashServiceRecords.add(new CarWashService(4, "Super Deluxe Wash", "Includes exterior and interior wash and wax", 60.0, VehicleType.CAR));
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Jerome's Car Wash Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JTabbedPane tabbedPane = new JTabbedPane();
        ViewServicesPanel viewServicesPanel = new ViewServicesPanel();
        JPanel scheduleAppointmentPanel = createScheduleAppointmentPanel();
        JPanel createCustomServicePanel = createCreateCustomServicePanel(viewServicesPanel);
        ViewInvoicesPanel viewInvoicesPanel = new ViewInvoicesPanel();
        tabbedPane.addTab("View Services", viewServicesPanel);
        tabbedPane.addTab("Schedule Appointment", scheduleAppointmentPanel);
        tabbedPane.addTab("Create Custom Service", createCustomServicePanel);
        tabbedPane.addTab("View Invoices", viewInvoicesPanel);
        frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }


    private static class ViewServicesPanel extends JPanel implements Observer {
        private JTextArea textArea;

        public ViewServicesPanel() {
            setLayout(new BorderLayout());
            textArea = new JTextArea(20, 50);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            add(scrollPane, BorderLayout.CENTER);
            updateServicesTextArea();
            carWashServiceRecords.addObserver(this);
        }

        @Override
        public void update() {
            updateServicesTextArea();
            updateServiceComboBox();
        }

        private void updateServicesTextArea() {
            textArea.setText("Available Car Wash Services:\n");
            for (CarWashService service : carWashServiceRecords.getRecords()) {
                textArea.append("Service ID: " + service.getId() + "\n");
                textArea.append("Service Name: " + service.getName() + "\n");
                textArea.append("Service Description: " + service.getDescription() + "\n");
                textArea.append("Regular Service Price: $" + service.getPriceForVehicleType() + "\n\n");
            }
        }

        private void updateServiceComboBox() {
            serviceComboBox.removeAllItems();
            for (CarWashService service : carWashServiceRecords.getRecords()) {
                serviceComboBox.addItem(service.getName());
            }
        }
    }


    private static JPanel createScheduleAppointmentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left

        // Create and set up labels and fields
        JLabel nameLabel = new JLabel("Customer Name:");
        JTextField nameField = new JTextField(20);
        JLabel emailLabel = new JLabel("Customer Email:");
        JTextField emailField = new JTextField(20);
        JLabel vehicleTypeLabel = new JLabel("Vehicle Type:");
        JComboBox<String> vehicleTypeComboBox = new JComboBox<>();
        for (VehicleType type : VehicleType.values()) {
            vehicleTypeComboBox.addItem(type.toString());
        }
        JLabel makeLabel = new JLabel("Vehicle Make:");
        JTextField makeField = new JTextField(20);
        JLabel modelLabel = new JLabel("Vehicle Model:");
        JTextField modelField = new JTextField(20);
        JLabel colorLabel = new JLabel("Vehicle Color:");
        JTextField colorField = new JTextField(20);
        JLabel licensePlateLabel = new JLabel("License Plate:");
        JTextField licensePlateField = new JTextField(20);
        JLabel servicesLabel = new JLabel("Select Services:");
        JComboBox<String> serviceComboBox = new JComboBox<>();
        for (CarWashService service : carWashServiceRecords.getRecords()) {
            serviceComboBox.addItem(service.getName());
        }
        JButton submitButton = new JButton("Submit");

        // Add components to the panel using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(emailLabel, gbc);
        gbc.gridx = 1;
        panel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(vehicleTypeLabel, gbc);
        gbc.gridx = 1;
        panel.add(vehicleTypeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(makeLabel, gbc);
        gbc.gridx = 1;
        panel.add(makeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(modelLabel, gbc);
        gbc.gridx = 1;
        panel.add(modelField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(colorLabel, gbc);
        gbc.gridx = 1;
        panel.add(colorField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        panel.add(licensePlateLabel, gbc);
        gbc.gridx = 1;
        panel.add(licensePlateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        panel.add(servicesLabel, gbc);
        gbc.gridx = 1;
        panel.add(serviceComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        // Add action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerName = nameField.getText();
                String customerEmail = emailField.getText();
                String vehicleType = (String) vehicleTypeComboBox.getSelectedItem();
                String vehicleMake = makeField.getText();
                String vehicleModel = modelField.getText();
                String vehicleColor = colorField.getText();
                String vehicleLicensePlate = licensePlateField.getText();
                if (!customerName.isEmpty() && !customerEmail.isEmpty() && vehicleType != null && !vehicleMake.isEmpty() && !vehicleModel.isEmpty() && !vehicleColor.isEmpty() && !vehicleLicensePlate.isEmpty()) {
                    Customer customer = new Customer(generateRandomId(), customerName, customerEmail);
                    Car car = new Car(VehicleType.valueOf(vehicleType), vehicleMake, vehicleModel, vehicleColor, vehicleLicensePlate);
                    CarWash carWash = new CarWash(generateRandomId(), generateRandomStationNumber(), customer, car);
                    String selectedServiceName = (String) serviceComboBox.getSelectedItem();
                    for (CarWashService service : carWashServiceRecords.getRecords()) {
                        if (service.getName().equals(selectedServiceName)) {
                            carWash.addService(service);
                        }
                    }
                    carWashGroup.addCarWash(carWash);
                    customers.add(customer);
                    cars.add(car);
                    nameField.setText("");
                    emailField.setText("");
                    makeField.setText("");
                    modelField.setText("");
                    colorField.setText("");
                    licensePlateField.setText("");
                    JOptionPane.showMessageDialog(panel, "Appointment Scheduled Successfully!");
                } else {
                    JOptionPane.showMessageDialog(panel, "Please fill in all fields.");
                }
            }
        });

        // Optional: Add a border around the panel for better separation
        panel.setBorder(BorderFactory.createTitledBorder("Schedule an Appointment"));

        return panel;
    }

    private static JPanel createCreateCustomServicePanel(ViewServicesPanel viewServicesPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));
        JLabel nameLabel = new JLabel("Service Name:");
        JTextField nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Service Description:");
        JTextField descriptionField = new JTextField();
        JLabel priceLabel = new JLabel("Service Price:");
        JTextField priceField = new JTextField();
        JLabel vehicleTypeLabel = new JLabel("Vehicle Type:");
        JComboBox<String> vehicleTypeComboBox = new JComboBox<>();
        for (VehicleType type : VehicleType.values()) {
            vehicleTypeComboBox.addItem(type.toString());
        }
        JButton submitButton = new JButton("Create Service");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serviceName = nameField.getText();
                String serviceDescription = descriptionField.getText();
                String servicePrice = priceField.getText();
                String vehicleType = (String) vehicleTypeComboBox.getSelectedItem();
                if (!serviceName.isEmpty() && !serviceDescription.isEmpty() && !servicePrice.isEmpty() && vehicleType != null) {
                    try {
                        double price = Double.parseDouble(servicePrice);
                        CarWashService customService = new CarWashService(generateRandomId(), serviceName, serviceDescription, price, VehicleType.valueOf(vehicleType));
                        customCarWashServiceRecords.add(customService);
                        carWashServiceRecords.add(customService);
                        nameField.setText("");
                        descriptionField.setText("");
                        priceField.setText("");
                        vehicleTypeComboBox.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(panel, "Custom Service Created Successfully!");
                        viewServicesPanel.update(); // Notify the ViewServicesPanel to refresh the services
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Please enter a valid price.");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Please fill in all fields.");
                }
            }
        });
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(vehicleTypeLabel);
        panel.add(vehicleTypeComboBox);
        panel.add(new JLabel());
        panel.add(submitButton);
        return panel;
    }


    private static class ViewInvoicesPanel extends JPanel implements Observer {
        public ViewInvoicesPanel() {
            setLayout(new BorderLayout());
            invoicesTextArea = new JTextArea(20, 50);
            invoicesTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(invoicesTextArea);
            add(scrollPane, BorderLayout.CENTER);
            updateInvoicesTextArea();
            carWashGroup.addObserver(this);
        }

        @Override
        public void update() {
            updateInvoicesTextArea();
        }

        private void updateInvoicesTextArea() {
            invoicesTextArea.setText("Invoices:\n");
            List<CarWash> carWashes = carWashGroup.getCarWashes();
            for (CarWash carWash : carWashes) {
                invoicesTextArea.append("Invoice for Customer: " + carWash.getCustomer().getName() + "\n");
                invoicesTextArea.append("Car Details: " + carWash.getCar().toString() + "\n");
                invoicesTextArea.append("Service Details:\n");
                for (CarWashService service : carWash.getServices()) {
                    invoicesTextArea.append(" - " + service.getName() + ": $" + service.getPriceForVehicleType() + "\n");
                }
                invoicesTextArea.append("Total Price: $" + carWash.getTotalPrice() + "\n");
                invoicesTextArea.append("Date: " + LocalDateTime.now().toString() + "\n");
                invoicesTextArea.append("Thank You For Washing Your Car At Jerome's Car Wash" + "\n\n");
        }
    }
}
    private static int generateRandomId() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    private static int generateRandomStationNumber() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
}

