package com.pingr.conections.Connections.AccountMirror;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonSerialize
public class Account {
    @Id
    private Long id;

    @Column(
            name = "username",
            nullable = false,
            unique = true
    )
    private String username;

    // sendo definido como oneToMany o spring cria uma chave estrangeira para o atributo friends, que é
    // o próprio accountID, bem como uma outra chave estrangeira accountID.
    // o problema é que por ser um para muitos eu não posso duplicar friendsID
    // fazendo com que seja ManyToMany
    @ManyToMany
    private Set<Account> friends = new HashSet<>();

    public Account(Long id, String username, Set<Account> friends) {
        this.id = id;
        this.username = username;
        this.friends = friends;
    }

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Account> getFriends() {
        HashSet<Account> _friends = new HashSet<>();

        for (Account friend: this.friends) {
            _friends.add(new Account(friend.getId(), friend.getUsername(), new HashSet<>()));
        }

        return _friends;
    }

    public void setFriends(Set<Account> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", friendsCount=" + friends.size() + // estava gerando loop
                '}';
    }
}
