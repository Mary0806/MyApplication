package com.example.myapplication.domain;

import javax.persistence.*;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long (more than 2kB)")
    private String text;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Message() {}
    public Message(String text, User user) {
        this.author = user;
        this.text = text;
    }
public String getAuthorName(){
        return author != null ? author.getUsername() : "<none>";
}
    public void setText (String text){
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
