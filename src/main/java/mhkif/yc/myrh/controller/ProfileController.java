package mhkif.yc.myrh.controller;

import lombok.RequiredArgsConstructor;
import mhkif.yc.myrh.dto.responses.ProfileRes;
import mhkif.yc.myrh.service.IProfileService;
import mhkif.yc.myrh.service.impl.ProfileServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/myrh/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private  final  IProfileService service;

    @GetMapping("/activity/{id}")
    public ResponseEntity<Set<ProfileRes>>getAllByActivity(@PathVariable Integer id){
        return  ResponseEntity.ok(service.getAllByArea(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileRes>getOne(@PathVariable Integer id){
        return  ResponseEntity.ok(service.getById(id));
    }
}
