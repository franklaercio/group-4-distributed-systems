package br.ufrn.data.manager.domain;

public class Tax {

    private int id;

    private String month;

    private String taxType;

    private int value;

    private String year;

    public Tax(int id, String month, String taxType, int value, String year) {
        this.id = id;
        this.month = month;
        this.taxType = taxType;
        this.value = value;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTaxType() {
        return taxType;
    }

    public void setTaxType(String taxType) {
        this.taxType = taxType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Tax{" +
                "id=" + id +
                ", month='" + month + '\'' +
                ", taxType='" + taxType + '\'' +
                ", value=" + value +
                ", year='" + year + '\'' +
                '}';
    }
}
