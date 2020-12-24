package tw.com.bruce.jmeterdemo.model.bo;

import org.springframework.beans.BeanUtils;
import tw.com.bruce.jmeterdemo.model.dto.DemoReservationDto;

import java.time.LocalDateTime;

public class DemoReservationBo {

    private String name;

    private String mail;

    private String content;

    private LocalDateTime trigger;

    public DemoReservationDto convertToDto() {
        DemoReservationDto dto = new DemoReservationDto();
        BeanUtils.copyProperties(this, dto);
        return dto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTrigger() {
        return trigger;
    }

    public void setTrigger(LocalDateTime trigger) {
        this.trigger = trigger;
    }
}
