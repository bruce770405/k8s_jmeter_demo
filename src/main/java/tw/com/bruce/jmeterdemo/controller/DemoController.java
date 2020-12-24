package tw.com.bruce.jmeterdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tw.com.bruce.jmeterdemo.model.bo.DemoReservationBo;
import tw.com.bruce.jmeterdemo.model.dto.DemoReservationDto;
import tw.com.bruce.jmeterdemo.model.dto.Response;
import tw.com.bruce.jmeterdemo.service.DemoService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class DemoController {

    public final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @PostMapping(value = "/v1.0/demo/reservation")
    public ResponseEntity<Void> reservation(@RequestBody DemoReservationDto dto) {
        if (dto.getTrigger().isBefore(LocalDateTime.now())) {
            return ResponseEntity.badRequest().build();
        }
        demoService.doReservation(dto.convertToBo());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/v1.0/demo/get/{name}")
    public ResponseEntity<Response<List<DemoReservationDto>>> getReservationData(@PathVariable String name) {

        if (StringUtils.isEmpty(name)) {
            return ResponseEntity.badRequest().build();
        }

        DemoReservationBo bo = demoService.getReservation(name);
        if (Objects.isNull(bo)) {
            return ResponseEntity.ok(new Response<>(Collections.emptyList()));
        }
        List<DemoReservationBo> datas = Collections.singletonList(bo);
        List<DemoReservationDto> returnData = datas.stream().map(DemoReservationBo::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(new Response<>(returnData));
    }

    @GetMapping(value = "/v1.0/demo/getAll")
    public ResponseEntity<Response<List<DemoReservationDto>>> getAllReservationData() {
        List<DemoReservationBo> datas = demoService.getAllReservation();
        if (datas.isEmpty()) {
            return ResponseEntity.ok(new Response<>(Collections.emptyList()));
        }
        List<DemoReservationDto> returnData = datas.stream().map(DemoReservationBo::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(new Response<>(returnData));
    }
}
