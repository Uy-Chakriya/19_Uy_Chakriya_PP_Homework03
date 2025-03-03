//===== >> Super class << =======
public abstract class StaffMember {
    protected int id;
    protected String name;
    protected String address;

    public StaffMember() {}
    //===== >> Constructor with param << =======
    public StaffMember(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    @Override
    public String toString() {
        return  id + " "
                + name + " "
                + address;
    }

    //====>> Payment
    public abstract double pay();
    //====>> type
    public String getType() {
        return "Staff";
    }

    //=====>> this is for set salary
    public void setSalary(double newSalary) {}
}