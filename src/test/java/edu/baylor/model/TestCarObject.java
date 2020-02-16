/***************************************
 *
 * Author       : Askar Kuvanychbekov
 * Assignment   : II - mini app
 * Class        : CSI 5354
 *
 ***************************************/

package edu.baylor.model;

public class TestCarObject extends Car{

    public TestCarObject(Integer id) {
        this.id = id;
    }

    TestCarObject(Integer id,
                   String brand,
                   String type,
                   String licensePlate,
                   Person owner) {
        this.id = id;
        this.brand = brand;
        this.type = type;
        this.licensePlate = licensePlate;
        this.owner = owner;
    }
}
