package mhkif.yc.myrh.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mhkif.yc.myrh.config.security.authenticators.AuthenticatedAdmin;
import mhkif.yc.myrh.config.security.jwt.JwtService;
import mhkif.yc.myrh.dto.HttpRes;
import mhkif.yc.myrh.dto.requests.AdminReq;
import mhkif.yc.myrh.dto.requests.AuthReq;
import mhkif.yc.myrh.dto.responses.AdminRes;
import mhkif.yc.myrh.mapper.AdminMapper;
import mhkif.yc.myrh.service.IAdminService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("myrh/api/v1/admin")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AdminController {

    private final IAdminService service;
    private final JwtService jwtService;
    private final AdminMapper mapper;







    @PostMapping("")
    public ResponseEntity<AdminRes> save(@RequestBody AdminReq adminReq) {
        AdminRes response = service.create(adminReq);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auth")
    public ResponseEntity<HttpRes> auth(@Valid @RequestBody AuthReq auth){
        AdminRes response = service.auth(auth.getEmail(), auth.getPassword());
        AuthenticatedAdmin authenticatedEntity = new AuthenticatedAdmin(mapper.resToEntity(response));

        var token = jwtService.generateToken(authenticatedEntity);
        return ResponseEntity.accepted().body(
                HttpRes.builder()
                        .timeStamp(LocalDateTime.now().toString())
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .path("myrh/api/v1/admin")
                        .status(HttpStatus.ACCEPTED)
                        .message("Admin has been authenticated")
                        .developerMessage("Admin  has been authenticated")
                        .data(Map.of("response", response, "token", token))
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<Page<AdminRes>> getAll(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(service.getAll(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<AdminRes> get(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

}
