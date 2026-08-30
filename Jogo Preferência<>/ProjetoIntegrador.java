package projeto;

import java.util.*;

class Perguntas {

    String pergunta1;
    String pergunta2;
    String resposta;
    String resultado1;
    String resultado2;

    Perguntas(String p1, String p2, String r1, String r2, String re) {
        pergunta1 = p1;
        pergunta2 = p2;
        resultado1 = r1;
        resultado2 = r2;
        resposta = re;
    }
}

public class ProjetoIntegrador {
    
    static Scanner leia = new Scanner(System.in);

    public static void main(String[] args) {

        // TITULO
        System.out.println(
                ConsoleColors.RED_BOLD + " _______                    ___                                        " + ConsoleColors.CYAN + "          \n"
                + ConsoleColors.RED_BOLD + "|_   __ \\                 .' ..]                                      " + ConsoleColors.CYAN + "(_)         \n"
                + ConsoleColors.RED_BOLD + "  | |__) |_ .--.  .---.  _| |_  .---.  " + ConsoleColors.CYAN + "_ .--.  .---.  _ .--.   .---.  __   ,--.   \n"
                + ConsoleColors.RED_BOLD + "  |  ___/[ `/'`\\]/ /__\\\\'-| |-'/ /__\\\\" + ConsoleColors.CYAN + "[ `/'`\\]/ /__\\\\[ `.-. | / /'`\\][  | `'_\\ :  \n"
                + ConsoleColors.RED_BOLD + " _| |_    | |    | \\__.,  | |  | \\__., " + ConsoleColors.CYAN + "| |    | \\__., | | | | | \\__.  | | // | |, \n"
                + ConsoleColors.RED_BOLD + "|_____|  [___]    '.__.' [___]  '.__.'" + ConsoleColors.CYAN + "[___]    '.__.'[___||__]'.___.'[___]\\'-;__/ \n"
                + "                                                                                      \n"
                + ConsoleColors.RESET + "_".repeat(85) + "\n");

        int opcao = 0;

        // DECISAO - MENU
        while (opcao != 3) {

            opcao = menu();

            switch (opcao) {

                case 1:
                    jogo();
                    break;

                case 2:
                    regras();
                    break;

                case 3:
                    encerrando();
                    break;

                default:
                    System.out.println(ConsoleColors.RED + "[ERRO] Valor Invalido");
            }
        }

        leia.close();
    }

    // RODAR O JOGO
    public static void jogo() {

        boolean jogarNovamente = true;

        while (jogarNovamente) {

            int qntd = dificuldade();

            if (qntd >= 10 && qntd <= 50) {

                ArrayList<Perguntas> lista = perguntas();

                Collections.shuffle(lista);

                jogarNovamente = jogarPartida(lista, qntd);

            } else {
                System.out.println(ConsoleColors.RED + "[ERRO] Valor Invalido");
            }
        }
    }

    // RODAR PARTIDA
    public static boolean jogarPartida(ArrayList<Perguntas> lista, int qntd) {

        int sucesso = 0;

        for (int i = 0; i < qntd && i < lista.size(); i++) {

            Perguntas atual = lista.get(i);

            mostrarPergunta(atual, sucesso);

            String resp = lerResposta(sucesso);

            System.out.println(ConsoleColors.RESET + "_".repeat(106));

            if (resp.equals(atual.resposta)) {
                acertou(atual);
                sucesso++;
            } else {
                errou(atual);
                int escolha = menuGameOver(sucesso);

                if (escolha == 1 || escolha == 2) {
                    if (escolha == 1) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    do {
                        System.out.println(ConsoleColors.RED + "[ERRO] Valor Invalido");
                        System.out.println(ConsoleColors.RESET);
                        escolha = menuGameOver(sucesso);
                    } while (escolha != 1 && escolha != 2);

                }
            }
        }
        vencedor(sucesso);
        return false;
    }

    // CARREGAR PERGUNTA
    public static void mostrarPergunta(Perguntas atual, int sucesso) {

        int largura = 50;
        int interna = largura - 2;

        System.out.println("_".repeat((largura * 2) + 6));

        // Borda Superior
        System.out.println(
                ConsoleColors.CYAN + "+" + "-".repeat(interna) + "+   " + ConsoleColors.RED_BOLD + "   +" + "-".repeat(interna) + "+"
        );

        // Conteudo
        System.out.printf(
                ConsoleColors.CYAN + "|%s|   " + ConsoleColors.RED_BOLD + "   |%s|\n",
                centralizar(atual.pergunta1, interna),
                centralizar(atual.pergunta2, interna)
        );
        System.out.printf(
                ConsoleColors.CYAN + "|%s|  V" + ConsoleColors.RED_BOLD + "   |%s|\n",
                centralizar("", interna),
                centralizar("", interna)
        );
        System.out.printf(
                ConsoleColors.CYAN + "|%s|   " + ConsoleColors.RED_BOLD + "S  |%s|\n",
                centralizar("ESCOLHA 1", interna),
                centralizar("ESCOLHA 2", interna)
        );
        System.out.printf(
                ConsoleColors.CYAN + "|%s|   " + ConsoleColors.RED_BOLD + "   |%s|\n",
                centralizar("", interna),
                centralizar("", interna)
        );

        // Borda Inferior
        System.out.println(
                ConsoleColors.CYAN + "+" + "-".repeat(interna) + "+   " + ConsoleColors.RED_BOLD + "   +" + "-".repeat(interna) + "+"
        );

        System.out.println(
                ConsoleColors.RESET + "_".repeat((largura * 2) + 6)
        );

        System.out.println();
    }

    // LER RESPOSTA
    public static String lerResposta(int acertos) {

        System.out.printf("Score: %d\n", acertos);
        System.out.print("Qual e mais popular?: ");

        int resposta = scanner(leia, 1, 2);

        return String.valueOf(resposta);
    }

    // CARREGAR ACERTO
    public static void acertou(Perguntas atual) {

        System.out.println();
        System.out.println(ConsoleColors.GREEN + "Acertou!\n");

        System.out.println(ConsoleColors.CYAN + gerarBarra(atual.resultado1));
        System.out.println(ConsoleColors.RED_BOLD + gerarBarra(atual.resultado2));

        System.out.println(ConsoleColors.RESET);
    }

    // CARREGAR ERRO
    public static void errou(Perguntas atual) {

        System.out.println();
        System.out.println(ConsoleColors.RED + "Errou!\n");

        System.out.println(ConsoleColors.CYAN + gerarBarra(atual.resultado1));
        System.out.println(ConsoleColors.RED_BOLD + gerarBarra(atual.resultado2));

        System.out.println(ConsoleColors.RESET);
    }

    // CARREGAR MENU
    public static int menu() {

        int largura = 85;
        int interna = largura - 2;

        System.out.println();

        // Borda Superior
        System.out.println("+" + "=".repeat(interna) + "+");

        // Conteúdo
        System.out.printf("|%s|\n",
                centralizar("MENU PRINCIPAL", interna));
        System.out.println("+" + "=".repeat(interna) + "+");
        System.out.printf("|%s|\n", centralizar("", interna));
        System.out.printf("|%s|\n",
                centralizar("[1] Iniciar", interna));
        System.out.printf("|%s|\n",
                centralizar("[2] Regras", interna));
        System.out.printf("|%s|\n",
                centralizar("[3] Sair  ", interna));
        System.out.printf("|%s|\n", centralizar("", interna));

        // Borda Inferior
        System.out.println("+" + "=".repeat(interna) + "+");

        System.out.print("Escolha uma opcao: ");
        return scanner(leia, 1, 3);
    }

    // Banco de Dados
    public static ArrayList<Perguntas> perguntas() {

        ArrayList<Perguntas> lista = new ArrayList<>();

        lista.add(new Perguntas(
                "Pepsi",
                "Coca-Cola",
                "Pepsi - 35.0%",
                "Coca-Cola - 65.0%",
                "2"
        ));

        lista.add(new Perguntas(
                "Gato",
                "Cachorro",
                "Gato - 36.8%",
                "Cachorro - 63.2%",
                "2"
        ));

        lista.add(new Perguntas(
                "Batman",
                "Superman",
                "Batman - 53.4%",
                "Superman - 46.6%",
                "1"
        ));

        lista.add(new Perguntas(
                "Verao",
                "Inverno",
                "Verao - 67.8%",
                "Inverno - 32.2%",
                "1"
        ));

        lista.add(new Perguntas(
                "YouTube",
                "Facebook",
                "YouTube - 81.9%",
                "Facebook - 18.1%",
                "1"
        ));

        lista.add(new Perguntas(
                "Windows",
                "MacOS",
                "Windows - 79.3%",
                "MacOS - 20.7%",
                "1"
        ));

        lista.add(new Perguntas(
                "Michael Jackson",
                "Freddie Mercury",
                "Michael Jackson - 59.0%",
                "Freddie Mercury - 41.0%",
                "1"
        ));

        lista.add(new Perguntas(
                "Teleportar",
                "Viajar no tempo",
                "Teleportar - 50.3%",
                "Viajar no tempo - 49.7%",
                "1"
        ));

        lista.add(new Perguntas(
                "Calor",
                "Frio",
                "Calor - 23.0%",
                "Frio - 77.0%",
                "2"
        ));

        lista.add(new Perguntas(
                "Fumar",
                "Beber",
                "Fumar - 29.5%",
                "Beber - 70.5%",
                "2"
        ));

        lista.add(new Perguntas(
                "Correr 26 kilometros",
                "Nadar 5 kilometros",
                "Correr 26 kilometros - 50.4%",
                "Nadar 5 kilometros - 49.6%",
                "1"
        ));

        lista.add(new Perguntas(
                "Se queimar",
                "Se afogar",
                "Se queimar - 31.5%",
                "Se afogar - 68.5%",
                "2"
        ));

        lista.add(new Perguntas(
                "Ser famoso enquanto vivo",
                "Ir para os livros de historia para sempre",
                "Ser famoso enquanto vivo - 52.5%",
                "Ir para os livros de historia para sempre - 47.5%",
                "1"
        ));

        lista.add(new Perguntas(
                "Perder a mao",
                "Perder o pe",
                "Perder a mao - 30.7%",
                "Perder o pe - 69.3%",
                "2"
        ));

        lista.add(new Perguntas(
                "Dor no estomago",
                "Dor de cabeca",
                "Dor no estomago - 48.0%",
                "Dor de cabeca - 52.0%",
                "2"
        ));

        lista.add(new Perguntas(
                "Ver como tudo comecou",
                "Ver como tudo acaba",
                "Ver como tudo comecou - 45.9%",
                "Ver como tudo acaba - 54.1%",
                "2"
        ));

        lista.add(new Perguntas(
                "Ser cantor",
                "Ser ator",
                "Ser cantor - 31.5%",
                "Ser ator - 68.5%",
                "2"
        ));

        lista.add(new Perguntas(
                "Fruta",
                "Vegetal",
                "Fruta - 87.9%",
                "Vegetal - 12.1%",
                "1"
        ));

        lista.add(new Perguntas(
                "Ir primeiro",
                "Ir por ultimo",
                "Ir primeiro - 49.4%",
                "Ir por ultimo - 50.6%",
                "2"
        ));

        lista.add(new Perguntas(
                "Passar um ano no mar",
                "Passar um ano no espaco",
                "Passar um ano no mar - 31.0%",
                "Passar um ano no espaco - 69.0%",
                "2"
        ));

        lista.add(new Perguntas(
                "Dirigir bebado",
                "Dirigir com sono",
                "Dirigir bebado - 39.0%",
                "Dirigir com sono - 60.1%",
                "2"
        ));

        lista.add(new Perguntas(
                "Viver uma vida esquecivel",
                "Ir para historia por algo terrivel",
                "Viver uma vida esquecivel - 68.3%",
                "Ir para historia por algo terrivel - 31.7%",
                "1"
        ));

        lista.add(new Perguntas(
                "Ser invencivel",
                "Ser invisivel",
                "Ser invencivel - 55.5%",
                "Ser invisivel - 44.5%",
                "1"
        ));

        lista.add(new Perguntas(
                "Salario bom e emprego horrivel",
                "Salario horrivel e emprego bom",
                "Salario bom e emprego horrivel - 55.6%",
                "Salario horrivel e emprego bom - 44.4%",
                "1"
        ));

        lista.add(new Perguntas(
                "Nao saber ler",
                "Nao saber escrever",
                "Nao saber ler - 25.4%",
                "Nao saber escrever - 74.6%",
                "2"
        ));

        lista.add(new Perguntas(
                "Deserto do Saara",
                "Polo Norte",
                "Deserto do Saara - 34.9%",
                "Polo Norte - 65.1%",
                "2"
        ));

        lista.add(new Perguntas(
                "Nao sentir tristeza",
                "Nao sentir raiva",
                "Nao sentir tristeza - 53.1%",
                "Nao sentir raiva - 46.9%",
                "1"
        ));

        lista.add(new Perguntas(
                "Ir para o pos-vida",
                "Renascer",
                "Ir para o pos-vida - 41.9%",
                "Renascer - 58.1%",
                "2"
        ));

        lista.add(new Perguntas(
                "Perder o paladar",
                "Perder o olfato",
                "Perder o paladar - 23.9%",
                "Perder o olfato - 76.1%",
                "2"
        ));

        lista.add(new Perguntas(
                "Inteligente entre burros",
                "Burro entre inteligentes",
                "Inteligente entre burros - 78.7%",
                "Burro entre inteligentes - 21.3%",
                "1"
        ));

        lista.add(new Perguntas(
                "Ganhar o Oscar",
                "Ganhar o Premio Nobel",
                "Ganhar o Oscar - 34.0%",
                "Ganhar o Premio Nobel - 66.0%",
                "2"
        ));

        lista.add(new Perguntas(
                "Voz baixa",
                "Voz alta",
                "Voz baixa - 71.1%",
                "Voz alta - 28.9%",
                "1"
        ));

        lista.add(new Perguntas(
                "Grecia antiga",
                "Egito antigo",
                "Grecia antiga - 75.8%",
                "Egito antigo - 24.2%",
                "1"
        ));

        lista.add(new Perguntas(
                "Pokemon",
                "Superherois",
                "Pokemon - 48.2%",
                "Superherois - 51.8%",
                "2"
        ));

        lista.add(new Perguntas(
                "Skittles",
                "M&M's",
                "Skittles - 52.2%",
                "M&M's - 47.8%",
                "1"
        ));

        lista.add(new Perguntas(
                "Elefante",
                "Rato",
                "Elefante - 64.5%",
                "Rato - 35.5%",
                "1"
        ));

        lista.add(new Perguntas(
                "Pintura",
                "Escultura",
                "Pintura - 33.0%",
                "Escultura - 67.0%",
                "2"
        ));

        lista.add(new Perguntas(
                "Comer",
                "Beber",
                "Comer - 54.6%",
                "Beber - 45.4%",
                "1"
        ));

        lista.add(new Perguntas(
                "Ser pego traindo",
                "Descobrir traicao",
                "Ser pego traindo - 31.1%",
                "Descobrir traicao - 68.9%",
                "2"
        ));

        lista.add(new Perguntas(
                "Viver no passado",
                "Viver no futuro",
                "Viver no passado - 29.1%",
                "Viver no futuro - 70.9%",
                "2"
        ));

        lista.add(new Perguntas(
                "Ter somente riqueza",
                "Ter somente fama",
                "Ter somente riqueza - 78.0%",
                "Ter somente fama - 22.0%",
                "1"
        ));

        lista.add(new Perguntas(
                "Vampiro",
                "Lobisomem",
                "Vampiro - 48.8%",
                "Lobisomem - 51.2%",
                "2"
        ));

        lista.add(new Perguntas(
                "Emo",
                "Gotico",
                "Emo - 44.6%",
                "Gotico - 55.4%",
                "2"
        ));

        lista.add(new Perguntas(
                "Ser atraente e pobre",
                "Ser feio e rico",
                "Ser atraente e pobre - 56.6%",
                "Ser feio e rico - 43.4%",
                "1"
        ));

        lista.add(new Perguntas(
                "Michael Jordan",
                "Kobe Bryant",
                "Michael Jordan - 74.2%",
                "Kobe Bryant - 25.8%",
                "1"
        ));

        lista.add(new Perguntas(
                "Saber quando mentem",
                "Mentir sem ser pego",
                "Saber quando mentem - 57.3%",
                "Mentir sem ser pego - 42.7%",
                "1"
        ));

        lista.add(new Perguntas(
                "Designer Grafico",
                "Arquiteto",
                "Designer Grafico - 56.4%",
                "Arquiteto - 43.6%",
                "1"
        ));

        lista.add(new Perguntas(
                "Taylor Swift",
                "Beyonce",
                "Taylor Swift - 49.9%",
                "Beyonce - 50.1%",
                "2"
        ));

        lista.add(new Perguntas(
                "Nao celebrar o Natal",
                "Nao celebrar aniversario",
                "Nao celebrar o Natal - 39.8%",
                "Nao celebrar aniversario - 60.2%",
                "2"
        ));

        lista.add(new Perguntas(
                "Policia",
                "Bombeiro",
                "Policia - 64.4%",
                "Bombeiro - 35.6%",
                "1"
        ));

        return lista;
    }

    // FUNÇÕES DE FORMATAÇÃO
    
    public static String centralizar(String texto, int largura) {

        // Se o texto estiver dentro dos limites, não corrigir
        if (texto.length() >= largura) {
            return texto.substring(0, largura);
        }

        // Correção do espaço
        int esquerda = (largura - texto.length()) / 2;
        int direita = largura - texto.length() - esquerda;

        // Centralizar
        return " ".repeat(esquerda)
                + texto
                + " ".repeat(direita);
    }

    public static String gerarBarra(String resultado) {

        // Pega a parte antes do %
        String porcentagemTexto = resultado
                .substring(resultado.lastIndexOf("-") + 1, resultado.indexOf("%"))
                .trim();

        // Converte para número
        double porcentagem = Double.parseDouble(porcentagemTexto);

        // Pega o primeiro dígito inteiro
        int quantidade = (int) porcentagem / 10;

        // Barra
        String barra = "[";
        for (int i = 0; i < quantidade; i++) {
            barra += "#";
        }

        for (int i = quantidade; i < 10; i++) {
            barra += "-";
        }

        barra += "]";

        return resultado + " - " + barra;
    }

    // FUNCIONALIDADES
    
    // CARREGAR REGRAS
    public static String regras() {

        int largura = 85;
        int interna = largura - 2;

        System.out.println();

        // Borda Superior
        System.out.println("+" + "=".repeat(interna) + "+");

        // Conteudo
        System.out.printf("|%s|\n",
                centralizar("COMO JOGAR", interna));
        System.out.println("+" + "=".repeat(interna) + "+");
        System.out.printf("|%s|\n",
                centralizar("", interna));
        System.out.printf("|%s|\n",
                centralizar("Entre duas opcoes", interna));
        System.out.printf("|%s|\n",
                centralizar("descubra qual recebeu", interna));
        System.out.printf("|%s|\n",
                centralizar("mais votos!", interna));
        System.out.printf("|%s|\n",
                centralizar("", interna));
        System.out.printf("|%s|\n",
                centralizar("", interna));

        // Borda Inferior
        System.out.println("+" + "=".repeat(interna) + "+");

        System.out.print("Pressione ENTER para voltar...");
        leia.nextLine();

        return "";
    }

    // CARREGAR WINNER
    public static String vencedor(int acertos) {

        int largura = 85;
        int interna = largura - 2;

        System.out.println();

        // Borda Superior
        System.out.println("+" + "=".repeat(interna) + "+");

        // Conteudo
        System.out.printf("|%s|\n",
                ConsoleColors.GREEN_BACKGROUND + centralizar("WINNER", interna) + ConsoleColors.RESET);
        System.out.println("+" + "=".repeat(interna) + "+");
        System.out.printf("|%s|\n",
                centralizar("", interna));
        System.out.printf("|%s|\n",
                centralizar("Parabens!", interna));
        System.out.printf("|%s|\n",
                centralizar("Voce acertou todas as perguntas!", interna));
        System.out.printf("|%s|\n",
                centralizar("Score: " + acertos, interna));
        System.out.printf("|%s|\n",
                centralizar("", interna));

        // Borda Inferior
        System.out.println("+" + "=".repeat(interna) + "+");

        System.out.print("Pressione ENTER para voltar...");
        leia.nextLine();

        return "";
    }

    // CARREGAR GAME OVER
    public static int menuGameOver(int acertos) {

        int largura = 85;
        int interna = largura - 2;

        System.out.println();

        // Borda Superior
        System.out.println("+" + "=".repeat(interna) + "+");

        // Conteudo
        System.out.printf("|%s|\n",
                ConsoleColors.RED_BACKGROUND + centralizar("GAME OVER", interna) + ConsoleColors.RESET);
        System.out.println("+" + "=".repeat(interna) + "+");
        System.out.printf("|%s|\n",
                centralizar("", interna));
        System.out.printf("|%s|\n",
                centralizar("[1] Tentar Novamente", interna));
        System.out.printf("|%s|\n",
                centralizar("[2] Menu Principal", interna));
        System.out.printf("|%s|\n",
                centralizar("", interna));
        System.out.println("+" + "=".repeat(interna) + "+");
        System.out.printf("|%s|\n",
                centralizar("Score: " + acertos, interna));

        // Borda Inferior
        System.out.println("+" + "=".repeat(interna) + "+");

        System.out.print("\nEscolha uma opcao: ");

        int escolha = scanner(leia, 1, 2);

        return escolha;
    }

    // CARREGAR QUANTIDADE DE PERGUNTAS
    public static int dificuldade() {
        int largura = 85;
        int interna = largura - 2;

        System.out.println();

        // Borda Superior
        System.out.println("+" + "=".repeat(interna) + "+");

        // Conteudo
        System.out.printf("|%s|\n",
                centralizar("CONFIGURACAO DE PARTIDA", interna));
        System.out.println("+" + "=".repeat(interna) + "+");
        System.out.printf("|%s|\n",
                centralizar("", interna));
        System.out.printf("|%s|\n",
                centralizar("Minimo: 10 | Maximo: 50", interna));
        System.out.printf("|%s|\n",
                centralizar("", interna));

        // Borda Inferior
        System.out.println("+" + "=".repeat(interna) + "+");

        System.out.print("\nQuantas perguntas?: ");

        int qntd = scanner(leia, 10, 50);

        return qntd;
    }

    // ENCERRAR O PROGRAMA
    public static void encerrando() {
        System.out.println("Encerrando...");
    }

    // Verificação de erros
    public static int scanner(Scanner leia, int min, int max) {
        while (true) {

            try {
                String entrada = leia.nextLine().trim();
                int valor = Integer.parseInt(entrada);

                if (valor < min || valor > max) {
                    System.out.println(
                            ConsoleColors.RED
                            + "[ERRO] Opcao invalida!"
                            + ConsoleColors.RESET
                    );

                    System.out.print("Tente novamente: ");
                    continue;
                }
                return valor;

            } catch (Exception e) {
                System.out.println(
                        ConsoleColors.RED
                        + "[ERRO] Digite apenas numeros!"
                        + ConsoleColors.RESET
                );
                System.out.print("Tente novamente: ");
            }
        }
    }

    // Cores
    public static class ConsoleColors {

        public static final String RESET = "\033[0m";

        public static final String GREEN = "\033[0;32m";
        public static final String GREEN_BACKGROUND = "\033[42m";
        public static final String RED = "\033[0;31m";
        public static final String RED_BACKGROUND = "\033[41m";
        ; 
        
        public static final String CYAN = "\033[0;36m";
        public static final String RED_BOLD = "\033[1;31m";
    }
}
