/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package po23s.view;

/**
 *
 * @author jonat
 */
import po23s.model.Ticker;
import po23s.http.ClienteHttp;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private JTextField txtTicker;
    private JButton btnAdicionar;
    private JButton btnAtualizar;
    private JButton btnRemover;
    private JTable table;
    private DefaultTableModel tableModel;

    private List<Ticker> tickers = new ArrayList<>();

    public MainFrame() {
        setTitle("CCH - Criptos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        txtTicker = new JTextField(8);
        btnAdicionar = new JButton("Adicionar");
        btnAtualizar = new JButton("Atualizar");
        btnRemover = new JButton("Remover");

        tableModel = new DefaultTableModel(new Object[]{"Ticker", "Buy", "Sell"}, 0);
        table = new JTable(tableModel);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Código:"));
        topPanel.add(txtTicker);
        topPanel.add(btnAdicionar);
        topPanel.add(btnAtualizar);
        topPanel.add(btnRemover);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        btnAdicionar.addActionListener(e -> adicionarTicker());
        btnAtualizar.addActionListener(e -> atualizarTickers());
        btnRemover.addActionListener(e -> removerTicker());
    }

    private void adicionarTicker() {
        String codigo = txtTicker.getText().trim().toUpperCase();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe um código de ticker.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        for (Ticker t : tickers) {
            if (t.getNome().equals(codigo)) {
                JOptionPane.showMessageDialog(this, "Ticker já adicionado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        String url = String.format("https://www.mercadobitcoin.net/api/%s/ticker", codigo);
        try {
            String resposta = new ClienteHttp().buscaDados(url);
            if ("Not Found\n".equals(resposta)) {
                JOptionPane.showMessageDialog(this, "Ticker não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JSONObject obj = new JSONObject(resposta).getJSONObject("ticker");
            double buy = obj.getDouble("buy");
            double sell = obj.getDouble("sell");

            Ticker novo = new Ticker(codigo, buy, sell);
            tickers.add(novo);
