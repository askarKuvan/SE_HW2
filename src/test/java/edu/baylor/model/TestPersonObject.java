/***************************************
 *
 * Author       : Askar Kuvanychbekov
 * Assignment   : II - mini app
 * Class        : CSI 5354
 *
 ***************************************/

package edu.baylor.model;

public class TestPersonObject extends Person {

    public TestPersonObject(Integer id) {
        this.id = id;
    }

    TestPersonObject(Integer id,
                       String name,
                       String email,
                       Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }
}
