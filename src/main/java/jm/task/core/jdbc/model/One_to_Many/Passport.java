package jm.task.core.jdbc.model.One_to_Many;

import jm.task.core.jdbc.TableName;
import jm.task.core.jdbc.model.One_to_one_BiDirect.Person;

import javax.persistence.*;

@Entity
@Table(name= TableName.MANY_to_ONE_1)
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String seria;

    @Column
    private int number;

    @OneToOne (optional=true /*cascade=CascadeType.ALL*//*,  mappedBy="passport"*/)
    @JoinColumn(name="person", referencedColumnName="id") //- можно заменить на нижеследующую PrimaryKeyJoinColumn
    //@PrimaryKeyJoinColumn
    private Person person;


    public Passport(String seria, int number, Person person) {
        this.seria = seria;
        this.number = number;
        this.person = person;
    }

    public Passport() {}

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
