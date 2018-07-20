package net.odtel.json2m3u;

import lombok.extern.slf4j.Slf4j;
import net.odtel.json2m3u.service.M3uService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class M3UController {
    private M3uService m3uService;

    @Autowired
    public M3UController(M3uService m3uService) {
        this.m3uService = m3uService;
    }

    @GetMapping(path = "/m3u")
    public ResponseEntity<String> getM3U() throws UnsupportedEncodingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        String all = m3uService.getAll();

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(all.getBytes("UTF-8").length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(all);

    }

}
