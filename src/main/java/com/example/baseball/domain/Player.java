package com.example.baseball.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

//import org.hibernate.validator.constraints.NotEmpty;

@Entity // DBのテーブルとつながる
public class Player {

    @Id // これを付けた変数がテーブルのプライマリーキーになる
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 連番が自動生成される（autoincrement的な？）
    private Long id;//@Id @GeneratedValueにかかっている説byJET
    @NotEmpty //@NotEmptyをつけるとnullと空文字を許容しなくなる
    @Size(max = 20)
    private String name;
    @NotNull // @NotNullをつけるとnullを許容しなくなる
    @Min(value = 0) // ③
    @Max(value = 150)
    private Integer age;
    @Size(max = 20) // ④
    private String team;
    private String position;

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
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Player [id=" + id + ", name=" + name + ", age=" + age + ", team=" + team + ", position=" + position + "]";
    }
}