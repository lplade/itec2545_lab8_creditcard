package name.lplade;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lade on 10/25/16.
 */
public class CCValidator extends JFrame {
    private JTextField creditCardNumberTextField;
    private JButton validateButton;
    private JButton quitButton;
    private JPanel rootPanel;
    private JLabel validMessageLabel;

    public CCValidator() {
        super("Credit Card Validator");
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ccNumber = creditCardNumberTextField.getText();

                boolean valid = isVisaCreditCardNumberValid(ccNumber);

                if (valid) {
                    validMessageLabel.setText("Credit card number is valid");
                } else {
                    validMessageLabel.setText("Credit card number is NOT valid");
                }
            }
        });
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private static boolean isVisaCreditCardNumberValid(String cc) {
        //Cut and pasted from my lab 3 solution

        //populate an array of the digits
        int Digits[] = new int[cc.length()];
        for (int i = 0; i < cc.length(); i++){
            Digits[i] = Character.getNumericValue(cc.charAt(i));
        }

        //First digit MUST be '4' for Visa card
        if (Digits[0] != 4) return false;
        if (cc.length() != 16) return false;

        //Compute the LUHN mod 10 checksum
        int checkSumTotal = 0;

        Boolean doubleNext = false;
        //set up a toggle switch - keeps from having to figure odd/even in case we need to change string length

        for (int i = cc.length()-1; i >= 0; i--) {
            int currentDigitEval = Digits[i];
            if (doubleNext){
                currentDigitEval *= 2; //double
                if (currentDigitEval > 9) {
                    //sum any resulting digits
                    currentDigitEval = (currentDigitEval / 10) + (currentDigitEval % 10);
                }
                doubleNext = false; //flip the toggle
                //System.out.print(currentDigitEval + " + ");
            } else {
                doubleNext = true; //flip the toggle
                //System.out.print(currentDigitEval + " + ");
            }
            checkSumTotal += currentDigitEval;
        }
        //System.out.println();
        //System.out.println("DEBUG: Checksum was " +checkSumTotal);

        if (checkSumTotal % 10 == 0) {
            return true;
        } else {
            return false;
        }

    }
}
