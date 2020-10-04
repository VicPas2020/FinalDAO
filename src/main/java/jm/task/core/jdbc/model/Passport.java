package jm.task.core.jdbc.model;

import jm.task.core.jdbc.TableName;

import javax.persistence.*;

@Entity
@Table(name= TableName.one_to_ONE)
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String seria;

    @Column
    private int number;

    @OneToOne (optional=false, mappedBy="passport")
    private Person person;


    public Passport(String seria, int number, Person person) {
        this.seria = seria;
        this.number = number;
        this.person = person;
    }
}
