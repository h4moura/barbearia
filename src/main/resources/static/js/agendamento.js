// Função para desabilitar dias que a barbearia não funciona (exemplo: domingo)
const diasDeFuncionamento = [1, 2, 3, 4, 5, 6]; // 1 = Segunda-feira, 6 = Sábado

document.getElementById('data').addEventListener('input', function() {
  const dataSelecionada = new Date(this.value);
  const diaDaSemana = dataSelecionada.getDay();

  // Verifica se o dia selecionado está entre os dias de funcionamento
  if (!diasDeFuncionamento.includes(diaDaSemana)) {
    alert('A barbearia não funciona nesse dia.');
    this.value = ''; // Reseta a data inválida
  }
});

// Função para enviar os dados de agendamento ao backend
document.getElementById('agendamentoForm').addEventListener('submit', function(event) {
  event.preventDefault();

  // Coleta os valores dos campos do formulário
  const nome = document.getElementById('nome').value;
  const contato = document.getElementById('contato').value;
  const cidade = document.getElementById('cidade').value;
  const servico = document.getElementById('servico').value;
  const data = document.getElementById('data').value;
  const horario = document.getElementById('horario').value;

  // Objeto com os dados de agendamento
  const agendamento = {
    nome: nome,
    contato: contato,
    cidade: cidade,
    tipoCorte: servico,
    data: data,
    horario: horario
  };

  // A função fetch() é usada para fazer uma requisição HTTP para o servidor
fetch('http://localhost:8080/api/agendamentos', {  // URL do back-end onde os dados serão enviados

  // Configurações da requisição HTTP
  method: 'POST',  // O método HTTP usado para enviar dados. "POST" é usado quando enviamos novos dados ao servidor.

  // Cabeçalhos HTTP, que descrevem informações adicionais sobre a requisição
  headers: {  
    'Content-Type': 'application/json',  // Define o tipo de conteúdo que está sendo enviado: JSON.
    // O servidor usará essa informação para entender o formato dos dados no corpo da requisição.
  },

  // Corpo da requisição: os dados do agendamento que estamos enviando.
  // JSON.stringify() é usado para converter o objeto JavaScript "agendamento" em uma string JSON.
  body: JSON.stringify(agendamento),  // O corpo da requisição contendo os dados que serão enviados ao servidor

})
.then(response => {
  // Verifica se a resposta do servidor tem um status OK (código HTTP entre 200-299 indica sucesso)
  if (response.ok) {
    // Se a requisição foi bem-sucedida, mostramos uma mensagem de sucesso ao usuário.
    alert('Agendamento realizado com sucesso!');

    // Após o sucesso, limpamos o formulário para permitir um novo agendamento.
    document.getElementById('agendamentoForm').reset();
  } else {
    // Se o servidor respondeu com um erro (ex.: código HTTP 400 ou 500), mostramos uma mensagem de erro ao usuário.
    alert('Erro ao realizar agendamento. Tente novamente.');
  }
})
.catch(error => {
  // Se houver algum erro durante a requisição (por exemplo, se o servidor não estiver acessível),
  // a Promise será rejeitada e o código no catch será executado.
  
  console.error('Erro:', error);  // Mostramos o erro no console para fins de depuração.
  alert('Erro ao conectar com o servidor.');  // Exibimos uma mensagem de erro ao usuário, informando que houve falha na conexão.
});


});
