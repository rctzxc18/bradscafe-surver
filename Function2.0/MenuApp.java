import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuApp {
    private JFrame frame;
    private JPanel panel;
    private JCheckBox[] menuItems;
    private JButton orderButton;

    // Prices for each menu item
    private double[] prices = {
        129, 129, 60, 90, 100, 120, 60, 110, 
        158, 50, 150, 129, 158, 159, 120
    };

    // Names of the menu items
    private String[] itemNames = {
        "Burger Steak - ₱129",
        "Spam & Egg - ₱129",
        "Classic Burger - ₱60",
        "Americano - ₱90",
        "Belgian Waffles - ₱100",
        "Chocolate Chip Frappe - ₱120",
        "French Fries - ₱60",
        "Chocolate - ₱110",
        "Bacon Carbonara - ₱158",
        "Blue Lemonade - ₱50",
        "Tuna Veggie Salad - ₱150",
        "Special Burger Steak - ₱129",
        "Caldereta - ₱158",
        "Chicken BBQ - ₱159",
        "Spaghetti - ₱120"
    };

    public MenuApp() {
        // Initialize the main frame
        frame = new JFrame("Brad's Eatery Menu");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create checkboxes for each menu item
        menuItems = new JCheckBox[itemNames.length];
        for (int i = 0; i < itemNames.length; i++) {
            menuItems[i] = new JCheckBox(itemNames[i]);
            panel.add(menuItems[i]);
        }

        // Create the order button
        orderButton = new JButton("Place Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proceedToOrder();
            }
        });

        panel.add(orderButton);
        frame.add(panel);
        frame.setSize(300, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void proceedToOrder() {
        StringBuilder orderSummary = new StringBuilder("Your Order:\n");
        double total = 0;

        // Build the order summary and calculate the total
        for (int i = 0; i < menuItems.length; i++) {
            if (menuItems[i].isSelected()) {
                orderSummary.append(itemNames[i]).append("\n");
                total += prices[i];
            }
        }

        // Show the order summary and ask for confirmation
        if (total > 0) {
            orderSummary.append("Total: ₱").append(total);
            int response = JOptionPane.showConfirmDialog(frame, 
                orderSummary.toString() + "\n\nProceed to Order Form?", 
                "Order Summary", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                openOrderForm(total, orderSummary.toString());
            }
        } else {
            JOptionPane.showMessageDialog(frame, 
                "Please select at least one item.", 
                "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void openOrderForm(double total, String orderSummary) {
        JFrame orderFrame = new JFrame("Order Form");
        JPanel orderPanel = new JPanel();
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

        // Display total price and order details
        JLabel totalLabel = new JLabel("Total Price: ₱" + total);
        JTextArea orderDetails = new JTextArea(orderSummary);
        orderDetails.setEditable(false);
        orderDetails.setLineWrap(true);
        orderDetails.setWrapStyleWord(true);

        // Input fields for user details
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone Number:");
        JTextField phoneField = new JTextField();

        // Confirm order button
        JButton confirmButton = new JButton("Confirm Order");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle order confirmation logic
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();

                // Here you can add logic to save the order details
                JOptionPane.showMessageDialog(orderFrame, 
                    "Order confirmed!\nName: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\nThank you for your order.");
                orderFrame.dispose(); // Close the order form
            }
        });

        // Add components to the order panel
        orderPanel.add(totalLabel);
        orderPanel.add(orderDetails);
        orderPanel.add(nameLabel);
        orderPanel.add(nameField);
        orderPanel.add(emailLabel);
        orderPanel.add(emailField);
        orderPanel.add(phoneLabel);
        orderPanel.add(phoneField);
        orderPanel.add(confirmButton);

        // Set up the order frame
        orderFrame.add(orderPanel);
        orderFrame.setSize(300, 400);
        orderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        orderFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuApp::new);
    }
}