import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class Item {
    //Item attributes
    protected int id;
    protected String name;
    protected double price;
    protected int quantity;
    protected String supplier;

    //Item object
    public Item(int i, String n, double p, int q, String s) {
        this.id = i;
        this.name = n;
        this.price = p;
        this.quantity = q;
        this.supplier = s;
    }

    //These are getter methods
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public String getSupplier() { return supplier; }

    //These are setter methods
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setSupplier(String supplier) { this.supplier = supplier; }

    //File writing and saving methods
    public String toCSV() {
        return id + "," + name + "," + price + "," + quantity + "," + supplier;
    }

    //Loads items from CSV by creating a string array and assigning attributes from each index
    //Uses regex to split by comma
    public static Item fromCSV(String csv) {
        String[] parts = csv.split(",", -1);
        int id = Integer.parseInt(parts[0]);
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        int quantity = Integer.parseInt(parts[3]);
        String supplier = parts[4];
        return new Item(id, name, price, quantity, supplier);
    }
}

//Main class
class RetailInventory {
    static ArrayList<Item> itemList = new ArrayList<>();
    static String FILE_NAME = "inventory.txt";
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Loads files before program runs
        loadItemsFromFile();

        //Main input loop
        //Items WILL NOT SAVE unless option 6 is selected before exiting
        boolean running = true;
        while (running) {
            System.out.println("Item Management Menu");
            System.out.println("1. Add Item");
            System.out.println("2. Update Item");
            System.out.println("3. Delete Item");
            System.out.println("4. Display Items");
            System.out.println("5. Search Item");
            System.out.println("6. Save & Exit");
            System.out.print("Choose an option: ");
            //Takes input as string, converts to integer
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 ->
                    addItem();
                case 2 ->
                    updateItem();
                case 3 ->
                    deleteItem();
                case 4 ->
                    displayItems();
                case 5 ->
                    searchItem();
                case 6 ->
                {
                    //Saves to a file before exiting
                    saveItemsToFile();
                    System.out.println("Items saved. Exiting...");
                    running = false;
                }
                default ->
                    System.out.println("Enter a valid option");
            }
        }
    }

    //Function to add a new item to inventory
    static void addItem() {
        int id = 0;
        //Adds the item id while checking for duplicates and formatting
        while (true) {
            System.out.print("Enter 6-digit ID: ");
            String idInput = scanner.nextLine();

            //ID must be 6 digits, basic regex is used here to confirm this
            if (!idInput.matches("\\d{6}")) {
                System.out.println("Invalid ID. It must be exactly 6 digits.");
                continue;
            }

            int idToCheck = Integer.parseInt(idInput);
            //This is a way to check for matches without writing a for loop
            boolean exists = itemList.stream().anyMatch(item -> item.getId() == idToCheck);

            if (exists) {
                System.out.println("Duplicate ID detected. Try again");
            } else {
                id = idToCheck;
                break;
            }
        }

        //Adds the other attributes
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter Quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Supplier: ");
        String supplier = scanner.nextLine();

        itemList.add(new Item(id, name, price, quantity, supplier));
        System.out.println("Item added successfully");
    }

    //Function to update attributes of an item
    private static void updateItem() {
        System.out.print("Enter ID of the item to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        //Provides the option to update each attribute
        for (Item item : itemList) {
            if (item.getId() == id) {
                System.out.print("Enter new Name: ");
                item.setName(scanner.nextLine());

                System.out.print("Enter new Price: ");
                item.setPrice(Double.parseDouble(scanner.nextLine()));

                System.out.print("Enter new Quantity: ");
                item.setQuantity(Integer.parseInt(scanner.nextLine()));

                System.out.print("Enter new Supplier: ");
                item.setSupplier(scanner.nextLine());

                System.out.println("Item updated successfully!");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    //Function to delete an item by id
    private static void deleteItem() {
        System.out.print("Enter ID of the item to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (Item item : itemList) {
            if (item.getId() == id) {
                itemList.remove(item);
                System.out.println("Item deleted");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    //Displays all items, alerts the user if item stock is less than 10
    private static void displayItems() {
        if (itemList.isEmpty()) {
            System.out.println("No items to display.");
        } else {
            System.out.println("Item List");
            for (Item item : itemList) {
                System.out.print(item);
                if (item.getQuantity() < 10) {
                    System.out.print(" Replenish stock");
                }
                System.out.println();
            }
        }
    }

    //Searches for an item by id or name
    private static void searchItem() {
        System.out.print("Search by (1) ID or (2) Name? ");
        int option = Integer.parseInt(scanner.nextLine());

        if (option == 1) {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            for (Item item : itemList) {
                if (item.getId() == id) {
                    System.out.println("Found: " + item);
                    return;
                }
            }
            System.out.println("Item not found.");

        } else if (option == 2) {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine().toLowerCase();
            boolean found = false;

            for (Item item : itemList) {
                if (item.getName().toLowerCase().contains(name)) {
                    System.out.println("Found: " + item);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Item not found.");
            }

        } else {
            System.out.println("Invalid option.");
        }
    }

    //Saves items to inventory.txt
    private static void saveItemsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Item item : itemList) {
                writer.println(item.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving items: " + e.getMessage());
        }
    }

    //Loads items from inventory.txt on startup
    protected static void loadItemsFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                itemList.add(Item.fromCSV(line));
            }
            System.out.println("Items loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading items: " + e.getMessage());
        }
    }
}
// purchase and stock updating
class stockSalesnSupplysUpdate{
    static Scanner scanner = new Scanner(System.in);
    static String FILE_NAME2 = "UsersCart.txt";
    static String FILE_NAME = "inventory.txt";
    static ArrayList<Item> SaleList = new ArrayList<>();
    protected static synchronized void inventoryCheck(Item Stock, Item User){
        if(stock.getQuantity() >= User.getQuantity()){
            stock.setQuantity(stock.getQuantity() - User.getQuantity());
            System.out.println(Thread.currentThread().getName() + " decreased stock of " + stock.getName() + " by " + User.getQuantity() + ". Remaining stock: " + stock.getQuantity());
        }
        else{
            System.out.println(Thread.currentThread().getName() + " Restock needed for Item " + stock.getName());

        }
    }
    protected synchronized void inventoryResupply(Item Stock, Item User){
        Random Supplies = new random(); // random extra supply, can be removed
        int Resupplies = User.getQuantity() + Supplies.nextInt(0, User.getQuantity()) ;
        stock.setQuantity(stock.getQuantity() + Resupplies);
        System.out.println(Thread.currentThread().getName() + " increased stock of " + stock.getName() + " by " + Resupplies + ". Remaining stock: " + stock.getQuantity());
    }
    protected static void PurchasedItemsFromUsers(){
        File file = new File(FILE_NAME2);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                File file2 = new File(line);
                try (BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {
                    String line2;
                    while ((line2 = reader2.readLine()) != null) {
                        Item Useritem = new Item.fromCSV(line2);
                        int quantity = Useritem.getQuantity();
                        if(SaleList.isEmpty()){
                            SaleList.add(Useritem);
                        }
                        else{
                            for (Item item : SaleList){
                                if (item.getId() == Useritem.getId() ){ 
                                    item.setQuantity( item.getQuantity() + quantity);                                  
                                 }
                            }
                        }
                    }
                    System.out.println("Items loaded from file.");
                } catch (IOException e) {
                    System.out.println("Error loading items: " + e.getMessage());
                }
            }
            System.out.println("Users loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading Users: " + e.getMessage());
        }
    }
    protected salesupdater(){
        ArrayList<Item> itemList = RetailInventory.itemList;
        for (Item item : RetailInventory.itemList){
            for (Item Useritem: SaleList){
                if(Useritem.getId() == item.getId()){
                    Runnable task1 = () -> {
                        inventoryCheck(item, Useritem);
                    };
                    Runnable task2 = () -> {
                        inventoryResupplies(item, Useritem);
                    };
                Thread Check = new Thread(task1, "thread-1");
                Thread Resupply = new Thread(task2, "thread-2");
                Check.start();
                Resupply.start();
                }
            }
        }
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Item item : itemList) {
                writer.println(item.toCSV());
            }
        } catch (IOException e) {
            System.out.println("Error saving items: " + e.getMessage());
        }
    }
}
