import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

public class IosifClicker implements ActionListener {

    JFrame frame;
    JPanel panel1;
    JPanel panel2;

    JButton IosifButton;
    JLabel IosifLabel;

    JButton Buttonprice2x;
    JButton Buttonprice3x;
    JButton buttonAuto;
    JButton buttonOneMoreAuto;
    JButton buttonLessPrice;

    JLabel price2x;
    JLabel price3x;
    JLabel priceAuto;
    JLabel priceOneMoreAuto;
    JLabel priceLessPrice;

    int points = 0;
    int pointsClick = 1;
    int autoClickers = 0;

    int baseprice2x = 50;
    int baseprice3x = 150;
    int basepriceAuto = 100;
    int basepriceOneMoreAuto = 200;
    int pricePriceReducer = 500;

    boolean bought2x = false;
    boolean bought3x = false;
    boolean boughtPriceReduce = false;
    boolean discount = false;

    Timer autoTimer;

    public static void main(String[] args) {
        new IosifClicker();
    }

    public IosifClicker() {

        frame = new JFrame("Iosif Clicker");
        frame.setSize(800, 500);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        panel1 = new JPanel();
        panel1.setLayout(new GridLayout(2, 1));
        panel1.setBackground(Color.BLACK);

        ImageIcon icon = new ImageIcon("/Users/Iosif.Tolchinski/Downloads/IosifClicker.png");

        if (icon.getIconWidth() > 0) {
            Image scaled = icon.getImage().getScaledInstance(280, 280, Image.SCALE_SMOOTH);
            IosifButton = new JButton(new ImageIcon(scaled));
        } else {
            IosifButton = new JButton("Click Me!");
            IosifButton.setFont(new Font("Arial", Font.BOLD, 36));
        }

        IosifButton.setBackground(Color.ORANGE);
        IosifButton.setBorderPainted(false);
        IosifButton.setFocusPainted(false);
        IosifButton.addActionListener(this);

        IosifLabel = new JLabel("Points: 0");
        IosifLabel.setHorizontalAlignment(SwingConstants.CENTER);
        IosifLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        IosifLabel.setForeground(Color.RED);

        panel1.add(IosifButton);
        panel1.add(IosifLabel);

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(11, 1));
        panel2.setBackground(new Color(20, 20, 50));
        panel2.setPreferredSize(new Dimension(220, 600));

        JLabel upgradeTitle = new JLabel("UPGRADES");
        upgradeTitle.setHorizontalAlignment(SwingConstants.CENTER);
        upgradeTitle.setFont(new Font("Arial", Font.BOLD, 18));
        upgradeTitle.setForeground(Color.WHITE);

        Buttonprice2x = new JButton("2x Clicks");
        Buttonprice2x.setBackground(Color.ORANGE);
        Buttonprice2x.setForeground(Color.BLUE);

        price2x = new JLabel("price: 50 points");
        price2x.setHorizontalAlignment(SwingConstants.CENTER);
        price2x.setForeground(Color.WHITE);

        Buttonprice3x = new JButton("3x Clicks");
        Buttonprice3x.setBackground(Color.ORANGE);
        Buttonprice3x.setForeground(Color.BLUE);

        price3x = new JLabel("price: 150 points");
        price3x.setHorizontalAlignment(SwingConstants.CENTER);
        price3x.setForeground(Color.WHITE);

        buttonAuto = new JButton("Buy Auto-Clicker");
        buttonAuto.setBackground(Color.ORANGE);
        buttonAuto.setForeground(Color.BLUE);

        priceAuto = new JLabel("price: 100 points");
        priceAuto.setHorizontalAlignment(SwingConstants.CENTER);
        priceAuto.setForeground(Color.WHITE);

        buttonOneMoreAuto = new JButton("One MoreAuto-Clicker");
        buttonOneMoreAuto.setBackground(Color.ORANGE);
        buttonOneMoreAuto.setForeground(Color.BLUE);

        priceOneMoreAuto = new JLabel("price: 200 points");
        priceOneMoreAuto.setHorizontalAlignment(SwingConstants.CENTER);
        priceOneMoreAuto.setForeground(Color.WHITE);

        buttonLessPrice = new JButton("Price Reducer (50% off)");
        buttonLessPrice.setBackground(Color.ORANGE);
        buttonLessPrice.setForeground(Color.BLUE);

        priceLessPrice = new JLabel("price: 500 points");
        priceLessPrice.setHorizontalAlignment(SwingConstants.CENTER);
        priceLessPrice.setForeground(Color.WHITE);

        Buttonprice2x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (bought2x) {
                    return;
                }
                int price = baseprice2x;
                if (discount) {
                    price = baseprice2x / 2;
                }
                if (points >= price) {
                    points = points - price;
                    pointsClick = 2;
                    bought2x = true;
                    Buttonprice2x.setEnabled(false);
                    IosifLabel.setText("Points: " + points);
                }
            }
        });

        Buttonprice3x.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (bought3x) {
                    return;
                }
                int price = baseprice3x;
                if (discount) {
                    price = baseprice3x / 2;
                }
                if (points >= price) {
                    points = points - price;
                    pointsClick = 3;
                    bought3x = true;
                    Buttonprice3x.setEnabled(false);
                    IosifLabel.setText("Points: " + points);
                }
            }
        });

        buttonAuto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (autoClickers > 0) {
                    return;
                }
                int price = basepriceAuto;
                if (discount) {
                    price = basepriceAuto / 2;
                }
                if (points >= price) {
                    points = points - price;
                    autoClickers = autoClickers + 1;
                    IosifLabel.setText("Points: " + points);
                }
            }
        });

        buttonOneMoreAuto.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int price = basepriceOneMoreAuto;
                if (discount) {
                    price = basepriceOneMoreAuto / 2;
                }
                if (points >= price) {
                    points = points - price;
                    autoClickers = autoClickers + 1;
                    IosifLabel.setText("Points: " + points);
                }
            }
        });


        buttonLessPrice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (boughtPriceReduce) {
                    return;
                }
                if (points >= pricePriceReducer) {
                    points = points - pricePriceReducer;
                    discount = true;
                    boughtPriceReduce = true;
                    buttonLessPrice.setEnabled(false);
                    price2x.setText("Price: " + baseprice2x / 2 + " points");
                    price3x.setText("Price: " + baseprice3x / 2 + " points");
                    priceAuto.setText("Price: " + basepriceAuto / 2 + " points");
                    priceOneMoreAuto.setText("Price: " + basepriceOneMoreAuto / 2 + " points");
                    IosifLabel.setText("Points: " + points);
                }
            }
        });

        panel2.add(upgradeTitle);
        panel2.add(Buttonprice2x);
        panel2.add(price2x);
        panel2.add(Buttonprice3x);
        panel2.add(price3x);
        panel2.add(buttonAuto);
        panel2.add(priceAuto);
        panel2.add(buttonOneMoreAuto);
        panel2.add(priceOneMoreAuto);
        panel2.add(buttonLessPrice);
        panel2.add(priceLessPrice);

        frame.add(panel1, BorderLayout.CENTER);
        frame.add(panel2, BorderLayout.EAST);

        frame.setVisible(true);

        autoTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (autoClickers > 0) {
                    points = points + (autoClickers * pointsClick);
                    IosifLabel.setText("Points: " + points);
                }
            }
        });
        autoTimer.start();
    }

    public void actionPerformed(ActionEvent e) {
        points = points + pointsClick;
        IosifLabel.setText("Points: " + points);
    }
}