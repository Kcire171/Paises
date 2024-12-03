package paises;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Pais brasil = new Pais("BRA", "Brasil", 213000000, 8515767.049);
        Pais argentina = new Pais("ARG", "Argentina", 45195777, 2780400.0);
        Pais colombia = new Pais("COL", "Colômbia", 50882891, 1141748.0);
        Pais chile = new Pais("CHL", "Chile", 19116201, 756102.0);
        Pais peru = new Pais("PER", "Peru", 33050325, 1285216.0);
        Pais venezuela = new Pais("VEN", "Venezuela", 28932010, 916445.0);
        Pais bolivia = new Pais("BOL", "Bolívia", 11673021, 1098581.0);
        Pais paraguai = new Pais("PRY", "Paraguai", 7132530, 406752.0);
        Pais uruguai = new Pais("URY", "Uruguai", 3551526, 176215.0);
        Pais equador = new Pais("ECU", "Equador", 17643054, 283561.0);
        Pais guiana = new Pais("GUY", "Guiana", 786552, 214969.0);
        Pais suriname = new Pais("SUR", "Suriname", 612985, 163820.0);

        Continente americaDoSul = new Continente("América do Sul");
        americaDoSul.adicionarPais(brasil);
        americaDoSul.adicionarPais(argentina);
        americaDoSul.adicionarPais(colombia);
        americaDoSul.adicionarPais(chile);
        americaDoSul.adicionarPais(peru);
        americaDoSul.adicionarPais(venezuela);
        americaDoSul.adicionarPais(bolivia);
        americaDoSul.adicionarPais(paraguai);
        americaDoSul.adicionarPais(uruguai);
        americaDoSul.adicionarPais(equador);
        americaDoSul.adicionarPais(guiana);
        americaDoSul.adicionarPais(suriname);

        System.out.println("Informações de todos os países da América do Sul:");
        for (Pais pais : americaDoSul.getPaises()) {
            System.out.printf("Código ISO: %s, Nome: %s, População: %,d, Dimensão: %.3f km²%n",
                    pais.getCodigoISO(), pais.getNome(), pais.getPopulacao(), pais.getDimensao());
        }

        System.out.println("\nDados do continente:");
        System.out.printf("Dimensão total: %.3f km²%n", americaDoSul.getDimensaoTotal());
        System.out.printf("População total: %,d%n", americaDoSul.getPopulacaoTotal());
        System.out.printf("Densidade populacional: %.3f hab/km²%n", americaDoSul.getDensidadePopulacional());
        System.out.println("País com maior população: " + americaDoSul.getPaisMaiorPopulacao().getNome());
        System.out.println("País com menor população: " + americaDoSul.getPaisMenorPopulacao().getNome());
        System.out.println("País de maior dimensão: " + americaDoSul.getPaisMaiorDimensao().getNome());
        System.out.println("País de menor dimensão: " + americaDoSul.getPaisMenorDimensao().getNome());
        System.out.printf("Razão territorial: %.3f%n", americaDoSul.getRazaoTerritorial());
    }
}

class Pais {
    private String codigoISO;
    private String nome;
    private long populacao;
    private double dimensao;

    public Pais(String codigoISO, String nome, long populacao, double dimensao) {
        this.codigoISO = codigoISO;
        this.nome = nome;
        this.populacao = populacao;
        this.dimensao = dimensao;
    }

    public String getCodigoISO() {
        return codigoISO;
    }

    public String getNome() {
        return nome;
    }

    public long getPopulacao() {
        return populacao;
    }

    public double getDimensao() {
        return dimensao;
    }
}

class Continente {
    private String nome;
    private List<Pais> paises;

    public Continente(String nome) {
        this.setNome(nome);
        this.paises = new ArrayList<>();
    }

    public void adicionarPais(Pais pais) {
        if (!paises.contains(pais)) {
            paises.add(pais);
        }
    }

    public List<Pais> getPaises() {
        return paises;
    }

    public double getDimensaoTotal() {
        return paises.stream().mapToDouble(Pais::getDimensao).sum();
    }

    public long getPopulacaoTotal() {
        return paises.stream().mapToLong(Pais::getPopulacao).sum();
    }

    public double getDensidadePopulacional() {
        return getPopulacaoTotal() / getDimensaoTotal();
    }

    public Pais getPaisMaiorPopulacao() {
        return paises.stream().max((p1, p2) -> Long.compare(p1.getPopulacao(), p2.getPopulacao())).orElse(null);
    }

    public Pais getPaisMenorPopulacao() {
        return paises.stream().min((p1, p2) -> Long.compare(p1.getPopulacao(), p2.getPopulacao())).orElse(null);
    }

    public Pais getPaisMaiorDimensao() {
        return paises.stream().max((p1, p2) -> Double.compare(p1.getDimensao(), p2.getDimensao())).orElse(null);
    }

    public Pais getPaisMenorDimensao() {
        return paises.stream().min((p1, p2) -> Double.compare(p1.getDimensao(), p2.getDimensao())).orElse(null);
    }

    public double getRazaoTerritorial() {
        Pais maior = getPaisMaiorDimensao();
        Pais menor = getPaisMenorDimensao();
        return maior != null && menor != null ? maior.getDimensao() / menor.getDimensao() : 0;
    }

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
