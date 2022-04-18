package web.model;

import javax.persistence.*;

@Entity
@Table
public class Car {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String color;

    @Column
    private String brand;

    @Column
    private int number;

    public Car() {}

    public Car(String color, String brand, int number) {
        this.color = color;
        this.brand = brand;
        this.number = number;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
