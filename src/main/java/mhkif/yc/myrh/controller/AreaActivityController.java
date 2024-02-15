package mhkif.yc.myrh.controller;

import lombok.RequiredArgsConstructor;
import mhkif.yc.myrh.dto.responses.ActivityAreaRes;
import mhkif.yc.myrh.service.IActivityAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("myrh/api/v1/areaActivity")
@RequiredArgsConstructor
public class AreaActivityController {

    private final IActivityAreaService areaService;

    @GetMapping("")
    public ResponseEntity<Page<ActivityAreaRes>>getAll(@RequestParam int page, @RequestParam int size){
       return ResponseEntity.ok(areaService.getAll(page,size));

    }



}
