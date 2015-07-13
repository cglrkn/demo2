/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ulakbim
 */
public class comments {
    int id;
    int user_id;
    int writer_id;
    String comment;
    int comment_reply_id;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getComment_reply_id() {
        return comment_reply_id;
    }

    public void setComment_reply_id(int comment_reply_id) {
        this.comment_reply_id = comment_reply_id;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getUser_id() {
        return user_id;
    }
    
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    public int getWriter_id() {
        return writer_id;
    }
    
    public void setWriter_id(int writer_id) {
        this.writer_id = writer_id;
    }
}