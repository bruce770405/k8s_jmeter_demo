package tw.com.bruce.jmeterdemo.model.dto;

import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import tw.com.bruce.jmeterdemo.model.bo.DemoReservationBo;

import java.time.LocalDateTime;

public class DemoReservationDto {

    private String name;

    private String mail;

    private String content;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime trigger;

    public DemoReservationBo convertToBo() {
        DemoReservationBo bo = new DemoReservationBo();
        BeanUtils.copyProperties(this, bo);
        return bo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTrigger() {
        return trigger;
    }

    public void setTrigger(LocalDateTime trigger) {
        this.trigger = trigger;
    }
}
