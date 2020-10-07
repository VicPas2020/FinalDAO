package jm.task.core.jdbc.model.One_to_One_UniDirect;

import jm.task.core.jdbc.TableName;

import javax.persistence.*;

@Entity
@Table (name= TableName.ONE_to_one_Uni_Dir)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;

    @OneToOne (optional=true, cascade=CascadeType.ALL/*, mappedBy="passport"*/)

    // эта строка опциональная - может быть , а может не быть - в колонку passport_id пишется автоматом, если она есть в таблице
    //@JoinColumn(name="passport_id"/*, referencedColumnName="id"*/) // name - имя колонки в ЭТОМ классе для пасспорта и она ДОЛЖНА УЖЕ БЫТЬ!

//  а это для связи через отдельную таблицу - "персона-паспорт".(person_passport)
//    @JoinTable(name = "person_passport",
//            joinColumns = @JoinColumn(name="person_id"),
//            inverseJoinColumns = @JoinColumn(name="passport_id")
//
//    )
    @JoinColumn(name="passport_id", referencedColumnName="id")
    private to_Passport passport;


    public Person() {}

    public Person(String name, String lastName, Byte age, to_Passport passport) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.passport = passport;

    }


    public to_Passport getPassport() {
        return passport;
    }

    public void setPassport(to_Passport passport) {
        this.passport = passport;
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
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}
