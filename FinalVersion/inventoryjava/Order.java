/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package inventoryjava;

//import statements
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

public class Order extends javax.swing.JFrame {

    public Order() {
        initComponents();
        SelectProduct();	//method calls in constructor
    	SelectCustomer();
    	getToday();
    }
    
    //prepares for database connection
    Connection Con = null;
    Statement St = null;
    ResultSet Rs = null;
    
    //selects customer from table
public void SelectCustomer(){
    try{
        Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Retaildb", "CoffeeRunners", "1234");
        St = Con.createStatement();
        Rs = St.executeQuery("select * from CUSTOMERTABLE");
        CustomerTable.setModel(DbUtils.resultSetToTableModel(Rs));
    } catch(SQLException e){
        e.printStackTrace();    //only for debugging, switch to logger
    }
}

//gets today's date for order form records
private void getToday(){
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	//System.out.println(dtf.format(now));
	DateSet.setText(dtf.format(now));
}

//selects product from table
public void SelectProduct(){
	try{
    	Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Retaildb", "CoffeeRunners", "1234");
    	St = Con.createStatement();
    	Rs = St.executeQuery("select * from PRODUCTTABLE");
        int currentStock = fetchCurrentStock(ProductUid);   //for quantity validation later
    	ProdTable.setModel(DbUtils.resultSetToTableModel(Rs));
	} catch(SQLException e){
    	e.printStackTrace();	//only for debugging, switch to logger
	}
}

//updates quantity in table
private void Update(){
    int NewQty = OldQty - Integer.parseInt(QtyBox.getText()); 

    //connection reset
    Connection Con = null;
    PreparedStatement stmt = null;

    try {
        //connects to database
        Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Retaildb", "CoffeeRunners", "1234");

        String UpdateQuery = "UPDATE CoffeeRunners.PRODUCTTABLE SET PRODUCTQUANTITY = ? WHERE PRODUCTUID = ?";
        stmt = Con.prepareStatement(UpdateQuery);

        stmt.setInt(1, NewQty); 
        stmt.setInt(2, ProductUid);

        //actually updates the info; informs the user of result
        int rowsAffected = stmt.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Quantity updated successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "No matching product found to update.");
        }

        SelectProduct();

    } catch (SQLException e) {
        e.printStackTrace();  // Log the specific SQL exception
        JOptionPane.showMessageDialog(this, "Error while updating product quantity: " + e.getMessage());
    } finally {
        try {
            //check for null connections; closes connections
            if (stmt != null) stmt.close();
            if (Con != null) Con.close();
        } catch (SQLException e) {
            e.printStackTrace();  // Log any issues with closing resources
        }
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ExitBtn = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        CustomerTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        ProdTable = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        OrderBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        QtyBox = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        PriceBox = new javax.swing.JTextField();
        DateSet = new javax.swing.JLabel();
        CustomerName = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        BillTable = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        HomeBtn = new javax.swing.JButton();
        PrintBtn = new javax.swing.JButton();
        AddItem = new javax.swing.JButton();
        AddOrderBtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        ViewBtn = new javax.swing.JButton();
        OrderTotBox = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 51, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Inventory Management System");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Manage Orders");

        ExitBtn.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        ExitBtn.setForeground(new java.awt.Color(255, 255, 255));
        ExitBtn.setText("X");
        ExitBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)))
                .addGap(313, 313, 313)
                .addComponent(ExitBtn)
                .addGap(43, 43, 43))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ExitBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        CustomerTable.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        CustomerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer ID", "Name", "Phone"
            }
        ));
        CustomerTable.setRowHeight(30);
        CustomerTable.setSelectionBackground(new java.awt.Color(51, 51, 255));
        CustomerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CustomerTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(CustomerTable);

        ProdTable.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        ProdTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UID", "Product", "Quantity", "Description", "Category"
            }
        ));
        ProdTable.setRowHeight(30);
        ProdTable.setSelectionBackground(new java.awt.Color(51, 51, 255));
        ProdTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ProdTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ProdTable);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 255));
        jLabel9.setText("CUSTOMERS");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 51, 255));
        jLabel10.setText("PRODUCTS");

        OrderBox.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        OrderBox.setForeground(new java.awt.Color(51, 51, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 255));
        jLabel4.setText("Order ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 255));
        jLabel5.setText("Customer");

        QtyBox.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        QtyBox.setForeground(new java.awt.Color(51, 51, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 255));
        jLabel6.setText("Date");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 255));
        jLabel7.setText("Quantity");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 255));
        jLabel8.setText("Price");

        PriceBox.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        PriceBox.setForeground(new java.awt.Color(51, 51, 255));

        DateSet.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        DateSet.setForeground(new java.awt.Color(255, 153, 0));
        DateSet.setText("Date");

        CustomerName.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        CustomerName.setForeground(new java.awt.Color(255, 153, 0));
        CustomerName.setText("Customer");

        BillTable.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        BillTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Num", "Product", "Quantity", "UPrice", "Total"
            }
        ));
        BillTable.setRowHeight(30);
        BillTable.setSelectionBackground(new java.awt.Color(51, 51, 255));
        jScrollPane3.setViewportView(BillTable);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 255));
        jLabel13.setText("Amount");

        HomeBtn.setBackground(new java.awt.Color(255, 153, 0));
        HomeBtn.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        HomeBtn.setForeground(new java.awt.Color(255, 255, 255));
        HomeBtn.setText("Home");
        HomeBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeBtnMouseClicked(evt);
            }
        });
        HomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeBtnActionPerformed(evt);
            }
        });

        PrintBtn.setBackground(new java.awt.Color(255, 153, 0));
        PrintBtn.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        PrintBtn.setForeground(new java.awt.Color(255, 255, 255));
        PrintBtn.setText("Print");
        PrintBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrintBtnMouseClicked(evt);
            }
        });
        PrintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintBtnActionPerformed(evt);
            }
        });

        AddItem.setBackground(new java.awt.Color(51, 51, 255));
        AddItem.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        AddItem.setForeground(new java.awt.Color(255, 255, 255));
        AddItem.setText("Add to Order");
        AddItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddItemMouseClicked(evt);
            }
        });
        AddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddItemActionPerformed(evt);
            }
        });

        AddOrderBtn.setBackground(new java.awt.Color(51, 51, 255));
        AddOrderBtn.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        AddOrderBtn.setForeground(new java.awt.Color(255, 255, 255));
        AddOrderBtn.setText("Add Order");
        AddOrderBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddOrderBtnMouseClicked(evt);
            }
        });
        AddOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddOrderBtnActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 255));
        jLabel14.setText("BILL:");

        ViewBtn.setBackground(new java.awt.Color(51, 51, 255));
        ViewBtn.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        ViewBtn.setForeground(new java.awt.Color(255, 255, 255));
        ViewBtn.setText("View Orders");
        ViewBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ViewBtnMouseClicked(evt);
            }
        });
        ViewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewBtnActionPerformed(evt);
            }
        });

        OrderTotBox.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        OrderTotBox.setForeground(new java.awt.Color(255, 153, 0));
        OrderTotBox.setText("Amount");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 586, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(AddOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(OrderBox, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QtyBox, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PriceBox, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(AddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addGap(59, 59, 59))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(PrintBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(6, 6, 6)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CustomerName)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(134, 134, 134)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(HomeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(ViewBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(OrderTotBox, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(DateSet))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(325, 325, 325))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OrderBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(QtyBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(PriceBox, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DateSet, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ViewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PrintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HomeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(OrderTotBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(130, 130, 130)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //user selects content from table
    private void CustomerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerTableMouseClicked
        DefaultTableModel model = (DefaultTableModel)CustomerTable.getModel();
        int Myindex = CustomerTable.getSelectedRow();
        OrderBox.setText(model.getValueAt(Myindex, 0).toString());
        CustomerName.setText(model.getValueAt(Myindex, 1).toString());
    }//GEN-LAST:event_CustomerTableMouseClicked

    //prepares for method of selecting product in table
    int flag = 0, ProductUid, OldQty;

    private void ProdTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ProdTableMouseClicked
        DefaultTableModel model = (DefaultTableModel)ProdTable.getModel();
        int Myindex = ProdTable.getSelectedRow();
        ProductUid = (int) model.getValueAt(Myindex, 0);
    	ProdName = model.getValueAt(Myindex, 1).toString();
    	OldQty = Integer.parseInt(model.getValueAt(Myindex, 2).toString());
    	flag = 1;
    }//GEN-LAST:event_ProdTableMouseClicked

    private void HomeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HomeBtnActionPerformed

    private void PrintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PrintBtnActionPerformed

    private void AddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddItemActionPerformed

    private void AddOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddOrderBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_AddOrderBtnActionPerformed

    private void AddOrderBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddOrderBtnMouseClicked
        //checks for null input
        if(OrderBox.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter the Bill ID: ");
        } else {
            try {
                //connects to database
                Con = DriverManager.getConnection("jdbc:derby://localhost:1527/Retaildb", "CoffeeRunners", "1234");

                //queries for order ID; checks for result
                String checkQuery = "SELECT COUNT(*) FROM ORDERTABLE WHERE OrderID = ?";
                PreparedStatement checkStmt = Con.prepareStatement(checkQuery);
                checkStmt.setInt(1, Integer.parseInt(OrderBox.getText()));
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                int count = rs.getInt(1);

                //validates order ID is unique
                if (count > 0) {
                    JOptionPane.showMessageDialog(this, "This Order ID already exists.");
                } else {
                    //inserts values into ordertable
                    String insertQuery = "INSERT INTO ORDERTABLE (OrderID, CustomerName, OrderDate, Amount) VALUES (?, ?, ?, ?)";
                    PreparedStatement addStmt = Con.prepareStatement(insertQuery);
                    addStmt.setInt(1, Integer.parseInt(OrderBox.getText()));
                    addStmt.setString(2, CustomerName.getText());
                    addStmt.setString(3, DateSet.getText());
                    addStmt.setInt(4, Integer.parseInt(OrderBox.getText()));

                    int row = addStmt.executeUpdate();

                    //informs user if content inserted into ordertable
                    if (row > 0) {
                        JOptionPane.showMessageDialog(this, "Order successfully added.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Failed to add order.");
                    }
                }

                //close connection
                Con.close();

            } catch(SQLException e) {
                e.printStackTrace();  // This will help in debugging
            }
        }             	 
    }//GEN-LAST:event_AddOrderBtnMouseClicked

    private void ExitBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitBtnMouseClicked
        System.exit(0);
    }//GEN-LAST:event_ExitBtnMouseClicked

    //checks product quantity; returns current stock values
    private int fetchCurrentStock(int productUid) {
        int currentStock = -1;                  // Default invalid value
        try {
            //connects to database
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/Retaildb", "CoffeeRunners", "1234");
            String query = "SELECT PRODUCTQUANTITY FROM PRODUCTTABLE WHERE PRODUCTUID = ?";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setInt(1, productUid);

            //executes query and sends to currentstock
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                currentStock = rs.getInt("PRODUCTQUANTITY");
            }

            con.close();
            
        } catch (SQLException e) {
            e.printStackTrace(); // Log error
        }

        return currentStock;
    }

    //prepares for add item method
    int i = 1;
    int Total = 0, OrderTot = 0, Uprice;
    String ProdName;

    private void AddItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddItemMouseClicked
    //conditions for using method
        if (flag == 0 || QtyBox.getText().isEmpty() || PriceBox.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Select a product and enter the quantity.");
    } else {
        try {
            //compare quantity with currentstock
            Uprice = Integer.parseInt(PriceBox.getText());
            int quantity = Integer.parseInt(QtyBox.getText());
            int currentStock = fetchCurrentStock(ProductUid);

            //informs user stock will be negative
            if (quantity > currentStock) {
                JOptionPane.showMessageDialog(this, "Not enough stock available.");
                return;
            }

            //computes total
            Total = Uprice * quantity;

            //inserts product into arraylist
            ArrayList<Object> list = new ArrayList<>();
            list.add(i);
            list.add(ProdName);
            list.add(quantity);
            list.add(Uprice);
            list.add(Total);

            DefaultTableModel dt = (DefaultTableModel) BillTable.getModel();
            dt.addRow(list.toArray());
            
            //computes final totals
            OrderTot = OrderTot + Total;
            OrderTotBox.setText("" + OrderTot);
            
            currentStock -= quantity;               // updates local stock

            //warning that product quantity has hit zero
            if (currentStock == 0) {
                JOptionPane.showMessageDialog(this, "Warning: Product stock is now 0!");
            }

            Update();                           //  updates DB stock
            i++;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for quantity and price.");
        }
    }
    }//GEN-LAST:event_AddItemMouseClicked

    private void ViewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewBtnActionPerformed
        new ViewOrders().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_ViewBtnActionPerformed

    private void PrintBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrintBtnMouseClicked
        try{
        	BillTable.print();
    	} catch(Exception exp){     //generic exception
        	exp.printStackTrace();
    	}
    }//GEN-LAST:event_PrintBtnMouseClicked

    private void ViewBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ViewBtnMouseClicked

    }//GEN-LAST:event_ViewBtnMouseClicked

    private void HomeBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeBtnMouseClicked
        new HomeForm().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_HomeBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Order.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Order().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddItem;
    private javax.swing.JButton AddOrderBtn;
    private javax.swing.JTable BillTable;
    private javax.swing.JLabel CustomerName;
    private javax.swing.JTable CustomerTable;
    private javax.swing.JLabel DateSet;
    private javax.swing.JLabel ExitBtn;
    private javax.swing.JButton HomeBtn;
    private javax.swing.JTextField OrderBox;
    private javax.swing.JLabel OrderTotBox;
    private javax.swing.JTextField PriceBox;
    private javax.swing.JButton PrintBtn;
    private javax.swing.JTable ProdTable;
    private javax.swing.JTextField QtyBox;
    private javax.swing.JButton ViewBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
