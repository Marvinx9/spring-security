package med.voll.web_application.domain.paciente;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Deprecated
    public Paciente(){}

    public Paciente(Long id, DadosCadastroPaciente dados) {
        modificarDados(dados);
        this.id = id;
    }

    public void modificarDados(DadosCadastroPaciente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
    }
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCpf() {
        return cpf;
    }
}