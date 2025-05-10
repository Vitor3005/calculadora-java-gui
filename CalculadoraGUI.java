import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraGUI extends JFrame implements ActionListener {

    JTextField campoTexto;
    JButton[] botoes = new JButton[10];
    JButton soma, sub, mult, div, igual, limpar;
    JPanel painel;

    double num1 = 0, num2 = 0, resultado = 0;
    char operador;

    public CalculadoraGUI() {
        setTitle("Calculadora - Rayssa");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        campoTexto = new JTextField();
        campoTexto.setEditable(false);
        campoTexto.setFont(new Font("Arial", Font.BOLD, 24));
        add(campoTexto, BorderLayout.NORTH);

        painel = new JPanel();
        painel.setLayout(new GridLayout(4, 4, 5, 5));

        for (int i = 0; i < 10; i++) {
            botoes[i] = new JButton(String.valueOf(i));
            botoes[i].addActionListener(this);
            botoes[i].setFont(new Font("Arial", Font.PLAIN, 20));
        }

        soma = new JButton("+");
        sub = new JButton("-");
        mult = new JButton("*");
        div = new JButton("/");
        igual = new JButton("=");
        limpar = new JButton("C");

        JButton[] operacoes = {soma, sub, mult, div, igual, limpar};
        for (JButton b : operacoes) {
            b.addActionListener(this);
            b.setFont(new Font("Arial", Font.PLAIN, 20));
        }

        // Adicionando os botões ao painel
        painel.add(botoes[7]); painel.add(botoes[8]); painel.add(botoes[9]); painel.add(div);
        painel.add(botoes[4]); painel.add(botoes[5]); painel.add(botoes[6]); painel.add(mult);
        painel.add(botoes[1]); painel.add(botoes[2]); painel.add(botoes[3]); painel.add(sub);
        painel.add(botoes[0]); painel.add(limpar);    painel.add(igual);     painel.add(soma);

        add(painel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == botoes[i]) {
                campoTexto.setText(campoTexto.getText() + i);
            }
        }

        if (e.getSource() == soma || e.getSource() == sub ||
            e.getSource() == mult || e.getSource() == div) {
            try {
                num1 = Double.parseDouble(campoTexto.getText());
                operador = ((JButton) e.getSource()).getText().charAt(0);
                campoTexto.setText("");
            } catch (NumberFormatException ex) {
                campoTexto.setText("Erro");
            }
        }

        if (e.getSource() == igual) {
            try {
                num2 = Double.parseDouble(campoTexto.getText());
                switch (operador) {
                    case '+': resultado = num1 + num2; break;
                    case '-': resultado = num1 - num2; break;
                    case '*': resultado = num1 * num2; break;
                    case '/':
                        if (num2 == 0) {
                            campoTexto.setText("Divisão por zero");
                            return;
                        }
                        resultado = num1 / num2;
                        break;
                }
                campoTexto.setText(String.valueOf(resultado));
            } catch (NumberFormatException ex) {
                campoTexto.setText("Erro");
            }
        }

        if (e.getSource() == limpar) {
            campoTexto.setText("");
            num1 = 0;
            num2 = 0;
            resultado = 0;
        }
    }

    public static void main(String[] args) {
        new CalculadoraGUI();
    }
}
