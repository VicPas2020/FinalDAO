package jm.task.core.jdbc.model.One_to_Many_Uni;

import jm.task.core.jdbc.TableName;

import javax.persistence.*;

@Entity
@Table(name= TableName.MANY_to_ONE_1)
public class to_PhoneNumbersUni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column
    private int number;

    @ManyToOne (/*optional=true*/ /*cascade=CascadeType.ALL*//*,  mappedBy="passport"*/)
    //@JoinColumn(name="person", referencedColumnName="id") //- можно заменить на нижеследующую PrimaryKeyJoinColumn
    //@PrimaryKeyJoinColumn
    private PersonUni person;







    public to_PhoneNumbersUni(int number/*, Person person*/) {

        this.number = number;
        //this.person = person;
    }

    public to_PhoneNumbersUni() {}

    public PersonUni getPerson() {
        return person;
    }

    public void setPerson(PersonUni person) {
        this.person = person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
