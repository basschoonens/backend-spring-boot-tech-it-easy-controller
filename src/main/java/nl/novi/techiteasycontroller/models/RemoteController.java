package nl.novi.techiteasycontroller.models;

import jakarta.persistence.*;

@Entity
@Table(name = "remotecontrollers")
public class RemoteController {

    @Id
    @GeneratedValue
    private Long id;
    private String compatibleWith;
    private String batteryType;
    private String name;
    private String brand;
    private Double price;
    private Integer originalStock;
    @OneToOne(mappedBy = "remoteController")
    private Television television;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Television getTelevision() {
        return television;
    }

    public void setTelevision(Television television) {
        this.television = television;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }

    public String getBatteryType() {
        return batteryType;
    }

    public void setBatteryType(String batteryType) {
        this.batteryType = batteryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getOriginalStock() {
        return originalStock;
    }

    public void setOriginalStock(Integer originalStock) {
        this.originalStock = originalStock;
    }
}
