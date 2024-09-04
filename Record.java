public class Record {
    private String name;
    private int id, age;
    private double salary;
    
    public Record(String name, int id, int age, double salary){
        this.age = age;
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    
    public String getName(){
        return name;
    }
    
    public int getID(){
        return id;
    }
    
    public int getAge(){
        return age;
    }
    
    public double getSalary(){
        return salary;
    }
}
