package org.agoncal.quarkus.panache.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "t_publishers")
public class Publisher extends PanacheEntity {
    @Column(length = 50, nullable = false)
    public String name;

    @Column(name = "created_date")
    public Instant createdDate = Instant.now();

    public Publisher() {

    }

    public Publisher(String name) {
        this.name = name;
    }

    public static Optional<Publisher> findByName(String name){
        return find("name", name).firstResultOptional();
    }

    public static List<Publisher> findContainName(String name){
        return list("name like ?1", '%'+name+'%');
    }
}