# MaviBot — Chatbot baseado em regras

Aluno: Samara Culere de Oliveira | Disciplina: Desenvolvimento de ChatBot | Professor: Murilo Costa

## 1. Descrição

O **MaviBot** é um chatbot baseado em regras desenvolvido em Java para a disciplina de Desenvolvimento de ChatBot, do curso de Ciência da Computação — Bacharelado, da UNIDERP.

O sistema simula um atendimento de **cobrança e negociação de dívidas**, utilizando palavras-chave, estruturas condicionais, laços de repetição, funções, validações, tratamento de erros e cálculos com datas e valores.

A **UCDB — Universidade Católica Dom Bosco** foi utilizada como credora na simulação. Todos os dados são fictícios e estão definidos diretamente no código.

A proposta é utilizar o chatbot como uma primeira etapa do atendimento, resolvendo solicitações simples e encaminhando casos que necessitam de análise para um atendente.

---

## 2. Como executar

Ao executar o programa, será exibido o menu principal:

1 - Negociar dívida
2 - Segunda via de boleto
3 - Boleto em atraso
4 - Falar com atendente
5 - Sair

Além das opções numéricas, o chatbot reconhece palavras relacionadas a cada serviço.

Para encerrar o atendimento, utilize `5`, `sair`, `tchau` ou `encerrar`.

---

## 3. Dados para teste

Os seguintes dados estão cadastrados no código:

### Negociação

**CPF:** `12345678910`

### Segunda via

**CPF:** `12345678911`

### Boletos em atraso

**CPF:** `12345678912`

---

## 4. Funcionalidades

### Negociação de dívida

Permite consultar a dívida e escolher uma condição:

* **À vista:** 20% do valor original;
* **Parcelado:** 25% de entrada e restante em até 7 parcelas;
* **Cartão:** valor à vista com acréscimo de 12%, em até 6 parcelas.

Após a confirmação, o usuário informa o canal de contato e os dados necessários.

A primeira data de vencimento é definida para 7 dias após a data atual. As próximas parcelas são calculadas com acréscimo de um mês.

### Segunda via de boleto

Permite consultar todas as parcelas ou selecionar uma ou mais por data.

O envio pode ser solicitado por:

* WhatsApp;
* E-mail.

### Boleto em atraso

Permite consultar uma ou mais parcelas vencidas e calcular o valor atualizado.

O cálculo considera:

* multa de 10%;
* juros de 1% ao mês.

Após a confirmação, o novo vencimento é definido para 7 dias após a data atual.

### Falar com atendente

Solicita:

* nome;
* CPF;
* canal de contato;
* telefone ou e-mail;
* motivo do atendimento.

Ao final, o sistema informa que a solicitação foi encaminhada e que um atendente entrará em contato em até 15 minutos.

---

## 5. Reconhecimento de mensagens

O chatbot aceita opções numéricas e palavras-chave.

| Atendimento      | Exemplos                                                 |
| ---------------- | -------------------------------------------------------- |
| Negociação       | `negociar`, `dívida`, `acordo`, `negociação`             |
| Segunda via      | `segunda via`, `segunda`, `parcela`                      |
| Boleto em atraso | `boleto atrasado`, `boleto vencido`, `boleto atualizado` |
| Atendente        | `atendente`, `atendimento`                               |
| Encerramento     | `sair`, `tchau`, `encerrar`                              |

O método `normalizar()` remove acentos, converte o texto para minúsculas e elimina caracteres especiais, facilitando o reconhecimento de diferentes formas de escrita.

---

## 6. Validações

O sistema possui validações para entradas inválidas, incluindo:

* **CPF:** 11 números;
* **Nome:** pelo menos dois caracteres;
* **Telefone:** 10 ou 11 números;
* **E-mail:** formato básico válido;
* **Parcelamento:** de 1 a 7 parcelas;
* **Cartão:** de 1 a 6 parcelas.

Alguns fluxos possuem limite de três tentativas.

---

## 7. Cálculos

### Boleto em atraso

**Multa:**

`valor original × 10%`

**Juros:**

`valor original × 1% × dias de atraso ÷ 30`

**Valor atualizado:**

`valor original + multa + juros`

### À vista

`valor à vista = valor original × 20%`

### Parcelamento

`entrada = valor original × 25%`

`restante = valor original - entrada`

O restante é dividido pela quantidade de parcelas escolhida.

### Cartão

`valor do cartão = valor original × 20% × 1,12`

---

## 8. Datas

O projeto utiliza `LocalDate` para manipulação de datas e `ChronoUnit` para calcular períodos.

Nas negociações e atualizações de boletos, o primeiro vencimento é definido para 7 dias após a data atual. As parcelas seguintes são calculadas com acréscimo de um mês.

---

## 9. Testes

Foram realizados testes dos principais fluxos do chatbot, incluindo:

* negociação de dívida;
* segunda via de boleto;
* boleto em atraso;
* atendimento com atendente;
* reconhecimento de palavras-chave;
* entradas inválidas;
* encerramento do atendimento.

Os registros dos testes estão disponíveis no arquivo `testes.pdf`.

---

## 10. Estrutura do projeto

```text
MaviBot
├── MaviBot.java
├── README.md
└── testes.pdf
```

* `MaviBot.java`: regras, fluxos, validações e cálculos.
* `README.md`: documentação do projeto.
* `testes.pdf`: registros dos testes realizados.

---

## 11. Limitações

O MaviBot é um protótipo acadêmico. Os dados são fictícios e armazenados diretamente no código.

Não há integração real com banco de dados, sistemas de cobrança, emissão de boletos, WhatsApp ou e-mail.

O reconhecimento das mensagens depende das regras implementadas, portanto frases que não correspondam aos padrões previstos podem não ser identificadas.

Uma aplicação real exigiria recursos adicionais de autenticação, segurança, proteção de dados e integração com os sistemas da empresa.

---

## 12. Comentário pessoal

Escolhi desenvolver o MaviBot na área de cobrança por ser um contexto que faz parte da minha rotina de trabalho. Existem situações em que o cliente precisa apenas de uma segunda via, atualização de boleto, consulta de informações ou orientação sobre uma negociação.

A ideia foi utilizar o chatbot como uma primeira etapa do atendimento, deixando para o agente os casos que precisam de uma análise mais específica.

Durante o desenvolvimento, percebi que mesmo um chatbot baseado em regras precisa considerar diferentes caminhos de interação, entradas inválidas e possibilidades de retorno ou encerramento.

Os cálculos de boletos atrasados e negociações também exigiram atenção por envolverem valores e datas. Embora os dados sejam simulados, procurei aproximar os fluxos de situações que podem ocorrer em um atendimento real.

O projeto permitiu relacionar o conteúdo da disciplina com uma situação conhecida na prática, trabalhando conceitos de Java, regras de chatbot, validações, cálculos e automação de atendimento.

Para uma aplicação real, seria necessário evoluir o projeto com banco de dados, sistemas de cobrança, emissão de boletos e integração com canais de atendimento.