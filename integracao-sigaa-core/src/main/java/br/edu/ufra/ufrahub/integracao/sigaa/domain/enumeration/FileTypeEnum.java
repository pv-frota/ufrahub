package br.edu.ufra.ufrahub.integracao.sigaa.domain.enumeration;

public enum FileTypeEnum {
    VINCULO("Declaração de Vínculo"), HISTORICO("Histórico Escolar");

    private final String nome;

    FileTypeEnum(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}