import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class calculWin {
    calc calc = new calc();
    JFrame f;
    ArrayList<JButton> L1 = new ArrayList<JButton>();
    private static String equation;
    private static String b;

    calculWin() {
        f = new JFrame("Calculatrice");//creating instance of JFrame
        f.setSize(400, 700);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JTextArea t1 = new JTextArea();
        p1.setLayout(new BorderLayout());
        p1.add(t1, BorderLayout.NORTH);
        for (int i = 1; i <= 9; i++) {
            L1.add(new JButton(Integer.toString(i)));
        }


        // setting grid layout of 3 rows and 3 columns

        JButton plusb = new JButton("+");

        L1.add(plusb);
        JButton minusb = new JButton("-");

        L1.add(minusb);
        JButton fois = new JButton("X");

        L1.add(fois);
        JButton division = new JButton("/");

        L1.add(division);
        JButton egale = new JButton("=");

        L1.add(egale);
        L1.add(new JButton("C"));
        for (JButton btn : L1) {
            p2.add(btn);
        }
        p2.setLayout(new GridLayout(4, 4));
        p1.add(p2, BorderLayout.CENTER);
        f.setContentPane(p1);
        for (JButton btn : L1) {
            if (btn == fois)
                btn.addActionListener(new ActionListener(  ){
                 public void actionPerformed(ActionEvent e){
                     t1.setText(calc.setOperator(Operators.MULTIPLE));
                    }
                });
            else if (btn == plusb)
                btn.addActionListener(new ActionListener(  ){
                    public void actionPerformed(ActionEvent e){
                        t1.setText(calc.setOperator(Operators.ADD));
                    }
                });
            else if (btn == minusb)
                btn.addActionListener(new ActionListener(  ){
                    public void actionPerformed(ActionEvent e){
                        t1.setText(calc.setOperator(Operators.MINUS));
                    }
                });
            else if (btn == division)
                btn.addActionListener(new ActionListener(  ){
                    public void actionPerformed(ActionEvent e){
                        t1.setText(calc.setOperator(Operators.DIVIDE));
                    }
                });
            else if (btn.getText().equals("C") ) {
                btn.addActionListener(new ActionListener(  ){
                    public void actionPerformed(ActionEvent e){
                        t1.setText(calc.clear());
                    }
                });
            } else if (btn.getText().equals("=")) {
                btn.addActionListener(new ActionListener(  ){
                    public void actionPerformed(ActionEvent e){
                        t1.setText(calc.equals());
                    }
                });
            } else {
                btn.addActionListener(new ActionListener(  ){
                    public void actionPerformed(ActionEvent e){
                        t1.setText(calc.addDigit(Integer.parseInt(btn.getText())));
                    }
                });
            }
        }
        //f.pack();
        f.setVisible(true);//making the frame visible


    }

}

