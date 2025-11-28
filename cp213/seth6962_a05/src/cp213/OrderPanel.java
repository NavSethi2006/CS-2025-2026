package cp213;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.print.PrinterException;
import java.awt.print.*;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * The GUI for the Order class.
 *
 * @author your name here
 * @author Abdul-Rahman Mawlood-Yunis
 * @author David Brown
 * @version 2025-09-07
 */
@SuppressWarnings("serial")
public class OrderPanel extends JPanel {

    /**
     * Implements an ActionListener for the 'Print' button. Prints the current
     * contents of the Order to a system printer or PDF.
     */
    private class PrintListener implements ActionListener {

	@Override
	public void actionPerformed(final ActionEvent e) {
	    // your code here

		PrinterJob job = PrinterJob.getPrinterJob();
	    job.setJobName("Order Printout");

	    String text = order.toString();

        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
                if (pageIndex > 0)
                    return NO_SUCH_PAGE;

                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(pf.getImageableX(), pf.getImageableY());

                // Print each line of order.toString()
                String[] lines = text.split("\n");
                int y = 15;

                for (String line : lines) {
                    g2d.drawString(line, 10, y);
                    y += 15;
                }

                return PAGE_EXISTS;
            }
        });
        
	    boolean ok = job.printDialog();
	    if (ok) {
	        try {
	            job.print();
	        }
	        catch (PrinterException ex) {
	            ex.printStackTrace();
	        }
	    }
		
	}
    }

    /**
     * Implements a FocusListener on a JTextField. Accepts only positive integers,
     * all other values are reset to 0. Adds a new MenuItem to the Order with the
     * new quantity, updates an existing MenuItem in the Order with the new
     * quantity, or removes the MenuItem from the Order if the resulting quantity is
     * 0.
     */
    private class QuantityListener implements FocusListener {
        private MenuItem item;

        QuantityListener(final MenuItem item) {
            this.item = item;
        }

        @Override
        public void focusGained(FocusEvent e) {
            // nothing special, leave user input alone
        }

        @Override
        public void focusLost(FocusEvent e) {
            JTextField field = (JTextField) e.getSource();
            String text = field.getText().trim();
            int qty = 0;

            try {
                qty = Integer.parseInt(text);
                if (qty < 0)
                    qty = 0;
            }
            catch (NumberFormatException ex) {
                qty = 0;
            }

            field.setText(Integer.toString(qty));

            if (qty == 0) {
                order.update(item, qty);
            } else {
                order.add(item, qty);
            }

            // Refresh totals
            subtotalLabel.setText(priceFormat.format(order.getSubTotal()));
            taxLabel.setText(priceFormat.format(order.getTaxes()));
            totalLabel.setText(priceFormat.format(order.getTotal()));
        }
	// your code here

    }

    // Attributes
    private Menu menu = null;
    private final Order order = new Order();
    private final DecimalFormat priceFormat = new DecimalFormat("$##0.00");
    private final JButton printButton = new JButton("Print");
    private final JLabel subtotalLabel = new JLabel("0");
    private final JLabel taxLabel = new JLabel("0");
    private final JLabel totalLabel = new JLabel("0");

    private JLabel nameLabels[] = null;
    private JLabel priceLabels[] = null;
    // TextFields for menu item quantities.
    private JTextField quantityFields[] = null;

    /**
     * Displays the menu in a GUI.
     *
     * @param menu The menu to display.
     */
    public OrderPanel(final Menu menu) {
	this.menu = menu;
	this.nameLabels = new JLabel[this.menu.size()];
	this.priceLabels = new JLabel[this.menu.size()];
	this.quantityFields = new JTextField[this.menu.size()];
	this.layoutView();
	this.registerListeners();
    }

    /**
     * Uses the GridLayout to place the labels and buttons.
     */
    private void layoutView() {

	// your code here
        int n = this.menu.size();

        // n rows for items, plus 4 extra rows for totals + print button
        this.setLayout(new GridLayout(5+n, 3));

        this.add(new JLabel("Item"));
        this.add(new JLabel("Price"));
        this.add(new JLabel("Quantity"));

        for (int i = 0; i < n; i++) {
            MenuItem item = menu.getItem(i);

            nameLabels[i] = new JLabel(item.getName());
            priceLabels[i] = new JLabel(priceFormat.format(item.getTariff()));
            quantityFields[i] = new JTextField("0");

            this.add(nameLabels[i]);
            this.add(priceLabels[i]);
            this.add(quantityFields[i]);
        }

        this.add(new JLabel("Subtotal:"));
        this.add(subtotalLabel);
        this.add(new JLabel(""));

        this.add(new JLabel("Tax:"));
        this.add(taxLabel);
        this.add(new JLabel(""));

        this.add(new JLabel("Total:"));
        this.add(totalLabel);
        this.add(new JLabel(""));

        this.add(new JLabel(""));
        this.add(printButton);
        this.add(new JLabel(""));
    }

    /**
     * Register the widget listeners with the widgets.
     */
    private void registerListeners() {
	// Register the PrinterListener with the print button.
	this.printButton.addActionListener(new PrintListener());

	// your code here

    for (int i = 0; i < quantityFields.length; i++) {
        MenuItem item = menu.getItem(i);
        quantityFields[i].addFocusListener(new QuantityListener(item));
    }
    
    }
}