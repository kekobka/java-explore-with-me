package ru.practicum.dto.mainservice.controllers.publics;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.practicum.dto.mainservice.dto.compilation.CompilationDto;
import ru.practicum.dto.mainservice.service.CompilationService;

import java.util.List;

@Controller
@RequestMapping("/compilations")
@RequiredArgsConstructor
public class CompilationsController {

    private final CompilationService compilationService;

    @GetMapping
    public ResponseEntity<List<CompilationDto>> findCompilations(@RequestParam(value = "pinned", required = false) Boolean pinned,
                                                                 @RequestParam(value = "from", defaultValue = "0")
                                                                 @PositiveOrZero Integer from,
                                                                 @RequestParam(value = "size", defaultValue = "10")
                                                                 @Positive Integer size) {
        return ResponseEntity.ok(compilationService.findByParams(pinned, from, size));
    }

    @GetMapping("/{comId}")
    public ResponseEntity<CompilationDto> findCompilationById(@PathVariable long comId) {
        return ResponseEntity.ok(compilationService.findById(comId));
    }
}