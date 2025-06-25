package med.voll.web_application.domain.consulta;

import jakarta.persistence.*;
import med.voll.web_application.domain.medico.Medico;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paciente_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Medico medico;

    private LocalDateTime data;

    @Deprecated
    public Consulta(){}

    public Consulta(Medico medico, DadosAgendamentoConsulta dados) {
        modificarDados(medico, dados);
    }

    public void modificarDados(Medico medico, DadosAgendamentoConsulta dados) {
        this.medico = medico;
        this.paciente_id = dados.paciente_id();
        this.data = dados.data();
    }

    public Long getId() {
        return id;
    }

    public String getPaciente() {
        return paciente_id;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDateTime getData() {
        return data;
    }

}
