# MaviBot - Chatbot baseado em regras

## 1. Descrição

O MaviBot é um chatbot baseado em regras desenvolvido em Java para a disciplina de Desenvolvimento de ChatBot, do curso de Ciência da Computação - Bacharelado, na UNIDERP.

O sistema simula um atendimento de **cobrança e negociação de dívidas**, utilizando palavras-chave, estruturas condicionais, laços de repetição, funções, validações, tratamento de erros e cálculos com datas e valores.

A **UCDB - Universidade Católica Dom Bosco** foi utilizada como credora na simulação. Os dados utilizados são fictícios e estão definidos diretamente no código.

A proposta é utilizar o chatbot como um primeiro atendimento, resolvendo solicitações mais simples e encaminhando situações que precisam de atendimento para um agente.

---

## 2. Como executar

É necessário ter o Java instalado no computador.

### Pelo Visual Studio Code

1. Abra a pasta do projeto no Visual Studio Code.
2. Abra o arquivo `MaviBot.java`.
3. Abra o terminal.
4. Compile o programa:

```bash
javac MaviBot.java
```

5. Execute:

```bash
java MaviBot
```

6. O menu principal será apresentado.

Para encerrar, escolha a opção **5** ou digite `sair`, `tchau` ou `encerrar`.

---

## 3. Menu principal

Ao iniciar o programa, são apresentadas as seguintes opções:

```text
1 - Negociar dívida
2 - Segunda via de boleto
3 - Boleto em atraso
4 - Falar com atendente
5 - Sair
```

Além das opções numéricas, o chatbot reconhece algumas palavras e frases relacionadas a cada atendimento.

---

## 4. Dados para teste

Os dados abaixo estão cadastrados diretamente no código e devem ser utilizados para testar as funcionalidades.

### 4.1. Negociação de dívida

**CPF:**

```text
12345678910
```

**Cliente:** Analice Marques

**Dívida:**

```text
Parcela 1 - 20/07/2020 - R$ 500,00
Parcela 2 - 20/08/2020 - R$ 500,00
Parcela 3 - 20/09/2020 - R$ 500,00
```

**Total original:** R$ 1.500,00

---

### 4.2. Segunda via de boleto

**CPF:**

```text
12345678911
```

**Cliente:** Samara Culere

**Parcelas:**

```text
Parcela 1 - 10/10/2026 - R$ 350,00
Parcela 2 - 10/11/2026 - R$ 350,00
Parcela 3 - 10/12/2026 - R$ 350,00
```

Para testar uma parcela específica, utilize uma destas datas:

```text
10/10/2026
10/11/2026
10/12/2026
```

Também é possível informar mais de uma data separando-as por vírgula, por exemplo:

```text
10/10/2026, 10/11/2026
```

---

### 4.3. Boleto em atraso

**CPF:**

```text
12345678912
```

**Cliente:** Gabriel Lizarb

**Parcelas em atraso:**

```text
Parcela 1 - 10/01/2026 - R$ 500,00
Parcela 2 - 10/02/2026 - R$ 209,00
Parcela 3 - 10/03/2026 - R$ 209,00
Parcela 4 - 10/04/2026 - R$ 209,00
Parcela 5 - 10/05/2026 - R$ 209,00
```

Para testar uma parcela específica, utilize uma destas datas:

```text
10/01/2026
10/02/2026
10/03/2026
10/04/2026
10/05/2026
```

Também é possível informar mais de uma data, separando-as por vírgula.

---

## 5. Funcionalidades

### 5.1. Negociação de dívida

A opção `1` permite consultar a dívida do CPF de teste e escolher uma forma de negociação.

As opções são:

* **À vista:** 20% do valor original da dívida;
* **Parcelado:** 25% de entrada e o restante em até 7 parcelas;
* **Cartão de crédito:** valor à vista com acréscimo de 12%, em até 6 parcelas;
* **Nenhuma dessas opções;**
* **Voltar ao menu principal.**

Após escolher uma negociação, o sistema apresenta os valores e pergunta se o cliente deseja prosseguir.

Quando a negociação é confirmada, são solicitados o canal de contato e o respectivo telefone ou e-mail.

A primeira data de vencimento é definida para **7 dias após a data atual**.

---

### 5.2. Segunda via de boleto

A opção `2` permite consultar o acordo cadastrado para o CPF de teste.

O usuário pode:

* solicitar todas as parcelas;
* informar uma data específica;
* informar mais de uma data;
* voltar ao menu.

Depois da consulta, o usuário escolhe receber a segunda via por:

* WhatsApp;
* E-mail.

O sistema apresenta os dados da solicitação e informa que o boleto será enviado pelo canal escolhido.

---

### 5.3. Boleto em atraso

A opção `3` permite consultar parcelas vencidas e calcular seus valores atualizados.

O usuário pode:

* atualizar todas as parcelas;
* informar uma data específica;
* informar mais de uma data;
* voltar ao menu.

O cálculo considera:

* multa de 10%;
* juros de 1% ao mês;
* juros proporcionais aos dias de atraso.

Depois de consultar os valores, o sistema pergunta se o usuário deseja atualizar os boletos.

Quando a atualização é confirmada, é definida uma nova data de vencimento para **7 dias após a data atual**.

---

### 5.4. Falar com atendente

A opção `4` permite solicitar atendimento com um agente.

O sistema solicita:

1. Nome;
2. CPF;
3. Canal de contato;
4. Telefone ou e-mail;
5. Motivo do atendimento.

Ao final, os dados da solicitação são apresentados e o chatbot informa que um atendente entrará em contato em até 15 minutos.

---

## 6. Regras de reconhecimento

O chatbot reconhece as opções numéricas e palavras relacionadas à solicitação.

### Negociação

Exemplos:

```text
negociar
dívida
acordo
negociação
```

### Segunda via

Exemplos:

```text
segunda via
segunda
parcela
```

### Boleto em atraso

Exemplos:

```text
boleto atrasado
boleto atraso
boleto vencido
boleto atualizado
```

Também são reconhecidas combinações de termos, como `boleto` junto com `atrasado`, `atraso` ou `vencido`.

### Atendimento

Exemplos:

```text
atendente
atendimento
```

### Encerramento

O atendimento pode ser encerrado digitando:

```text
sair
tchau
encerrar
```

ou escolhendo a opção:

```text
5
```

---

## 7. Normalização das mensagens

O método `normalizar()` utiliza a classe `Normalizer` para facilitar o reconhecimento das mensagens.

A normalização:

* remove acentos;
* converte letras para minúsculas;
* remove caracteres especiais;
* mantém números e espaços.

Assim, palavras como:

```text
Negociação
NEGOCIAÇÃO
negociacao
```

podem ser reconhecidas de forma semelhante.

---

## 8. Validações e tratamento de erros

O sistema possui validações para evitar entradas incorretas.

### CPF

O CPF deve possuir exatamente **11 números**.

Para consultar dados de negociação, segunda via ou boletos em atraso, deve ser utilizado um dos CPFs cadastrados na seção **Dados para teste**.

### Nome

O nome precisa ser informado e possuir pelo menos dois caracteres.

### Telefone

O telefone deve possuir **10 ou 11 números**, incluindo o DDD.

Exemplo:

```text
67999999999
```

### E-mail

O sistema verifica se o e-mail possui um formato básico válido.

Exemplo:

```text
exemplo@email.com
```

### Quantidade de parcelas

Na negociação parcelada:

* mínimo: 1 parcela;
* máximo: 7 parcelas.

No cartão:

* mínimo: 1 parcela;
* máximo: 6 parcelas.

### Tentativas

Algumas opções possuem limite de **3 tentativas**.

Quando o limite é atingido, o fluxo ou atendimento é encerrado.

---

## 9. Cálculos

### 9.1. Boleto em atraso

O valor atualizado é calculado utilizando multa de 10% e juros proporcionais aos dias de atraso.

```text
multa = valor original × 10%

juros = valor original × 1% × dias de atraso ÷ 30

valor atualizado = valor original + multa + juros
```

### 9.2. Negociação à vista

```text
valor à vista = valor original × 20%
```

### 9.3. Negociação parcelada

```text
entrada = valor original × 25%

restante = valor original - entrada
```

O restante pode ser dividido em até 7 parcelas.

### 9.4. Cartão de crédito

```text
valor do cartão = valor original × 20% × 1,12
```

O valor correspondente à condição à vista recebe um acréscimo de 12%.

---

## 10. Datas

O projeto utiliza `LocalDate` para trabalhar com datas e `ChronoUnit` para calcular a diferença de dias entre datas.

Na atualização de boletos, o novo vencimento é definido para:

```text
data atual + 7 dias
```

Nas negociações, a primeira parcela também possui vencimento para 7 dias após a data atual. As parcelas seguintes são calculadas acrescentando um mês.

---

## 11. Passo a passo para testar

### Teste 1 - Negociação à vista

1. Execute o programa.
2. Digite:

```text
1
```

3. Informe:

```text
12345678910
```

4. O sistema apresentará a dívida de **Analice Marques**.
5. Escolha:

```text
1
```

para pagamento à vista.
6. Confira o valor apresentado.
7. Digite `1` para confirmar.
8. Escolha o canal:

* `1` para WhatsApp; ou
* `2` para E-mail.

9. Informe um telefone válido ou e-mail válido.

---

### Teste 2 - Negociação parcelada

1. Digite `1`.
2. Informe:

```text
12345678910
```

3. Escolha:

```text
2
```

4. Informe uma quantidade entre `1` e `7`.

Por exemplo:

```text
5
```

5. Confira a entrada e o valor das parcelas.
6. Digite `1` para confirmar.
7. Escolha o canal de contato.
8. Informe o contato solicitado.

---

### Teste 3 - Negociação no cartão

1. Digite `1`.
2. Informe:

```text
12345678910
```

3. Escolha:

```text
3
```

4. Informe uma quantidade entre `1` e `6`.

Por exemplo:

```text
3
```

5. Confira o valor calculado.
6. Confirme a negociação.
7. Informe o canal e o contato.

---

### Teste 4 - Segunda via de todas as parcelas

1. Digite:

```text
2
```

2. Informe:

```text
12345678911
```

3. Escolha:

```text
1
```

4. O sistema apresentará as três parcelas.
5. Escolha WhatsApp ou E-mail.
6. Informe um contato válido.

---

### Teste 5 - Segunda via de uma parcela específica

1. Digite `2`.
2. Informe:

```text
12345678911
```

3. Escolha:

```text
2
```

4. Informe uma das datas cadastradas:

```text
10/10/2026
```

ou:

```text
10/11/2026
```

ou:

```text
10/12/2026
```

5. O sistema localizará a parcela correspondente.
6. Escolha o canal de envio.
7. Informe o contato.

Também pode ser testado mais de uma data:

```text
10/10/2026, 10/11/2026
```

---

### Teste 6 - Atualização de todos os boletos em atraso

1. Digite:

```text
3
```

2. Informe:

```text
12345678912
```

3. Escolha:

```text
1
```

4. O sistema calculará o valor atualizado de todas as parcelas.
5. Digite `1` para confirmar a atualização.
6. O sistema apresentará o novo vencimento.
7. Confirme a continuidade.
8. Escolha o canal de contato.
9. Informe o telefone ou e-mail.

---

### Teste 7 - Atualização de uma parcela específica

1. Digite `3`.
2. Informe:

```text
12345678912
```

3. Escolha:

```text
2
```

4. Informe uma das datas:

```text
10/01/2026
10/02/2026
10/03/2026
10/04/2026
10/05/2026
```

5. Confira o valor original e o valor atualizado.
6. Confirme a atualização.
7. Confirme a continuidade.
8. Escolha o canal de contato.

Também pode ser testado mais de um vencimento:

```text
10/01/2026, 10/02/2026
```

---

### Teste 8 - Falar com atendente

1. Digite:

```text
4
```

2. Informe um nome, por exemplo:

```text
João da Silva
```

3. Informe um CPF com 11 números.
4. Escolha:

   * `1` para WhatsApp; ou
   * `2` para E-mail.
5. Informe o contato.
6. Informe o motivo do atendimento.

Exemplo:

```text
Preciso de ajuda com minha negociação.
```

7. O sistema apresentará a solicitação e informará o encaminhamento para o atendente.

---

### Teste 9 - Palavras-chave

Também é possível testar o reconhecimento sem utilizar os números do menu.

Exemplos:

```text
negociar
dívida
acordo
segunda via
parcela
boleto atrasado
boleto vencido
atendente
```

Também podem ser testadas palavras com letras maiúsculas, minúsculas e acentuação.

---

### Teste 10 - Encerramento

Para encerrar o atendimento, utilize:

```text
5
```

ou:

```text
sair
```

Também podem ser utilizados:

```text
tchau
encerrar
```

---

## 12. Estrutura do projeto

```text
MaviBot
│
├── MaviBot.java
├── README.md
└── testes.pdf
```

* **MaviBot.java:** contém as regras, funções, validações, cálculos e fluxos do chatbot.
* **README.md:** apresenta a descrição, execução, funcionalidades e instruções para testes.
* **testes.pdf:** reúne os registros dos testes realizados durante o desenvolvimento.

---

## 13. Limitações

Por ser um projeto acadêmico baseado em regras, o MaviBot possui algumas limitações.

Os dados são fictícios e ficam diretamente no código. O sistema não possui integração real com banco de dados, sistemas de cobrança, emissão de boletos, WhatsApp ou e-mail.

O reconhecimento das mensagens depende das palavras e condições programadas. Uma frase diferente das regras existentes pode não ser identificada corretamente.

Uma aplicação real também precisaria de recursos mais completos de segurança, autenticação, controle de acesso e proteção dos dados dos clientes.

---

## 14. Comentário pessoal

Escolhi desenvolver o MaviBot na área de cobrança porque é uma área que faz parte da minha rotina de trabalho. Existem situações em que o cliente precisa apenas de uma segunda via, atualização de um boleto, consulta de informações ou orientação sobre uma negociação.

A partir disso, pensei em utilizar o chatbot como uma primeira etapa do atendimento, deixando para o agente os casos que precisam de uma análise mais específica.

Durante o desenvolvimento, percebi que mesmo um chatbot baseado em regras precisa considerar vários caminhos possíveis. Foi necessário pensar nas palavras que poderiam ser utilizadas pelo usuário, nas entradas inválidas, nas tentativas de erro e nas opções de voltar ou encerrar o atendimento.

Os cálculos de boletos atrasados e negociações também exigiram atenção, principalmente por envolverem valores e datas diferentes. Apesar de os dados serem simulados, procurei deixar os fluxos próximos de situações que podem acontecer em um atendimento real.

O projeto ajudou a entender melhor como regras e fluxos de atendimento podem ser organizados em Java. Também mostrou que, para transformar a ideia em uma aplicação realmente utilizada por uma empresa, seria necessário integrar o chatbot com banco de dados, sistemas de cobrança, emissão de boletos e canais de atendimento.

De forma geral, a MaviBot foi uma forma de relacionar o conteúdo da disciplina com uma situação que conheço na prática, trabalhando com Java, regras de chatbot, validações e automação de atendimento.