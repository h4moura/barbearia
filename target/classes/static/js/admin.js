// script.js

// Função para buscar agendamentos do backend
async function fetchAgendamentos() {
    try {
        const response = await fetch('/api/agendamentos'); // URL do endpoint REST
        if (!response.ok) {
            throw new Error('Erro ao buscar agendamentos');
        }
        const agendamentos = await response.json(); // Converte a resposta para JSON

        // Chama a função para exibir agendamentos
        displayAgendamentos(agendamentos);
    } catch (error) {
        console.error('Erro:', error);
    }
}

// Função para exibir agendamentos na tabela
function displayAgendamentos(agendamentos) {
    const tableBody = document.querySelector('#agendamentosTable tbody');
    tableBody.innerHTML = ''; // Limpa o conteúdo atual da tabela

    // Percorre cada agendamento e cria uma nova linha na tabela
    agendamentos.forEach(agendamento => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${agendamento.nome}</td>
            <td>${agendamento.contato}</td>
            <td>${agendamento.cidade}</td>
            <td>${agendamento.tipoCorte}</td>
            <td>${new Date(agendamento.dataHora).toLocaleString('pt-BR')}</td>
        `;
        tableBody.appendChild(row); // Adiciona a nova linha ao corpo da tabela
    });
}

// Chama a função fetchAgendamentos quando a página é carregada
document.addEventListener('DOMContentLoaded', fetchAgendamentos);
