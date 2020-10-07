package jm.task.core.jdbc.model.Many_to_One;

import jm.task.core.jdbc.TableName;
import jm.task.core.jdbc.model.One_to_one_BiDirect.to_Person;

import javax.persistence.*;

@Entity
@Table(name= TableName.ONE_to_MANY_2)
public class to_Citizenship {

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
    private to_Person person;


    public to_Citizenship(String seria, int number, to_Person person) {
        this.seria = seria;
        this.number = number;
        this.person = person;
    }

    public to_Citizenship() {}

    public to_Person getPerson() {
        return person;
    }

    public void setPerson(to_Person person) {
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