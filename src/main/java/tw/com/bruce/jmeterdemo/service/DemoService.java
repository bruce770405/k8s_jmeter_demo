package tw.com.bruce.jmeterdemo.service;

import tw.com.bruce.jmeterdemo.model.bo.DemoReservationBo;

import java.util.List;

public interface DemoService {

    void doReservation(DemoReservationBo bo);

    DemoReservationBo getReservation(String name);

    List<DemoReservationBo> getAllReservation();
}
