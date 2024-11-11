import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VerificadorSentenca {
	private List<String> estadosFinais;
	private List<Transicao> listaTransicoes;
	private String estadoAtual;

	public VerificadorSentenca() {
		this.estadosFinais = new ArrayList<>();
		this.listaTransicoes = new ArrayList<>();
		this.estadoAtual = "";
	}

	public void carregaCsv(String caminhoArquivo) throws FileNotFoundException {
		File arquivoCsv = new File(caminhoArquivo);
		Scanner scanner = new Scanner(arquivoCsv);

		if (scanner.hasNextLine()) {
			String linha = scanner.nextLine();
			this.estadoAtual = linha;
		}

		if (scanner.hasNextLine()) {
			String linha = scanner.nextLine();
			String[] partes = linha.split(";");
			this.estadosFinais.addAll(Arrays.asList(partes));
		}

		while (scanner.hasNextLine()) {
			String linha = scanner.nextLine();
			String[] dadosLinha = linha.split(";");

			if (dadosLinha.length == 3) {
				this.listaTransicoes.add(new Transicao(
								dadosLinha[0], //Origem
								dadosLinha[1].charAt(0), //Letra
								dadosLinha[2] //Destino
				));
			}
		}
		scanner.close();
	}

	public boolean validarAutomato(String sentenca) {
		boolean encontrado;
		System.out.println("finais: " + String.join(", ", this.estadosFinais));
		for (int i = 0; i < sentenca.length(); i++) {
			encontrado = false;
			for (Transicao passoSigma : this.listaTransicoes) {
				if (passoSigma.getOrigem().equals(this.estadoAtual) && passoSigma.getLetra() == sentenca.charAt(i)) {
					System.out.println("d(" + passoSigma.getOrigem() + ";" + passoSigma.getLetra() + ") = " + passoSigma.getDestino());
					this.estadoAtual = passoSigma.getDestino();
					encontrado = true;
					break;
				}
			}
			if (!encontrado) {
				return false;
			}
		}

		return this.estadosFinais.contains(this.estadoAtual);
	}

	public String getEstadoAtual() {
		return this.estadoAtual;
	}

	public void setEstadoAtual(String estadoAtual) {
		this.estadoAtual = estadoAtual;
	}

	public List<Transicao> getListaTransicoes() {
		return this.listaTransicoes;
	}

	public void setListaTransicoes(List<Transicao> listaTransicoes) {
		this.listaTransicoes = listaTransicoes;
	}

	public List<String> getEstadosFinais() {
		return this.estadosFinais;
	}

	public void setEstadosFinais(List<String> estadosFinais) {
		this.estadosFinais = estadosFinais;
	}
}