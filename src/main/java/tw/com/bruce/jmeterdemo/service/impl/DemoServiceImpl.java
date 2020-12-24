package tw.com.bruce.jmeterdemo.service.impl;

import org.springframework.stereotype.Service;
import tw.com.bruce.jmeterdemo.model.bo.DemoReservationBo;
import tw.com.bruce.jmeterdemo.service.DemoService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DemoServiceImpl implements DemoService {
    private final Map<String, DemoReservationBo> CACHE_DATA = new LinkedHashMap<>();

    @Override
    public void doReservation(DemoReservationBo bo) {
        CACHE_DATA.put(bo.getName(), bo);
    }

    @Override
    public DemoReservationBo getReservation(String name) {
        return CACHE_DATA.get(name);
    }

    @Override
    public List<DemoReservationBo> getAllReservation() {
        return new ArrayList<>(CACHE_DATA.values());
    }
}
