// Função para buscar agendamentos do backend
async function fetchAgendamentos() {
    try {
        const response = await fetch('/api/agendamentos'); // Ajuste a URL conforme necessário
        if (!response.ok) {
            throw new Error('Erro ao buscar agendamentos');
        }
        const data = await response.json();
        populateTable(data);
        updateSummary(data);
    } catch (error) {
        document.getElementById("message").innerText = "Erro ao carregar agendamentos.";
        console.error('Error fetching agendamentos:', error);
    }
}

function populateTable(agendamentos) {
    const tableBody = document.getElementById('agendamentosTable').querySelector('tbody');
    tableBody.innerHTML = ''; // Limpa a tabela

    agendamentos.forEach(agendamento => {
        const row = document.createElement('tr');
        const formattedDate = new Date(agendamento.data).toLocaleDateString('pt-BR');
        const formattedTime = agendamento.horario; // A hora deve vir como string no formato "HH:mm"

        row.innerHTML = `
            <td>${agendamento.nome}</td>
            <td>${agendamento.contato}</td>
            <td>${agendamento.cidade}</td>
            <td>${agendamento.tipoCorte}</td>
            <td>${formattedDate}</td>
            <td>${formattedTime}</td>
            <td>

                <button class="button" onclick="excluir(${agendamento.id})">Excluir</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

// Função para atualizar as informações gerais
function updateSummary(agendamentos) {
    document.getElementById('totalAgendamentos').innerText = agendamentos.length;
    const hoje = new Date();
    const agendamentosHoje = agendamentos.filter(agendamento => {
        const dataAgendamento = new Date(agendamento.data); // Ajustado para pegar a data corretamente
        return dataAgendamento.getFullYear() === hoje.getFullYear() &&
               dataAgendamento.getMonth() === hoje.getMonth() &&
               dataAgendamento.getDate() === hoje.getDate();
    });
    document.getElementById('agendamentosHoje').innerText = agendamentosHoje.length;
}

// Funções para reagendar e excluir agendamentos (defina conforme necessário)
function reagendar(id) {
    console.log('Reagendar:', id);
    // Lógica para reagendar
}

function excluir(id) {
    console.log('Excluir:', id);
    // Lógica para excluir
}

// Chama a função fetchAgendamentos quando a página é carregada
document.addEventListener("DOMContentLoaded", fetchAgendamentos);



// Função para excluir agendamentos
async function excluir(id) {
    const confirmacao = confirm('Tem certeza que deseja excluir este agendamento?');
    if (confirmacao) {
        try {
            const response = await fetch(`/api/agendamentos/${id}`, {
                method: 'DELETE'
            });
            if (!response.ok) {
                throw new Error('Erro ao excluir agendamento');
            }
            // Recarregar os agendamentos após a exclusão
            fetchAgendamentos();
        } catch (error) {
            document.getElementById("message").innerText = "Erro ao excluir agendamento.";
            console.error('Error excluding agendamento:', error);
        }
    }
}

function reagendar(id) {
    // Redireciona para a página de reagendamento com o ID do agendamento na URL
        window.location.href = `reagendar.html?id=${id}`;
}
