import java.util.Scanner;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;   // Calcula diferença entre datas.
import java.time.format.DateTimeFormatter;

public class MaviBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int erros = 0;

        // Exibe a apresentação inicial do chatbot.
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║       MAVI - COBBOT UCDB      ║");
        System.out.println("╚═══════════════════════════════╝");
        System.out.println("MaviBot: Olá! Eu sou a Mavi, sua assistente virtual financeira da Universidade Católica Dom Bosco!");
        System.out.println("Para encerrar o atendimento, é só digitar 'sair'.");

        while (true) {
            System.out.print("\nVocê: ");
            String mensagem = normalizar(scanner.nextLine());

            if (mensagem.equals("sair") || mensagem.equals("tchau") || mensagem.equals("encerrar") || mensagem.equals("5")) {
                System.out.println("MaviBot: Atendimento encerrado. Obrigado pelo contato!");
                break;

            } else if (mensagem.equals("oi") || mensagem.equals("ola") || mensagem.equals("bom dia") || mensagem.equals("boa tarde") || mensagem.equals("boa noite")) {
                System.out.println("MaviBot: Espero que você esteja bem! Como posso te ajudar?");
                mostrarMenuPrincipal();

            } else if (mensagem.equals("1") || mensagem.contains("negociar") || mensagem.contains("divida") || mensagem.contains("acordo") || mensagem.contains("negociacao")) {
                erros = 0;
                if (!negociarDivida(scanner)) {
                    break;
                }

            } else if (mensagem.equals("2") || mensagem.contains("segunda via") || mensagem.contains("segunda") || mensagem.equals("parcela")) {
                erros = 0;
                if (!segundaVia(scanner)) {
                    break;
                }

            } else if (mensagem.equals("3") || (mensagem.contains("boleto") && (mensagem.contains("atrasado") || mensagem.contains("atraso") || mensagem.contains("vencido") || mensagem.equals("boleto atualizado")))) {
                erros = 0;
                if (!boletoAtrasado(scanner)) {
                    break;
                }

            } else if (mensagem.equals("4") || mensagem.contains("atendente") || mensagem.contains("atendimento")) {
                erros = 0;
                if (!falarComAtendente(scanner)) {
                    break;
                }

            } else {

                // Controla as tentativas quando a mensagem não é reconhecida.
                erros++;

                if (erros == 1) {
                    System.out.println("MaviBot: Desculpe, não entendi sua solicitação. Poderia tentar novamente?");

                } else if (erros == 2) {
                    System.out.println("MaviBot: Ainda não consegui identificar sua solicitação.");
                    System.out.println("MaviBot: Tente novamente utilizando uma das opções do menu.");
                    mostrarMenuPrincipal();

                } else {
                    System.out.println("MaviBot: Não consegui entender sua solicitação. O atendimento será encerrado.");
                    System.out.println("MaviBot: Obrigado pelo contato!");
                    break;
                }
            }
        }
        scanner.close();
    }

    // Consulta a dívida pelo CPF e inicia o processo de negociação.
    public static boolean negociarDivida(Scanner scanner) {
        String cpf = solicitarCPF(scanner);

        if (cpf.equals("")) {
            return false;
        }
        ;

        if (cpf.equals("12345678910")) {

            String nome = "Analice Marques";
            String[] datas = { "20/07/2020", "20/08/2020", "20/09/2020" };

            System.out.println("\n========== DÍVIDA ==========");
            System.out.println("Cliente: " + nome);
            System.out.println("CPF: " + cpf);
            System.out.println("Credora: UCDB - Universidade Católica Dom Bosco");
            System.out.println("Quantidade de parcelas: 3");

            double totalOriginal = mostrarTotal(3, 500.00, datas);

            int erros = 0;

            while (true) {
                System.out.println("\nMaviBot: Você reconhece essa dívida?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
                System.out.print("Você: ");

                String respostaDivida = normalizar(scanner.nextLine());

                if (respostaDivida.equals("2") || respostaDivida.equals("nao") || respostaDivida.contains("nao reconheco")) {
                    System.out.println("\nMaviBot: Entendi.");
                    System.out.println("MaviBot: Para contestar a dívida, entre em contato com a Universidade Católica Dom Bosco.");
                    mostrarMenuPrincipal();
                    return true;

                } else if (respostaDivida.equals("1") || respostaDivida.equals("sim")) {
                    return negociarDivida(scanner, cpf, nome, totalOriginal);

                } else {
                    erros++;

                    System.out.println("\nMaviBot: Opção inválida!");
                    System.out.println("MaviBot: Escolha uma das opções:");
                    System.out.println("1 - Sim");
                    System.out.println("2 - Não");

                    if (erros >= 3) {
                        System.out.println("MaviBot: Opção inválida.");
                        System.out.println("MaviBot: O atendimento será encerrado.");
                        return false;
                    }
                }
            }

        } else {
            System.out.println("\nMaviBot: Não encontrei uma dívida para esse CPF.");
            System.out.println("MaviBot: Para verificar seu cadastro, entre em contato pelo telefone 0800 878 2734.");
            mostrarMenuPrincipal();
            return true;
        }
    }

    // Apresenta as opções de negociação e calcula os valores de cada modalidade.
    public static boolean negociarDivida(Scanner scanner, String cpf, String nome, double valorDivida) {
        int erros = 0;

        while (true) {
            System.out.println("\nMaviBot: Como você deseja negociar?");
            System.out.println("1 - À vista");
            System.out.println("2 - Parcelado");
            System.out.println("3 - Parcelamento no cartão de crédito");
            System.out.println("4 - Nenhuma dessas opções");
            System.out.println("5 - Voltar ao menu principal");
            System.out.print("Você: ");

            String opcao = normalizar(scanner.nextLine());

            if (opcao.equals("1") || opcao.contains("vista")) {

                double valorVista = valorDivida * 0.20;

                System.out.printf("%nMaviBot: À vista, o valor fica em %s.%n", formatarMoeda(valorVista));

                int confirmacao = confirmarNegociacao(scanner);

                if (confirmacao == -1) {
                    return false;
                }

                if (confirmacao == 1) {
                    String[] contato = solicitarCanal(scanner);

                    if (contato == null) {
                        return false;
                    }

                    imprimirAcordo(nome, cpf, "Negociação à vista", formatarMoeda(valorVista), contato, 1);
                    return perguntarSePrecisaMais(scanner);
                }

            } else if (opcao.equals("2") || opcao.contains("parcelado")) {
                double valorEntrada = valorDivida * 0.25;
                double valorRestante = valorDivida - valorEntrada;

                System.out.printf("%nMaviBot: O valor da valorEntrada será de %s.%n", formatarMoeda(valorEntrada));

                int quantidadeParcelas = pedirQuantidadeParcelas(scanner, 1, 7);

                if (quantidadeParcelas == -2) {
                    return false;
                }

                double valorParcela = valorRestante / quantidadeParcelas;

                System.out.printf("MaviBot: valorEntrada: %s%n", formatarMoeda(valorEntrada));
                System.out.printf("MaviBot: %d parcela(s) de %s.%n", quantidadeParcelas, formatarMoeda(valorParcela));

                int confirmacao = confirmarNegociacao(scanner);

                if (confirmacao == -1) {
                    return false;
                }

                if (confirmacao == 1) {
                    String[] contato = solicitarCanal(scanner);

                    if (contato == null) {
                        return false;
                    }

                    imprimirAcordo(nome, cpf, "Negociação parcelada",
                            "valorEntrada " + formatarMoeda(valorEntrada) + " + "
                                    + quantidadeParcelas + "x de "
                                    + formatarMoeda(valorParcela),
                            contato, quantidadeParcelas);
                    return perguntarSePrecisaMais(scanner);
                }

            } else if (opcao.equals("3") || opcao.contains("cartao") || opcao.contains("credito")) {
                double valorCartao = valorDivida * 0.20 * 1.12;

                System.out.printf("%nMaviBot: No cartão, o valor fica em %s.%n",
                        formatarMoeda(valorCartao));

                int quantidadeParcelas = pedirQuantidadeParcelas(scanner, 1, 6);

                if (quantidadeParcelas == -2) {
                    return false;
                }

                double valorParcela = valorCartao / quantidadeParcelas;

                System.out.printf("MaviBot: %d parcela(s) de %s.%n", quantidadeParcelas,
                        formatarMoeda(valorParcela));

                int confirmacao = confirmarNegociacao(scanner);

                if (confirmacao == -1) {
                    return false;
                }

                if (confirmacao == 1) {
                    String[] contato = solicitarCanal(scanner);

                    if (contato == null) {
                        return false;
                    }

                    imprimirAcordo(nome, cpf, "Parcelamento no cartão de crédito",
                            quantidadeParcelas + "x de " + formatarMoeda(valorParcela),
                            contato,
                            quantidadeParcelas);
                    return perguntarSePrecisaMais(scanner);
                }

            } else if (opcao.equals("4") || opcao.contains("nenhuma")) {
                System.out.println("MaviBot: Tudo bem. Nenhuma negociação foi realizada.");
                return perguntarSePrecisaMais(scanner);

            } else if (opcao.equals("5") || opcao.equals("voltar")) {
                mostrarMenuPrincipal();
                return true;

            } else {

                erros++;
                System.out.println("MaviBot: Não consegui entender sua escolha.");

                if (erros >= 3) {
                    System.out.println("MaviBot: O atendimento será encerrado.");
                    return false;
                }
            }
        }
    }

    public static int pedirQuantidadeParcelas(Scanner scanner, int min, int max) {
        int tentativas = 0;

        while (tentativas < 3) {
            System.out.println("\nMaviBot: Em quantas parcelas você deseja dividir? (" + min + " a " + max
                    + ")");
            System.out.print("Você: ");

            String texto = normalizar(scanner.nextLine());

            try {
                int quantidade = Integer.parseInt(texto);

                if (quantidade >= min && quantidade <= max) {
                    return quantidade;
                }

            } catch (NumberFormatException e) {

            }

            tentativas++;

            System.out.println("MaviBot: Quantidade de parcelas inválida.");
        }

        System.out.println("MaviBot: Não consegui entender a quantidade de parcelas.");
        System.out.println("MaviBot: O atendimento será encerrado.");

        return -2;
    }

    public static int confirmarNegociacao(Scanner scanner) {

        int tentativas = 0;

        while (tentativas < 3) {

            System.out.println();
            System.out.println("MaviBot: Deseja prosseguir com essa negociação?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            System.out.print("Você: ");

            String resposta = normalizar(scanner.nextLine());

            if (resposta.equals("1") || resposta.equals("sim")) {
                return 1;
            }

            if (resposta.equals("2") || resposta.equals("nao")) {
                return 0;
            }

            tentativas++;

            System.out.println();
            System.out.println("\nMaviBot: Opção não encontrado. Digite uma das opções:");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
        }

        System.out.println("MaviBot: Não consegui entender sua resposta.");
        System.out.println("MaviBot: O atendimento será encerrado.");

        return -1;
    }

     // Exibe os dados do acordo e calcula as datas de vencimento das parcelas.
    public static void imprimirAcordo(String nome, String cpf, String operacao, String detalhe, String[] contato,
            int quantidadeParcelas) {

        System.out.println("\n========== ACORDO REALIZADO ==========");
        System.out.println("Cliente: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Credora: UCDB - Universidade Católica Dom Bosco");
        System.out.println("Operação: " + operacao);
        System.out.println("Valor/Parcelas: " + detalhe);
        System.out.println("Canal de contato: " + contato[0]);
        System.out.println("Contato: " + contato[1]);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Vencimentos:");

        if (operacao.equals("Negociação parcelada")) {
            LocalDate vencimentoEntrada = calcularPrimeiroVencimento();

            System.out.println("Vencimento da Entrada: " + vencimentoEntrada.format(formato));

            for (int i = 1; i <= quantidadeParcelas; i++) {
                LocalDate vencimento = calcularVencimentoParcela(i + 1);

                System.out.println(
                        "Parcela " + i + ": " + vencimento.format(formato));
            }
        } else {
            for (int i = 1; i <= quantidadeParcelas; i++) {
                LocalDate vencimento = calcularVencimentoParcela(i);

                System.out.println(
                        "Parcela " + i + ": " + vencimento.format(formato));
            }
        }
        System.out.println("=======================================");
        System.out.println(
                "MaviBot: O(s) boleto(s) será(ão) enviado(s) em até 5 minutos pelo canal solicitado.");
    }

    // Consulta o acordo do cliente e permite solicitar todas ou uma parcela específica.
    public static boolean segundaVia(Scanner scanner) {

        String cpf = solicitarCPF(scanner);

        if (cpf.equals("")) {
            return false;
        }

        String nome = "";
        String[] parcelas = new String[3];

        if (cpf.equals("12345678911")) {

            nome = "Samara Culere";

            parcelas[0] = "10/10/2026 - R$ 350,00";
            parcelas[1] = "10/11/2026 - R$ 350,00";
            parcelas[2] = "10/12/2026 - R$ 350,00";

            System.out.println("\n========== ACORDO ==========");
            System.out.println("Cliente: Samara Culere");
            System.out.println("CPF: 12345678911");
            System.out.println("Credora: UCDB - Universidade Católica Dom Bosco");
            System.out.println("Parcela 1: 10/10/2026 - R$ 350,00");
            System.out.println("Parcela 2: 10/11/2026 - R$ 350,00");
            System.out.println("Parcela 3: 10/12/2026 - R$ 350,00");

        } else {

            System.out.println("\nMaviBot: Não encontrei um acordo ativo para esse CPF.");
            System.out.println("MaviBot: Entre em contato pelo telefone 0800 878 2734.");
            mostrarMenuPrincipal();

            return true;
        }

        int erros = 0;
        String parcelasSolicitadas;

        while (true) {

            System.out.println("\nMaviBot: Como deseja receber as parcelas?");
            System.out.println("1 - Todas as parcelas");
            System.out.println("2 - Informar uma data específica");
            System.out.println("3 - Voltar ao menu principal");
            System.out.print("Você: ");

            String valorEntrada = scanner.nextLine().trim();
            String opcao = normalizar(valorEntrada);

            if (opcao.equals("1") || opcao.contains("todas")) {

                parcelasSolicitadas = "Todas as parcelas";

                System.out.println("\nMaviBot: Parcelas disponíveis:");

                for (String parcela : parcelas) {

                    if (parcela != null) {
                        System.out.println("- " + parcela);
                    }
                }

                break;
            }

            else if (opcao.equals("2") || opcao.contains("data") || opcao.contains("especifica")
                    || valorEntrada.matches(
                            "\\d{2}/\\d{2}/\\d{4}(\\s*,\\s*\\d{2}/\\d{2}/\\d{4})*")) {

                int tentativasData = 0;
                String dataInformada = "";
                StringBuilder parcelasEncontradas = new StringBuilder();

                while (tentativasData < 3) {

                    if (tentativasData == 0) {
                        if (valorEntrada.matches(
                                "\\d{2}/\\d{2}/\\d{4}(\\s*,\\s*\\d{2}/\\d{2}/\\d{4})*")) {
                            dataInformada = valorEntrada;
                        } else {
                            System.out.println(
                                    "\nMaviBot: Informe a data da parcela em atraso.");
                            System.out.println(
                                    "MaviBot: Se quiser mais de uma, separe as datas por vírgula.");
                            System.out.print("Você: ");
                            dataInformada = scanner.nextLine().trim();
                        }
                    } else {
                        System.out.println(
                                "\nMaviBot: Não localizei nenhuma das parcelas informadas.");
                        System.out.println("MaviBot: Digite a data novamente.");
                        System.out.print("Você: ");
                        dataInformada = scanner.nextLine().trim();
                    }

                    parcelasEncontradas = new StringBuilder();

                    if (dataInformada.matches(
                            "\\d{2}/\\d{2}/\\d{4}(\\s*,\\s*\\d{2}/\\d{2}/\\d{4})*")) {

                        String[] datasInformadas = dataInformada.split(",");

                        for (String data : datasInformadas) {
                            data = data.trim();

                            for (String parcela : parcelas) {
                                if (parcela != null && parcela.contains(data)) {

                                    if (parcelasEncontradas
                                            .indexOf(parcela) == -1) {

                                        if (parcelasEncontradas.length() > 0) {
                                            parcelasEncontradas
                                                    .append("\n");
                                        }

                                        parcelasEncontradas.append("- ")
                                                .append(parcela);
                                    }

                                    break;
                                }
                            }
                        }
                    }

                    if (parcelasEncontradas.length() > 0) {
                        break;
                    }

                    tentativasData++;
                }

                if (parcelasEncontradas.length() == 0) {
                    System.out.println("MaviBot: Não consegui localizar a parcela informada.");
                    System.out.println("MaviBot: O atendimento será encerrado.");
                    return false;
                }

                parcelasSolicitadas = parcelasEncontradas.toString();

                System.out.println("\nMaviBot: Parcelas localizadas:\n" + parcelasSolicitadas);
                break;

            } else if (opcao.equals("3") || opcao.equals("voltar")) {
                mostrarMenuPrincipal();

                return true;

            } else {

                erros++;

                System.out.println("MaviBot: Opção inválida.");

                if (erros >= 3) {

                    System.out.println("MaviBot: Não consegui entender sua escolha.");
                    System.out.println("MaviBot: O atendimento será encerrado.");

                    return false;
                }
            }
        }

        String[] contato = solicitarCanal(scanner);

        if (contato == null) {
            return false;
        }

        System.out.println("\n===== SEGUNDA VIA DO BOLETO SOLICITADA =====");
        System.out.println("Cliente: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Credora: UCDB - Universidade Católica Dom Bosco");
        System.out.println("Parcela(s):");

        if (parcelasSolicitadas.equals("Todas as parcelas")) {

            for (String parcela : parcelas) {

                if (parcela != null) {
                    System.out.println("- " + parcela);
                }
            }

        } else {

            System.out.println(parcelasSolicitadas);
        }

        System.out.println("Canal de envio: " + contato[0]);
        System.out.println("Contato: " + contato[1]);
        System.out.println("============================================");
        System.out.println(
                "MaviBot: O(s) boleto(s) será(ão) enviado(s) em até 5 minutos pelo canal solicitado.");

        return perguntarSePrecisaMais(scanner);
    }

    // Consulta as parcelas vencidas e permite calcular seus valores atualizados.
    public static boolean boletoAtrasado(Scanner scanner) {

        String cpf = solicitarCPF(scanner);

        if (cpf.equals("")) {
            return false;
        }

        String nome = "";
        String parcela1 = "";
        String parcela2 = "";
        String parcela3 = "";
        String parcela4 = "";
        String parcela5 = "";

        if (cpf.equals("12345678912")) {

            nome = "Gabriel Lizarb";

            parcela1 = "10/01/2026 - R$ 500,00";
            parcela2 = "10/02/2026 - R$ 209,00";
            parcela3 = "10/03/2026 - R$ 209,00";
            parcela4 = "10/04/2026 - R$ 209,00";
            parcela5 = "10/05/2026 - R$ 209,00";

            System.out.println("\n========== PARCELAS EM ATRASO ==========");
            System.out.println("Cliente: Gabriel Lizarb");
            System.out.println("CPF: 12345678912");
            System.out.println("Credora: UCDB - Universidade Católica Dom Bosco");
            System.out.println("1 - " + parcela1);
            System.out.println("2 - " + parcela2);
            System.out.println("3 - " + parcela3);
            System.out.println("4 - " + parcela4);
            System.out.println("5 - " + parcela5);

        } else {

            System.out.println("\nMaviBot: Não encontrei parcelas em atraso para esse CPF.");
            System.out.println(
                    "MaviBot: Para verificar seu cadastro, entre em contato pelo telefone 0800 878 2734.");
            mostrarMenuPrincipal();

            return true;
        }

        int erros = 0;
        String parcelasSolicitadas;

        String[] parcelas = { parcela1, parcela2, parcela3, parcela4, parcela5 };

        while (true) {

            System.out.println("\nMaviBot: Quais parcelas deseja atualizar?");
            System.out.println("1 - Todas as parcelas");
            System.out.println("2 - Informar uma data específica");
            System.out.println("3 - Voltar ao menu principal");
            System.out.print("Você: ");

            String valorEntrada = scanner.nextLine().trim();
            String opcao = normalizar(valorEntrada);

            if (opcao.equals("1") || opcao.contains("todas")) {
                parcelasSolicitadas = "Todas as parcelas";

                System.out.println("\nMaviBot: Parcelas em atraso:");

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                for (String parcela : parcelas) {
                    if (parcela != null) {

                        String[] dadosParcela = parcela.split(" - ");

                        LocalDate vencimento = LocalDate.parse(dadosParcela[0], formato);

                        double valorOriginal = Double.parseDouble(
                                dadosParcela[1]
                                        .replace("R$ ", "")
                                        .replace(",", "."));

                        double valorAtualizado = calcularValorAtualizado(
                                valorOriginal, vencimento);

                        System.out.println(
                                "- Vencimento: " + dadosParcela[0]
                                        + " | Valor original: "
                                        + formatarMoeda(valorOriginal)
                                        + " | Valor atualizado: "
                                        + formatarMoeda(valorAtualizado));
                    }
                }

                break;
            }

            else if (opcao.equals("2") || opcao.contains("data") || opcao.contains("especifica")
                    || valorEntrada.matches(
                            "\\d{2}/\\d{2}/\\d{4}(\\s*,\\s*\\d{2}/\\d{2}/\\d{4})*")) {

                int tentativasData = 0;
                String dataInformada = "";
                StringBuilder parcelasEncontradas = new StringBuilder();

                while (tentativasData < 3) {

                    if (tentativasData == 0) {
                        if (valorEntrada.matches(
                                "\\d{2}/\\d{2}/\\d{4}(\\s*,\\s*\\d{2}/\\d{2}/\\d{4})*")) {
                            dataInformada = valorEntrada;
                        } else {
                            System.out.println(
                                    "\nMaviBot: Informe a data da parcela em atraso.");
                            System.out.println(
                                    "MaviBot: Se quiser mais de uma, separe as datas por vírgula.");
                            System.out.print("Você: ");
                            dataInformada = scanner.nextLine().trim();
                        }
                    } else {
                        System.out.println(
                                "\nMaviBot: Não localizei nenhuma das parcelas informadas.");
                        System.out.println("MaviBot: Digite a data novamente.");
                        System.out.print("Você: ");
                        dataInformada = scanner.nextLine().trim();
                    }

                    parcelasEncontradas = new StringBuilder();

                    if (dataInformada.matches(
                            "\\d{2}/\\d{2}/\\d{4}(\\s*,\\s*\\d{2}/\\d{2}/\\d{4})*")) {

                        String[] datasInformadas = dataInformada.split(",");

                        for (String data : datasInformadas) {
                            data = data.trim();

                            for (String parcela : parcelas) {
                                if (parcela != null && parcela.contains(data)) {

                                    if (parcelasEncontradas
                                            .indexOf(parcela) == -1) {

                                        if (parcelasEncontradas.length() > 0) {
                                            parcelasEncontradas
                                                    .append("\n");
                                        }

                                        parcelasEncontradas.append("- ")
                                                .append(parcela);
                                    }

                                    break;
                                }
                            }
                        }
                    }

                    if (parcelasEncontradas.length() > 0) {
                        break;
                    }

                    tentativasData++;
                }

                if (parcelasEncontradas.length() == 0) {
                    System.out.println("MaviBot: Não consegui localizar a parcela informada.");
                    System.out.println("MaviBot: O atendimento será encerrado.");
                    return false;
                }

                parcelasSolicitadas = parcelasEncontradas.toString();

                System.out.println("\nMaviBot: Parcelas localizadas:");

                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                String[] linhas = parcelasSolicitadas.split("\n");

                for (String linha : linhas) {

                    linha = linha.substring(2);

                    String[] dadosParcela = linha.split(" - ");

                    LocalDate vencimento = LocalDate.parse(dadosParcela[0], formato);

                    double valorOriginal = Double.parseDouble(
                            dadosParcela[1]
                                    .replace("R$ ", "")
                                    .replace(",", "."));

                    double valorAtualizado = calcularValorAtualizado(valorOriginal, vencimento);

                    System.out.println("- " + dadosParcela[0]
                            + " - Valor original: " + formatarMoeda(valorOriginal)
                            + " | Valor atualizado: " + formatarMoeda(valorAtualizado));
                }

                break;

            } else if (opcao.equals("3") || opcao.equals("voltar")) {
                mostrarMenuPrincipal();
                return true;

            } else {

                erros++;

                System.out.println("MaviBot: Opção inválida.");

                if (erros >= 3) {

                    System.out.println("MaviBot: Não consegui entender sua escolha.");
                    System.out.println("MaviBot: O atendimento será encerrado.");

                    return false;
                }
            }
        }

        int tentativasAtualizar = 0;
        String respostaAtualizar;

        while (tentativasAtualizar < 3) {

            System.out.println("\nMaviBot: Você deseja atualizar essa(s) parcela(s)?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            System.out.print("Você: ");

            respostaAtualizar = normalizar(scanner.nextLine());

            if (respostaAtualizar.equals("1") || respostaAtualizar.equals("sim")) {
                break;

            } else if (respostaAtualizar.equals("2") || respostaAtualizar.equals("nao")) {
                System.out.println("MaviBot: Tudo bem. O boleto não foi atualizado.");
                return perguntarSePrecisaMais(scanner);

            } else {
                tentativasAtualizar++;

                if (tentativasAtualizar < 3) {
                    System.out.println(
                            "MaviBot: Resposta inválida. Digite 1 para Sim ou 2 para Não.");
                }
            }
        }

        if (tentativasAtualizar >= 3) {
            System.out.println("MaviBot: Não consegui entender sua resposta.");
            System.out.println("MaviBot: O atendimento será encerrado.");
            return false;
        }

        // Define o novo vencimento do boleto para sete dias após a consulta.
        LocalDate novoVencimento = LocalDate.now().plusDays(7);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\nMaviBot: O boleto será atualizado.");

        System.out.println("MaviBot: Novo vencimento: " + novoVencimento.format(formato));

        int tentativasContinuar = 0;
        String respostaContinuar;

        while (tentativasContinuar < 3) {

            System.out.println("MaviBot: Você deseja continuar?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            System.out.print("Você: ");

            respostaContinuar = normalizar(scanner.nextLine());

            if (respostaContinuar.equals("1") || respostaContinuar.equals("sim")) {
                break;

            } else if (respostaContinuar.equals("2") || respostaContinuar.equals("nao")) {
                System.out.println("MaviBot: Tudo bem. A atualização não foi concluída.");
                return perguntarSePrecisaMais(scanner);

            } else {
                tentativasContinuar++;

                if (tentativasContinuar < 3) {
                    System.out.println(
                            "MaviBot: Resposta inválida. Digite 1 para Sim ou 2 para Não.");
                }
            }
        }

        if (tentativasContinuar >= 3) {
            System.out.println("MaviBot: Não consegui entender sua resposta.");
            System.out.println("MaviBot: O atendimento será encerrado.");
            return false;
        }

        String[] contato = solicitarCanal(scanner);

        if (contato == null) {
            return false;
        }

        double totalAtualizado = 0;

        for (String parcela : parcelas) {

            if (parcelasSolicitadas.equals("Todas as parcelas") || parcelasSolicitadas.contains(parcela)) {

                String[] dadosParcela = parcela.split(" - ");

                LocalDate vencimento = LocalDate.parse(dadosParcela[0], formato);

                double valorOriginal = Double.parseDouble(
                        dadosParcela[1]
                                .replace("R$ ", "")
                                .replace(",", "."));

                double valorAtualizado = calcularValorAtualizado(valorOriginal, vencimento);

                totalAtualizado += valorAtualizado;
            }
        }

        System.out.println("\n========== BOLETO ATUALIZADO ==========");
        System.out.println("Cliente: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Credora: UCDB - Universidade Católica Dom Bosco");
        System.out.println("Parcela(s):");

        if (parcelasSolicitadas.equals("Todas as parcelas")) {

            for (String parcela : parcelas) {

                if (parcela != null) {

                    String[] dadosParcela = parcela.split(" - ");

                    LocalDate vencimentoOriginal = LocalDate.parse(dadosParcela[0], formato);

                    double valorOriginal = Double.parseDouble(
                            dadosParcela[1]
                                    .replace("R$ ", "")
                                    .replace(",", "."));

                    double valorAtualizado = calcularValorAtualizado(valorOriginal,
                            vencimentoOriginal);

                    System.out.println("- " + dadosParcela[0] + " - Valor original: "
                            + formatarMoeda(valorOriginal) + " | Valor atualizado: "
                            + formatarMoeda(valorAtualizado));
                }
            }

        } else {

            String[] linhas = parcelasSolicitadas.split("\n");

            for (String linha : linhas) {

                linha = linha.substring(2);

                String[] dadosParcela = linha.split(" - ");

                LocalDate vencimentoOriginal = LocalDate.parse(dadosParcela[0], formato);

                double valorOriginal = Double.parseDouble(
                        dadosParcela[1]
                                .replace("R$ ", "")
                                .replace(",", "."));

                double valorAtualizado = calcularValorAtualizado(valorOriginal, vencimentoOriginal);

                System.out.println("- " + dadosParcela[0] + " - Valor original: "
                        + formatarMoeda(valorOriginal) + " | Valor atualizado: "
                        + formatarMoeda(valorAtualizado));
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("Total atualizado do(s) boleto(s): " + formatarMoeda(totalAtualizado));
        System.out.println("Novo vencimento: " + novoVencimento.format(formato));
        System.out.println("Canal de contato: " + contato[0]);
        System.out.println("Contato: " + contato[1]);
        System.out.println("========================================");
        System.out.println(
                "MaviBot: Seu boleto será enviado em até 5 minutos pelo canal solicitado. O pagamento poderá ser realizado até a nova data de vencimento.");

        return perguntarSePrecisaMais(scanner);
    }

    // Coleta os dados necessários para encaminhar o cliente ao atendente.
    public static boolean falarComAtendente(Scanner scanner) {

        System.out.println("\nMaviBot: Vou encaminhar você para um atendente.");
        System.out.println("MaviBot: Preciso de alguns dados para realizar esta solicitação.");

        String nome = solicitarNome(scanner);

        if (nome.equals("")) {
            return false;
        }

        String cpf = solicitarCPF(scanner);

        if (cpf.equals("")) {
            return false;
        }

        String[] contato = solicitarCanal(scanner);

        if (contato == null) {
            return false;
        }
        
        System.out.println("\nMaviBot: Qual o motivo da solicitação de atendimento?");
        System.out.print("Você: ");
        String motivo = scanner.nextLine().trim();

        System.out.println("\n====== SOLICITAÇÃO DE ATENDIMENTO ======");
        System.out.println("Cliente: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Canal de contato: " + contato[0]);
        System.out.println("Contato: " + contato[1]);
        System.out.println("Motivo: " + motivo);
        System.out.println("=========================================");
        System.out.println("MaviBot: Um atendente entrará em contato em até 15 minutos.");

        return perguntarSePrecisaMais(scanner);
    }
  // Exibe as opções disponíveis no menu principal.
    public static void mostrarMenuPrincipal() {

        System.out.println("\nMaviBot: Escolha uma opção:");
        System.out.println("╔═══════════════════════════════╗");
        System.out.println("║       MENU DE ATENDIMENTO     ║");
        System.out.println("╠═══════════════════════════════╣");
        System.out.println("║ 1 - Negociar dívida           ║");
        System.out.println("║ 2 - Segunda via de boleto     ║");
        System.out.println("║ 3 - Boleto em atraso          ║");
        System.out.println("║ 4 - Falar com atendente       ║");
        System.out.println("║ 5 - Sair                      ║");
        System.out.println("╚═══════════════════════════════╝");
    }

    // Normaliza o texto para facilitar o reconhecimento das mensagens.
    public static String normalizar(String texto) {

        return Normalizer
                .normalize(texto, Normalizer.Form.NFD)  // Separa as letras dos acentos
                .replaceAll("\\p{M}", "")  // Remove os acentos
                .toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")  // Remove símbolos e caracteres especiais
                .trim();
    }

    // Solicita e valida o CPF, permitindo até três tentativas.
    public static String solicitarCPF(Scanner scanner) {

        int tentativas = 0;
        System.out.println(
                "\nMaviBot: Conforme a LGPD, seu CPF será utilizado apenas para identificação e atendimento desta solicitação.");
        System.out.println("MaviBot: Informe seu CPF:");

        while (tentativas < 3) {

            if (tentativas > 0) {
                System.out.println("MaviBot: Poderia tentar novamente?");
            }

            System.out.print("Você: ");

            String cpf = normalizar(scanner.nextLine());

            if (cpf.length() == 11 && cpf.matches("\\d{11}")) {

                return cpf;
            }

            tentativas++;

            System.out.println("MaviBot: CPF inválido. O CPF deve ter 11 números.");
        }

        System.out.println("\nMaviBot: Não consegui validar o CPF informado.");

        return "";
    }

    // Solicita e valida o nome do cliente.
    public static String solicitarNome(Scanner scanner) {

        int tentativas = 0;

        while (tentativas < 3) {

            System.out.println("\nMaviBot: Informe seu nome:");
            System.out.print("Você: ");

            String nome = scanner.nextLine().trim();

            if (!nome.equals("") && nome.length() >= 2) {

                return nome;
            }

            tentativas++;

            System.out.println("MaviBot: Nome inválido. Tente novamente.");
        }

        System.out.println("MaviBot: Não consegui validar o nome informado.");

        return "";
    }

    // Define o canal de contato e solicita o respectivo dado.
    public static String[] solicitarCanal(Scanner scanner) {

        int tentativas = 0;

        while (tentativas < 3) {

            System.out.println("\nMaviBot: Como deseja receber o contato?");
            System.out.println("1 - WhatsApp");
            System.out.println("2 - E-mail");
            System.out.print("Você: ");

            String canal = normalizar(scanner.nextLine());

            if (canal.equals("1") || canal.contains("whatsapp")) {

                String telefone = solicitarTelefone(scanner);

                if (telefone.equals("")) {
                    return null;
                }

                return new String[] { "WhatsApp", telefone };

            } else if (canal.equals("2") || canal.contains("email")) {

                String email = solicitarEmail(scanner);

                if (email.equals("")) {
                    return null;
                }

                return new String[] { "E-mail", email };

            } else {

                tentativas++;

                System.out.println("MaviBot: Não identifiquei o canal informado.");
            }
        }

        System.out.println("MaviBot: Não consegui identificar o canal de contato.");

        return null;
    }

    // Solicita e valida o telefone informado pelo cliente.
    public static String solicitarTelefone(Scanner scanner) {

        int tentativas = 0;

        while (tentativas < 3) {

            System.out.println("\nMaviBot: Informe seu telefone com DDD:");
            System.out.print("Você: ");

            String telefone = scanner.nextLine().trim();

            if (validarTelefone(telefone)) {
                return telefone;
            }

            tentativas++;

            System.out.println(
                    "MaviBot: Telefone inválido. Informe somente números, com 10 ou 11 dígitos.");
        }

        System.out.println("MaviBot: Não consegui validar o telefone informado.");

        return "";
    }

    public static boolean validarTelefone(String telefone) {

        return telefone.matches("\\d{10,11}");
    }

    // Solicita e valida o e-mail informado pelo cliente.
    public static String solicitarEmail(Scanner scanner) {

        int tentativas = 0;

        while (tentativas < 3) {

            System.out.println("\nMaviBot: Informe seu e-mail:");
            System.out.print("Você: ");

            String email = scanner.nextLine().trim();

            if (validarEmail(email)) {
                return email;
            }

            tentativas++;

            System.out.println("MaviBot: E-mail inválido. Use o formato exemplo@email.com.");
        }

        System.out.println("MaviBot: Não consegui validar o e-mail informado.");

        return "";
    }

    public static boolean validarEmail(String email) {

        return email.matches(
                "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    }

    // Pergunta se o cliente deseja continuar o atendimento.
    public static boolean perguntarSePrecisaMais(Scanner scanner) {

        int erros = 0;

        while (true) {

            System.out.println("\nMaviBot: Você precisa de mais alguma coisa?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");
            System.out.print("Você: ");

            String resposta = normalizar(scanner.nextLine());

            if (resposta.equals("1") || resposta.equals("sim")) {

                mostrarMenuPrincipal();

                return true;

            } else if (resposta.equals("2") || resposta.equals("nao")) {

                System.out.println("MaviBot: Tudo bem! Obrigado pelo contato!");

                return false;

            } else {
                erros++;

                System.out.println("MaviBot: Opção inválida!");
                System.out.println("MaviBot: Escolha uma das opções:");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");

                if (erros >= 3) {
                    System.out.println("MaviBot: Opção não reconhecida.");
                    System.out.println("MaviBot: O atendimento será encerrado.");
                    return false;
                }

            }
        }
    }
    // Calcula o valor atualizado de uma parcela vencida com multa e juros.
    public static double calcularValorAtualizado(double valorOriginal, LocalDate vencimento) {

        LocalDate hoje = LocalDate.now();

        long dias = ChronoUnit.DAYS.between(vencimento, hoje);

        if (dias <= 0) {
            return valorOriginal;
        }

        double multa = valorOriginal * 0.10;
        double juros = valorOriginal * 0.01 * dias / 30;
        return valorOriginal + multa + juros;
    }

    // Define o primeiro vencimento como sete dias após a data atual.
    public static LocalDate calcularPrimeiroVencimento() {
        return LocalDate.now().plusDays(7);
    }

     // Calcula o vencimento de cada parcela acrescentando os meses necessários.
    public static LocalDate calcularVencimentoParcela(int numeroParcela) {
        LocalDate primeiroVencimento = calcularPrimeiroVencimento();

        return primeiroVencimento.plusMonths(numeroParcela - 1);
    }

    public static double mostrarTotal(int quantidade, double valorParcela, String[] datas) {

        double totalOriginal = quantidade * valorParcela;

        LocalDate hoje = LocalDate.now();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < quantidade; i++) {

            System.out.println("Parcela " + (i + 1) + " - Vencimento: " + datas[i] + " - Valor original: "
                    + formatarMoeda(valorParcela));
        }

        LocalDate vencimentoMaisAntigo = LocalDate.parse(datas[0], formato);
        long dias = ChronoUnit.DAYS.between(vencimentoMaisAntigo, hoje);

        if (dias < 0) {
            dias = 0;
        }

        double multa = totalOriginal * 0.10;
        double juros = totalOriginal * 0.01 * dias / 30;

        double totalAtualizado = totalOriginal + multa + juros;

        System.out.println("----------------------------");
        System.out.println("Total original: " + formatarMoeda(totalOriginal));
        System.out.println("Total atualizado: " + formatarMoeda(totalAtualizado));
        System.out.println("============================");

        return totalOriginal;
    }

    // Formata valores numéricos para o padrão de moeda utilizado no atendimento.
    public static String formatarMoeda(double valor) {

        return String
                .format("R$ %.2f", valor)
                .replace(".", ",");
    }
}