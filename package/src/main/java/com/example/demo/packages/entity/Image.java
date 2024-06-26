package com.example.demo.packages.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String filename;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    @ManyToOne
    @JoinColumn(name = "package_id")
    private Package aPackage;
    public enum Type{
        PKG("Package"),
        LBL("Label"),
        DOC("Document"),
        SIGN("Signature"),
        DAM("Damage");
        @Getter
        private String type;

        Type(String type) {
            this.type = type;
        }
    }
}
