import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    public MainFrame() {
        setTitle("BMI APP");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(new WelcomePanel(this), "welcome");
        mainPanel.add(new CalculatorPanel(), "bmi");

        add(mainPanel);
        setVisible(true);    }
    public void showBMIPage() {
        cardLayout.show(mainPanel, "bmi");
}
}
class WelcomePanel extends JPanel {
    public WelcomePanel(MainFrame frame) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JLabel title = new JLabel("BMI Calculator", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
       JTextArea description = new JTextArea(
        "This app calculates your Body Mass Index (BMI).\n" +
        "Enter your height and weight to see your body status."
);

        description.setEditable(false);
        description.setLineWrap(true);
        description.setWrapStyleWord(true);
        description.setFont(new Font("Arial", Font.PLAIN, 14));
        description.setBackground(getBackground());

        JButton startButton = new JButton("Start Application");
        startButton.addActionListener(e -> frame.showBMIPage());

        add(title, BorderLayout.NORTH);
        add(description, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
}
}
class CalculatorPanel extends JPanel {
    private JTextField heightField;
    private JTextField weightField;
    private JLabel resultLabel;
    public CalculatorPanel() {
        setLayout(new GridLayout(8, 2, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel heightLabel = new JLabel("Height (cm):");
        JLabel weightLabel = new JLabel("Weight (kg):");

        heightField = new JTextField();
        weightField = new JTextField();

        JLabel genderLabel = new JLabel("Gender:");
        JComboBox<String> genderBox = new JComboBox<>(new String[]{"Male", "Female"});

        JButton calculateButton = new JButton("Calculate BMI");
        JButton colorButton = new JButton("Change Background Color");

        resultLabel = new JLabel("Result: ");

        calculateButton.addActionListener(e -> calculateBMI());
        colorButton.addActionListener(e -> chooseBackgroundColor());

        add(heightLabel);
        add(heightField);
        add(weightLabel);
        add(weightField);
        add(genderLabel);
        add(genderBox);
        add(calculateButton);
        add(colorButton);
        add(new JLabel());
        add(resultLabel);
    }
    private void calculateBMI() {
        try {
            double heightCm = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());

            double heightM = heightCm / 100;
            double bmi = weight / (heightM * heightM);

            String status;
            if (bmi < 18.5)
                status = "Underweight";
            else if (bmi < 25)
                status = "Normal";
            else if (bmi < 30)
                status = "Overweight";
            else
                status = "Obese";

            resultLabel.setText(String.format("Result: BMI = %.2f (%s)", bmi, status));
    } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter valid numeric values",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
 );
    }
}
    private void chooseBackgroundColor() {
        Color selectedColor = JColorChooser.showDialog(
                this,
                "Choose Background Color",
                getBackground()
    );

        if (selectedColor != null) {
            setBackground(selectedColor);
            for (Component component : getComponents()) {
                component.setBackground(selectedColor);        }
       }
    }}
