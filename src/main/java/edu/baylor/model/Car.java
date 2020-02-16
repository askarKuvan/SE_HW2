/***************************************
 *
 * Author       : Askar Kuvanychbekov
 * Assignment   : II - mini app
 * Class        : CSI 5354
 *
 ***************************************/

package edu.baylor.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import edu.baylor.model.Person;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "car")
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "SELECT car from Car car"),
        @NamedQuery(name = "Car.findPersonCars",
                query = "select cars from Person p join p.cars cars where p.id = :personId"),
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Car {

    @Id
    @SequenceGenerator(
            name = "car_sequence",
            allocationSize = 1,
            initialValue = 7
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_sequence")
    @Column(name = "id", updatable = false, nullable = false)
    protected Integer id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "brand", length = 50, nullable = false)
    protected String brand;

    @Size(min = 1, max = 50)
    @Column(name = "type", length = 50, nullable = false)
    protected String type;

    @Size(min = 1, max = 50)
    @Column(name = "license_plate", length = 50, nullable = false)
    protected String licensePlate;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    protected Person owner;

    public Integer getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id.equals(car.id) &&
                brand.equals(car.brand) &&
                Objects.equals(type, car.type) &&
                Objects.equals(licensePlate, car.licensePlate) &&
                (owner == null ? car.owner == null : Objects.equals(owner.getId(), car.owner.getId()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, type, licensePlate, (owner == null) ? owner : owner.getId() );
    }
}
