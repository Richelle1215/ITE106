import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CalculatorApp {
    // List to store history of results
    private static final ArrayList<String> history = new ArrayList<>();

    public static void main(String[] args) {
       
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK); 

        JTextField display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false); 
        display.setBackground(Color.BLACK); 
        display.setForeground(Color.WHITE); 
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 5, 5)); // Increased rows for history button
        buttonPanel.setBackground(Color.BLACK); 

        String[] buttonTexts = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "History", "Clear"
        };

        for (String text : buttonTexts) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setForeground(Color.WHITE); 

            if (text.matches("[0-9\\.]")) { 
                button.setBackground(Color.decode("#f89DAE"));
            } else if (text.equals("=")) { 
                button.setBackground(Color.decode("#FBCC58"));
            } else if (text.equals("History")) {
                button.setBackground(Color.decode("#507041"));
            } else if (text.equals("Clear")) {  // Special styling for the "Clear" button
                button.setBackground(Color.decode("#FBCC58"));
            } else { 
                button.setBackground(Color.decode("#507041"));
            }

            buttonPanel.add(button);

            button.addActionListener(e -> {
                String command = e.getActionCommand();

                if (command.equals("=")) {
                    try {
                        double result = evaluateExpression(display.getText());
                        String resultText = (result == (long) result) ? String.valueOf((long) result) : String.valueOf(result);
                        history.add(display.getText() + " = " + resultText);  // Store history
                        display.setText(resultText);
                    } catch (Exception ex) {
                        display.setText("Error");
                    }
                } else if (command.equals("History")) {
                    // Show the history in a dialog box
                    StringBuilder historyString = new StringBuilder();
                    for (String entry : history) {
                        historyString.append(entry).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, historyString.toString(), "Calculation History", JOptionPane.INFORMATION_MESSAGE);
                } else if (command.equals("Clear")) {
                    // Clear the display when "Clear" button is clicked
                    display.setText("");
                } else {
                    display.setText(display.getText() + command); 
                }
            });
        }

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static double evaluateExpression(String expression) throws Exception {
        javax.script.ScriptEngineManager manager = new javax.script.ScriptEngineManager();
        javax.script.ScriptEngine engine = manager.getEngineByName("JavaScript");
        return Double.parseDouble(engine.eval(expression).toString());
    }
}
