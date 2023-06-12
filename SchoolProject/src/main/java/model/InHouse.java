package model;

/**
 * model.inhouse
 * @author Peter Moffett
 */
public class InHouse extends Part {
    private int machineId;

    /**
     * Constructor for new instance of Inhousepart
     * @param id of part
     * @param name of part
     * @param price of part
     * @param stock Inventory level of part
     * @param min value of part
     * @param max value of part
     * @param machineId of part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Getter
     * @return Part's machine Id
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Setter
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}