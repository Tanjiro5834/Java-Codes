import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EmployeeManagementSystem {

    static final Scanner scanner = new Scanner(System.in);
    static final HashMap<String, Integer> map = new HashMap<>();
    static final HashMap<String, Double> map2 = new HashMap<>();
    static final int EMPLOYEE_LIMIT = 5;
    static int input, id, age, employeeCount = 0;
    static String employeeName;
    static double salary;
    static boolean isAdminAuthenticated = false;

    private static class Admin {
        private static final String adminUsername = "admin";
        private static final String adminPassword = hashPassword("admin123456");

        private static String hashPassword(String password){
            try{
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[]hashBytes = md.digest(password.getBytes());
                StringBuilder sb = new StringBuilder();
                for(byte b : hashBytes){
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();
            }catch(NoSuchAlgorithmException e){
                e.getMessage();
                return null;
            }
        }

        static boolean authenticate(String username, String password){
            return adminUsername.equals(username) && adminPassword.equals(hashPassword(password));
        }

        public static void authenticateAdmin(){
            if(!isAdminAuthenticated){
                for(int attempts = 0; attempts < 8; attempts++){
                    System.out.println("Enter admin username: "); String adminName = scanner.nextLine();
                    System.out.println("Enter admin password: "); String adminPass = scanner.nextLine();
                    if(Admin.authenticate(adminName, adminPass)){
                        isAdminAuthenticated = true;
                        return;
                    }else{
                        System.err.println("Invalid username and password. Please try again.");
                    }
                }
            }
            System.out.println("You've reached the maximum attempts. Exiting the program...");
            System.exit(1);
        }
    }
    public static void main(String[]args) throws InterruptedException{
        Admin.authenticateAdmin();
        mainMenu();
    }

    static void mainMenu() throws InterruptedException{
        while(true){
            System.out.println("Please select an option:\n1.Add employee\n2.Remove employee +"
            +"\n3.View employees"
            +"\n4.Search employees\n5.Exit");
            input = scanner.nextInt();
            if(scanner.hasNextInt()){
                switch(input){
                    case 1 -> addEmployee();
                    case 2 -> removeEmployee();
                    case 3 -> viewEmployees();
                    case 4 -> searchEmployees();
                    case 5 -> exit();
                    default -> System.err.print("Invalid response.");
                }
            }else{
                scanner.next();
                System.err.println("Please enter a number.");
            }
        }
    }

    static void sort(int arr[]){
        int n = arr.length;
        for(int i = 1; i < n; ++i){
            int temp = arr[i];
            int j = i - 1;

            while(j >= 0 && arr[j] > temp){
                arr[j + i] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = temp;
        }
    }

    static void printArray(int arr[]){
        int n = arr.length;
        for(int i = 0; i < n; i++){
            System.out.println(arr[i] + " ");
        }
        System.out.println();
    }

    static void exit(){
        try(scanner){
            System.out.println("Exiting the program...");
        }
        System.exit(0);
    }
    
    static void addEmployee()throws InterruptedException{
        System.out.print("Employee name: "); employeeName = scanner.next().trim();
        System.out.print("Employee ID: "); id = scanner.nextInt();
        System.out.print("Age: "); age = scanner.nextInt();
        System.out.print("Salary: "); salary = scanner.nextDouble();
        
        Record record = new Record(employeeName,id,age,salary);
        map.put(employeeName, id);
        map2.put(employeeName, salary);
        
        employeeCount++;
        
        System.out.print("Record added. Below are the details:\n");
        System.out.println("Name: "+record.getName()+
                "\nId: "+record.getID()+
                "\nAge: "+record.getAge()+
                "\nSalary: "+record.getSalary());
        
        if(employeeCount >= EMPLOYEE_LIMIT){
            System.out.println("Employee limit reached. Sorting...");
            Thread.sleep(5000);
            int[]employeeIds = map.values().stream().mapToInt(Integer::intValue).toArray();
            sort(employeeIds);
            System.out.print("Employees sorted by ID. Result:\n");
            for(int empId : employeeIds){
                String empName = getKeyFromValue(map, empId);
                double empSalary = map2.get(empName);
                System.out.println("-----------------------------------------------------------");
                System.out.println("Name: " + empName + "\nID: " + empId + "\nSalary: $" + empSalary);
                System.out.println("-----------------------------------------------------------");
            }
        }
    }
    
    static void removeEmployee(){
        if(map.isEmpty() && map2.isEmpty()){
            System.err.println("No employee added.");
        }
        try{
            System.out.print("Enter employee name to remove: "); employeeName = scanner.next();
            if(map.containsKey(employeeName)){
                map.remove(employeeName, id); 
                map2.remove(age, salary);
                System.out.print("Employee removed successfully.");
            }else{
                System.err.print("Employee record not found!");
            }    
        }catch(InputMismatchException e){
            e.getCause(); 
            e.getMessage();
        }
    }
    
    static void viewEmployees(){
        if(map.isEmpty() && map2.isEmpty()){
            System.err.println("No employee added.");
        }
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String empName = entry.getKey();
            int empId = entry.getValue();
            double empSalary = map2.get(empName);
            System.out.println("-----------------------------------------------------------");
            System.out.println("Name: " + empName + "\nID: " + empId + "\nSalary: $" + empSalary);
            System.out.println("-----------------------------------------------------------");
        }
    }
    
    static String getKeyFromValue(Map<String, Integer> map, int value){
        for(Map.Entry<String, Integer> e : map.entrySet()){
            if(e.getValue().equals(value)){
                return e.getKey();
            }
        }
        return null;
    }

    static void searchEmployees(){
        if(map.isEmpty()){
            System.out.println("No employee added.");
            return;
        }

        System.out.println("Enter employee name to search: ");
        String searchName = scanner.next();

        int foundIndex = binarySearch(map.keySet().toArray(new String[0]), searchName);
        if(foundIndex != -1){
            String empName = map.keySet().toArray(new String[0])[foundIndex];
            int empId = map.get(empName);
            double empSalary = map2.get(empName);
            System.out.println("----------------------------------------");
            System.out.println("Name: "+empName+"\nId: "+empId+"\nSalary: $ "+empSalary);
            System.out.println("----------------------------------------");
        }else{
            System.out.println("Employee not found.");
        }
    }

    static int binarySearch(String[]arr, String key){
        int left = 0;
        int right = arr.length - 1;

        while(right <= left){
            int mid = (right + left) / 2;
            int cmp = key.compareTo(arr[mid]);

            if(cmp == 0){
                return mid;
            }else if(cmp < 0){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }
}
