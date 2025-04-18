package com.ccsw.tutorial.loan;

import com.ccsw.tutorial.config.ResponsePage;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Loan", description = "API of Loan")
@RequestMapping(value = "/loan")
@RestController
@CrossOrigin(origins = "*")
public class LoanController {

    @Autowired
    LoanService loanService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para obtener una lista paginada de préstamos y con filtros también
     */
    @Operation(summary = "Find Page", description = "Returns a paginated list of loans with optional filters")
    @PostMapping("")
    public ResponsePage<LoanDto> find(@RequestBody LoanSearchRequest request) {
        return loanService.find(request.getPageable(), request.getFilters());
    }

    /**
     * Nuevo método sin paginación, como en catálogo, lo hice al principio para que me fuera mas sencillo
     */
    @Operation(summary = "Find all (filtered)", description = "Returns all loans with optional filters")
    @GetMapping("/filtered")
    public List<LoanDto> findFiltered(@RequestParam(value = "clientId", required = false) Long clientId, @RequestParam(value = "gameId", required = false) Long gameId,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Loan> loans = loanService.findFiltered(clientId, gameId, date);

        return loans.stream().map(e -> mapper.map(e, LoanDto.class)).collect(Collectors.toList());
    }

    /**
     * Guardar préstamo (no se utiliza)
     */
    @Operation(summary = "Save", description = "Create or update a loan")
    @PutMapping
    public void save(@RequestBody LoanDto dto) {
        loanService.save(dto);
    }

    /**
     * Crear préstamo
     * @param dto
     */
    @Operation(summary = "Save", description = "Create a loan")
    @PostMapping("/create")
    public void create(@RequestBody LoanDto dto) {
        loanService.save(dto);
    }

    /**
     * Eliminar préstamo
     */
    @Operation(summary = "Delete", description = "Delete a loan by its ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        loanService.delete(id);
    }

    @GetMapping("/all-debug")
    public List<Loan> findAllDebug() {
        return loanService.findAll();
    }

}
