# MaviBot - Chatbot baseado em regras

## 1. DESCRIÇÃO

A MaviBot é um chatbot baseado em regras desenvolvido em Java para a disciplina de Desenvolvimento de ChatBot, do curso de Ciência da Computação - Bacharelado, na UNIDERP.

O projeto foi desenvolvido com foco em cobrança e recuperação de crédito, área relacionada à minha rotina de trabalho. A ideia surgiu a partir de situações comuns desse tipo de atendimento, como negociação de dívidas, consulta e atualização de boletos e encaminhamento para atendentes.

A proposta é utilizar a MaviBot como um primeiro atendimento, resolvendo solicitações mais simples e encaminhando para um atendente os casos que precisam de uma análise específica.

Neste trabalho, a UCDB - Universidade Católica Dom Bosco foi utilizada como exemplo de credora. Os dados presentes no programa são simulados e estão cadastrados diretamente no código para demonstrar o funcionamento do chatbot.

O projeto utiliza regras, palavras-chave, estruturas condicionais, laços de repetição, funções, tratamento de erros e recursos de data do Java.

## 2. COMO EXECUTAR

É necessário ter o Java instalado no computador.

### 2.1. Pelo Visual Studio Code

1. Abra a pasta do projeto no Visual Studio Code.
2. Abra o arquivo `MaviBot.java`.
3. Execute o programa pelo próprio Visual Studio Code ou pelo terminal.

### 2.2. Pelo terminal

Compile o arquivo:

```bash
javac MaviBot.java
```

Depois execute:

```bash
java MaviBot
```

Ao iniciar, o chatbot apresenta o menu principal. O usuário pode escolher uma opção pelo número ou utilizar palavras relacionadas à solicitação.

Para encerrar, podem ser utilizados comandos como `sair`, `tchau` e `encerrar`, além da opção `5`.

## 3. FUNCIONALIDADES

### 3.1. Negociação de dívida

Permite consultar uma dívida utilizando um CPF de teste cadastrado no programa. Após a consulta, são apresentadas as mensalidades, os valores originais e o total da dívida.

O usuário pode escolher entre:

* Pagamento à vista;
* Pagamento parcelado;
* Pagamento com cartão de crédito;
* Não realizar a negociação;
* Voltar ao menu.

As condições implementadas no código são:

* **À vista:** o cliente paga 20% do valor original da dívida, correspondendo a 80% de desconto;
* **Parcelado:** é calculada uma entrada de 25% do valor original e o restante pode ser dividido em até 7 parcelas;
* **Cartão de crédito:** o valor correspondente ao pagamento à vista recebe um acréscimo de 12% e pode ser dividido em até 6 parcelas.

Após o cálculo, o usuário pode confirmar ou não a negociação. Em caso de confirmação, são solicitados os dados de contato para continuidade do atendimento.

### 3.2. Segunda via de boleto

Permite consultar os boletos vinculados a um CPF cadastrado.

É possível consultar todas as parcelas ou informar uma ou mais datas específicas. Quando uma data é informada, o programa verifica se existe uma parcela correspondente.

Após a consulta, o usuário pode escolher entre WhatsApp e e-mail para receber a segunda via.

### 3.3. Boleto em atraso

Permite consultar parcelas vencidas e calcular o valor atualizado.

O cálculo considera:

* Valor original da parcela;
* Multa de 10%;
* Juros de 1% ao mês, calculados proporcionalmente aos dias de atraso.

É possível consultar todas as parcelas em atraso ou informar datas específicas.

Depois de apresentar os valores atualizados, o usuário pode confirmar a atualização do boleto. Nesse caso, o sistema define uma nova data de vencimento para 7 dias após a data atual e solicita o canal de contato para envio.

### 3.4. Falar com atendente

Quando a solicitação não pode ser resolvida pelas opções disponíveis, o usuário pode solicitar atendimento com um agente.

Nesse fluxo são solicitados:

* Nome;
* CPF;
* Canal de contato;
* Telefone ou e-mail;
* Motivo do atendimento.

Ao final, o chatbot informa que a solicitação será encaminhada para um atendente.

## 4. REGRAS E RECONHECIMENTO DE MENSAGENS

A MaviBot identifica as solicitações por meio de palavras-chave e condições definidas diretamente no código. O projeto não utiliza inteligência artificial ou banco de dados.

### 4.1. Menu principal

```text
1 - Negociar dívida
2 - Segunda via de boleto
3 - Boleto em atraso
4 - Falar com atendente
5 - Sair
```

Além das opções numéricas, algumas solicitações podem ser identificadas por palavras relacionadas.

**Negociação:** `negociar`, `dívida`, `acordo`, `negociação`

**Segunda via:** `segunda via`, `segunda`, `parcela`

**Boleto em atraso:** `boleto`, `atrasado`, `atraso`, `vencido`, `boleto atualizado`

**Atendimento:** `atendente`, `atendimento`

### 4.2. Normalização do texto

O programa utiliza a classe `Normalizer` para tratar diferentes formas de escrita.

Assim, palavras como:

```text
Negociação
NEGOCIAÇÃO
negociacao
```

podem ser reconhecidas de forma semelhante, mesmo com diferenças de letras maiúsculas, minúsculas ou acentuação.

### 4.3. Regras compostas

Algumas intenções dependem da combinação de palavras.

No caso do boleto em atraso, por exemplo, o chatbot verifica termos relacionados a `boleto` junto com palavras que indicam atraso, como `atrasado`, `atraso` ou `vencido`.

Esse tipo de regra ajuda a diferenciar solicitações que possuem palavras semelhantes.

### 4.4. Encerramento

A conversa pode ser encerrada pela opção `5` ou por comandos como:

* `sair`;
* `tchau`;
* `encerrar`.

Quando identificados, esses comandos interrompem o laço principal do programa.

## 5. VALIDAÇÕES E TRATAMENTO DE ERROS

Para evitar entradas incorretas durante o atendimento, foram implementadas algumas validações.

### 5.1. CPF

O CPF informado é validado para verificar se possui 11 números. Depois, é comparado com os registros simulados no código.

Quando o CPF não é encontrado, o chatbot informa que não localizou os dados e apresenta um telefone para contato.

### 5.2. Nome

O nome precisa ser informado e possuir pelo menos dois caracteres. Caso contrário, o usuário pode tentar novamente dentro do limite definido.

### 5.3. Telefone

Ao escolher WhatsApp, o usuário informa um número de telefone com DDD.

O programa verifica se o número possui 10 ou 11 dígitos.

### 5.4. E-mail

Para o e-mail, é realizada uma validação simples com base em características básicas de um endereço, como a presença de `@` e `.`.

### 5.5. Opções inválidas

Quando uma opção não reconhecida é informada, o chatbot apresenta uma mensagem de erro.

Alguns fluxos possuem limite de três tentativas. Ao atingir esse limite, o programa encerra o fluxo ou o atendimento, dependendo da situação.

Esse tratamento evita que o usuário fique preso indefinidamente em uma opção incorreta.

## 6. CÁLCULOS E DATAS

O projeto utiliza `LocalDate` para trabalhar com datas e `ChronoUnit` para calcular a diferença de dias entre vencimentos e a data atual.

### 6.1. Atualização de boleto

Para parcelas vencidas, o valor atualizado é calculado considerando multa de 10% e juros de 1% ao mês, proporcionalmente aos dias de atraso.

A fórmula utilizada no código é:

```text
multa = valor original × 10%
juros = valor original × 1% × dias de atraso ÷ 30
valor atualizado = valor original + multa + juros
```

### 6.2. Negociação

Na negociação, o valor utilizado como base é o valor original retornado pelo método `mostrarTotal()`.

Para pagamento à vista:

```text
valor à vista = valor original × 20%
```

Isso representa um desconto de 80% sobre o valor original.

No parcelamento:

```text
entrada = valor original × 25%
restante = valor original - entrada
```

O restante pode ser dividido em até 7 parcelas.

No cartão:

```text
valor do cartão = valor original × 20% × 1,12
```

Ou seja, primeiro é aplicado o valor de 20% utilizado na condição à vista e depois o acréscimo de 12%.

### 6.3. Vencimentos

Na atualização de boleto, o novo vencimento é definido para 7 dias após a data atual.

Nas negociações, o primeiro vencimento também é calculado para 7 dias após a data atual. As parcelas seguintes são definidas acrescentando um mês para cada parcela.

### 6.4. Formatação dos valores

Os valores financeiros são apresentados com duas casas decimais no formato de moeda brasileira:

```text
R$ 500,00
R$ 1.250,50
```

## 7. ESTRUTURA DO PROJETO

O projeto utiliza um único arquivo Java principal:

```text
MaviBot
│
├── MaviBot.java
├── README.md
└── testes.pdf
```

* **MaviBot.java:** contém as regras, funções, cálculos e fluxos do chatbot.
* **README.md:** apresenta as informações e instruções do projeto.
* **testes.pdf:** reúne os registros dos testes realizados durante o desenvolvimento.

## 8. TESTES REALIZADOS

Foram realizados testes nos principais fluxos para verificar o funcionamento das regras e o comportamento do chatbot diante de diferentes entradas.

Entre eles:

* Opções do menu principal;
* Negociação à vista, parcelada e por cartão;
* Consulta de todas as parcelas;
* Consulta por datas específicas;
* Cálculo de multa e juros;
* Atualização da data de vencimento;
* Atendimento com agente;
* Validação de CPF, telefone e e-mail;
* Entradas inválidas e limite de tentativas;
* Comandos de retorno e encerramento.

Também foram verificadas diferentes formas de escrita das palavras-chave, incluindo palavras com e sem acentuação e letras maiúsculas ou minúsculas.

Os testes ajudaram a identificar problemas durante o desenvolvimento e verificar se, após uma entrada inválida, o programa retornava corretamente ao fluxo esperado.

## 9. LIMITAÇÕES

Por se tratar de um projeto acadêmico baseado em regras, a MaviBot possui algumas limitações.

Os dados são simulados e ficam diretamente no código. Em uma aplicação real, seria mais adequado utilizar um banco de dados para armazenar e consultar as informações dos clientes.

Também não existem integrações reais com sistemas de cobrança, emissão de boletos, WhatsApp ou e-mail. Esses processos são representados apenas como parte do fluxo do chatbot.

O reconhecimento das mensagens depende das palavras previstas nas regras. Dessa forma, uma frase diferente das condições programadas pode não ser identificada corretamente.

Além disso, uma aplicação real precisaria de recursos mais completos de segurança, autenticação, controle de acesso e proteção dos dados dos clientes.

Essas limitações fazem parte da proposta do trabalho, que tem como objetivo demonstrar a construção de um chatbot baseado em regras utilizando Java.

## 10. COMENTÁRIO PESSOAL

Escolhi desenvolver a MaviBot na área de cobrança porque é uma área que faz parte da minha rotina de trabalho. No dia a dia, existem situações em que o cliente precisa apenas de uma segunda via, atualização de um boleto, consulta de informações ou orientação sobre uma negociação.

A partir disso, pensei em utilizar o chatbot como uma primeira etapa do atendimento, deixando para o agente os casos que precisam de uma análise mais específica.

Durante o desenvolvimento, percebi que mesmo um chatbot baseado em regras precisa considerar vários caminhos possíveis. Foi necessário pensar nas palavras que poderiam ser utilizadas pelo usuário, nas entradas inválidas, nas tentativas de erro e também nas opções de voltar ou encerrar o atendimento.

Os cálculos de boletos atrasados e negociações também exigiram atenção, principalmente por envolverem valores e datas diferentes. Apesar de os dados serem simulados, procurei deixar os fluxos próximos de situações que podem acontecer em um atendimento real.

O projeto ajudou a entender melhor como regras e fluxos de atendimento podem ser organizados em Java. Também mostrou que, para transformar a ideia em uma aplicação realmente utilizada por uma empresa, seria necessário integrar o chatbot com banco de dados, sistemas de cobrança, emissão de boletos e canais de atendimento.

De forma geral, a MaviBot foi uma forma de relacionar o conteúdo da disciplina com uma situação que conheço na prática. Além de trabalhar com Java e regras de chatbot, o projeto permitiu pensar em como uma automação pode facilitar algumas etapas do atendimento de cobrança.