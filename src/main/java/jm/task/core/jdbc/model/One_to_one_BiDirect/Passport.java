package jm.task.core.jdbc.model.One_to_one_BiDirect;

import jm.task.core.jdbc.TableName;

import javax.persistence.*;

@Entity
@Table(name= TableName.one_to_ONE_BiDir)
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String seria;

    @Column
    private int number;

    @OneToOne (optional=true, cascade=CascadeType.ALL)
    // эта аннотация для двунаправленной связи в исходных таблицах
    @JoinColumn(name="person", referencedColumnName="id") //id - только то, что Foreign Key

    // эта аннотация на случай создания отдельной таблицы со связями(во втором объекте - не здесь). В исходных таблицах не будет связей.
    //@PrimaryKeyJoinColumn  // удалить элементы с таблиц будет нельзя, пока они не удалены из таблицы связей

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
