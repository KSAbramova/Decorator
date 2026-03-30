package gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.event.ItemListener;
import model.*;

public class OrderFrame extends JFrame {
    private final JTable orderTable;
    private final DefaultTableModel tableModel;
    private final JCheckBox cbFiery, cbDouble, cbBerries, cbFlatbread;
    private final JButton btnOrder;

    public OrderFrame() {
        setTitle("Заказ Нордского рагу");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel mainDishLabel = new JLabel("Основное блюдо: Нордское рагу (50 септимов)");
        mainDishLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainDishLabel.setForeground(new Color(0, 102, 204));
        topPanel.add(mainDishLabel);
        add(topPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Время", "Название блюда", "Цена"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        orderTable = new JTable(tableModel);
        
        orderTable.getColumnModel().getColumn(0).setPreferredWidth(90);
        orderTable.getColumnModel().getColumn(1).setPreferredWidth(720);
        orderTable.getColumnModel().getColumn(2).setPreferredWidth(100);

        orderTable.setRowHeight(28);
        orderTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

        JScrollPane tableScroll = new JScrollPane(orderTable);
        JPanel leftPanel = new JPanel(new BorderLayout(0, 5));
        leftPanel.setBorder(BorderFactory.createTitledBorder("История заказов"));
        leftPanel.add(tableScroll, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.CENTER);

        JPanel rightPanel = new JPanel(new GridLayout(4, 1, 10, 15));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Выберите дополнения (максимум 3)"));
        rightPanel.setPreferredSize(new Dimension(320, 0));

        cbFiery = new JCheckBox("Огненный соус (+10 септимов)");
        cbDouble = new JCheckBox("Двойная порция оленнины (+20 септимов)");
        cbBerries = new JCheckBox("Снежные ягоды (+5 септимов)");
        cbFlatbread = new JCheckBox("Нордская лепешка (+7 септимов)");

        Font checkFont = new Font("Arial", Font.PLAIN, 14);
        cbFiery.setFont(checkFont);
        cbDouble.setFont(checkFont);
        cbBerries.setFont(checkFont);
        cbFlatbread.setFont(checkFont);

        rightPanel.add(cbFiery);
        rightPanel.add(cbDouble);
        rightPanel.add(cbBerries);
        rightPanel.add(cbFlatbread);

        add(rightPanel, BorderLayout.EAST);

        btnOrder = new JButton("Оформить заказ");
        btnOrder.setFont(new Font("Arial", Font.BOLD, 18));
        btnOrder.setPreferredSize(new Dimension(0, 60));
        add(btnOrder, BorderLayout.SOUTH);
        
        ItemListener listener = e -> updateCheckboxStates();
        cbFiery.addItemListener(listener);
        cbDouble.addItemListener(listener);
        cbBerries.addItemListener(listener);
        cbFlatbread.addItemListener(listener);

        btnOrder.addActionListener(e -> placeOrder());

        updateCheckboxStates();
    }

    private void updateCheckboxStates() {
        int count = 0;
        if (cbFiery.isSelected()) count++;
        if (cbDouble.isSelected()) count++;
        if (cbBerries.isSelected()) count++;
        if (cbFlatbread.isSelected()) count++;

        boolean canSelectMore = count < 3;

        cbFiery.setEnabled(canSelectMore || cbFiery.isSelected());
        cbDouble.setEnabled(canSelectMore || cbDouble.isSelected());
        cbBerries.setEnabled(canSelectMore || cbBerries.isSelected());
        cbFlatbread.setEnabled(canSelectMore || cbFlatbread.isSelected());
    }

    private void placeOrder() {
        Dish dish = new NordicStew();

        if (cbFiery.isSelected())    dish = new FierySauceDecorator(dish);
        if (cbDouble.isSelected())   dish = new DoubleVenisonDecorator(dish);
        if (cbBerries.isSelected())  dish = new SnowBerriesDecorator(dish);
        if (cbFlatbread.isSelected()) dish = new NordicFlatbreadDecorator(dish);

        String name = dish.getDescription();
        int price = dish.getCost();

        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm.ss");
        String timeStr = now.format(formatter);

        tableModel.addRow(new Object[]{timeStr, name, price + " септимов"});

        cbFiery.setSelected(false);
        cbDouble.setSelected(false);
        cbBerries.setSelected(false);
        cbFlatbread.setSelected(false);
        updateCheckboxStates();
    }

}