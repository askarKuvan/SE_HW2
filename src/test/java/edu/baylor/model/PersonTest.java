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

public class PersonTest {

    @Test
    public void personsAreEqual() throws Exception {

        Person p1 = createPerson(1, "Askar", "askar@gmail.com", 23);
        Person p2 = createPerson(1, "Askar", "askar@gmail.com", 23);

        Assertions.assertThat(p1).isEqualTo(p2);
        assertThat(p1.equals(p2)).isTrue();
        assertThat(p1.hashCode()).isEqualTo(p2.hashCode());
    }

    @Test
    public void personsAreNotEqual() throws Exception {

        Person p1 = createPerson(1, "Askar", "askar@gmail.com", 23);
        Person p2 = createPerson(2, "Askar", "askar@gmail.com", 23);

        Assertions.assertThat(p1).isNotEqualTo(p2);
        assertThat(p1.equals(p2)).isFalse();
        assertThat(p1.hashCode()).isNotEqualTo(p2.hashCode());
    }

    @Test
    public void personModification() throws Exception {

        Person p1 = createPerson(1, "Askar", "askar@gmail.com", 23);
        Person p2 = createPerson(1, "Askar", "askar@gmail.com", 23);

        Assertions.assertThat(p1).isEqualTo(p2);
        assertThat(p1.equals(p2)).isTrue();
        assertThat(p1.hashCode()).isEqualTo(p2.hashCode());

        p1.setEmail("kuvanychbekov@gmail.com");

        Assertions.assertThat(p1).isNotEqualTo(p2);
        assertThat(p1.equals(p2)).isFalse();
        assertThat(p1.hashCode()).isNotEqualTo(p2.hashCode());
    }

    private Person createPerson(Integer id, String name, String email, Integer age) {
        return new TestPersonObject(id, name, email, age);
    }
}
