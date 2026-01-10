const API_CONSULTA = "http://localhost:8080/consulta";
const API_MEDICO = "http://localhost:8080/medico";
const API_PACIENTE = "http://localhost:8080/paciente";

document.getElementById('consulta-form').addEventListener('submit', salvarConsulta);

// Ao carregar a página, busca Médicos, Pacientes e lista Consultas
window.onload = async () => {
    await carregarSelects();
    listarConsultas();
};

async function carregarSelects() {
    // Preenche o Select de Médicos
    const resMed = await fetch(API_MEDICO);
    const medicos = await resMed.json();
    const selMed = document.getElementById('select-medico');
    if(Array.isArray(medicos)) {
        medicos.forEach(m => selMed.innerHTML += `<option value="${m.id}">${m.nome} - ${m.especialidade}</option>`);
    }

    // Preenche o Select de Pacientes
    const resPac = await fetch(API_PACIENTE);
    const pacientes = await resPac.json();
    const selPac = document.getElementById('select-paciente');
    if(Array.isArray(pacientes)) {
        pacientes.forEach(p => selPac.innerHTML += `<option value="${p.id}">${p.nome}</option>`);
    }
}

async function listarConsultas() {
    try {
        const response = await fetch(API_CONSULTA);
        const consultas = await response.json();
        const tbody = document.getElementById('consulta-table-body');
        tbody.innerHTML = "";

        if (!Array.isArray(consultas)) return;

        consultas.forEach(c => {
            // Verifica se medico/paciente não são nulos para evitar erro no display
            const nomeMedico = c.medico ? c.medico.nome : 'N/A';
            const nomePaciente = c.paciente ? c.paciente.nome : 'N/A';

            tbody.innerHTML += `
                <tr>
                    <td>${c.data} às ${c.horario}</td>
                    <td>${nomeMedico}</td>
                    <td>${nomePaciente}</td>
                    <td>${c.observacoes}</td>
                    <td>
                        <button class="btn-delete" onclick="excluirConsulta(${c.id})">Cancelar</button>
                    </td>
                </tr>`;
        });
    } catch (error) { console.error("Erro ao listar consultas:", error); }
}

async function salvarConsulta(event) {
    event.preventDefault();

    const id = document.getElementById('consulta-id').value;

    // Montando o objeto JSON conforme o Java espera (com objetos aninhados)
    const consulta = {
        data: document.getElementById('data').value,
        horario: document.getElementById('horario').value,
        observacoes: document.getElementById('observacoes').value,
        medico: { id: document.getElementById('select-medico').value },
        paciente: { id: document.getElementById('select-paciente').value }
    };

    // Consultas geralmente usamos POST para agendar. Edição é mais complexa.
    // Vamos manter simples: POST para criar.
    try {
        const response = await fetch(API_CONSULTA, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(consulta)
        });

        if (response.ok) {
            alert("Consulta agendada!");
            resetForm();
            listarConsultas();
        } else {
            alert("Erro ao agendar. Verifique os dados.");
        }
    } catch (error) { console.error(error); }
}

async function excluirConsulta(id) {
    if(confirm("Cancelar esta consulta?")) {
        await fetch(`${API_CONSULTA}/${id}`, { method: 'DELETE' });
        listarConsultas();
    }
}

function resetForm() {
    document.getElementById('consulta-form').reset();
}