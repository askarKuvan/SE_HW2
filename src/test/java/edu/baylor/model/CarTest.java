/***************************************
 *
 * Author       : Askar Kuvanychbekov
 * Assignment   : II - mini app
 * Class        : CSI 5354
 *
 ***************************************/

package edu.baylor.model;

import org.junit.Test;
import org.fest.assertions.Assertions;
import static org.fest.assertions.Assertions.assertThat;

public class CarTest {

    @Test
    public void carsAreEqual() throws Exception {
        
        Car car1 = createCar(1, "Toyota", "Camry");
        Car car2 = createCar(1, "Toyota", "Camry");

        Assertions.assertThat(car1).isEqualTo(car2);
        assertThat(car1.equals(car2)).isTrue();
        assertThat(car1.hashCode()).isEqualTo(car2.hashCode());
    }

    @Test
    public void carsAreNotEqual() throws Exception {

        Car car1 = createCar(1, "Toyota", "Camry");
        Car car2 = createCar(2, "Toyota", "Camry");

        Assertions.assertThat(car1).isNotEqualTo(car2);
        assertThat(car1.equals(car2)).isFalse();
        assertThat(car1.hashCode()).isNotEqualTo(car2.hashCode());
    }

    @Test
    public void carModification() throws Exception {

        Car car1 = createCar(1, "Toyota", "Camry");
        Car car2 = createCar(1, "Toyota", "Camry");

        Assertions.assertThat(car1).isEqualTo(car2);
        assertThat(car1.equals(car2)).isTrue();
        assertThat(car1.hashCode()).isEqualTo(car2.hashCode());

        car1.setType("Prius");

        Assertions.assertThat(car1).isNotEqualTo(car2);
        assertThat(car1.equals(car2)).isFalse();
        assertThat(car1.hashCode()).isNotEqualTo(car2.hashCode());
    }

    @Test
    public void carsWithIdenticalParentAreEqual() throws Exception {

        Person person1 = createPerson(1, "Askar", "askar@gmail.com", 23);
        Car car1 = createCar(1, "Toyota", "Camry", person1);
        Car car2 = createCar(1, "Toyota", "Camry", person1);

        Assertions.assertThat(car1).isEqualTo(car2);
        assertThat(car1.equals(car2)).isTrue();
        assertThat(car1.hashCode()).isEqualTo(car2.hashCode());
    }

    @Test
    public void carsWithIdenticalParentIdAreEqual() throws Exception {

        Person person1 = createPerson(1, "Askar", "askar@gmail.com", 23);
        Person person2 = createPerson(1, "Askar", "askar@gmail.com", 18);
        Car car1 = createCar(1, "Toyota", "Camry", person1);
        Car car2 = createCar(1, "Toyota", "Camry", person2);

        Assertions.assertThat(car1).isEqualTo(car2);
        assertThat(car1.equals(car2)).isTrue();
        assertThat(car1.hashCode()).isEqualTo(car2.hashCode());
    }

    @Test
    public void carsWithDifferentParentNotEqual() throws Exception {

        Person person1 = createPerson(1, "Askar", "askar@gmail.com", 23);
        Person person2 = createPerson(2, "Tom", "tom@gmail.com", 18);
        Car car1 = createCar(1, "Toyota", "Camry", person1);
        Car car2 = createCar(1, "Toyota", "Camry", person2);

        Assertions.assertThat(car1).isNotEqualTo(car2);
        assertThat(car1.equals(car2)).isFalse();
        assertThat(car1.hashCode()).isNotEqualTo(car2.hashCode());
    }

    private Person createPerson(Integer id, String name, String email, Integer age) {
        return new TestPersonObject(id, name, email, age);
    }

    private Car createCar(Integer id, String brand, String type) {
        return createCar(id, brand, type, "AAA007", null);
    }

    private Car createCar(Integer id, String brand, String type, Person owner) {
        return createCar(id, brand, type, "AAA007", owner);
    }

    private Car createCar(Integer id, String brand, String type, String licensePlate, Person owner) {
        return new TestCarObject(id, brand, type, licensePlate, owner);
    }
}
