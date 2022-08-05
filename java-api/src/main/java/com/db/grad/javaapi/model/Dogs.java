package com.db.grad.javaapi.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dogs")
public class Dogs {

	@Id
	private long id;
    private String name;
    private long age;

    public Dogs() {

    }

    public Dogs(long id, String name, long age) {
    	this.id=id;
        this.name = name;
        this.age = age;

    }

    @Id
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "age", nullable = false)
    public long getAge() {
        return age;
    }
    public void setAge(long age) {
        this.age = age;
    }
}