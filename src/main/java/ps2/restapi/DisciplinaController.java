package ps2.restapi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {
    // Simulação de um repositório de disciplinas
    private List<Disciplina> disciplinas = new ArrayList<>();
    
    public DisciplinaController() {
        disciplinas.add(new Disciplina(1, "Matemática", "MAT", "Engenharia", 1));
        disciplinas.add(new Disciplina(2, "Programação", "PROG", "Sistemas de Informação", 2));
        disciplinas.add(new Disciplina(3, "Física", "FIS", "Engenharia", 1));
    }
    
    @GetMapping
    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getDisciplina(@PathVariable long id) {
        Disciplina disciplina = findDisciplinaById(id);
        if (disciplina != null) {
            return ResponseEntity.ok(disciplina);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Disciplina> addDisciplina(@RequestBody Disciplina disciplina) {
        disciplinas.add(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplina);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> updateDisciplina(@PathVariable long id, @RequestBody Disciplina disciplina) {
        Disciplina existingDisciplina = findDisciplinaById(id);
        if (existingDisciplina != null) {
            existingDisciplina.setNome(disciplina.getNome());
            existingDisciplina.setSigla(disciplina.getSigla());
            existingDisciplina.setCurso(disciplina.getCurso());
            existingDisciplina.setSemestre(disciplina.getSemestre());
            return ResponseEntity.ok(existingDisciplina);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable long id) {
        Disciplina disciplina = findDisciplinaById(id);
        if (disciplina != null) {
            disciplinas.remove(disciplina);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Método auxiliar para encontrar uma disciplina pelo ID
    private Disciplina findDisciplinaById(long id) {
        for (Disciplina disciplina : disciplinas) {
            if (disciplina.getId() == id) {
                return disciplina;
            }
        }
        return null;
    }
}


