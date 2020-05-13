package Model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author onkar
 */
public class Month {
    private String monthNumber;
    private String monthName;
    private int count;
    
    

    public Month(String monthName, int count) {
        this.monthName = monthName;
        this.count = count;
    }
    
    

    public Month(String monthNumber, String monthName, int count) {
        this.monthNumber = monthNumber;
        this.monthName = monthName;
        this.count = count;
    }
    
    

    public String getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(String monthNumber) {
        this.monthNumber = monthNumber;
    }

    public String getName() {
        return monthName;
    }

    public void setName(String name) {
        this.monthName = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    
    
    
    
    
}
