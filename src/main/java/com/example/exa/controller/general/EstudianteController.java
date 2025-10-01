package com.example.exa.controller.general;

import com.example.exa.dto.EstudianteDTO;
import com.example.exa.dto.NotaDTO;
import com.example.exa.service.service.EstudianteService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // ✅ Listar todos los estudiantes (con sus notas y materias)
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> listAll() throws ServiceException {
        return ResponseEntity.ok(estudianteService.findAll());
    }

    // ✅ Obtener estudiante por ID (con sus notas y materias)
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> read(@PathVariable Long id) throws ServiceException {
        EstudianteDTO estudianteDTO = estudianteService.findById(id);
        return ResponseEntity.ok(estudianteDTO);
    }

    // ✅ Crear estudiante
    @PostMapping
    public ResponseEntity<EstudianteDTO> create(@RequestBody EstudianteDTO estudianteDTO) throws ServiceException {
        EstudianteDTO estudianteCreado = estudianteService.create(estudianteDTO);
        return new ResponseEntity<>(estudianteCreado, HttpStatus.CREATED);
    }

    // ✅ Actualizar estudiante
    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> update(@PathVariable Long id,
                                                @RequestBody EstudianteDTO estudianteDTO) throws ServiceException {
        EstudianteDTO estudianteActualizado = estudianteService.update(id, estudianteDTO);
        return ResponseEntity.ok(estudianteActualizado);
    }

    // ✅ Eliminar estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        estudianteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Listar solo las notas de un estudiante
    @GetMapping("/{id}/notas")
    public ResponseEntity<List<NotaDTO>> listNotasByEstudiante(@PathVariable Long id) throws ServiceException {
        List<NotaDTO> notas = estudianteService.findNotasByEstudiante(id);
        return ResponseEntity.ok(notas);
    }
}
