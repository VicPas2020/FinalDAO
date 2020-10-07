package jm.task.core.jdbc.model.One_to_Many_Uni;

import jm.task.core.jdbc.TableName;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name= TableName.ONE_to_MANY_1)
public class PersonUni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    @OneToMany (/*targetEntity = to_PhoneNumbers.class,*/
            cascade=CascadeType.ALL
           /* mappedBy="person"*/)// если есть mappedBy, то не нужно @JoinColumn

    // эта строка опциональная - может быть , а может не быть - в колонку passport_id пишется автоматом, если она есть в таблице
    @JoinColumn(name="person_id" /*, referencedColumnName="id"*/) // name - имя колонки в ЭТОМ классе для пасспорта и она ДОЛЖНА УЖЕ БЫТЬ!

//  а это для связи через отдельную таблицу - "персона-паспорт".(person_passport)
//    @JoinTable(name = "person_to_PhonesNumber",
//            joinColumns = @JoinColumn(name="person_id"),
//            inverseJoinColumns = @JoinColumn(name="phonenumbers_id")
//
//    )
    //@JoinColumn(name="person", referencedColumnName="id") //phoneNumbers_id
    private List<to_PhoneNumbersUni> phoneNumbers = new ArrayList<>();


    public PersonUni() {}

    public PersonUni(String name, String lastName, Byte age, List<to_PhoneNumbersUni> phoneNumbers) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumbers = phoneNumbers;
    }

    public List<to_PhoneNumbersUni> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<to_PhoneNumbersUni> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Byte getAge() {
        return age;
    }

    public void setAge(Byte age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
