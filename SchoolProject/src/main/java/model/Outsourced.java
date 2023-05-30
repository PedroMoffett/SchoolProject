package model;

/**
 * Outsourced Model
 * @author Peter Moffett
 */
public class Outsourced extends Part{
    private String companyName;

    /**
     * Constructor for new instance of Outsourcedpart
     * @param id of the part
     * @param name of the part
     * @param price of the part
     * @param stock Inventory level of the part
     * @param min value of the part
     * @param max value of the part
     * @param companyName of the part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Getter for companyname
     * @return CompanyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Setter for companyname
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}