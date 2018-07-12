/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ecamping.entidade;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Ramon
 */
@Entity
@Table(name = "tb_comment")
@DiscriminatorValue(value = "C")
@PrimaryKeyJoinColumn(name = "ID_FEEDBACK", referencedColumnName = "ID")
@NamedQueries(
    {
        @NamedQuery(
                name = Comment.ALL_COMMENTS,
                query = "SELECT c FROM Comment c"
        ),
        @NamedQuery(
                name = Comment.COMENTARIO_POR_USER_CAMPING,
                query = "SELECT c FROM Comment c WHERE c.user.id LIKE ?1 and c.camping.id LIKE ?2"
        )
    }
)
public class Comment extends Feedback implements Serializable{
    public static final String ALL_COMMENTS = "AllComment";
    public static final String COMENTARIO_POR_USER_CAMPING = "ComentarioPorUserCamping";
    
    @Size(min=1, max=500)
    @NotNull (message = "Precisa escrever a mensagem!")
    @Column(name = "TXT_MESSAGE", nullable = false)
    private String message;
    
    @NotNull (message = "O usuario precisa ser definido!")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name="ID_USER", referencedColumnName = "ID", nullable = false)
    private User user;
    
    @NotNull (message = "O camping precisa ser definido!")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name="ID_CAMPING", referencedColumnName = "ID", nullable = false)
    private Camping camping;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Camping getCamping() {
        return camping;
    }

    public void setCamping(Camping camping) {
        this.camping = camping;
    }
    
    
    
}
